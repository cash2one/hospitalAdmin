package com.jumper.hospital.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.MonitorHospitalDao;
import com.jumper.hospital.dao.MonitorOrderSpecDao;
import com.jumper.hospital.dao.MonitorSettingOptionDao;
import com.jumper.hospital.dao.MonitorUserInfoDao;
import com.jumper.hospital.dao.PayOrderInfoDao;
import com.jumper.hospital.dao.UserInfoDao;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorOrder;
import com.jumper.hospital.entity.MonitorSettingOption;
import com.jumper.hospital.entity.MonitorUserInfo;
import com.jumper.hospital.entity.PayOrderInfo;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.enums.RemoteOrderState;
import com.jumper.hospital.service.MonitorOrderSpecService;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.Const;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HBSmsCodeUtils;
import com.jumper.hospital.utils.IDGenerator;
import com.jumper.hospital.utils.QRCodeUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VoMonitorOrder;
import com.jumper.hospital.vo.VoPreCreateResult;
import com.jumper.hospital.vo.pay.VoLinePayInfo;

import cn.com.jumper.anglesound.dubbo.BaseDataResult;
import cn.com.jumper.anglesound.dubbo.DubboPaymentService;



@Service
public class MonitorOrderSpecServiceImpl extends BaseServiceImpl<MonitorOrder, Integer> implements MonitorOrderSpecService {

	private static final Logger logger = Logger.getLogger(MonitorOrderSpecServiceImpl.class);
	
	@Autowired
	private MonitorOrderSpecDao monitorOrderSpecDaoImpl;
	
	@Autowired
	private MonitorSettingOptionDao monitorSettingOptionDaoImpl;
	@Autowired
	private MonitorHospitalDao monitorHospitalDaoImpl;
	@Autowired
	private MonitorUserInfoDao monitorUserInfoDaoImpl;
	@Autowired
	private UserInfoDao userInfoDaoImpl;
	@Autowired
	private PayOrderInfoDao payOrderInfoDaoImpl;
	@Autowired
	private DubboPaymentService dubboPaymentService;

	
	@Override
	public BaseDao<MonitorOrder, Integer> getBaseDAO() {
		return monitorOrderSpecDaoImpl;
	}
	
	/**
	 * 统计今日订单使用次数
	 * @param hospitalId
	 * @return
	 */
	public int countOrderUsedToday(Integer hospitalId){
		return monitorOrderSpecDaoImpl.countOrderUsedToday(hospitalId);
	}
	
	/**
	 * 查询失效订单线上总数
	 * @param hospitalId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int countOrderInvalid(int hospitalId, String startTime, String endTime){
		return monitorOrderSpecDaoImpl.countOrderInvalid(hospitalId, startTime, endTime);
	}

	@Override
	public Page<MonitorOrder> findOrderList(Integer type, String searchKey, String startTime, String endTime, Page<MonitorOrder> page, 
			Integer hospitalId, Integer remoteType, Integer expireType, String invalidStartTime, String invalidEndTime,Integer payChannels) {
		return monitorOrderSpecDaoImpl.findOrderList(type, searchKey, startTime, endTime, page, hospitalId, remoteType, expireType, invalidStartTime, invalidEndTime, payChannels);
	}

	@Override
	public VoLinePayInfo addMonitorOrder(MonitorUserInfo userInfo, Integer optionId, Integer hospitalId, String edcDate, Integer channelWay,String basePath,Integer payId) {
		try {
			MonitorSettingOption option = monitorSettingOptionDaoImpl.get(optionId);
			MonitorHospital hospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(hospitalId);
			if((optionId != -1 && option == null) || hospital == null){
				return null;
			}
			Integer userId=0;
			MonitorOrder order = new MonitorOrder();
			order.setAddTime(TimeUtils.getCurrentTime());
			order.setIsEffective(false);
			order.setMonitorHospitalId(hospital);
			order.setLeftCount(option.getNumber());
			order.setMoney(Double.valueOf(option.getPrice()));
			order.setMonitorCount(option.getNumber());
			order.setOptionId(optionId);
			order.setRemoteType(option.getMonitorSetId().getType());
			order.setState(RemoteOrderState.待监测);
			order.setDealStates(channelWay != 0 ? 0 : 1);
			order.setOrderId(new IDGenerator(1).nextId());
			order.setPayChannels(channelWay);
			//院内用户表 MonitorUserInfo（医院后台只修改这张表）   天使医生用户需要修改userinfo 及UserExtraInfo 
			/** 此处处理是否为新添加用户 **/
			if(userInfo.getId() == null){//院内主键ID 在院内是否存在
				//查询院内表 是否存在/是否绑定 院内用户
				boolean isBind = monitorUserInfoDaoImpl.doCheckMobileIsExist(userInfo.getMobile().trim());
				if(isBind){//老用户
					//查询院内用户信息
					MonitorUserInfo firstInfo = monitorUserInfoDaoImpl.findMUSerByMobile(userInfo.getMobile().trim());
					//根据表单的值   修改 院内用户的的预产期 真实姓名 年龄
					BeanUtils.copy(userInfo,firstInfo);//已经copy
					if(edcDate!=null){
						firstInfo.setEdc(TimeUtils.getTimestampDate(edcDate, Consts.FORMAT_TIME_THREE));
					}
					firstInfo.setAge(userInfo.getAge());
					//if(firstInfo.getUserId() == null){//未关联 以下逻辑,不用这个逻辑，每次都要关联
						//查询APP用户
						UserInfo appUser = userInfoDaoImpl.findByMobile(firstInfo.getMobile().trim());
						//关联 并修改APP用户
						if(appUser != null){
							//修改userinfo 的expectedDateOfConfinement  pregnantWeek（根据预产期计算）  并且推算出末次月经 去修改userExitInfo 的age realname lastPeriod
							appUser.setExpectedDateOfConfinement(TimeUtils.getTimestampDate(edcDate, Consts.FORMAT_TIME_THREE));
							int[] pregnantWeek_415 = TimeUtils.getPregnantWeek_415(TimeUtils.getTimestampDate(edcDate, Consts.FORMAT_TIME_THREE), new Date());
							appUser.setPregnantWeek(pregnantWeek_415[0]);
							//设置用户额外信息表
							UserExtraInfo userExitInfo=appUser.getUserExitInfo();
							if(userExitInfo!=null){
								userExitInfo.setRealName(userInfo.getRealName());
								userExitInfo.setAge(userInfo.getAge());
								Date lastPeriod= TimeUtils.getCurrentStartTime(-280,userInfo.getEdc());
								userExitInfo.setLastPeriod(lastPeriod);
								appUser.setUserExitInfo(userExitInfo);
							}
						}else{
							appUser = userInfoDaoImpl.createInsideAppUser(userInfo,edcDate);//去创建APP用户
						}
						firstInfo.setUserId(appUser);
					//}
					//订单关联APP用户
					order.setMonitorUserId(firstInfo);
				}else{//新用户
					//先去userinfo app是否存在 
					UserInfo appUser = userInfoDaoImpl.findByMobile(userInfo.getMobile().trim());
					if(edcDate!=null){
						userInfo.setEdc(TimeUtils.getTimestampDate(edcDate, Consts.FORMAT_TIME_THREE));
					}
					userInfo.setAge(userInfo.getAge());
					
					//更新数据
					//if(appUser != null && option.getMonitorSetId().getType() == RemoteType.院内监护){   //不做院内显示
					if(appUser != null){
						//修改userinfo 的expectedDateOfConfinement  pregnantWeek（根据预产期计算）  并且推算出末次月经 去修改userExitInfo 的age realname lastPeriod
						appUser.setExpectedDateOfConfinement(userInfo.getEdc());
						int[] pregnantWeek_415 = TimeUtils.getPregnantWeek_415(userInfo.getEdc(), new Date());
						appUser.setPregnantWeek(pregnantWeek_415[0]);
						//设置用户额外信息表
						UserExtraInfo userExitInfo=appUser.getUserExitInfo();
						if(userExitInfo!=null){
							userExitInfo.setRealName(userInfo.getRealName());
							userExitInfo.setAge(userInfo.getAge());
							Date lastPeriod= TimeUtils.getCurrentStartTime(-280,userInfo.getEdc());
							userExitInfo.setLastPeriod(lastPeriod);
							appUser.setUserExitInfo(userExitInfo);
						}
					}
					//if(appUser == null && option.getMonitorSetId().getType() == RemoteType.院内监护){//不存在app用户
					if(appUser == null){//不存在app用户
						appUser = userInfoDaoImpl.createInsideAppUser(userInfo,edcDate);//去创建APP用户
						if(appUser != null){//并发送短信
							//hospitalId 判断是否义乌妇幼
							if(Integer.parseInt(Const.YWHOSPITAL_ID)==hospitalId){
								HBSmsCodeUtils.sendSmsMsgCommon(String.format(
										Consts.INSIDE_REGISTER_YW, new Object[]{
										userInfo.getMobile().trim(), "123456"}), 
										userInfo.getMobile().trim(), 
										hospital.getHospitalId().getName(), 
										hospitalId);
							}else{
								HBSmsCodeUtils.sendSmsMsgCommon(String.format(
										Consts.INSIDE_REGISTER, new Object[]{
										userInfo.getMobile().trim(), "123456"}), 
										userInfo.getMobile().trim(),
										hospital.getHospitalId().getName(), 
										49);
							}
						}
					}
					userId=appUser.getId();
					userInfo.setUserId(appUser);
					order.setMonitorUserId(userInfo);
				}
			}else{//院内的老用户
				MonitorUserInfo user = monitorUserInfoDaoImpl.get(userInfo.getId());
				if(StringUtils.isNotEmpty(edcDate)){
					user.setEdc(TimeUtils.getTimestampDate(edcDate, Consts.FORMAT_TIME_THREE));
				}
				if(StringUtils.isNotEmpty(userInfo.getAddress())){
					user.setAddress(userInfo.getAddress());
				}
				/** 此处如果ape用户注册手机号码和在医院购买订单的手机号码相同，则将app用户绑定 **/
				//if(appUser == null && option.getMonitorSetId().getType() == RemoteType.院内监护){//不存在app用户
				if(user != null){//未和APP绑定
					UserInfo appUser = userInfoDaoImpl.findByMobile(userInfo.getMobile());
					if(appUser != null){//关联并且需要将 预产期 真实姓名 年龄 修改到APP
						//修改userinfo 的expectedDateOfConfinement  pregnantWeek（根据预产期计算）  并且推算出末次月经 去修改userExitInfo 的age realname lastPeriod
						appUser.setExpectedDateOfConfinement(user.getEdc());
						int[] pregnantWeek_415 = TimeUtils.getPregnantWeek_415(user.getEdc(), new Date());
						appUser.setPregnantWeek(pregnantWeek_415[0]);
						//设置用户额外信息表
						UserExtraInfo userExitInfo=appUser.getUserExitInfo();
						if(userExitInfo!=null){
							if(user.getRealName()==null&&userExitInfo.getRealName()!=null){
								user.setRealName(userExitInfo.getRealName());
							}else{
								userExitInfo.setRealName(user.getRealName()); 
							}
							if(user.getAge()==null&&userExitInfo.getAge()!=null){
								user.setAge(userExitInfo.getAge());
							}else{
								userExitInfo.setAge(user.getAge());
							}
							Date lastPeriod= TimeUtils.getCurrentStartTime(-280,userInfo.getEdc());
							userExitInfo.setLastPeriod(lastPeriod);
							appUser.setUserExitInfo(userExitInfo);
						}
						user.setUserId(appUser);
					}else{//如果userInfo为空则创建，之前有老数据userInfo为空，并且关联
						appUser = userInfoDaoImpl.createInsideAppUser(userInfo,edcDate);
						user.setUserId(appUser);
					}
				}
				if(user.getUserId()!=null){
					userId= user.getUserId().getId();
				}
				order.setMonitorUserId(user);
			}

			//订单表添加数据
			MonitorOrder monitorOrder = monitorOrderSpecDaoImpl.save(order);
			if (monitorOrder == null) {
				return null;
			}
			
			    //线上支付，订单支付表添加数据,线下支付也要保存在这个表
				//添加订单支付
				PayOrderInfo payOrderInfo = new PayOrderInfo();
				payOrderInfo.setUserName(order.getMonitorUserId().getRealName());
				payOrderInfo.setDoctorId(order.getMonitorHospitalId().getHospitalId().getId());
				payOrderInfo.setDoctorName(order.getMonitorHospitalId().getHospitalId().getName());
				payOrderInfo.setPayChannels(channelWay);
				payOrderInfo.setDeaMoney(order.getMoney());
				payOrderInfo.setOrderNo(order.getOrderId());
				payOrderInfo.setOrderStartTime(TimeUtils.getCurrentTime());
				payOrderInfo.setIncomeMoney(order.getMoney());
				payOrderInfo.setHandleState(0);//0：默认初始值
				payOrderInfo.setServiceType(3);//服务类型：0.胎心监护
				payOrderInfo.setOrderType(1); //订单类型：1:医生或者医院创建
				payOrderInfo.setDoctorType(2);//1: 医生   2: 医院
				payOrderInfo.setDealState(-1);//待支付
				payOrderInfo.setIsAutonomy(hospital.getHospitalId().getIsAutonomy()); //是否是独立医院
				payOrderInfo.setPayConfigId(payId);   //获取医院的配置id
				payOrderInfo.setUserId(userId);
				PayOrderInfo payResult = payOrderInfoDaoImpl.save(payOrderInfo);
				if (payResult == null) {
					return new VoLinePayInfo(Consts.FALSE, "平台下单出错");
				}
			//线上订单业务逻辑
			if(channelWay != 0){
				BaseDataResult precreateResult = dubboPaymentService.precreate(payId,channelWay, 1, "实时胎心监护服务", order.getMoney(), payResult.getOrderNo(), "NATIVE");
				if(precreateResult == null || precreateResult.getCode() == Consts.FALSE){
					return new VoLinePayInfo(Consts.FALSE, "第三方下单出错");
				}
				VoPreCreateResult payInfo = (VoPreCreateResult) precreateResult.getData();
				if(payInfo.getReturn_code().equalsIgnoreCase(Consts.SUCCESS)){
					/** 生成支付二维码 **/
					String qr_url = QRCodeUtils.CreateQrCodeLocal(payInfo.getQr_code(),basePath);
					VoLinePayInfo vo = new VoLinePayInfo(Consts.TRUE, "后台统一下单处理成功！");
					vo.setOrder_no(payOrderInfo.getOrderNo());
					vo.setPay_channels(channelWay);
					vo.setQr_code(qr_url);
					vo.setTotal_fee(payOrderInfo.getDeaMoney());
					return vo;
				}
			}
			return new VoLinePayInfo(Consts.TRUE, "添加订单成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(Consts.ERROR_TAG+"添加订单信息异常："+userInfo.getMobile().trim()+"异常原因："+e.getMessage());
			return new VoLinePayInfo(Consts.FALSE, "添加订单异常！");
		}
	}
	
	public static void main(String[] args) throws ParseException {
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2017-7-7");
		
		int[] pregnantWeek_415 = TimeUtils.getPregnantWeek_415(date2, new Date());
		System.out.println(pregnantWeek_415[0]);
		System.out.println(pregnantWeek_415[1]);
		System.out.println(pregnantWeek_415[2]);
		System.out.println(pregnantWeek_415);
		
	}

	@Override
	public VoMonitorOrder convertOrderDetail(MonitorOrder order) {
		try {
			if(order == null){
				return null;
			}
			VoMonitorOrder voOrder = new VoMonitorOrder();
			voOrder.setId(order.getId());
			voOrder.setAge(order.getMonitorUserId().getAge());
			voOrder.setMobile(order.getMonitorUserId().getMobile());
			voOrder.setMonitorUserId(order.getMonitorUserId().getId());
			voOrder.setOptionId(order.getOptionId());
			voOrder.setRealName(order.getMonitorUserId().getRealName());
			
			Date pregantDate = new Date();
			if(order.getMonitorUserId().getEdc() != null){
				pregantDate = order.getMonitorUserId().getEdc();
			}else{
				UserInfo user = order.getMonitorUserId().getUserId();
				if(user != null && user.getExpectedDateOfConfinement() != null){
					pregantDate = user.getExpectedDateOfConfinement();
				}
			}
			voOrder.setEdc(pregantDate == null ? "" : TimeUtils.convertTime(pregantDate, Consts.FORMAT_TIME_THREE));
			
			return voOrder;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<MonitorOrder> findMonitorOrderList(Page<MonitorOrder> page, Integer id, String searchKey,String startTime,String endTime) {
		return monitorOrderSpecDaoImpl.findMonitorOrderList(page,id,searchKey,startTime,endTime);
	}

	@Override
	public Double getProfits(String sql) {
		return monitorOrderSpecDaoImpl.getProfits(sql);
	}

	@Override
	public Integer getRemoteCountByHospital(Integer id) {
		return monitorOrderSpecDaoImpl.getRemoteCountByHosptial(id);
	}

	@Override
	public Double getSumTrueCost(Integer id) {
		String sql = "select sum(true_cost) from hospital_cons_service_order where status=1 and hospital_id="+id+" and is_enabled=0";
		return monitorOrderSpecDaoImpl.getSumTrueCost(sql);
	}
	@Override
	public Double getSumMoney(Integer id) {
		String sql = "select sum(money) from monitor_order where monitor_hospital_id="+id;
		return monitorOrderSpecDaoImpl.getSumMoney(sql);
	}

	@Override
	public Double getSumTrueCostByConds(Integer id,String searchKey, String startTime,
			String endTime) {
		String sql = "select sum(true_cost) from hospital_cons_service_order,hospital_info,user_info " +
				"where hospital_info.id=hospital_cons_service_order.hospital_id and " +
				"user_info.id=hospital_cons_service_order.user_id and hospital_cons_service_order.status=1 " +
				"and hospital_info.id="+id+" and is_enabled=0 ";
		if(StringUtils.isNotEmpty(searchKey)){
			if(StringUtils.isNumeric(searchKey)){
				sql += " and user_info.mobile like '%"+searchKey+"%' ";
			}else{
				sql += " and user_info.nick_name like '%"+searchKey+"%' ";
			}
		}
		
		if(StringUtils.isNotEmpty(startTime)){
			sql += " and hospital_cons_service_order.add_time >= '"+TimeUtils.getTimestampDate(startTime, Consts.FORMAT_TIME_THREE)+"'";
		}
		if(StringUtils.isNotEmpty(endTime)){
			sql += " and hospital_cons_service_order.add_time <= '"+TimeUtils.getTimestampDate(endTime+" 23:59:59", Consts.FORMAT_TIME_ONE)+"'";
		}
		return monitorOrderSpecDaoImpl.getSumTrueCostByConds(sql);
	}

	@Override
	public Double getSumMoneyByConds(Integer id, String searchKey,
			String startTime, String endTime) {
		String sql = "select sum(money) from monitor_order,monitor_hospital,monitor_user_info " +
				"where monitor_hospital.id=monitor_order.monitor_hospital_id and " +
				"monitor_order.monitor_user_id = monitor_user_info.id and monitor_hospital_id="+id;
		
		if(StringUtils.isNotEmpty(searchKey)){
			if(StringUtils.isNumeric(searchKey)){
				sql += " and monitor_user_info.mobile like '%"+searchKey+"%' ";
			}else{
				sql += " and monitor_user_info.real_name like '%"+searchKey+"%' ";
			}
		}
		
		if(StringUtils.isNotEmpty(startTime)){
			sql += " and monitor_order.add_time >= '"+TimeUtils.getTimestampDate(startTime, Consts.FORMAT_TIME_THREE)+"'";
		}
		if(StringUtils.isNotEmpty(endTime)){
			sql += " and monitor_order.add_time <= '"+TimeUtils.getTimestampDate(endTime+" 23:59:59", Consts.FORMAT_TIME_ONE)+"'";
		}
		return monitorOrderSpecDaoImpl.getSumMoneyByConds(sql);
	}

	@Override
	public String doChgInsideOrder(Integer id) {
		try {
			if(id == null || id == 0){
				return Consts.PARAMS_ERROR;
			}
			MonitorOrder order = monitorOrderSpecDaoImpl.get(id);
			order.setState(RemoteOrderState.待监测);
			MonitorOrder result = monitorOrderSpecDaoImpl.save(order);
			if(result != null){
				return Consts.SUCCESS;
			}
			return Consts.FAILED;
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	/**
	 * 加一次监护服务次数
	 * @param id
	 * @return
	 */
	public String plusMonitorCount(Integer id) {
		try {
			if(id == null || id == 0){
				return Consts.PARAMS_ERROR;
			}
			MonitorOrder order = monitorOrderSpecDaoImpl.get(id);
			if (order == null) {
				return Consts.ERROR;
			}
			order.setMonitorCount(order.getMonitorCount() + 1);
			order.setLeftCount(order.getLeftCount() + 1);
			return Consts.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}
}
