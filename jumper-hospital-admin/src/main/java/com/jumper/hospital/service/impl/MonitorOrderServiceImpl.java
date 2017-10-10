package com.jumper.hospital.service.impl;
/**
 * 远程监控订单业务实现类
 * @author rent
 * @date 2015-09-18
 */
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.MonitorHospitalDao;
import com.jumper.hospital.dao.MonitorOrderDao;
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
import com.jumper.hospital.service.MonitorOrderService;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.Const;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HBSmsCodeUtils;
import com.jumper.hospital.utils.IDGenerator;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VoMonitorOrder;

@Service
public class MonitorOrderServiceImpl extends BaseServiceImpl<MonitorOrder, Integer> implements MonitorOrderService {

	private static final Logger logger = Logger.getLogger(MonitorOrderServiceImpl.class);
	
	@Autowired
	private MonitorOrderDao monitorOrderDaoImpl;
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
	
	@Override
	public BaseDao<MonitorOrder, Integer> getBaseDAO() {
		return monitorOrderDaoImpl;
	}

	@Override
	public Page<MonitorOrder> findOrderList(Integer type, String searchKey, String startTime, String endTime, Page<MonitorOrder> page, 
			Integer hospitalId, Integer remoteType, Integer expireType, String invalidStartTime, String invalidEndTime) {
		return monitorOrderDaoImpl.findOrderList(type, searchKey, startTime, endTime, page, hospitalId, remoteType, expireType, invalidStartTime, invalidEndTime);
	}

	@SuppressWarnings("unused")
	@Override
	public String addMonitorOrder(MonitorUserInfo userInfo, Integer optionId, Integer hospitalId, String edcDate) {
		try {
			MonitorSettingOption option = monitorSettingOptionDaoImpl.get(optionId);
			MonitorHospital hospital = monitorHospitalDaoImpl.findMonitorHospitalByHospitalId(hospitalId);
			if((optionId != -1 && option == null) || hospital == null){
				return Consts.PARAMS_ERROR;
			}

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
			order.setDealStates(1);
			order.setPayChannels(0);
			IDGenerator generator = new IDGenerator(1);
			order.setOrderId(generator.nextId());
			/** 此处处理是否为新添加用户 **/
			if(userInfo.getId() == null){
				boolean isBind = monitorUserInfoDaoImpl.doCheckMobileIsExist(userInfo.getMobile().trim());
				if(isBind){
					MonitorUserInfo firstInfo = monitorUserInfoDaoImpl.findMUSerByMobile(userInfo.getMobile().trim());
					BeanUtils.copy(userInfo, firstInfo);
					if(edcDate!=null){
						firstInfo.setEdc(TimeUtils.getTimestampDate(edcDate, Consts.FORMAT_TIME_THREE));
					}
					firstInfo.setAge(userInfo.getAge());
					//if(firstInfo.getUserId() == null){//未关联 以下逻辑，每次都要重新关联
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
						userInfo.setUserId(appUser);
					//}
					order.setMonitorUserId(firstInfo);
				}else{//新用户
					UserInfo appUser = userInfoDaoImpl.findByMobile(userInfo.getMobile().trim());
					if(edcDate!=null){
						userInfo.setEdc(TimeUtils.getTimestampDate(edcDate, Consts.FORMAT_TIME_THREE));
					}
					userInfo.setAge(userInfo.getAge());
					
					//if(appUser != null && option.getMonitorSetId().getType() == RemoteType.院内监护){ //不做院内限制，都创建账号
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
					//if(appUser != null && option.getMonitorSetId().getType() == RemoteType.院内监护){ //不做院内限制，都创建账号
					if(appUser == null){
						appUser = userInfoDaoImpl.createInsideAppUser(userInfo,edcDate);
						if(appUser != null){
							if(Integer.parseInt(Const.YWHOSPITAL_ID)==hospitalId){
								HBSmsCodeUtils.sendSmsMsgCommon(String.format(Consts.INSIDE_REGISTER_YW, new Object[]{
										appUser.getMobile().trim(), "123456"}), 
										appUser.getMobile().trim(), 
										hospital.getHospitalId().getName(), hospitalId);
							}else{
								HBSmsCodeUtils.sendSmsMsgCommon(String.format(Consts.INSIDE_REGISTER, new Object[]{
										appUser.getMobile().trim(), "123456"}),
										appUser.getMobile().trim(),
										hospital.getHospitalId().getName(), 49);//默认天使医生发送
							}
						}
					}
					userInfo.setUserId(appUser);
					order.setMonitorUserId(userInfo);
				}
			}else{//院内的老用户
				MonitorUserInfo user = monitorUserInfoDaoImpl.get(userInfo.getId());
				BeanUtils.copy(userInfo, user);
				if(edcDate!=null){
					user.setEdc(TimeUtils.getTimestampDate(edcDate, Consts.FORMAT_TIME_THREE));
				}
				/** 此处如果app用户注册手机号码和在医院购买订单的手机号码相同，则将app用户绑定 **/
				if(user != null){
					UserInfo appUser = userInfoDaoImpl.findByMobile(userInfo.getMobile());
					if(appUser != null){
						//修改userinfo 的expectedDateOfConfinement  pregnantWeek（根据预产期计算）  并且推算出末次月经 去修改userExitInfo 的age realname lastPeriod
						appUser.setExpectedDateOfConfinement(user.getEdc());
						int[] pregnantWeek_415 = TimeUtils.getPregnantWeek_415(user.getEdc(), new Date());
						appUser.setPregnantWeek(pregnantWeek_415[0]);
						//设置用户额外信息表
						UserExtraInfo userExitInfo=appUser.getUserExitInfo();
						if(userExitInfo!=null){
							userExitInfo.setRealName(user.getRealName());
							userExitInfo.setAge(user.getAge());
							Date lastPeriod= TimeUtils.getCurrentStartTime(-280,userInfo.getEdc());
							userExitInfo.setLastPeriod(lastPeriod);
							appUser.setUserExitInfo(userExitInfo);
						}
						user.setUserId(appUser);
						userInfo.setUserId(appUser);
					}else{
						appUser = userInfoDaoImpl.createInsideAppUser(userInfo,edcDate);
						user.setUserId(appUser);
						userInfo.setUserId(appUser);
					}
				}
				order.setMonitorUserId(user);
			}
			MonitorOrder result = monitorOrderDaoImpl.save(order);
			
		    //线上支付，订单支付表添加数据,线下支付也要保存在这个表
			//添加订单支付 begin
			PayOrderInfo payOrderInfo = new PayOrderInfo();
			payOrderInfo.setUserName(order.getMonitorUserId().getRealName());
			payOrderInfo.setDoctorId(order.getMonitorHospitalId().getHospitalId().getId());
			payOrderInfo.setDoctorName(order.getMonitorHospitalId().getHospitalId().getName());
			payOrderInfo.setPayChannels(0);
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
			payOrderInfo.setPayConfigId(0);   //获取医院的配置id
			payOrderInfo.setUserId(userInfo.getUserId().getId());
			PayOrderInfo payResult = payOrderInfoDaoImpl.save(payOrderInfo);
			//end
			
			
			if(result != null){
				return Consts.SUCCESS;
			}
			return Consts.FAILED;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(Consts.ERROR_TAG+"添加或编辑胎监订单异常，异常用户手机号码："+userInfo.getMobile().trim()+"异常原因："+e.getMessage());
			return Consts.ERROR;
		}
	}

	@Override
	public VoMonitorOrder convertOrderDetail(MonitorOrder order) {
		try {
			if(order == null){
				return null;
			}
			VoMonitorOrder voOrder = new VoMonitorOrder();
			voOrder.setId(order.getId());
			voOrder.setAge(order.getMonitorUserId().getUserId().getUserExitInfo().getAge()==null?order.getMonitorUserId().getAge():order.getMonitorUserId().getUserId().getUserExitInfo().getAge());
			voOrder.setMobile(order.getMonitorUserId().getMobile());
			voOrder.setMonitorUserId(order.getMonitorUserId().getId());
			voOrder.setOptionId(order.getOptionId());
			voOrder.setRealName(order.getMonitorUserId().getUserId().getUserExitInfo().getRealName()==null?order.getMonitorUserId().getRealName():order.getMonitorUserId().getUserId().getUserExitInfo().getRealName());
			voOrder.setAddress(order.getMonitorUserId().getAddress());
			
			Date pregantDate = new Date();
			UserInfo user = order.getMonitorUserId().getUserId();
			if( user != null && user.getExpectedDateOfConfinement() != null){
				pregantDate = user.getExpectedDateOfConfinement();
			}else{
				if(order.getMonitorUserId().getEdc() != null){
					pregantDate = order.getMonitorUserId().getEdc();
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
		return monitorOrderDaoImpl.findMonitorOrderList(page,id,searchKey,startTime,endTime);
	}

	@Override
	public Double getProfits(String sql) {
		return monitorOrderDaoImpl.getProfits(sql);
	}

	@Override
	public Integer getRemoteCountByHospital(Integer id) {
		return monitorOrderDaoImpl.getRemoteCountByHosptial(id);
	}

	@Override
	public Double getSumTrueCost(Integer id) {
		String sql = "select sum(true_cost) from hospital_cons_service_order where status=1 and hospital_id="+id+" and is_enabled=0";
		return monitorOrderDaoImpl.getSumTrueCost(sql);
	}
	@Override
	public Double getSumMoney(Integer id) {
		String sql = "select sum(money) from monitor_order where monitor_hospital_id="+id;
		return monitorOrderDaoImpl.getSumMoney(sql);
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
		return monitorOrderDaoImpl.getSumTrueCostByConds(sql);
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
		return monitorOrderDaoImpl.getSumMoneyByConds(sql);
	}

	@Override
	public String doChgInsideOrder(Integer id) {
		try {
			if(id == null || id == 0){
				return Consts.PARAMS_ERROR;
			}
			MonitorOrder order = monitorOrderDaoImpl.get(id);
			order.setState(RemoteOrderState.待监测);
			MonitorOrder result = monitorOrderDaoImpl.save(order);
			if(result != null){
				return Consts.SUCCESS;
			}
			return Consts.FAILED;
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

}
