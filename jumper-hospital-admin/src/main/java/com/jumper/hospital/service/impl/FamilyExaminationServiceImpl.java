package com.jumper.hospital.service.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import com.jumper.hospital.dao.FamilyDoctorinfoDao;
import com.jumper.hospital.dao.FamilyExaminationDao;
import com.jumper.hospital.dao.FamilyMoitorRecordsDao;
import com.jumper.hospital.dao.FamilyUserHeartrateRecordDao;
import com.jumper.hospital.dao.FamilyUserOxygenRecordDao;
import com.jumper.hospital.dao.FamilyUserPressureRecordDao;
import com.jumper.hospital.dao.FamilyUserSugarRecordDao;
import com.jumper.hospital.dao.FamilyUserTemperatureRecordDao;
import com.jumper.hospital.dao.FamilyUserWeightRecordDao;
import com.jumper.hospital.dao.FamilyUserinfoDao;
import com.jumper.hospital.dao.HospitalInfoDao;
import com.jumper.hospital.draw.GenerateMonitorPDF;
import com.jumper.hospital.draw.RemotepRegnancyTestReport;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.FamilyDoctorinfo;
import com.jumper.hospital.entity.FamilyExamination;
import com.jumper.hospital.entity.FamilyMoitorRecords;
import com.jumper.hospital.entity.FamilyUserHeartrateRecord;
import com.jumper.hospital.entity.FamilyUserOxygenRecord;
import com.jumper.hospital.entity.FamilyUserPressureRecord;
import com.jumper.hospital.entity.FamilyUserSugarRecord;
import com.jumper.hospital.entity.FamilyUserTemperatureRecord;
import com.jumper.hospital.entity.FamilyUserWeightRecord;
import com.jumper.hospital.entity.FamilyUserinfo;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.service.FamilyExaminationService;
import com.jumper.hospital.service.UserInfoService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.FastdfsUpload;
import com.jumper.hospital.utils.HttpRequestUtils;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.TestItemUtil;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.MsgBean;
import com.jumper.hospital.vo.VoFetalMove;
import com.jumper.hospital.vo.VoRemoteData;
import com.jumper.hospital.vo.VoRemoteHeart;
import com.jumper.hospital.vo.familyDoctor.MonitoringResults;
import com.jumper.hospital.vo.familyDoctor.TestItem;
import com.jumper.hospital.vo.familyDoctor.VOFamilyExaminationResult;

@Service
public class FamilyExaminationServiceImpl extends BaseServiceImpl<FamilyExamination, Integer>
		implements FamilyExaminationService {
	private static final Logger logger = Logger.getLogger(FamilyExaminationServiceImpl.class);
	@Autowired
	private FamilyExaminationDao familyExaminationDao;
	@Autowired
	private FamilyUserWeightRecordDao familyUserWeightRecordDao;
	@Autowired
	private FamilyUserHeartrateRecordDao familyUserHeartrateRecordDao;
	@Autowired
	private FamilyUserOxygenRecordDao familyUserOxygenRecordDao;
	@Autowired
	private FamilyUserPressureRecordDao familyUserPressureRecordDao;
	@Autowired
	private FamilyUserSugarRecordDao familyUserSugarRecordDao;
	@Autowired
	private FamilyUserTemperatureRecordDao familyUserTemperatureRecordDao;
	@Autowired
	private FamilyUserinfoDao familyUserinfoDao;
	@Autowired
	private HospitalInfoDao hospitalInfoDao;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private FamilyDoctorinfoDao familyDoctorinfoDao;
	@Autowired
	FamilyMoitorRecordsDao familyMoitorRecordsDao;
 
	
	@Override
	public BaseDao<FamilyExamination, Integer> getBaseDAO() {
		return familyExaminationDao;
	}
	
	/**
	 * 0:偏低1:正常2:偏高
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void getHospitalNotExamineResultByHospitalId(Page<VOFamilyExaminationResult> page, Admin admin) throws Exception {
		Integer hospitalId = admin.getHospitalInfo().getId();
		this.familyExaminationDao.getHospitalNotExamineResultByHospitalId(page,hospitalId);
		List<VOFamilyExaminationResult> vousers = page.getResult();
			for (int i = 0; i < vousers.size(); i++) {
				VOFamilyExaminationResult result = vousers.get(i);
				Integer userid = result.getUserid();//用户id
				//该map存储的是监测项目的 项目名和对应的监测项目id
				Map<String,Integer> map =familyMoitorRecordsDao.getExamineRecords(result.getExaminationId(),result.getCheckTime());
				
				List<MonitoringResults> list=new ArrayList<MonitoringResults>();//结果集list
				FamilyUserHeartrateRecord familyUserHeartrateRecord = getHeartRecord(userid, map, list);//--获取胎心展示结果
				if(null!=familyUserHeartrateRecord){//胎心的范围值是110-160次/分
					result.setRawFiles(Consts.BASE_FILE_URL+familyUserHeartrateRecord.getRawFiles());//设置音频/group1/M00/04/2E/CnQsP1aB5VyAM3CFAAB2aFTOmU4570.mp3
					result.setFetalMoveValue(familyUserHeartrateRecord.getFetalMoveFiles());//上传的胎动数据*//
					result.setRecordData(JsonUtils.getJsonFileStr(Consts.BASE_FILE_URL+familyUserHeartrateRecord.getRecordFiles()));//填充json数据http://192.168.2.67:8888/group1/M00/04/2E/CnQsP1aB5VyAAILqAAAAMRlZjqQ37.json
				}
				getWeightRecord(userid, map, list);//--获取体重展示结果
				getOxygenRecord(userid, map, list);//--获取血氧展示结果
				getPressureRecord(userid, map, list);//---获取血压展示结果
				getSugarRecord(userid, map, list);//--获取血糖展示结果
				getTemperatureRecord(userid, map, list);//--获取体温展示结果
				if(list.size()%2!=0){//保证list的长度为偶数，这样jsp展示就可以完整的一行有两条数据
					list.add(new MonitoringResults("", "", "", "1"));
				}
				result.setList(list);
			}
	 
	}

	public FamilyUserTemperatureRecord getTemperatureRecord(Integer userid,Map<String,Integer> map,List<MonitoringResults> list) {
		Map<Integer, String> typeMap = FamilyMoitorRecords.typeMap;
		String  project=typeMap.get(FamilyMoitorRecords.TEMPERATURE_TYPE);
		if(null==map.get(project)) return null;
		FamilyUserTemperatureRecord familyUserTemperatureRecord=familyUserTemperatureRecordDao.get(map.get(project));
		if(null!=familyUserTemperatureRecord){//小于37℃
			TestItem temperatureItem = TestItemUtil.getTestItem(TestItemUtil.temperature);
			String content = familyUserTemperatureRecord.getAverageValue()+temperatureItem.getCompany();
			Integer state = familyUserTemperatureRecord.getTemperatureState();
			if(null!=list)list.add(new MonitoringResults(temperatureItem.getProject(),content,temperatureItem.getRange(), state+""));
		}
		return familyUserTemperatureRecord;
	}

	public FamilyUserSugarRecord getSugarRecord(Integer userid, Map<String,Integer> map ,List<MonitoringResults> list) {
		Map<Integer, String> typeMap = FamilyMoitorRecords.typeMap;
		String  project=typeMap.get(FamilyMoitorRecords.SUGAR_TYPE);
		if(null==map.get(project)) return null;
		FamilyUserSugarRecord familyUserSugarRecord=familyUserSugarRecordDao.get(map.get(project));
		if(null!=familyUserSugarRecord){//小于5.1mmol/L
			TestItem sugarItem = TestItemUtil.getTestItem(TestItemUtil.sugar);
			Integer sugarState = familyUserSugarRecord.getSugarState();
			String content=familyUserSugarRecord.getAverageValue()+sugarItem.getCompany();
			if(null!=list)list.add(new MonitoringResults(sugarItem.getProject(), content,sugarItem.getRange(), sugarState+""));
		}
		return familyUserSugarRecord;
	}

	public FamilyUserPressureRecord getPressureRecord(Integer userid, Map<String,Integer> map,List<MonitoringResults> list) {
		Map<Integer, String> typeMap = FamilyMoitorRecords.typeMap;
		String  project=typeMap.get(FamilyMoitorRecords.PRESSURE_TYPE);
		if(null==map.get(project)) return null;
		FamilyUserPressureRecord familyUserPressureRecord=familyUserPressureRecordDao.get(map.get(project));
		if(null!=familyUserPressureRecord){// 90mmHg-139mmHg/60mmHg-89mmHg
			TestItem pressureHeightItem = TestItemUtil.getTestItem(TestItemUtil.pressureHeight);
			Integer height = familyUserPressureRecord.getPressureHeightResult();
			String content_h = familyUserPressureRecord.getPressureHeight()+pressureHeightItem.getCompany();
			if(null!=list)list.add( new MonitoringResults(pressureHeightItem.getProject(),content_h,pressureHeightItem.getRange(),height+""));
			
			TestItem pressureLowItem = TestItemUtil.getTestItem(TestItemUtil.pressureLow);
			int low = familyUserPressureRecord.getPressureLowResult();
			String content_l = familyUserPressureRecord.getPressureLow()+pressureLowItem.getCompany();
			if(null!=list)list.add( new MonitoringResults(pressureLowItem.getProject(),content_l,pressureLowItem.getRange(),low+""));
		}
		return familyUserPressureRecord;
	}

	public FamilyUserOxygenRecord getOxygenRecord(Integer userid,Map<String,Integer> map,
			List<MonitoringResults> list) {
		Map<Integer, String> typeMap = FamilyMoitorRecords.typeMap;
		String  project=typeMap.get(FamilyMoitorRecords.OXYGEN_TYPE);
		if(null==map.get(project)) return null;
		FamilyUserOxygenRecord familyUserOxygenRecord=familyUserOxygenRecordDao.get(map.get(project));
		if(null!=familyUserOxygenRecord){//大于95% -100% 正常  ↑↓
			TestItem oxygenItem = TestItemUtil.getTestItem(TestItemUtil.oxygen);
			String content=familyUserOxygenRecord.getAverageOxygen()+oxygenItem.getCompany();
			int resultState = familyUserOxygenRecord.getOxygeResultState();
			if(null!=list)list.add(new MonitoringResults(oxygenItem.getProject(),content,oxygenItem.getRange(),resultState+""));
		}
		return familyUserOxygenRecord;
	}

	public FamilyUserHeartrateRecord getHeartRecord(Integer userid, Map<String,Integer> map, List<MonitoringResults> list) {
		Map<Integer, String> typeMap = FamilyMoitorRecords.typeMap;
		String  project=typeMap.get(FamilyMoitorRecords.HEART_TYPE);
		if(null==map.get(project)) return null;
		FamilyUserHeartrateRecord familyUserHeartrateRecord = familyUserHeartrateRecordDao.get(map.get(project));
		if(null!=familyUserHeartrateRecord){//胎心的范围值是110-160次/分
			TestItem heartItem = TestItemUtil.getTestItem(TestItemUtil.heart);
			String content=familyUserHeartrateRecord.getAverageRate()+heartItem.getCompany();
			int resultState = familyUserHeartrateRecord.getResultState();
			if(null!=list)list.add(new MonitoringResults(heartItem.getProject(), content, heartItem.getRange(), resultState+""));
		}
		return familyUserHeartrateRecord;
	}

	public FamilyUserWeightRecord getWeightRecord(Integer userid,Map<String,Integer> map,List<MonitoringResults> list) {
		
		TestItem weightItem = TestItemUtil.getTestItem(TestItemUtil.weight);
		Map<Integer, String> typeMap = FamilyMoitorRecords.typeMap;
		String  project=typeMap.get(FamilyMoitorRecords.WEIGHT_TYPE);
		if(null==map.get(project)) return null;
		FamilyUserWeightRecord familyUserWeightRecord= familyUserWeightRecordDao.get(map.get(project));
		if(null!=familyUserWeightRecord){//目前体重没有范围值
			String content=familyUserWeightRecord.getAverageValue()+weightItem.getCompany();
			
			MonitoringResults weightRecord = new MonitoringResults(weightItem.getProject(),
					content+weightItem.getRange(),"无","1");
			if(null!=list)list.add(weightRecord);
		}
		return familyUserWeightRecord;
	}
 
	@Override
	public Boolean generateReport(HttpServletRequest request, Integer consumerId, String reason, String doctorName, Integer offset) {
		try {
			//获取报告单信息
			FamilyExamination consumer = familyExaminationDao.get(consumerId);//得到家庭医生的报告单
			FamilyUserinfo userinfo = familyUserinfoDao.get(consumer.getUserid());
			HospitalInfo hospitalInfo = hospitalInfoDao.get(userinfo.getFamilyHospitalId());
			VoRemoteData data = getRemoteDataDetail(consumer);
			if(null==data)return false;
			offset = Math.abs(offset);
			// 获取胎心报告单路径并上传
			String PDFPath = GenerateMonitorPDF.generatePDF(request, data,hospitalInfo.getName(), reason, doctorName, offset);
			if(StringUtils.isEmpty(PDFPath)){
				return false;
			}
			File file = new File(PDFPath);
			InputStream inputStream = new FileInputStream(file);
			String remoteFileUrl = FastdfsUpload.upoladFile("report.PDF", inputStream);
		    if(file.isFile() && file.exists()) {
		        file.delete();
		    }
		    if(StringUtils.isEmpty(remoteFileUrl)){
		    	return false;
		    }
		    //更新报告单的状态
		    consumer.setReportPdfUrl(remoteFileUrl);
		    consumer.setFinishTime(TimeUtils.getCurrentTime());
		    consumer.setState((short)1);
		    consumer.setReason(reason);
		    familyExaminationDao.update(consumer);
		    return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//生成胎心报告单的数据封装
	@SuppressWarnings("unchecked")
	public VoRemoteData getRemoteDataDetail(FamilyExamination consumer) {
		try {
			if(consumer != null){
				VoRemoteData vo = new VoRemoteData();
				/** 获取用户信息 **/
				FamilyUserinfo mUser = familyUserinfoDao.get(consumer.getUserid());
				Date preganyDate = mUser.getPregancyDay();  //预产期
				vo.setConsumerId(consumer.getId());//订单id
				vo.setUserName(mUser.getName());//用户姓名
				vo.setUserAge(mUser.getAge()*1);//年龄
				vo.setMobile(mUser.getMobile());//手机
				vo.setHospitalInfo(hospitalInfoDao.get(mUser.getFamilyHospitalId()));//设置医院
				//获取报告单中的检测时间就是报告单的创建时间
				Timestamp checkTime = consumer.getAddTime();//
				vo.setPreganyDate(preganyDate == null ? "暂无" : TimeUtils.convertTime(preganyDate, Consts.FORMAT_TIME_THREE));///** 预产期 **/
				vo.setPreganyWeek(preganyDate == null ? "暂无" : String.valueOf(TimeUtils.getPregnantWeek(preganyDate,checkTime)[0]+"周"+TimeUtils.getPregnantWeek(preganyDate,checkTime)[1]+"天"));///** 孕周 **/
				
				//该map存储的是监测项目的 项目名和对应的监测项目id
				Map<String,Integer> map =familyMoitorRecordsDao.getExamineRecords(consumer.getId(),consumer.getAddTime());
				Map<Integer, String> typeMap = FamilyMoitorRecords.typeMap;
				String  project=typeMap.get(FamilyMoitorRecords.HEART_TYPE);
				//(根据用户id 和报告单的checkTime )获取胎心数据
				FamilyUserHeartrateRecord heartRate=null;
				if(null!=map.get(project)){
					 heartRate = familyUserHeartrateRecordDao.get(map.get(project));
				}
				if(null!=heartRate){//如果有胎心数据
					VoRemoteHeart heart=new VoRemoteHeart();
					heart.setId(heartRate.getId());//胎心记录id
					if(StringUtils.isNotBlank(heartRate.getRawFiles())) heart.setMusic(Consts.BASE_FILE_URL+heartRate.getRawFiles());//音乐
					if(null!=heartRate.getAddTime()) heart.setTime(TimeUtils.convertTime(heartRate.getAddTime()));//记录添加时间
					if(null!=heartRate.getAddTime() && null !=heartRate.getTestTime()) heart.setEndTime(TimeUtils.calculateTime(new Timestamp(heartRate.getAddTime().getTime()), heartRate.getTestTime()));//结束时间
					if(StringUtils.isNotBlank(heartRate.getRecordFiles()))
						heart.setData(JsonUtils.getJsonFileStr(Consts.BASE_FILE_URL+heartRate.getRecordFiles()));//json 字符串数据
					if(null!=heartRate.getTestTime())
						heart.setMinute(TimeUtils.getTimeStrBySeconds(heartRate.getTestTime()));//设置测试时长
					if(StringUtils.isNotBlank(heartRate.getFetalMoveValue()))
						heart.setFetalMoveData(convertFetalMoveData(heartRate.getFetalMoveValue()));//胎动数据
					vo.setHeart(heart);
					return vo;
				}else{//没有胎心数据 就不用创建胎心报告单
					return null;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean familyComprehensiveReportForm(HttpServletRequest request,Integer reportid,String username,String reason) {
		try {//报告单相关数据封装模型
			VOFamilyExaminationResult voFamilyExaminationResult = new VOFamilyExaminationResult();
			
			//根据报告单id获取对应的报告单几可以知道对应的用户id  checkTime /根据这两个参数从6个明细表中获取数据
			FamilyExamination familyExamination = get(reportid);
			Integer userid = familyExamination.getUserid();
			Timestamp checkTime = familyExamination.getAddTime();
			
			FamilyUserinfo familyUserinfo = familyUserinfoDao.get(userid);
			voFamilyExaminationResult.setName(familyUserinfo.getName());
			voFamilyExaminationResult.setAge(familyUserinfo.getAge());
			voFamilyExaminationResult.setMobile(familyUserinfo.getMobile());
			voFamilyExaminationResult.setUsername(username);//设置审核医生
			voFamilyExaminationResult.setCheckTime(familyExamination.getAddTime());//
			voFamilyExaminationResult.setReason(familyExamination.getReason());
			//获取家庭医生的胎心数据明细
			 FamilyUserHeartrateRecord heartrateRecord = familyUserHeartrateRecordDao.getResultByUseridAndCheckTime(userid,checkTime);
			 if(null!=heartrateRecord){
				 
				 Date addTime = heartrateRecord.getAddTime();//添加时间
				 Integer testTime = heartrateRecord.getTestTime();//测试时长
				 if(null!=testTime){//好像在freemark中处理条件判断是否为空,有问题,后续解决了再在 ftl中填写前缀字符串
					 String endTime = TimeUtils.calculateTime(new Timestamp(addTime.getTime()), testTime);//结束时间
					 voFamilyExaminationResult.setHeartStartTime("开始监测时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(addTime));//开始时间
					 voFamilyExaminationResult.setHeartEndTime("结束监测时间:"+endTime);//结束时间
					 voFamilyExaminationResult.setTimeStrBySeconds("监测时长:"+TimeUtils.getTimeStrBySeconds(testTime));//转换后的测试时长
				 }
			 }
			 FamilyDoctorinfo familyDoctorinfo = familyDoctorinfoDao.get(familyUserinfo.getFamilyDoctorId());
			 voFamilyExaminationResult.setFamilyDoctorinfo(familyDoctorinfo);//设置家庭医生
//			 设置孕周
			 if(null!=familyUserinfo.getPregancyDay() && null!= familyExamination.getAddTime()){
				 int[] pregnantWeek = TimeUtils.getPregnantWeek(familyUserinfo.getPregancyDay(), familyExamination.getAddTime());
				 voFamilyExaminationResult.setYunzhou(pregnantWeek[0]+"周"+pregnantWeek[1]+"天");
			 }
			 //设置备注
			 if(StringUtils.isNotBlank(reason)){
				 voFamilyExaminationResult.setReason(reason);
			 }else{
				 voFamilyExaminationResult.setReason("");
			 }
			List<MonitoringResults> list=new ArrayList<MonitoringResults>();//结果集list
			voFamilyExaminationResult.setList(list);
			//该map存储的是监测项目的 项目名和对应的监测项目id
			Map<String,Integer> map =familyMoitorRecordsDao.getExamineRecords(familyExamination.getId(),familyExamination.getAddTime());
			
				getHeartRecord(userid, map, list);//--获取胎心展示结果
				getWeightRecord(userid, map, list);//--获取体重展示结果
				getOxygenRecord(userid, map, list);//--获取血氧展示结果
				getPressureRecord(userid, map, list);//---获取血压展示结果
				getSugarRecord(userid, map, list);//--获取血糖展示结果
				getTemperatureRecord(userid, map, list);//--获取体温展示结果
				
				 for (int i = 0; i < list.size(); i++) {
					MonitoringResults results = list.get(i);
					if("0".equalsIgnoreCase(results.getResultState())){
						results.setResultState("↓");
					}else if("2".equalsIgnoreCase(results.getResultState())){
						results.setResultState("↑");
					}else if("1".equalsIgnoreCase(results.getResultState())){
						results.setResultState("");
					}
				}
				 if(list.size()%2!=0){//如果结果集的数据不是偶数 ,freeMark在展示的时候算法会有问题
					 list.add(new MonitoringResults("","","",""));
				 }
				 //设置报告单的生成日期(报告单完成时间)
				voFamilyExaminationResult.setSysTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				
				String PDFPath = RemotepRegnancyTestReport.draw(request, voFamilyExaminationResult);
				if(StringUtils.isEmpty(PDFPath)){
					return false;
				}
				File file = new File(PDFPath);
				InputStream inputStream = new FileInputStream(file);
				String remoteFileUrl = FastdfsUpload.upoladFile("report.PDF", inputStream);
			    if(file.isFile() && file.exists()) {
			        file.delete();
			    }
			    if(StringUtils.isEmpty(remoteFileUrl)){
			    	return false;
			    }
			   Short result=1;//检查结果:1正常;0异常
			    for (MonitoringResults monitoringResults : list) {//以为要在pdf中显示箭头,当值为1 是就会被修改为""
			    	boolean flag = "".equals(monitoringResults.getResultState());
					if(!flag){
						result=0;
						break;
					}
				}
			    familyExamination.setResult(result);//设置总体报告单的结果状态
			    familyExamination.setDetailedreportPdfUrl(remoteFileUrl) ;//保存报告单url
			    familyExamination.setState((short)1);
			    familyExamination.setReason(reason);
			    familyExamination.setFinishTime(TimeUtils.getCurrentTime());
			    familyExaminationDao.update(familyExamination);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	public Boolean saveReport(HttpServletRequest request, Integer id,String reason, String name, Integer offset)throws RuntimeException {
		//生成胎心报告单
		Boolean report = generateReport(request, id, reason, name, offset);
		//生成院外远程产检综合报告单
		Boolean reportForm = familyComprehensiveReportForm(request, id,name,reason);
		
		if(false==report && false==reportForm){
			throw new RuntimeException("创建报告单异常");
		}
		
	 //先到天使医生的账号表中根据手机号码获取数据,有就推送没有就不推送
	   FamilyExamination familyExamination = familyExaminationDao.get(id);
	   FamilyUserinfo familyUserinfo = familyUserinfoDao.get(familyExamination.getUserid());//家庭医生用户
	   UserInfo user= userInfoService.findByContactPhone(familyUserinfo.getMobile());//天使医生用户
	   if(null!=user && null!= user.getMobile()){//10577  13266825843
		   String title = "监测提醒";
		    String content = "您有通过家庭医生进行的远程监测报告单已生成！";
        	String result = HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, "id="+id+"&content="+content+"&title="+title+"&language=cn&Msg_type=19&user_type=1&user_msg="+user.getId());
        	MsgBean bean = JSONArray.parseObject(result, MsgBean.class);
        	if(bean.getMsg() != 1){
        		logger.error("报告单推送失败！");
        	}
	   }else{
		   logger.error("家庭医生用户的id为:"+familyUserinfo.getId()+"的"+familyUserinfo.getName()+"没有注册天使医生的账号不能推送");
	   }
		return true;
	}

	@Override
	public List<Map<String, Object>> getListByUserid(Integer userid) {
		//这里计算孕周  几格式化checkTime
		List<Map<String, Object>> list = this.familyExaminationDao.getListByUserid(userid);
		 
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Integer> getExamineRecords(Integer id, Timestamp addTime) {
		return familyMoitorRecordsDao.getExamineRecords(id,addTime);
	}

	  
	
}
