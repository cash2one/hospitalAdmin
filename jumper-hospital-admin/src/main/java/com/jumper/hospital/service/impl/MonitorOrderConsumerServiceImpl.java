package com.jumper.hospital.service.impl;
/**
 * 用户已消费订单service实现类
 * @author rent
 * @date 2015-09-22
 */
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalDoctorInfoDao;
import com.jumper.hospital.dao.MonitorHeartrateRecordDao;
import com.jumper.hospital.dao.MonitorOrderConsumerDao;
import com.jumper.hospital.dao.UserInfoDao;
import com.jumper.hospital.dao.impl.HospitalSubordinateDaoImpl;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalDoctorInfo;
import com.jumper.hospital.entity.HospitalSubordinate;
import com.jumper.hospital.entity.MonitorHeartrateRecord;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorOrder;
import com.jumper.hospital.entity.MonitorOrderConsumer;
import com.jumper.hospital.entity.MonitorUserInfo;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.enums.RemoteType;
import com.jumper.hospital.enums.ReportState;
import com.jumper.hospital.service.MonitorHospitalService;
import com.jumper.hospital.service.MonitorOrderConsumerService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.Const;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HBSmsCodeUtils;
import com.jumper.hospital.utils.HttpRequestUtils;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.ReportPushUtil;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.MsgBean;
import com.jumper.hospital.vo.VoFetalMove;
import com.jumper.hospital.vo.VoFinishedOrder;
import com.jumper.hospital.vo.VoRemoteData;
import com.jumper.hospital.vo.VoRemoteHeart;
import com.jumper.record.service.DubboEfmCommonService;
@Service
public class MonitorOrderConsumerServiceImpl extends BaseServiceImpl<MonitorOrderConsumer, Integer> implements MonitorOrderConsumerService {

	private static final Logger logger = Logger.getLogger(MonitorOrderConsumerServiceImpl.class);
	
	@Autowired
	private MonitorOrderConsumerDao monitorOrderConsumerDaoImpl;
	@Autowired
	private MonitorHeartrateRecordDao monitorHeartrateRecordDaoImpl;
	@Autowired
	private UserInfoDao userInfo;
//	@Autowired
//	private AdminDao monitorAdminDao;
	@Autowired
	private HospitalDoctorInfoDao hospitalDoctorInfoDao;
	@Autowired
	private HospitalSubordinateDaoImpl hospitalSubordinateDaoImpl;
	@Autowired
	DubboEfmCommonService dubboEfmCommonService;
	@Autowired
	private MonitorHospitalService monitorHospitalService;
	
	@Override
	public BaseDao<MonitorOrderConsumer, Integer> getBaseDAO() {
		return monitorOrderConsumerDaoImpl;
	}

	/**
	 * 医生审核后台获取未审核订单信息
	 */
	public Page<VoRemoteData> findNotFinishOrder(Page<MonitorOrderConsumer> page, Integer hospitalId) {
		try {
			Page<MonitorOrderConsumer> pageData = monitorOrderConsumerDaoImpl.findNotFinishOrder(page, hospitalId);
			Page<VoRemoteData> result = converData(pageData);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public VoRemoteData findFinishOrderDataDetail(Integer id) {
		try {
			MonitorOrderConsumer consumer = monitorOrderConsumerDaoImpl.get(id);
			VoRemoteData vo = convertRemoteDate(consumer);
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	private VoRemoteData convertRemoteDate(MonitorOrderConsumer consumer) throws ParseException {
		VoRemoteData vo = null;
		if(consumer != null){
			vo = new VoRemoteData();
			/** 获取用户信息 **/
			MonitorUserInfo mUser = consumer.getMonitorOrderId().getMonitorUserId();
			MonitorOrder mOrder  = consumer.getMonitorOrderId();
			if(mOrder == null || mOrder.getRemoteType() == null){
				vo.setRemoteType("--");
			}else{
				vo.setRemoteType(mOrder.getRemoteType().toString());
			}
			if(mUser == null || mUser.getUserId() == null){
				vo.setPreganyDate("--");
				vo.setPreganyWeek("--");
			}else{
				Date preganyDate = mUser.getUserId().getExpectedDateOfConfinement();
				vo.setPreganyDate(preganyDate == null ? "-" : TimeUtils.convertTime(preganyDate, Consts.FORMAT_TIME_THREE));
				vo.setPreganyWeek(preganyDate == null ? "-" : String.valueOf(TimeUtils.getPregnantWeek_415(preganyDate,new Date())[0]));
			}
			if(null!=consumer.getCommentInfo()){
				vo.setCommentInfo(consumer.getCommentInfo());
			}
			vo.setConsumerId(consumer.getId());
			vo.setQuestionId(consumer.getQuestionId());
			if(mUser.getUserId() != null){
				vo.setUserId(mUser.getUserId().getId());
				UserExtraInfo user =  userInfo.findUserExtra(vo.getUserId());
				vo.setUserName(user.getRealName());
				vo.setUserAge(user.getAge());
			}else{
				vo.setUserName(mUser.getRealName());
				vo.setUserAge(mUser.getAge());
			}
			if(consumer.getRecordId()!=null&&!"".equals(consumer.getRecordId())){
				vo.setRecordId(consumer.getRecordId());
			}
			
			vo.setMobile(mUser.getMobile());
			if(consumer.getMonitorOrderId() != null){
				if(consumer.getMonitorOrderId().getId() != null){
					vo.setOrderId(consumer.getMonitorOrderId().getId());
					vo.setMonitorTimes( monitorOrderConsumerDaoImpl.getMonitorSequence(consumer.getMonitorOrderId().getId(),consumer.getApplyTime()));
				}
			}
			if(consumer.getRecordId() != null && consumer.getRecordId() != 0){
				VoRemoteHeart heart = new VoRemoteHeart();
				MonitorHeartrateRecord heartRate = monitorHeartrateRecordDaoImpl.get(consumer.getRecordId());
				if(heartRate!=null){
					heart.setId(heartRate.getId());
					heart.setMusic(Consts.BASE_FILE_URL+heartRate.getRaw_files());
					heart.setTime(TimeUtils.convertTime(heartRate.getAdd_time()));
					//heart.setTime(TimeUtils.calculateTime(heartRate.getAdd_time(), -heartRate.getTestTime()));
					if(null!=heartRate.getAdd_time()&&null!=heartRate.getTestTime()){
						heart.setEndTime(TimeUtils.calculateTime(heartRate.getAdd_time(),+heartRate.getTestTime()));
					}
					
					if(null!=heartRate.getRecord_files()&&!"".equals(heartRate.getRecord_files())){
						heart.setData(JsonUtils.getJsonFileStr(Consts.BASE_FILE_URL+heartRate.getRecord_files()));
					}
					if(StringUtils.isNotEmpty(heartRate.getUterusRecord())){
						heart.setUterusData(JsonUtils.getJsonFileStr(Consts.BASE_FILE_URL+heartRate.getUterusRecord()));
					}
					heart.setMinute(TimeUtils.getTimeStrBySeconds(heartRate.getTestTime()));
					heart.setFetalMoveData(convertFetalMoveData(heartRate.getFetal_move_value()));
					//处理孕周 根据监护时间计算
					if(mUser == null || mUser.getUserId() == null){
						vo.setPreganyDate("--");
						vo.setPreganyWeek("--");
					}else{
						Date preganyDate = mUser.getUserId().getExpectedDateOfConfinement();
						vo.setPreganyDate(preganyDate == null ? "-" : TimeUtils.convertTime(preganyDate, Consts.FORMAT_TIME_THREE));
						if(null!=heartRate.getAdd_time()){
							vo.setPreganyWeek(preganyDate == null ? "-" : String.valueOf(TimeUtils.getPregnantWeek_415(preganyDate,TimeUtils.getString2Date(TimeUtils.convertTime(heartRate.getAdd_time()), "yyyy-MM-dd HH:mm:ss"))[0]));
						}
					}
					vo.setHeart(heart);
				}else{
					logger.info("待审核报告查询 convertRemoteDate:MonitorHeartrateRecord="+heartRate);
					vo.setHeart(heart);
				}
			}
		}
		return vo;
	}

	@Override
	public Page<VoFinishedOrder> findFinishOrderData(Page<MonitorOrderConsumer> page, Integer hospitalId, String searchKey, String startTime, String endTime, RemoteType remoteType, Integer selectId,List<Integer> doctorIds,Admin admin) {
		try {
			Page<MonitorOrderConsumer> pageData = monitorOrderConsumerDaoImpl.findFinishedOrder(page, hospitalId, searchKey, startTime, endTime, remoteType, selectId,doctorIds);
			Page<VoFinishedOrder> result = converFinishedData(pageData,admin);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Page<VoRemoteData> converData(Page<MonitorOrderConsumer> page){
		Page<VoRemoteData> pageData = new Page<VoRemoteData>();
		List<VoRemoteData> dataList = new ArrayList<VoRemoteData>();
		try {
			if(page != null && ArrayUtils.isNotEmpty(page.getResult())){
				for(MonitorOrderConsumer consumer : page.getResult()){
					VoRemoteData vo = convertRemoteDate(consumer);
					
					dataList.add(vo);
				}
				pageData.setPageNo(page.getPageNo());
				pageData.setPageSize(page.getPageSize());
				pageData.setResult(dataList);
				pageData.setTotalCount(page.getTotalCount());
			}
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 转换胎动数据
	 * @param fetalMoveJson 胎动数据库记录值
	 * @return String
	 */
	public String convertFetalMoveData(String fetalMoveJson){
		try {
			if(StringUtils.isEmpty(fetalMoveJson)){
				return String.valueOf("");
			}
			Gson gson = new Gson();
			List<VoFetalMove> result = gson.fromJson(fetalMoveJson, new TypeToken<List<VoFetalMove>>(){}.getType());
			List<Integer> list = new ArrayList<Integer>();
			for(VoFetalMove str : result){
				String[] timer = str.getTime().split(":");
				Integer time = Integer.valueOf(timer[0]) * 60 + Integer.valueOf(timer[1]);
				list.add(time);
			}
			Collections.sort(list);
			StringBuilder sb = new StringBuilder();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (i < list.size() - 1) {
						sb.append(list.get(i) + ",");
					} else {
						sb.append(list.get(i));
					}
				}
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("未完成报告胎动数据转换异常！");
			return String.valueOf("");
		}
	}
	
	@Override
	public VoRemoteData getRemoteDataDetail(MonitorOrderConsumer consumer) {
		try {
			if(consumer != null){
				VoRemoteData vo = new VoRemoteData();
				/** 获取用户信息 **/
				MonitorUserInfo mUser = consumer.getMonitorOrderId().getMonitorUserId();
				Date preganyDate = null;
				Integer userId = null;
				if(mUser != null && mUser.getUserId() != null){//院内有用户并且APP 有用户 
					preganyDate = mUser.getUserId().getExpectedDateOfConfinement();
					userId =  mUser.getUserId().getId();
				}else if(mUser != null){//院内有用户  APP未绑定 
					preganyDate = mUser.getEdc();
				}else if(consumer.getUserId() != null){//APP有数据  院内没数据
					userId =  consumer.getUserId().getId();
				}
				UserExtraInfo user=null;
				//如果userid不存在就用悦康那边的数据
				if(null!=userId){
					user =  userInfo.findUserExtra(userId);
					vo.setUserName(user.getRealName());
					vo.setUserAge(user.getAge());
				}else{
					vo.setUserName(mUser.getRealName());
					vo.setUserAge(mUser.getAge());
				}
				vo.setConsumerId(consumer.getId());
				vo.setUserId(userId);
				vo.setHospitalInfo(consumer.getMonitorOrderId().getMonitorHospitalId().getHospitalId());
				vo.setMobile(mUser.getMobile());
				vo.setRemoteType(consumer.getMonitorOrderId().getRemoteType().name());
				vo.setPreganyDate(preganyDate == null ? "暂无" : TimeUtils.convertTime(preganyDate, Consts.FORMAT_TIME_THREE));
				vo.setPreganyWeek(preganyDate == null ? "暂无" : String.valueOf(TimeUtils.getPregnantWeek_415(preganyDate, new Date())[0]+"周"+TimeUtils.getPregnantWeek_415(preganyDate, new Date())[1]+"天"));
				
				if(consumer.getRecordId() != null && consumer.getRecordId() != 0){
					VoRemoteHeart heart = new VoRemoteHeart();
					MonitorHeartrateRecord heartRate = monitorHeartrateRecordDaoImpl.get(consumer.getRecordId());
					heart.setId(heartRate.getId());
					heart.setMusic(Consts.BASE_FILE_URL+heartRate.getRaw_files());
					heart.setTime(TimeUtils.convertTime(heartRate.getAdd_time()));
					heart.setEndTime(TimeUtils.calculateTime(heartRate.getAdd_time(),+heartRate.getTestTime()));
					if(StringUtils.isNotEmpty(heartRate.getRecord_files())){
						heart.setData(JsonUtils.getJsonFileStr(Consts.BASE_FILE_URL+heartRate.getRecord_files()));
					}
					if(StringUtils.isNotEmpty(heartRate.getUterusRecord())){
						heart.setUterusData(JsonUtils.getJsonFileStr(Consts.BASE_FILE_URL+heartRate.getUterusRecord()));
					}
					heart.setMinute(TimeUtils.getTimeStrBySeconds(heartRate.getTestTime()));
					heart.setFetalMoveData(convertFetalMoveData(heartRate.getFetal_move_value()));
					heart.setTitle(heartRate.getTitle());
					vo.setPreganyWeek(preganyDate == null ? "暂无" : String.valueOf(TimeUtils.getPregnantWeek_415(preganyDate, TimeUtils.getString2Date(TimeUtils.convertTime(heartRate.getAdd_time()), "yyyy-MM-dd HH:mm:ss"))[0]+"周"+TimeUtils.getPregnantWeek_415(preganyDate, TimeUtils.getString2Date(TimeUtils.convertTime(heartRate.getAdd_time()), "yyyy-MM-dd HH:mm:ss"))[1]+"天"));
					vo.setHeart(heart);
				}
				return vo;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Page<VoFinishedOrder> converFinishedData(Page<MonitorOrderConsumer> page,Admin admin){
		Page<VoFinishedOrder> pageData = new Page<VoFinishedOrder>();
		List<VoFinishedOrder> dataList = new ArrayList<VoFinishedOrder>();
		try {
			if(page != null && ArrayUtils.isNotEmpty(page.getResult())){
				for(MonitorOrderConsumer consumer : page.getResult()){
					if(consumer != null){
						VoFinishedOrder voOrder = new VoFinishedOrder();
						voOrder.setId(consumer.getId());
						if(consumer.getUserId()!= null){
							//老数据的空判断
							UserExtraInfo user =  userInfo.findUserExtra(consumer.getUserId().getId());
							voOrder.setUserName(user!=null?user.getRealName():consumer.getMonitorOrderId().getMonitorUserId().getRealName());
							voOrder.setAge(user!=null?user.getAge():consumer.getMonitorOrderId().getMonitorUserId().getAge());
						}else{
							voOrder.setAge(consumer.getMonitorOrderId().getMonitorUserId().getAge());
							voOrder.setUserName(consumer.getMonitorOrderId().getMonitorUserId().getRealName());
						}
						Integer doctorId  =consumer.getDoctorId();
						
						if(null!=doctorId){
							voOrder.setDoctorId(consumer.getDoctorId());
							//如果为-1则没有根据医院id和手机号码匹配到该账号，默认设置为管理员解读的报告
							if(Integer.valueOf("-1").equals(doctorId)){
								voOrder.setDoctorName(admin.getName());//设置为当前的admin
							}else{
								HospitalDoctorInfo hospitalDoctorInfo=	hospitalDoctorInfoDao.findUniqueBy("id", doctorId);
								if(null!=hospitalDoctorInfo){
									voOrder.setDoctorName(hospitalDoctorInfo.getName());
								}else{
									voOrder.setDoctorName(admin.getName());//设置为当前的admin
								}
							}
						}
						
						voOrder.setIsViewed(consumer.getIsViewed());
						voOrder.setUserId(consumer.getUserId() != null ? consumer.getUserId().getId() : null);
						voOrder.setMobile(consumer.getMonitorOrderId().getMonitorUserId() == null ? "" : consumer.getMonitorOrderId().getMonitorUserId().getMobile());
						voOrder.setAddress(consumer.getMonitorOrderId().getMonitorUserId() == null ? "" : consumer.getMonitorOrderId().getMonitorUserId().getAddress());
						voOrder.setApplyTime(TimeUtils.convertTime(consumer.getApplyTime()));
						voOrder.setFile(Consts.BASE_FILE_URL+consumer.getReportUrl());
						voOrder.setReportTime(consumer.getReportTime() == null ? "" : TimeUtils.convertTime(consumer.getReportTime()));
						MonitorHeartrateRecord heartRate = monitorHeartrateRecordDaoImpl.get(consumer.getRecordId());
						/*//已手机app的孕周为主
						if(null!=consumer.getMonitorOrderId().getMonitorUserId().getUserId()&&consumer.getMonitorOrderId().getMonitorUserId().getUserId().getPregnantWeek()!=null){
							Date preganyDate = consumer.getMonitorOrderId().getMonitorUserId().getUserId().getExpectedDateOfConfinement()==null?consumer.getMonitorOrderId().getMonitorUserId().getEdc():consumer.getMonitorOrderId().getMonitorUserId().getUserId().getExpectedDateOfConfinement();
							if(null==voOrder.getReportTime()){
								voOrder.setReportTime(TimeUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
							}
							voOrder.setPreganyWeek(TimeUtils.getPregnantWeek_415(preganyDate,TimeUtils.getString2Date(voOrder.getReportTime(), "yyyy-MM-dd HH:mm:ss"))[0]);
						}else{
							voOrder.setPreganyWeek(consumer.getMonitorOrderId().getPregnantWeek());
						}*/
						//处理孕周 根据监护时间计算
						if(consumer.getMonitorOrderId().getMonitorUserId().getUserId() == null){
							Date preganyDate = consumer.getMonitorOrderId().getMonitorUserId().getEdc();
							if(null!=heartRate.getAdd_time()){
								voOrder.setPreganyWeek(preganyDate == null ? 0 : TimeUtils.getPregnantWeek_415(preganyDate,TimeUtils.getString2Date(TimeUtils.convertTime(heartRate.getAdd_time()), "yyyy-MM-dd HH:mm:ss"))[0]);
							}
						}else{
							Date preganyDate = consumer.getMonitorOrderId().getMonitorUserId().getUserId().getExpectedDateOfConfinement();
							if(null!=heartRate.getAdd_time()){
								voOrder.setPreganyWeek(preganyDate == null ? 0 : TimeUtils.getPregnantWeek_415(preganyDate,TimeUtils.getString2Date(TimeUtils.convertTime(heartRate.getAdd_time()), "yyyy-MM-dd HH:mm:ss"))[0]);
							}
						}
						Integer hospitalId = consumer.getMonitorOrderId().getMonitorHospitalId().getHospitalId().getId();
						String hospitalName = consumer.getMonitorOrderId().getMonitorHospitalId().getHospitalId().getName();
						voOrder.setHospitalId(hospitalId);
						voOrder.setHospitalName(hospitalName);
						//voOrder.setPreganyWeek(consumer.getUserId() != null ? consumer.getUserId().getExpectedDateOfConfinement() == null ? 0 : TimeUtils.getPregnantWeek(consumer.getUserId().getExpectedDateOfConfinement())[0] : 0);
						voOrder.setRemoteType(consumer.getMonitorOrderId().getRemoteType() == null ? "" : consumer.getMonitorOrderId().getRemoteType().toString());
						dataList.add(voOrder);
					}
				}
				pageData.setPageNo(page.getPageNo());
				pageData.setPageSize(page.getPageSize());
				pageData.setResult(dataList);
				pageData.setTotalCount(page.getTotalCount());
			}
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Page<VoFinishedOrder> converRealTimeData(Page<MonitorOrderConsumer> page){
		Page<VoFinishedOrder> pageData = new Page<VoFinishedOrder>();
		List<VoFinishedOrder> dataList = new ArrayList<VoFinishedOrder>();
		try {
			if(page != null && ArrayUtils.isNotEmpty(page.getResult())){
				for(MonitorOrderConsumer consumer : page.getResult()){
					if(consumer != null){
						VoFinishedOrder voOrder = new VoFinishedOrder();
						voOrder.setId(consumer.getId());
						voOrder.setUserId(consumer.getUserId().getId());
						voOrder.setUserName(consumer.getMonitorOrderId().getMonitorUserId().getRealName());
						voOrder.setAge(consumer.getMonitorOrderId().getMonitorUserId().getAge());
						voOrder.setApplyTime(TimeUtils.convertTime(consumer.getApplyTime()));
						voOrder.setPreganyWeek(TimeUtils.getPregnantWeek(consumer.getUserId().getExpectedDateOfConfinement())[0]);
						dataList.add(voOrder);
					}
				}
				int size = dataList.size();
				if(dataList.size() < 9){
					for(int j = 0;j < 9 - size;j++){
						dataList.add(null);
					}
				}
				pageData.setPageNo(page.getPageNo());
				pageData.setPageSize(page.getPageSize());
				pageData.setResult(dataList);
				pageData.setTotalCount(page.getTotalCount());
			}else{
				for(int j = 0;j < 9;j++){
					dataList.add(null);
				}
				pageData.setPageNo(page.getPageNo());
				pageData.setPageSize(page.getPageSize());
				pageData.setResult(dataList);
				pageData.setTotalCount(page.getTotalCount());
			}
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<VoFinishedOrder> findRealTimeOrder(Page<MonitorOrderConsumer> page, Integer hospitalId) {
		try {
			Page<MonitorOrderConsumer> pageData = monitorOrderConsumerDaoImpl.findRealTimeOrder(page, hospitalId);
			Page<VoFinishedOrder> voPage = converRealTimeData(pageData);
			return voPage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String checkConsumerIsValidForChat(Integer consumerId) {
		
		int state = 0;
		String reportUrl = "";
		/** 实时监测  **/
		if(consumerId == 0){
			state = 1;
		}
		MonitorOrderConsumer orderConsumer = monitorOrderConsumerDaoImpl.get(consumerId);
		
		if(orderConsumer != null){
			if(StringUtils.isNotBlank(orderConsumer.getReportUrl())){
				reportUrl = Consts.BASE_FILE_URL + orderConsumer.getReportUrl();
			}
			Timestamp reportTime = orderConsumer.getReportTime();
			if(reportTime == null){
				state = 1;
			}else{
				/** 判断生成报告是否已过3天，三天则不能继续聊天 **/
				if(!TimeUtils.checkLimitTime(TimeUtils.getCurrentTime(),reportTime, 60*60*24*3)){
					state = 1;
				}
			}
		}
		String result = String.format("{\"state\":%s,\"report\":\"%s\"}", state,reportUrl);
		return result;
	}

	@SuppressWarnings("unused")
	@Override
	public Boolean xGenerateReport(HttpServletRequest request, Integer consumerId, String remark, Admin admin,
			Integer offset, String items, Integer source, Integer speed, Integer start, Integer end, Integer printType,String hospitalName) {
		try {
			MonitorOrderConsumer consumer = monitorOrderConsumerDaoImpl.get(consumerId);
			VoRemoteData data = getRemoteDataDetail(consumer);
			//offset = Math.abs(offset);
			MonitorOrder monitorOrder = null;
			if(consumer!=null){
				monitorOrder = consumer.getMonitorOrderId();
				if(monitorOrder!=null){		
					if((monitorOrder.getRemoteType()==RemoteType.院内监护)){
						//hospitalName = "院内胎心监护报告单";
					}
				}
			}
			
			/** 以下代码根据是否是陕西省的医院来生成报告 **/
			String PDFPath = "";
			//添加打印方式改用公共接口
			com.jumper.record.bean.BaseDataResult result1 = null;			
			String doctorName = "";
			if(admin!=null){
				doctorName = admin.getName();
			}			
			//Province province = admin.getHospitalInfo().getProvince();	
			
			Integer hospitalId=  admin.getHospitalInfo().getId();
			MonitorHospital mHospital=monitorHospitalService.findMonitorHospitalByHospitalId(hospitalId);
			Integer scoringMethod= mHospital.getScoringMethod();  // scoringMethod 评分方式 1:Kreb''s  2:改良Fischer  3:三级图形分类评分方法（ACCOG）
			
			if(printType==1){//双曲线模式
				//双曲线模式  type 1.Krebs曲线模式报告,2、改良Fischer  3.ACOG曲线模式报告  
				result1 = dubboEfmCommonService.generateCurveEfmPDF(consumer.getRecordId(),data.getMobile(),data.getPreganyWeek(), data.getUserAge(), data.getUserName(), items, source, speed,doctorName, hospitalName, remark, start, end,scoringMethod);
			}if(printType==0){ //常规模式
				if(Integer.valueOf("3").equals(scoringMethod)){
					//常规生成报告 ，常规没有双曲线   Sogc 西北妇幼主要使用的
					result1 = dubboEfmCommonService.generateSogcPDF(consumer.getRecordId(),data.getMobile(),data.getPreganyWeek(), data.getUserAge(), data.getUserName(), items, source, speed,doctorName, hospitalName, remark, start, end);
				}else{	
					//常规生成报告 ， type 1:Krebs曲线模式报告   , 2: 改良Fischer
					result1 = dubboEfmCommonService.generateAutoEfmPDF(consumer.getRecordId(),data.getMobile(),data.getPreganyWeek(), data.getUserAge(), data.getUserName(), items, source, speed,doctorName, hospitalName, remark, start, end,scoringMethod);
				}
			}
			
			PDFPath =  result1.getData()==null?null:String.valueOf(result1.getData());			
			if(StringUtils.isEmpty(PDFPath)){
				return false;
			}		
			
			String remoteFileUrl = PDFPath;
		    consumer.setReportUrl(remoteFileUrl);
		    consumer.setReportTime(TimeUtils.getCurrentTime());
		    consumer.setState(ReportState.审核完成);
		    //添加审核医生ID,要按照hospital_doctor_info 的id来查如果是当前医院，则为当前医院的id
		    HospitalDoctorInfo info= hospitalDoctorInfoDao.selectDoctorByPhone(admin.getHospitalInfo().getId(),admin.getMobile());
		    
		    //判断是否为下级医院,如果为下级医院则更新状态
		    Boolean isSubordinatehospital= hospitalSubordinateDaoImpl.isSubordinatehospital(admin.getHospitalInfo().getId());
		    //判断是否有下级医院，如果有也需要更新状态
		    List<HospitalSubordinate> hospitalSubordinateList = hospitalSubordinateDaoImpl.findHospitalSubordinateList(admin.getHospitalInfo().getId());
		    if(isSubordinatehospital || hospitalSubordinateList.size()>0){
		    	consumer.setIsViewed(false);
		    }
		    if(null!=info&&null!=info.getId()&&!admin.getIsFather()){
		    	 consumer.setDoctorId(info.getId());
		    }else if(admin.getIsFather()){
		    	 consumer.setDoctorId(-1);//如果没有查到,则默认为系统生成的报告默认设置为-1
		    }else{
		    	 consumer.setDoctorId(-1);//如果没有查到,则默认为系统生成的报告默认设置为-1
		    }
		    monitorOrderConsumerDaoImpl.save(consumer);
		    //组装推送数据
		    String title = "监测提醒";
		    hospitalName = "";
		    Integer userId = 0;
		    if(data != null){
		    	userId = data.getUserId();
		    	if(data.getHospitalInfo() != null){
		    		hospitalName = data.getHospitalInfo().getName();
		    	}
		    }
		    String content = "您在"+hospitalName+"进行的远程监测报告单已生成！";
		    if(Consts.JPUSH_VERSION.equals("4.0")){
		    	log.info("开始推送消息方法"+userId);
		    	if(null!=userId){
		    		//统一调用基础平台的消息推送
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("msgTitle", title);
					map.put("receiver", userId);
					map.put("msgContent", content);
					map.put("type", "15");
					map.put("appid", "0003");
					map.put("hospId",data.getHospitalInfo().getId());
					map.put("url", "");
					map.put("id", consumerId);//monitor_order_consumer 对应的id
					HttpRequestUtils.sendPost(Consts.CONSULT_IM_URL+"/push/send_jpush_receiver_msg", net.sf.json.JSONObject.fromObject(map));
					
					log.info("发送用户:"+userId+((Consts.IS_DEBUG.trim().toString()).equals("0")?"test":""));
					log.info("结束推送消息方法");
					
					if(consumer.getUserId()!=null && consumer.getUserId().getRegTime()!=null){
						//如果小于24小时发送短信,否则只有激光推送
						Timestamp regTime=consumer.getUserId().getRegTime();
						Timestamp now =new Timestamp(System.currentTimeMillis());
						Long diffHours= TimeUtils.getHourDifferent(now, regTime);
						String mobile=consumer.getUserId().getMobile()==null?consumer.getMonitorOrderId().getMonitorUserId().getMobile():consumer.getUserId().getMobile();
						if(diffHours<=24){
							boolean b = HBSmsCodeUtils.sendSmsMsgCommon(content, 
									mobile, 
									data.getHospitalInfo().getName(), data.getHospitalInfo().getId());
						}
					}
		    	}
		    	
			}else{
			    //APP消息推送
	        	String result = HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, "id="+consumerId+"&content=+"+content+"&title="+title+"&language=cn&Msg_type=15&user_type=1&user_msg="+userId);
	        	if(StringUtils.isNotBlank(result)){
	        		MsgBean bean = JSONArray.parseObject(result, MsgBean.class);
	            	if(bean.getMsg() != 1){
	            		logger.error(hospitalName+"报告单推送失败！");
	            	}
	        	}else{
	        		logger.error(hospitalName+"报告单推送失败！");
	        	}
			}
        	
        	//延安大学附属医院推送给蓓儿母婴（第三方服务商）
		    if(Const.YK_HOSPITAL_KEY.equals(data.getHospitalInfo().getHospitalKey())){
		    	 ReportPushUtil.push(data.getHeart().getTitle(),Const.YKAPPID, Consts.BASE_FILE_URL+remoteFileUrl, "", consumer.getOrderId(), TimeUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		    }
		    return true;
		}catch (NoSuchMethodError e) {
			e.printStackTrace();
			log.error("调用平台生成PDF接口报错");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("id", "1");//还是统一留ID字段
//		map.put("type", "14");
//		map.put("title", "医院消息");
//		map.put("content", "医院消息");
//		String js = JSONObject.fromObject(map).toString();
//		System.out.println(js);//11717   10481
//		String sa = (Consts.IS_DEBUG.toString()).equals("0")?"test":"";
//		System.out.println(sa);
//		Jpush.sendPush(10481+sa,"医院消息", "医院消息", 15, "1");
		
		
		Timestamp today= TimeUtils.getTimestampDate("2017-08-03 00:00:00","yyyy-MM-dd HH:mm:ss");
		Timestamp now=new Timestamp(System.currentTimeMillis());
		Long hours= TimeUtils.getHourDifferent(now, today);
		System.out.println(hours);
		
	}

	@Override
	public Integer finishOrderCount(Integer hospitalId, String searchKey,String startTime, String endTime, RemoteType remoteType,Integer selectId, 
				List<Integer> doctorIds) {
	Integer finishOrderCount=monitorOrderConsumerDaoImpl.finishedOrderCount(hospitalId,searchKey,startTime,endTime,remoteType,selectId, doctorIds);

		return finishOrderCount;
	}
	
	/**
	 * 当前医院未完成报告数
	 */
	@Override
	public Integer countNotFinishOrder(Integer hospitalId) {
		try {
			Integer countNotFinishOrder = monitorOrderConsumerDaoImpl.countNotFinishOrder(hospitalId);
			return countNotFinishOrder;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 从属医院审核完成，但是没有查阅的报告
	 */
	@Override
	public Integer totalFinishUnRedReport(Integer hospitalId) {
		try {
			Integer totalFinishUnRedReport = monitorOrderConsumerDaoImpl.totalFinishUnRedReport(hospitalId);
			return totalFinishUnRedReport;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean updateIsViewed(Integer reportId) {
		Boolean b= monitorOrderConsumerDaoImpl.updateIsViewed(reportId);
		return b;
	}

	@SuppressWarnings("unused")
	@Override
	public Boolean autoScorGenerateReport(HttpServletRequest request, Integer consumerId, String remark, Admin admin,
			 String items, Integer source, Integer speed, Integer start, Integer end, Integer printType,String hospitalName) {

		try {
			Integer hospitalId=  admin.getHospitalInfo().getId();
			MonitorHospital mHospital=monitorHospitalService.findMonitorHospitalByHospitalId(hospitalId);
			Integer scoringMethod=mHospital.getScoringMethod();
			
			MonitorOrderConsumer consumer = monitorOrderConsumerDaoImpl.get(consumerId);
			VoRemoteData data = getRemoteDataDetail(consumer);
			
			/** 以下代码根据是否是陕西省的医院来生成报告 **/
			String PDFPath = "";
			//添加打印方式改用公共接口
			com.jumper.record.bean.BaseDataResult result1 = null;			
			String doctorName = "";
			if(admin!=null){
				doctorName = admin.getName();
			}			
			
			if(printType==1){//双曲线模式   ，自动评分没有acog
				//双曲线模式  type 1.Krebs曲线模式报告,2、改良Fischer  3.ACOG曲线模式报告  
				result1 = dubboEfmCommonService.generateCurveEfmPDF(consumer.getRecordId(),data.getMobile(),data.getPreganyWeek(), data.getUserAge(), data.getUserName(), items, source, speed,doctorName, hospitalName, remark, start, end,scoringMethod);
			}if(printType==0){ //常规模式
				//自动评分生成报告,由于自动评分的只有 Krebss和改良Fischer的所以
				result1 = dubboEfmCommonService.generateAutoEfmPDF(consumer.getRecordId(),data.getMobile(),data.getPreganyWeek(), data.getUserAge(), data.getUserName(), items, source, speed,doctorName, hospitalName, remark, start, end,scoringMethod);
			}
			
			PDFPath =  result1.getData()==null?null:String.valueOf(result1.getData());			
			if(StringUtils.isEmpty(PDFPath)){
				return false;
			}		
			
			String remoteFileUrl = PDFPath;
		    consumer.setReportUrl(remoteFileUrl);
		    consumer.setReportTime(TimeUtils.getCurrentTime());
		    consumer.setState(ReportState.审核完成);
		    //添加审核医生ID,要按照hospital_doctor_info 的id来查如果是当前医院，则为当前医院的id
		    HospitalDoctorInfo info= hospitalDoctorInfoDao.selectDoctorByPhone(admin.getHospitalInfo().getId(),admin.getMobile());
		    
		    //判断是否为下级医院,如果为下级医院则更新状态
		    Boolean isSubordinatehospital= hospitalSubordinateDaoImpl.isSubordinatehospital(admin.getHospitalInfo().getId());
		    //判断是否有下级医院，如果有也需要更新状态
		    List<HospitalSubordinate> hospitalSubordinateList = hospitalSubordinateDaoImpl.findHospitalSubordinateList(admin.getHospitalInfo().getId());
		    if(isSubordinatehospital || hospitalSubordinateList.size()>0){
		    	consumer.setIsViewed(false);
		    }
		    if(null!=info&&null!=info.getId()&&!admin.getIsFather()){
		    	 consumer.setDoctorId(info.getId());
		    }else if(admin.getIsFather()){
		    	 consumer.setDoctorId(-1);//如果没有查到,则默认为系统生成的报告默认设置为-1
		    }else{
		    	 consumer.setDoctorId(-1);//如果没有查到,则默认为系统生成的报告默认设置为-1
		    }
		    monitorOrderConsumerDaoImpl.save(consumer);
		    //组装推送数据
		    String title = "监测提醒";
		    hospitalName = "";
		    Integer userId = 0;
		    if(data != null){
		    	userId = data.getUserId();
		    	if(data.getHospitalInfo() != null){
		    		hospitalName = data.getHospitalInfo().getName();
		    	}
		    }
		    String content = "您在"+hospitalName+"进行的远程监测报告单已生成！";
		    if(Consts.JPUSH_VERSION.equals("4.0")){
		    	log.info("开始推送消息方法"+userId);
		    	if(null!=userId){
		    		//统一调用基础平台的消息推送
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("msgTitle", title);
					map.put("receiver", userId);
					map.put("msgContent", content);
					map.put("type", "15");
					map.put("appid", "0003");
					map.put("hospId",data.getHospitalInfo().getId());
					map.put("url", "");
					map.put("id", consumerId);//monitor_order_consumer 对应的id
					//map.put("extraField", consumerId+"");
					HttpRequestUtils.sendPost(Consts.CONSULT_IM_URL+"/push/send_jpush_receiver_msg", net.sf.json.JSONObject.fromObject(map));
					
					log.info("发送用户:"+userId+((Consts.IS_DEBUG.trim().toString()).equals("0")?"test":""));
					log.info("结束推送消息方法");
					
					if(consumer.getUserId()!=null && consumer.getUserId().getRegTime()!=null){
						//如果小于24小时发送短信,否则只有激光推送
						Timestamp regTime=consumer.getUserId().getRegTime();
						Timestamp now =new Timestamp(System.currentTimeMillis());
						Long diffHours= TimeUtils.getHourDifferent(now, regTime);
						String mobile=consumer.getUserId().getMobile()==null?consumer.getMonitorOrderId().getMonitorUserId().getMobile():consumer.getUserId().getMobile();
						if(diffHours<=24){
							boolean b = HBSmsCodeUtils.sendSmsMsgCommon(content, 
									mobile, 
									data.getHospitalInfo().getName(), data.getHospitalInfo().getId());
						}
					}
		    	}
		    	
			}else{
			    //APP消息推送
	        	String result = HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, "id="+consumerId+"&content=+"+content+"&title="+title+"&language=cn&Msg_type=15&user_type=1&user_msg="+userId);
	        	if(StringUtils.isNotBlank(result)){
	        		MsgBean bean = JSONArray.parseObject(result, MsgBean.class);
	            	if(bean.getMsg() != 1){
	            		logger.error(hospitalName+"报告单推送失败！");
	            	}
	        	}else{
	        		logger.error(hospitalName+"报告单推送失败！");
	        	}
			}
        	
        	//延安大学附属医院推送给蓓儿母婴（第三方服务商）
		    if(Const.YK_HOSPITAL_KEY.equals(data.getHospitalInfo().getHospitalKey())){
		    	 ReportPushUtil.push(data.getHeart().getTitle(),Const.YKAPPID, Consts.BASE_FILE_URL+remoteFileUrl, "", consumer.getOrderId(), TimeUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		    }
		    return true;
		}catch (NoSuchMethodError e) {
			e.printStackTrace();
			log.error("调用平台生成PDF接口报错");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}


	
}
