package com.jumper.hospital.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.UserBloodFatRecord;
import com.jumper.hospital.entity.UserEcgRecord;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.entity.UserFetalmoveRecord;
import com.jumper.hospital.entity.UserHeartrateRecord;
import com.jumper.hospital.entity.UserHeartrateReportFile;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.entity.UserOxygenRecord;
import com.jumper.hospital.entity.UserPressureRecord;
import com.jumper.hospital.entity.UserSugarRecord;
import com.jumper.hospital.entity.UserTemperatureRecord;
import com.jumper.hospital.entity.UserUrineRecord;
import com.jumper.hospital.entity.UserWeightRecord;
import com.jumper.hospital.service.BaseService;
import com.jumper.hospital.service.UserBloodFatRecordService;
import com.jumper.hospital.service.UserEcgRecordService;
import com.jumper.hospital.service.UserExtraInfoService;
import com.jumper.hospital.service.UserFetalmoveRecordService;
import com.jumper.hospital.service.UserHeartrateRecordService;
import com.jumper.hospital.service.UserHeartrateReportFileService;
import com.jumper.hospital.service.UserInfoService;
import com.jumper.hospital.service.UserOxygenRecordService;
import com.jumper.hospital.service.UserPressureRecordService;
import com.jumper.hospital.service.UserSugarRecordService;
import com.jumper.hospital.service.UserTemperatureRecordService;
import com.jumper.hospital.service.UserUrineRecordService;
import com.jumper.hospital.service.UserWeightRecordService;
import com.jumper.hospital.utils.Const;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.FastdfsUpload;
import com.jumper.hospital.utils.mointorData.GenerateSFYMonitorPDF;
import com.jumper.hospital.utils.mointorData.JsonUtils;
import com.jumper.hospital.utils.mointorData.ObjectUtils;
import com.jumper.hospital.utils.mointorData.TimeUtils;
import com.jumper.hospital.utils.mointorData.WeightUtils;
import com.jumper.hospital.vo.monitorData.Params;
import com.jumper.hospital.vo.monitorData.UserPrintVO;
import com.jumper.hospital.vo.monitorData.UserViewVO;
/*import cn.com.jumper.basedata.entity.base.UserBookBasicInfo;
import cn.com.jumper.basedata.service.UserBookBasicInfoService;
*/
/**
 * 孕期管理
 * 监测数据controller
 * @author admin
 * 2016-7-28
 */
@Controller
@RequestMapping("/monitor")
public class PregnantMonitorDataController {
	
	private static final Logger logger =  Logger.getLogger(PregnantMonitorDataController.class);
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserExtraInfoService userExtraInfoService;
	@Autowired
	private UserWeightRecordService userWeightRecordService;
	@Autowired
	private UserHeartrateRecordService userHeartrateRecordService;
	@Autowired
	private UserHeartrateReportFileService userHeartrateReportFileService;
	@Autowired
	private UserSugarRecordService userSugarRecordService;
	@Autowired
	private UserPressureRecordService userPressureRecordService;
	@Autowired
	private UserOxygenRecordService userOxygenRecordService;
	@Autowired
	private UserBloodFatRecordService userBloodFatRecordService;
	@Autowired
	private UserTemperatureRecordService userTemperatureRecordService;
	@Autowired
	private UserUrineRecordService userUrineRecordService;
	@Autowired
	private UserEcgRecordService userEcgRecordService;
	/*@Autowired
    private UserBookBasicInfoService userBookBasicInfoService;*/
	@Autowired
	private UserFetalmoveRecordService  userFetalmoveRecordService;
	
	
	/**
	 * 加载用户档案基本信息
	 * @param model
	 * @param archiveBaseId
	 */
	public void setArchiveBaseInfo(Model model,Integer archiveBaseId,Integer archiveId){
		try {
			//回传到Url中
			model.addAttribute("archiveBaseId", archiveBaseId);
			model.addAttribute("archiveId",archiveId);
//			//根据档案id获取档案基本信息
//			UserBookBasicInfo userBookBasicInfo=userBookBasicInfoService.find(archiveBaseId);
//			//根据预产期计算孕周
//			if(userBookBasicInfo != null && userBookBasicInfo.getPregnantDate()!=null){
//				int[] weekDay=TimeUtils.getPregnantWeek(userBookBasicInfo.getPregnantDate());
//				model.addAttribute("pregnantWeek", weekDay[0]);
//				model.addAttribute("pregnantDay", weekDay[1]);
//			}
//			model.addAttribute("userBaseInfo", userBookBasicInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**体重监测数据 **/
	@SuppressWarnings("finally")
	@RequestMapping(value="/weight")
	public String monitorWeight(Model model,@ModelAttribute Params params){
			model.addAttribute("type", "weight"); //显示不同的检测数据
		try {
			setArchiveBaseInfo(model,params.getArchiveBaseId(),params.getArchiveId());
			String userId = params.getUserId();
//			String userId="34";
			if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId)){
				logger.info("userId:"+userId);
				//通过userId查询UserExtraInfo,userInfo
				UserExtraInfo userExtra = userExtraInfoService.findByUserId(Integer.valueOf(userId));
				model.addAttribute("userExtra", userExtra);
				if(null != userExtra){
					UserInfo user = userInfoService.findUserInfoById(Integer.valueOf(userId));
					//获取预产期
					Date expectedDate = user.getExpectedDateOfConfinement();
					UserWeightRecord weight = new UserWeightRecord();
					weight.setUserInfo(user);
					//设置用户展示在页面的基本信息
					UserViewVO viewVO  = setUserViewVO(userWeightRecordService, weight,userExtra,user,true,params);
					//获取孕妇在整个保健期的具体日期
					StringBuffer totalData = new StringBuffer();
					totalData = getTotalData(totalData, params.getShowType(), expectedDate, userExtra.getBabyBirthday());
					//处理相关查询条件
					int pageNum = 0;
					Page<UserWeightRecord> page = new Page<UserWeightRecord>();//分页体重数据
					List<UserWeightRecord> userWeigthList = new ArrayList<UserWeightRecord>();
					if(StringUtils.isNotBlank(params.getPageNum()) && StringUtils.isNumeric(params.getPageNum())){
						logger.info("获取所有的监测体重数据------------");
						pageNum = Integer.valueOf(params.getPageNum());
						page.setPageNo(pageNum);
						page = userWeightRecordService.getUserWeightRecordWithPage(page,weight,params);
						userWeigthList = page.getResult();
						if(userWeigthList.size() > 0){
							List<UserViewVO> voList = new ArrayList<UserViewVO>();
							//记录体重历史数据
							for(int i=0;i<userWeigthList.size();i++){
								UserViewVO vo = new UserViewVO();
								//计算孕周和孕天
								String addTimeStr = TimeUtils.getTimeStampNumberFormat(userWeigthList.get(i).getAdd_time(),"yyyy-MM-dd HH:mm:ss");
								Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
								int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
								vo.setPregnantWeek(arr[0]);
								vo.setPregnantDay(arr[1]);
								vo.setMonitorTime(TimeUtils.getTimeStampNumberFormat(userWeigthList.get(i).getAdd_time(), "yyyy-MM-dd"));
								vo.setValue(userWeigthList.get(i).getAverage_value());
								vo.setWeightState(userWeigthList.get(i).getWeight_state()+"");
								//获取健康区间
								String[] totalDays = totalData.toString().substring(1, totalData.length()-1).split(",");
								Double initBmi = WeightUtils.getBmiByWeightAndHeight(userExtra);
								Double weigh = Double.valueOf(userExtra.getWeight().toString());//(kg)
								Map<String, String> healthWeight = WeightUtils.getHealthWeight(initBmi, weigh, totalDays);
								String timeKey = TimeUtils.getTimeStampNumberFormat(userWeigthList.get(i).getAdd_time(), "yyyy:MM:dd");
								String value = healthWeight.get(timeKey);
								if(StringUtils.isNotBlank(value)){
									vo.setHealthMin(value.split(",")[0]);
									vo.setHealthMax(value.split(",")[1]);
								}
								voList.add(vo);
								model.addAttribute("weightList", voList);
							}
						}
						
					}else{ //不是分页
						logger.info("体重趋势图-------------");
						userWeigthList = userWeightRecordService.findByCondtion(weight,params);
						//拼装按月和天查询时 健康曲线x轴要显示的数据
						StringBuffer xData = new StringBuffer();
						xData = getXData(xData, params.getTimeFlag(),params.getShowType(),expectedDate,params.getStartTime(), params.getEndTime());
						if(userWeigthList.size() > 0){
							model.addAttribute("size", userWeigthList.size());
							//拼接表格要动态显示的数据
							StringBuffer buffer = new StringBuffer();
							buffer.append("[");
							for(int i = userWeigthList.size()-1;i>=0;i--){
								//默认以孕周查看体重数据
								if(StringUtils.isNotBlank(params.getShowType())){//以天或者月查看数据
									//拼装要显示的数据
									String time = TimeUtils.getTimeStampNumberFormat(userWeigthList.get(i).getAdd_time(),"yyyy:MM:dd");
									if(i == 0){
										buffer.append("["+time+":"+userWeigthList.get(i).getAverage_value()+"]");
									}else{
										buffer.append("["+time+":"+userWeigthList.get(i).getAverage_value()+"],");
									}
								}else{//以孕周查看体重数据
									//计算孕周和孕天
									String addTimeStr = TimeUtils.getTimeStampNumberFormat(userWeigthList.get(i).getAdd_time(),"yyyy-MM-dd HH:mm:ss");
									Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
									int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
									//userWeigthList.get(i).setTest_week(arr[0]);
									//组装数据
									if(i == 0){
										buffer.append("["+(arr[0]*7+arr[1])+","+
												userWeigthList.get(i).getAverage_value()+"]");
									}else{
										buffer.append("["+(arr[0]*7+arr[1])+","+
												userWeigthList.get(i).getAverage_value()+"],");
									}
								}
							}
							buffer.append("]");
							model.addAttribute("buffer", buffer);
							model.addAttribute("xData", xData);
							model.addAttribute("totalData", totalData);
						}
					
					}
					model.addAttribute("viewVO", viewVO);
					model.addAttribute("page", page);
				}
				model.addAttribute("params", params);
//				return "syncdata/monitorWeight";
//				return "syncdata/jcsj";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("monitorWeight error "+e.getMessage());
		} finally{
			if("jh".equals(params.getCssType())){
				return "syncdata/monitorWeight";
			}else{
				return "syncdata/jcsj";
			}
		}
		
	}
	
	/**胎心监测数据**/
	@SuppressWarnings("finally")
	@RequestMapping(value="/heartrate")
	public String monitorHeartrate(Model model,@ModelAttribute Params params,Page<UserHeartrateRecord> page){
		try {
			setArchiveBaseInfo(model,params.getArchiveBaseId(),params.getArchiveId());
			model.addAttribute("type", "heartrate");
			String userId = params.getUserId();
			if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId) ){
				UserExtraInfo userExtra = userExtraInfoService.findByUserId(Integer.valueOf(userId));
				if(null != userExtra ){
					UserInfo userInfo = userExtra.getUserInfo();
					UserHeartrateRecord heart = new UserHeartrateRecord();
					heart.setUserInfo(userInfo);
					int pageNum = 1;
					if(StringUtils.isNotBlank(params.getPageNum()) && StringUtils.isNumeric(params.getPageNum())){//带分页查询
						pageNum = Integer.valueOf(params.getPageNum());
					}else{
						params.setPageNum(pageNum+"");
					}
					page.setPageNo(pageNum);
					//查询出改用户的所有胎心记录
					Page<UserHeartrateRecord> pageList = userHeartrateRecordService.findByConditionWithPage(heart,page,params);
					List<UserHeartrateRecord> heartRecordsList = pageList.getResult();
					if(heartRecordsList.size()>0){
						logger.info("heartRecordsList size:"+heartRecordsList.size());
						Integer heartId = 0;
						String 	heartJson = "";
						String 	fetalMoveValue = "";
						String 	fetalMoveData = "",uterusData = "";
						List<UserViewVO> voList = new ArrayList<UserViewVO>(); //展示在table中的数据
						for(int i=0;i<heartRecordsList.size();i++){
							//基础的展示数据
							UserViewVO vo = new UserViewVO();
							//设置胎心记录id
							vo.setId(heartRecordsList.get(i).getId()); 
							//计算出孕周孕天
							int[] arr ={0,0};
							String addTimeStr = TimeUtils.getTimeStampNumberFormat(heartRecordsList.get(i).getAdd_time(),"yyyy-MM-dd HH:mm:ss");
							Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
							Date	pregnantDate = userInfo.getExpectedDateOfConfinement(); ///获取用户的预产期
							if(null != userInfo){
							  arr = TimeUtils.getPregnantWeekAndDay(addTime, pregnantDate);
							}
							vo.setPregnantWeek(arr[0]);//孕周
							vo.setPregnantDay(arr[1]);  //孕天
							//监测日期
							vo.setMonitorTime(TimeUtils.formatStringDate(addTimeStr, "yyyy-MM-dd"));
							vo.setValue(heartRecordsList.get(i).getAverage_rate());
							vo.setStartTime(addTimeStr);
							int time = heartRecordsList.get(i).getTestTime();
							vo.setTestTimeLength(time+"");
							vo.setTestTime(time/3600+"时"+time%3600/60+"分"+time%3600%60+"秒"); 
							vo.setName(userExtra.getRealName());   //姓名
							vo.setAge(userExtra.getAge()+""); //年龄
							vo.setPhone(userExtra.getContactPhone()); //电话
							//计算当前的孕周与孕天
							vo.setCurrentPregnant(setCurrentPregnant(userInfo));
							//胎心的添加时间
							if(null != heartRecordsList.get(i).getAdd_time()){
								String heartrateTime = TimeUtils.getTimeStampNumberFormat(heartRecordsList.get(i).getAdd_time());
								vo.setAddTime(heartrateTime);
							}
							voList.add(vo);
							
							if(i == 0){
								params.setStartPrintTime(TimeUtils.getTimeStampNumberFormat(heartRecordsList.get(0).getAdd_time(),"yyyy-MM-dd HH:mm:ss"));
								params.setMonitorTimeLength(heartRecordsList.get(0).getTestTime()+"");
								String addTimeStr1 = TimeUtils.getTimeStampNumberFormat(heartRecordsList.get(0).getAdd_time(),"yyyy-MM-dd HH:mm:ss");
								Date addTime1 = TimeUtils.convertDate(addTimeStr1, "yyyy-MM-dd HH:mm:ss");
								int[] arr1 = TimeUtils.getPregnantWeekAndDay(addTime1, pregnantDate);
								params.setMonitorPregnant(arr1[0]+"周"+arr1[1]+"天");
								Integer length = heartRecordsList.get(0).getTestTime();
								String testLength = length/3600+"小时"+length%3600/60+"分"+length%3600%60+"秒";
								params.setTestLength(testLength);
								params.setAddDate(TimeUtils.getTimeStampNumberFormat(heartRecordsList.get(0).getAdd_time(),"yyyy-MM-dd"));
								params.setName(userExtra.getRealName());
								params.setAge(userExtra.getAge()+"");
								params.setPhone(userExtra.getContactPhone());
								params.setCurrentPregnant(setCurrentPregnant(userInfo));
								if(null==params.getId()||"".equals(params.getId())){
									params.setId(heartRecordsList.get(0).getId()+"");
								}
							}
							//处理数据
							heartId = heartRecordsList.get(i).getId();
							String path = Consts.BASE_FILE_URL;
							model.addAttribute("FILE_DOMAIN", path);
							if(params.getId() != null && params.getId() != ""){
								int hid = Integer.valueOf(params.getId()) ;
								if(heartId == hid){
									/**解析胎心json文件*/
									heartJson = JsonUtils.getJsonFileStr(path+heartRecordsList.get(i).getRecord_files());
									/**处理胎动数据*/
									fetalMoveValue = heartRecordsList.get(i).getFetal_move_files();
									model.addAttribute("userHeart", heartRecordsList.get(i));
									if(StringUtils.isNotBlank(fetalMoveValue)){
										fetalMoveValue = fetalMoveValue.substring(1, fetalMoveValue.length()-1);
									}
									fetalMoveData = JsonUtils.getFetalMoveData(fetalMoveValue);
									if(StringUtils.isNotBlank(fetalMoveData)){
										fetalMoveData = fetalMoveData.substring(1, fetalMoveData.length()-1);
									}
								}
								
							}
//							if(heartId == id){
//								/**解析胎心json文件*/
//								heartJson = JsonUtils.getJsonFileStr(path+heartRecordsList.get(i).getRecord_files());
//								/**处理胎动数据*/
//								fetalMoveValue = heartRecordsList.get(i).getFetal_move_files();
//								model.addAttribute("userHeart", heartRecordsList.get(i));
//								if(StringUtils.isNotBlank(fetalMoveValue)){
//									fetalMoveValue = fetalMoveValue.substring(1, fetalMoveValue.length()-1);
//								}
//								fetalMoveData = JsonUtils.getFetalMoveData(fetalMoveValue);
//								if(StringUtils.isNotBlank(fetalMoveData)){
//									fetalMoveData = fetalMoveData.substring(1, fetalMoveData.length()-1);
//								}
//								/**处理宫缩数据*/
////							if(StringUtils.isNotBlank(heartRecordsList.get(i).getUterusRecord())){
////								uterusData = JsonUtils.getJsonFileStr(path+heartRecordsList.get(i).get);
////							}
//							}
							
							if(i==0){
								/**解析胎心json文件*/
								heartJson = JsonUtils.getJsonFileStr(path+heartRecordsList.get(0).getRecord_files());
								/**处理胎动数据*/
								fetalMoveValue = heartRecordsList.get(0).getFetal_move_value();
								model.addAttribute("userHeart", heartRecordsList.get(0));
								if(StringUtils.isNotBlank(fetalMoveValue)){
									fetalMoveValue = fetalMoveValue.substring(1, fetalMoveValue.length()-1);
								}
								fetalMoveData = JsonUtils.getFetalMoveData(fetalMoveValue);
								if(StringUtils.isNotBlank(fetalMoveData)){
									fetalMoveData = fetalMoveData.substring(1, fetalMoveData.length()-1);
								}
								/**处理宫缩数据*/
	//							if(StringUtils.isNotBlank(heartRecordsList.get(0).getUterusRecord())){
	//								uterusData = JsonUtils.getJsonFileStr(path+heartRecordsList.get(0).getUterusRecord());
	//							}
							}
//							
						}
						//将监测要动态显示的数据返回到前台
						model.addAttribute("heartJson", heartJson);
						model.addAttribute("fetalMoveJson", fetalMoveData);
						model.addAttribute("uterusData", uterusData);
						model.addAttribute("heartList", voList);
						model.addAttribute("page", page);
					}
				}
				model.addAttribute("params", params);
//				return "syncdata/jcsj";
//				return "syncdata/monitorHeartrate";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("monitorHeartrate "+e.getMessage());
		}finally{
			if("jh".equals(params.getCssType())){
				return "syncdata/monitorHeartrate";
			}else{
				return "syncdata/jcsj";
			}
		}
		
	}
	
	/**血糖数据*/
	@SuppressWarnings("finally")
	@RequestMapping(value = "/sugar")
	public String monitorSugar(Model model,@ModelAttribute Params params,Page<UserSugarRecord> page){
		try {
			setArchiveBaseInfo(model,params.getArchiveBaseId(),params.getArchiveId());
			model.addAttribute("type", "sugar");
			String userId = params.getUserId();
			if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId)){
				UserExtraInfo userExtra = userExtraInfoService.findByUserId(Integer.valueOf(userId)); //通过userId查询UserExtraInfo,userInfo
				if(null != userExtra){
					//通过用户id获取用户信息
					UserInfo user = userInfoService.findUserInfoById(Integer.valueOf(userId));
					//获取预产期
					UserSugarRecord sugar = new UserSugarRecord();
					sugar.setUserInfo(user);
					 //获得预产期
					Date expectedDate = user.getExpectedDateOfConfinement();
					UserViewVO viewVO = new UserViewVO();
					if(null != expectedDate){//计算当前的孕周与孕天
						int[] pregnant = TimeUtils.getPregnantWeekAndDay(new Date(), expectedDate);
						String expected = TimeUtils.converStringDate(expectedDate, "yyyy-MM-dd");
						viewVO.setPregnantWeek(pregnant[0]);
						viewVO.setPregnantDay(pregnant[1]);
						viewVO.setExpectedDate(expected);
					}//组装vo
					viewVO.setName(userExtra.getRealName());
					viewVO.setPhone(user.getMobile());
					viewVO.setAge(userExtra.getAge()+"");
					//设置测量的总次数
					int count = userSugarRecordService.getCount(Integer.valueOf(userId));
					viewVO.setTotalTestCount(count);
					model.addAttribute("viewVO", viewVO);
					//默认查询当日
					int pageNum = 1;
					String addDate = TimeUtils.converStringDate(new Date(), "yyyy-MM-dd");
					if(StringUtils.isNotBlank(params.getPageNum()) && StringUtils.isNumeric(params.getPageNum())){//带分页查询
						pageNum = Integer.valueOf(params.getPageNum());
					}
					if(StringUtils.isNotBlank(params.getAddDate())){
						addDate = params.getAddDate();
					}
					page.setPageNo(pageNum);
					Page<UserSugarRecord> pageList = userSugarRecordService.findByConditionWithPage(sugar,page,params);
					List<UserSugarRecord> userSugarList =pageList.getResult();
					String currentDate = "";
					if(userSugarList.size() > 0){
						currentDate = TimeUtils.getTimeStampNumberFormat(userSugarList.get(0).getAdd_time(), "yyyy-MM-dd");
						if(currentDate.equals(addDate)){//说明当天有数据
							Date addTime = TimeUtils.convertDate(currentDate, "yyyy-MM-dd");
							int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
							params.setMonitorPregnant(arr[0]+"周"+arr[1]+"天");
							params.setAddDate(addDate);
						}
						List<UserViewVO> voList = new ArrayList<UserViewVO>();
						String compareTime = "";
						String compareNextTime = "";
						ArrayList<UserSugarRecord> currentList = new ArrayList<UserSugarRecord>();
						ArrayList<UserSugarRecord> sameDayList = new ArrayList<UserSugarRecord>();
						//血糖数据处理
						for(int i=0;i<userSugarList.size();i++){
							compareTime = TimeUtils.getTimeStampNumberFormat(userSugarList.get(i).getAdd_time(), "yyyy-MM-dd");
							if(i == userSugarList.size() -1){
								compareNextTime = "";
							}else{
								compareNextTime = TimeUtils.getTimeStampNumberFormat(userSugarList.get(i+1).getAdd_time(), "yyyy-MM-dd");
							}
							if(compareTime.equals(addDate)){//说明该条记录是当前要展示的记录
								currentList.add(userSugarList.get(i));
								currentDate = addDate;
							}
							sameDayList.add(userSugarList.get(i));
							if(!compareTime.equals(compareNextTime)){//说明这两条记录不是同一天的
								//开始组装vo的血糖数据
								UserViewVO vo = new UserViewVO();
								vo.setName(userExtra.getRealName());
								//计算孕周和孕天
								String addTimeStr = TimeUtils.getTimeStampNumberFormat(userSugarList.get(i).getAdd_time(),"yyyy-MM-dd HH:mm:ss");
								Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
								int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
								vo.setPregnantWeek(arr[0]);
								vo.setPregnantDay(arr[1]);
								vo.setMonitorTime(TimeUtils.formatStringDate(addTimeStr,"yyyy-MM-dd"));
								for(int j=0;j<sameDayList.size();j++){
									Integer state = userSugarList.get(j).getTest_time_state();
									if(state == 0){//早餐前
										vo.setBeforeBreakfast(sameDayList.get(j).getAverage_value());
									}else if(state == 1){//早餐后
										vo.setAfterBreakfast(sameDayList.get(j).getAverage_value());
									}else if(state == 2){//午餐前
										vo.setBeforeLunch(sameDayList.get(j).getAverage_value());
									}else if(state == 3){//午餐后
										vo.setAfterLunch(sameDayList.get(j).getAverage_value());
									}else if(state == 4){//晚餐前
										vo.setBeforeDinner(sameDayList.get(j).getAverage_value());
									}else if(state == 5){//晚餐后
										vo.setAfterDinner(sameDayList.get(j).getAverage_value());
									}else if(state == 6){//睡前
										vo.setBeforeSleep(sameDayList.get(j).getAverage_value());
									}
								}
								voList.add(vo);
								//新建个sameDayList -- 避免数据重复
								sameDayList = new ArrayList<UserSugarRecord>();
							}
						}
						page.setTotalCount(voList.size());
						model.addAttribute("sugarList", voList);
						model.addAttribute("page", page);
						//拼接表格要动态显示的数据
						StringBuffer buffer = new StringBuffer();
						for(int i =currentList.size()-1; i>=0;i--){
							String time = TimeUtils.getTimeStampNumberFormat(currentList.get(i).getAdd_time(),"HH.mm");
							//将第一个0去掉
							time = removeFirstZero(time);
							if(i == 0){
								buffer.append("["+time+","+currentList.get(i).getAverage_value()+","+currentList.get(i).getTest_time_state()+"]");
							}else{
								buffer.append("["+time+","+currentList.get(i).getAverage_value()+","+currentList.get(i).getTest_time_state()+"]:");
							}
						}
						model.addAttribute("buffer", buffer);
					}
					model.addAttribute("currentDate", currentDate);
				}
			}
			model.addAttribute("params", params);
//			return "syncdata/jcsj";
//			return "syncdata/monitorSugar";
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if("jh".equals(params.getCssType())){
				return "syncdata/monitorSugar";
			}else{
				return "syncdata/jcsj";
			}
		}
		
	}
	
	/**血压数据*/
	@SuppressWarnings("finally")
	@RequestMapping(value = "/pressure")
	public String monitorPressure(Model model,@ModelAttribute Params params,Page<UserPressureRecord> page){
		try {
			setArchiveBaseInfo(model,params.getArchiveBaseId(),params.getArchiveId());
			model.addAttribute("type", "pressure");
			String userId = params.getUserId();
			if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId)){
				UserExtraInfo userExtra = userExtraInfoService.findByUserId(Integer.valueOf(userId)); //通过userId查询UserExtraInfo,userInfo
				UserInfo user = userInfoService.findUserInfoById(Integer.valueOf(userId));
				UserPressureRecord  pressure  = new UserPressureRecord();
				pressure.setUserInfo(user);
				Date expectedDate = user.getExpectedDateOfConfinement();//获取用户预产期
				UserViewVO viewVO = setCommonViewVO(userExtra,user);
				//获取总记录数
				int countNum = userPressureRecordService.getCount(pressure);
				viewVO.setTotalTestCount(countNum);
				model.addAttribute("viewVO", viewVO);
				//获取孕妇在整个保健期的具体日期
				StringBuffer totalData = new StringBuffer();
				totalData = getTotalData(totalData, params.getShowType(), expectedDate, userExtra.getBabyBirthday());
				//处理相关查询条件
				int pageNum = 0;
				Page<UserPressureRecord> pageList = new Page<UserPressureRecord>();
				List<UserPressureRecord> userPressureList = null;
				if(StringUtils.isNotBlank(params.getPageNum()) && StringUtils.isNumeric(params.getPageNum())){//分页
					pageNum = Integer.valueOf(params.getPageNum());
					page.setPageNo(pageNum);
					pageList = userPressureRecordService.findByConditionWithPage(pressure,page,params);//通过用户id查询用户血压监测信息
					userPressureList = pageList.getResult();
					if(userPressureList.size() > 0){
						//获取血压历史数据
						List<UserViewVO> voList = new ArrayList<UserViewVO>();
						for(int i=0;i<userPressureList.size();i++){
							UserViewVO vo = new UserViewVO();
							vo.setName(userExtra.getRealName());
							//计算孕周和孕天
							String addTimeStr = TimeUtils.getTimeStampNumberFormat(userPressureList.get(i).getAdd_time(),"yyyy-MM-dd HH:mm:ss");
							Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
							int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
							vo.setPregnantWeek(arr[0]);
							vo.setPregnantDay(arr[1]);
							//监测日期
							vo.setMonitorTime(TimeUtils.formatStringDate(addTimeStr, "yyyy-MM-dd"));
							//收缩压和舒张压
							vo.setSystolicPressure(userPressureList.get(i).getPressure_height());
							vo.setDiastoliPressure(userPressureList.get(i).getPressure_low());
							voList.add(vo);
						}
						model.addAttribute("pressureList", voList);
					}
					model.addAttribute("page", page);
				}else{//不分页
					userPressureList = userPressureRecordService.findByCondition(pressure,params);
					logger.info("userPressureList size"+userPressureList.size());
					if(userPressureList.size() > 0 ){
						model.addAttribute("size", userPressureList.size());
						//拼接表格要动态显示的数据
						StringBuffer low = new StringBuffer();
						StringBuffer high = new StringBuffer();
						low.append("[");
						high.append("[");
						for(int i = userPressureList.size()-1;i>=0;i--){
							//默认以孕周查看血压数据
							if(StringUtils.isNotBlank(params.getShowType())){//以天或者月查看数据
								//拼装要显示的数据
								String time = TimeUtils.getTimeStampNumberFormat(userPressureList.get(i).getAdd_time(),"yyyy:MM:dd");
								if(i == 0){
									low.append("["+time+":"+userPressureList.get(i).getPressure_low()+"]");
									high.append("["+time+":"+userPressureList.get(i).getPressure_height()+"]");
								}else{
									low.append("["+time+":"+userPressureList.get(i).getPressure_low()+"],");
									high.append("["+time+":"+userPressureList.get(i).getPressure_height()+"],");
								}
							}else{//以孕周查看体重数据
								//计算孕周和孕天
								String addTimeStr = TimeUtils.getTimeStampNumberFormat(userPressureList.get(i).getAdd_time(),"yyyy-MM-dd HH:mm:ss");
								Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
								int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
								//组装数据
								if(i == 0){
									low.append("["+(arr[0]*7+arr[1])+","+userPressureList.get(i).getPressure_low()+"]");
									high.append("["+(arr[0]*7+arr[1])+","+userPressureList.get(i).getPressure_height()+"]");
								}else{
									low.append("["+(arr[0]*7+arr[1])+","+userPressureList.get(i).getPressure_low()+"],");
									high.append("["+(arr[0]*7+arr[1])+","+userPressureList.get(i).getPressure_height()+"],");
								}
							}
						}
						low.append("]");
						high.append("]");
						model.addAttribute("low", low);
						model.addAttribute("high", high);
					}
				}
			}
			model.addAttribute("params", params);
//			return "syncdata/jcsj";
//			return "syncdata/monitorPressure";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("monitorPressure error "+e.getMessage());
		} finally{
				if("jh".equals(params.getCssType())){
					return "syncdata/monitorPressure";
				}else{
					return "syncdata/jcsj";
				}
			}
		}
		
	
	/**血氧数据*/
	@SuppressWarnings("finally")
	@RequestMapping(value = "/oxygen")
	public String monitorOxygen(Model model,@ModelAttribute Params params,Page<UserOxygenRecord> page){
		try {
			setArchiveBaseInfo(model,params.getArchiveBaseId(),params.getArchiveId());
			model.addAttribute("type", "oxygen");
			String userId = params.getUserId();
			if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId)){
				UserExtraInfo userExtra = userExtraInfoService.findByUserId(Integer.valueOf(userId));
				if(null != userExtra){
					UserInfo user = userInfoService.findUserInfoById(Integer.valueOf(userId)); //获取userInfo
					//获取预产期
					Date expectedDate = user.getExpectedDateOfConfinement();
					//通过用户查血氧监护信息
					UserOxygenRecord oxygen = new UserOxygenRecord();
					oxygen.setUserInfo(user);
					UserViewVO viewVO = setCommonViewVO(userExtra,user);
					//获取总记录数
					int countNum = userOxygenRecordService.getCount(oxygen);
					viewVO.setTotalTestCount(countNum);
					model.addAttribute("viewVO", viewVO);
					int pageNum = 1,id=0;
					if(StringUtils.isNotBlank(params.getPageNum()) && StringUtils.isNumeric(params.getPageNum())){//带分页查询
						pageNum = Integer.valueOf(params.getPageNum());
					}
					if(StringUtils.isNotBlank(params.getId()) && StringUtils.isNumeric(params.getId())){//带分页查询
						id = Integer.valueOf(params.getId());
					}
					page.setPageNo(pageNum);
					Page<UserOxygenRecord> pageList = userOxygenRecordService.findByConditionWithPage(oxygen, page, params); //分页查询
					List<UserOxygenRecord> userOxygenList = pageList.getResult();
					String pulseJson = "",oxyJson="";
					if(userOxygenList.size() > 0 ){
						if(id == 0){
							params.setAddDate(TimeUtils.getTimeStampNumberFormat(userOxygenList.get(0).getTest_time(), "yyyy-MM-dd"));
							params.setId(userOxygenList.get(0).getId()+"");
							String addTimeStr = TimeUtils.getTimeStampNumberFormat(userOxygenList.get(0).getTest_time(),"yyyy-MM-dd HH:mm:ss");
							Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
							int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
							params.setMonitorPregnant(arr[0]+"周"+arr[1]+"天");
							Integer length = userOxygenList.get(0).getTest_time_length();
							String monitorTimeLength = length/3600+"小时"+length%3600/60+"分"+length%3600%60+"秒";
							params.setMonitorTimeLength(monitorTimeLength);
							params.setAverageOxygen(userOxygenList.get(0).getAverage_oxygen()+"");
							params.setAveragePulse(userOxygenList.get(0).getAverage_pulse()+"");
						}
						//获取血氧历史数据
						List<UserViewVO> voList = new ArrayList<UserViewVO>();
						for(int i=0;i<userOxygenList.size();i++){
							UserViewVO vo = new UserViewVO();
							vo.setName(userExtra.getRealName());
							//计算孕周和孕天
							String addTimeStr = TimeUtils.getTimeStampNumberFormat(userOxygenList.get(i).getTest_time(),"yyyy-MM-dd HH:mm:ss");
							Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
							int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
							vo.setPregnantWeek(arr[0]);
							vo.setPregnantDay(arr[1]);
							vo.setMonitorTime(TimeUtils.formatStringDate(addTimeStr,"yyyy-MM-dd"));
							Integer length = userOxygenList.get(i).getTest_time_length();
							String testTimeLength = length/3600+"小时"+length%3600/60+"分"+length%3600%60+"秒";
							vo.setTestTimeLength(testTimeLength);
							vo.setAverageOxygen(userOxygenList.get(i).getAverage_oxygen());
							vo.setAveragePulse(userOxygenList.get(i).getAverage_pulse());
							vo.setId(userOxygenList.get(i).getId());
							voList.add(vo);
							int oxygenId = userOxygenList.get(i).getId();
							/**读取心率文件**/
							String path = Consts.BASE_FILE_URL;
							if(oxygenId == id){
								/**解析心率json文件*/
								pulseJson = JsonUtils.getJsonFileStr(path+userOxygenList.get(i).getPulse_file());
								String[] pulseAttr = pulseJson.substring(1, pulseJson.length()-3).split(",");
								//获取心率测试时长的json String
								String pulseTime = JsonUtils.getTestJsonLength(pulseAttr.length,false);
								String[] pulseTimeAttr = pulseTime.substring(1, pulseTime.length()-1).split(",");
								//将2个String[]合并成json 2个String[]长度必须一致
								pulseJson = JsonUtils.getMixJson(pulseTimeAttr, pulseAttr);
								/**解析血氧json文件*/
								oxyJson = JsonUtils.getJsonFileStr(path+userOxygenList.get(i).getOxygen_file());
								String[] oxyAttr = oxyJson.substring(1, oxyJson.length()-3).split(",");
								//获取血氧测试时长的json String
								String oxyTime = JsonUtils.getTestJsonLength(oxyAttr.length,false);
								String[] oxyTimeAttr = oxyTime.substring(1, oxyTime.length()-1).split(",");
								//将2个String[]合并成json 2个String[]长度必须一致
								oxyJson = JsonUtils.getMixJson(oxyTimeAttr, oxyAttr);
							}else if(i==0){
								/**解析心率json文件*/
								pulseJson = JsonUtils.getJsonFileStr(path+userOxygenList.get(0).getPulse_file());
								String[] pulseAttr = pulseJson.substring(1, pulseJson.length()-3).split(",");
								//获取心率测试时长的json String
								String pulseTime = JsonUtils.getTestJsonLength(pulseAttr.length,false);
								String[] pulseTimeAttr = pulseTime.substring(1, pulseTime.length()-1).split(",");
								//将2个String[]合并成json 2个String[]长度必须一致
								pulseJson = JsonUtils.getMixJson(pulseTimeAttr, pulseAttr);
								/**解析血氧json文件*/
								oxyJson = JsonUtils.getJsonFileStr(path+userOxygenList.get(0).getOxygen_file());
								String[] oxyAttr = oxyJson.substring(1, oxyJson.length()-3).split(",");
								//获取血氧测试时长的json String
								String oxyTime = JsonUtils.getTestJsonLength(oxyAttr.length,false);
								String[] oxyTimeAttr = oxyTime.substring(1, oxyTime.length()-1).split(",");
								//将2个String[]合并成json 2个String[]长度必须一致
								oxyJson = JsonUtils.getMixJson(oxyTimeAttr, oxyAttr);
							}
						}
						//将要动态显示的监测数据返回到页面
						model.addAttribute("pulseJson", pulseJson);
						model.addAttribute("oxyJson", oxyJson);
						model.addAttribute("oxygenList", voList);
						model.addAttribute("page", page);
					}
				}
				model.addAttribute("params", params);
//				return "syncdata/jcsj";
//				return "syncdata/monitorOxygen";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("monitorOxygen error"+e.getMessage());
		}finally{
			if("jh".equals(params.getCssType())){
				return "syncdata/monitorOxygen";
			}else{
				return "syncdata/jcsj";
			}
		}
		
	}
	
	/**血脂数据*/
	@SuppressWarnings("finally")
	@RequestMapping(value = "/bloodfat")
	public String monitorBloodfat(Model model,@ModelAttribute Params params,Page<UserBloodFatRecord> page){
		try {
			setArchiveBaseInfo(model,params.getArchiveBaseId(),params.getArchiveId());
			model.addAttribute("type","bloodfat");
			String userId = params.getUserId();
			if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId)){
				UserExtraInfo userExtra = userExtraInfoService.findByUserId(Integer.valueOf(userId));
				if(userExtra != null){
					UserInfo user = userInfoService.findUserInfoById(Integer.valueOf(userId));
					UserBloodFatRecord  blood  = new UserBloodFatRecord();
					blood.setUserInfo(user);
					Date expectedDate = user.getExpectedDateOfConfinement();//获取用户预产期
					UserViewVO viewVO = setCommonViewVO(userExtra,user);
					//获取总记录数
					int countNum = userBloodFatRecordService.getCount(blood);
					viewVO.setTotalTestCount(countNum);
					model.addAttribute("viewVO", viewVO);
					int pageNum = 0;
					Page<UserBloodFatRecord> pageList = new Page<UserBloodFatRecord>();
					List<UserBloodFatRecord> userBloodList = null;
					if(StringUtils.isNotBlank(params.getPageNum()) && StringUtils.isNumeric(params.getPageNum())){//分页查询
						pageNum = Integer.valueOf(params.getPageNum());
						pageList = userBloodFatRecordService.findByConditionWithPage(blood,page,params);
						pageList.setPageNo(pageNum);
						userBloodList = pageList.getResult();
						if(userBloodList.size() >  0){
							//记录体重历史数据
							for(int i=0;i<userBloodList.size();i++){
								//计算孕周和孕天
								String addTimeStr = TimeUtils.getTimeStampNumberFormat(userBloodList.get(i).getAdd_time(),"yyyy-MM-dd HH:mm:ss");
								Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
								int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
								userBloodList.get(i).setPregnant(arr[0]+"周"+arr[1]+"天");
							}
						}
						model.addAttribute("bloodList", userBloodList);
					}else{//不是分页查询
						userBloodList = userBloodFatRecordService.findByUserBloodFatCondition(blood, params);
						if(userBloodList.size() > 0){
							model.addAttribute("size", userBloodList.size());
							//拼接表格要动态显示的数据
							StringBuffer cholData = new StringBuffer(),trigData = new StringBuffer(),hdlData = new StringBuffer();
							StringBuffer ldlData = new StringBuffer(),cholHdlData = new StringBuffer();
							cholData.append("[");
							trigData.append("[");
							hdlData.append("[");
							ldlData.append("[");
							cholHdlData.append("[");
							for(int i = userBloodList.size()-1;i>=0;i--){
								if(StringUtils.isNotBlank(params.getShowType())){//以天或者月查看数据
									//拼装要显示的数据
									String time = TimeUtils.getTimeStampNumberFormat(userBloodList.get(i).getAdd_time(),"yyyy:MM:dd");
									if(i == 0){
										cholData.append("["+time+":"+userBloodList.get(i).getChol()+"]");
										trigData.append("["+time+":"+userBloodList.get(i).getTrig()+"]");
										hdlData.append("["+time+":"+userBloodList.get(i).getHdl()+"]");
										ldlData.append("["+time+":"+userBloodList.get(i).getLdl()+"]");
										cholHdlData.append("["+time+":"+userBloodList.get(i).getCholHdl()+"]");
									}else{
										cholData.append("["+time+":"+userBloodList.get(i).getChol()+"],");
										trigData.append("["+time+":"+userBloodList.get(i).getTrig()+"],");
										hdlData.append("["+time+":"+userBloodList.get(i).getHdl()+"],");
										ldlData.append("["+time+":"+userBloodList.get(i).getLdl()+"],");
										cholHdlData.append("["+time+":"+userBloodList.get(i).getCholHdl()+"],");
									}
								}else{//以孕周查看体重数据
									//计算孕周和孕天
									String addTimeStr = TimeUtils.getTimeStampNumberFormat(userBloodList.get(i).getAdd_time(),"yyyy-MM-dd HH:mm:ss");
									Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
									int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
									userBloodList.get(i).setPregnant(arr[0]+"."+arr[1]);
									//组装数据
									if(i == 0){
										cholData.append("["+userBloodList.get(i).getPregnant()+","+userBloodList.get(i).getChol()+"]");
										trigData.append("["+userBloodList.get(i).getPregnant()+","+userBloodList.get(i).getTrig()+"]");
										hdlData.append("["+userBloodList.get(i).getPregnant()+","+userBloodList.get(i).getHdl()+"]");
										ldlData.append("["+userBloodList.get(i).getPregnant()+","+userBloodList.get(i).getLdl()+"]");
										cholHdlData.append("["+userBloodList.get(i).getPregnant()+","+userBloodList.get(i).getCholHdl()+"]");
									}else{
										cholData.append("["+userBloodList.get(i).getPregnant()+","+userBloodList.get(i).getChol()+"],");
										trigData.append("["+userBloodList.get(i).getPregnant()+","+userBloodList.get(i).getTrig()+"],");
										hdlData.append("["+userBloodList.get(i).getPregnant()+","+userBloodList.get(i).getHdl()+"],");
										ldlData.append("["+userBloodList.get(i).getPregnant()+","+userBloodList.get(i).getLdl()+"],");
										cholHdlData.append("["+userBloodList.get(i).getPregnant()+","+userBloodList.get(i).getCholHdl()+"],");
									}
								}
							}
							cholData.append("]");
							trigData.append("]");
							hdlData.append("]");
							ldlData.append("]");
							cholHdlData.append("]");
							model.addAttribute("cholData", cholData);
							model.addAttribute("trigData", trigData);
							model.addAttribute("hdlData", hdlData);
							model.addAttribute("ldlData", ldlData);
							model.addAttribute("cholHdlData", cholHdlData);
						}
					}
				}
				model.addAttribute("params", params);
//				return "syncdata/jcsj";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("monitorBloodfat error"+e.getMessage());
		}finally{
			if("jh".equals(params.getCssType())){
				return "syncdata/monitorBloodfat";
			}else{
				return "syncdata/jcsj";
			}
		}
	}
	
	/**体温数据*/
	@SuppressWarnings("finally")
	@RequestMapping(value = "/temperature")
	public String monitorTemperature(Model model,@ModelAttribute Params params,Page<UserTemperatureRecord> page){
		try {
			setArchiveBaseInfo(model,params.getArchiveBaseId(),params.getArchiveId());
			model.addAttribute("type", "temperature");
			String userId = params.getUserId();
			if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId)){
				UserExtraInfo userExtra = userExtraInfoService.findByUserId(Integer.valueOf(userId));
				if(null != userExtra){
					UserInfo user = userInfoService.findUserInfoById(Integer.valueOf(userId));
					UserTemperatureRecord  temperature  = new UserTemperatureRecord();
					temperature.setUserInfo(user);
					Date expectedDate = user.getExpectedDateOfConfinement();//获取用户预产期
					UserViewVO viewVO = setCommonViewVO(userExtra,user);
					//获取总记录数
					int countNum = userTemperatureRecordService.getCount(temperature);
					viewVO.setTotalTestCount(countNum);
					model.addAttribute("viewVO", viewVO);
					//处理相关查询条件
					int pageNum = 1;
					String addDate = TimeUtils.converStringDate(new Date(), "yyyy-MM-dd");//默认查询当日
					if(StringUtils.isNotBlank(params.getPageNum()) && StringUtils.isNumeric(params.getPageNum())){
						pageNum = Integer.valueOf(params.getPageNum());
					}
					if(StringUtils.isNotBlank(params.getAddDate())){
						addDate = params.getAddDate();
					}
					//分页查询
					page.setPageNo(pageNum);
					Page<UserTemperatureRecord> pageList = userTemperatureRecordService.getUserTemperatureWithPage(page,temperature,params);
					List<UserTemperatureRecord> userTemperatureList = pageList.getResult();
					String currentDate = "";
					if(userTemperatureList.size() > 0 ){
						currentDate = TimeUtils.getTimeStampNumberFormat(userTemperatureList.get(0).getAdd_time(), "yyyy-MM-dd");
						if(currentDate.equals(addDate)){//说明当天有数据
							Date addTime = TimeUtils.convertDate(currentDate, "yyyy-MM-dd");
							int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
							params.setMonitorPregnant(arr[0]+"周"+arr[1]+"天");
							params.setAddDate(addDate);
						}
						List<UserViewVO> voList = new ArrayList<UserViewVO>();
						String compareTime = "";
						String compareNextTime = "";
						ArrayList<UserTemperatureRecord> currentList = new ArrayList<UserTemperatureRecord>();
						ArrayList<UserTemperatureRecord> sameDayList = new ArrayList<UserTemperatureRecord>();
						//血糖数据处理
						for(int i=0;i<userTemperatureList.size();i++){
							compareTime = TimeUtils.getTimeStampNumberFormat(userTemperatureList.get(i).getAdd_time(), "yyyy-MM-dd");
							if(i == userTemperatureList.size() -1){
								compareNextTime = "";
							}else{
								compareNextTime = TimeUtils.getTimeStampNumberFormat(userTemperatureList.get(i+1).getAdd_time(), "yyyy-MM-dd");
							}
							if(compareTime.equals(addDate)){//说明该条记录是当前要展示的记录
								currentList.add(userTemperatureList.get(i));
								currentDate = addDate;
							}
							sameDayList.add(userTemperatureList.get(i));
							if(!compareTime.equals(compareNextTime)){//说明这两条记录不是同一天的
								//开始组装vo的体温数据
								UserViewVO vo = new UserViewVO();
								vo.setName(userExtra.getRealName());
								//计算孕周和孕天
								String addTimeStr = TimeUtils.getTimeStampNumberFormat(userTemperatureList.get(i).getAdd_time(),"yyyy-MM-dd HH:mm:ss");
								Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
								int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
								vo.setPregnantWeek(arr[0]);
								vo.setPregnantDay(arr[1]);
								vo.setMonitorTime(TimeUtils.formatStringDate(addTimeStr,"yyyy-MM-dd"));
								vo.setMonitorCount(sameDayList.size());
								double averageValue = 0;
								for(int j=0;j<sameDayList.size();j++){
									//计算平均体温
									averageValue += sameDayList.get(j).getAverage_value();
								}
								vo.setAverageTemperature(averageValue/sameDayList.size());
								voList.add(vo);
								//新建个sameDayList -- 避免数据重复
								sameDayList = new ArrayList<UserTemperatureRecord>();
							}
						}
						page.setTotalCount(voList.size());
						model.addAttribute("currentDate", currentDate);
						model.addAttribute("temperatureList", voList);
						model.addAttribute("page", page);
						//拼接表格要动态显示的数据
						StringBuffer buffer = new StringBuffer();
						buffer.append("[");
						for(int i =currentList.size()-1; i>=0;i--){
							String time = TimeUtils.getTimeStampNumberFormat(currentList.get(i).getAdd_time(),"HH.mm");
							//将第一个0去掉
							time = removeFirstZero(time);
							if(i == 0){
								buffer.append("["+time+","+currentList.get(i).getAverage_value()+"]");
							}else{
								buffer.append("["+time+","+currentList.get(i).getAverage_value()+"],");
							}
						}
						buffer.append("]");
						model.addAttribute("buffer", buffer);
					}
				}
				model.addAttribute("params", params);
//				return "syncdata/jcsj";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("monitorTemperature error"+e.getMessage());
		} finally{
			if("jh".equals(params.getCssType())){
				return "syncdata/monitorTemperature";
			}else{
				return "syncdata/jcsj";
			}
		}
	}
	
	/**尿液数据*/
	@SuppressWarnings("finally")
	@RequestMapping(value = "/urine")
	public String monitorUrine(Model model,@ModelAttribute Params params,Page<UserUrineRecord> page ){
		try {
			setArchiveBaseInfo(model,params.getArchiveBaseId(),params.getArchiveId());
			model.addAttribute("type", "urine");
			String userId = params.getUserId();
			if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId)){
				UserExtraInfo userExtra = userExtraInfoService.findByUserId(Integer.valueOf(userId));
				if(null != userExtra){
					UserInfo user = userInfoService.findUserInfoById(Integer.valueOf(userId));
					UserUrineRecord urine = new UserUrineRecord();
					urine.setUserInfo(user);
					Date expectedDate = user.getExpectedDateOfConfinement();//获取用户预产期
					UserViewVO viewVO = setCommonViewVO(userExtra,user);
					//获取总记录数
					int countNum = userUrineRecordService.getCount(urine);
					viewVO.setTotalTestCount(countNum);
					model.addAttribute("viewVO", viewVO);
					//处理相关查询条件
					int pageNum = 1;
					if(StringUtils.isNotBlank(params.getPageNum()) && StringUtils.isNumeric(params.getPageNum())){//带分页查询
						pageNum = Integer.valueOf(params.getPageNum());
					}
					page.setPageNo(pageNum);
					Page<UserUrineRecord> pageList = userUrineRecordService.getUrineByConditionWithPage(urine,page,params);
					List<UserUrineRecord> userUrineList = pageList.getResult();
					if(userUrineList.size() > 0)
					{
						List<Object> voList = new ArrayList<Object>();
						String compareTime = "";
						String compareNextTime = "";
						ArrayList<UserUrineRecord> sameDayList = new ArrayList<UserUrineRecord>();
						for(int i=0;i<userUrineList.size();i++){
							compareTime = TimeUtils.getTimeStampNumberFormat(userUrineList.get(i).getAdd_time(), "yyyy-MM-dd");
							if(i == userUrineList.size() -1){
								compareNextTime = "";
							}else{
								compareNextTime = TimeUtils.getTimeStampNumberFormat(userUrineList.get(i+1).getAdd_time(), "yyyy-MM-dd");
							}
							//计算孕周和孕天
							String addTimeStr = TimeUtils.getTimeStampNumberFormat(userUrineList.get(i).getAdd_time(),"yyyy-MM-dd HH:mm:ss");
							Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
							int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
							userUrineList.get(i).setPregnant(arr[0]+"周"+arr[1]+"天");
							sameDayList.add(userUrineList.get(i));
							if(!compareTime.equals(compareNextTime)){//说明不是同一天的数据
								sameDayList.get(0).setSize(sameDayList.size()+1);
								voList.add(sameDayList);
								sameDayList = new ArrayList<UserUrineRecord>();
							}
						}
						//将尿液数据传入前端显示
						model.addAttribute("userUrineList", voList);
					}
					model.addAttribute("page", page);
				}
				model.addAttribute("params", params);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("monitorUrine error "+e.getMessage());
		} finally{
			if("jh".equals(params.getCssType())){
				return "syncdata/monitorUrine";
			}else{
				return "syncdata/jcsj";
			}
		}
	}
	
	/**心电数据*/
	@SuppressWarnings("finally")
	@RequestMapping(value = "/ecg")
	public String monitorEcg(Model model,@ModelAttribute Params params,Page<UserEcgRecord> page){
		try {
			setArchiveBaseInfo(model,params.getArchiveBaseId(),params.getArchiveId());
			model.addAttribute("type", "ecg");
			String userId = params.getUserId();
			if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId)){
				UserExtraInfo userExtra = userExtraInfoService.findByUserId(Integer.valueOf(userId));
				if(null != userExtra){
				UserInfo user = userInfoService.findUserInfoById(Integer.valueOf(userId));
				UserEcgRecord ecg = new UserEcgRecord();
				ecg.setUserInfo(user);
				Date expectedDate = user.getExpectedDateOfConfinement();//获取用户预产期
				UserViewVO viewVO = setCommonViewVO(userExtra,user);
				//获取总记录数
				int countNum = userEcgRecordService.getCount(ecg);
				viewVO.setTotalTestCount(countNum);
				model.addAttribute("viewVO", viewVO);
				int pageNum = 1,id=0;
				if(StringUtils.isNotBlank(params.getPageNum()) && StringUtils.isNumeric(params.getPageNum())){
					pageNum = Integer.valueOf(params.getPageNum());
				}
				if(StringUtils.isNotBlank(params.getId()) && StringUtils.isNumeric(params.getId())){
					id = Integer.valueOf(params.getId());
				}
				page.setPageNo(pageNum);
				Page<UserEcgRecord> pageList = userEcgRecordService.getEcgByConditionWithPage(ecg,page,params);
				List<UserEcgRecord> userEcgList = pageList.getResult();
				if(userEcgList.size() > 0){
					if(id == 0){
						params.setAddDate(TimeUtils.getTimeStampNumberFormat(userEcgList.get(0).getTestTime(), "yyyy-MM-dd"));
						params.setId(userEcgList.get(0).getId()+"");
						//监测孕周
						String addTimeStr = TimeUtils.getTimeStampNumberFormat(userEcgList.get(0).getTestTime(),"yyyy-MM-dd HH:mm:ss");
						Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
						int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
						params.setMonitorPregnant(arr[0]+"周"+arr[1]+"天");
						//心电图
						if(StringUtils.isNotBlank(userEcgList.get(0).getImage())){
							params.setImage(userEcgList.get(0).getImage());
						}else{
							params.setImage(Const.NO_ECG_IMAGE);
						}
					}
					//获取心电历史数据
					List<UserViewVO> voList = new ArrayList<UserViewVO>();
					for(int i=0;i<userEcgList.size();i++){
						UserViewVO vo = new UserViewVO();
						vo.setName(userExtra.getRealName());
						//计算孕周和孕天
						String addTimeStr = TimeUtils.getTimeStampNumberFormat(userEcgList.get(i).getTestTime(),"yyyy-MM-dd HH:mm:ss");
						Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
						int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
						vo.setPregnantWeek(arr[0]);
						vo.setPregnantDay(arr[1]);
						vo.setMonitorTime(TimeUtils.formatStringDate(addTimeStr,"yyyy-MM-dd"));
						vo.setId(userEcgList.get(i).getId());
						vo.setSt(userEcgList.get(i).getAvrStvolt());
						vo.setEcgPulse(userEcgList.get(i).getAvrHeartrate());
						if(StringUtils.isNotBlank(userEcgList.get(i).getImage())){
							vo.setImage(userEcgList.get(i).getImage());
						}else{//设置为没监测心电的默认图片
							vo.setImage(Const.NO_ECG_IMAGE);
						}
						voList.add(vo);
					}
					model.addAttribute("FILE_DOMAIN", Consts.BASE_FILE_URL);
					model.addAttribute("ecgList", voList);
					model.addAttribute("page", page);
				}
			}
				model.addAttribute("params", params);
		 }
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("monitorEcg error "+e.getMessage());
		} finally{
			if("jh".equals(params.getCssType())){
				return "syncdata/monitorEcg";
			}else{
				return "syncdata/jcsj";	
			}
		}
 }
	
	
	/**胎动数据***/
	@SuppressWarnings("finally")
	@RequestMapping(value="fetal")
	public String monitorFetalMovement(Model model,@ModelAttribute Params params,Page<UserFetalmoveRecord> page){
		try {
			setArchiveBaseInfo(model,params.getArchiveBaseId(),params.getArchiveId());
			model.addAttribute("type", "fetal");
			String userId = params.getUserId();
//			String userId = "32";
			if(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId)){
				UserInfo  userInfo = userInfoService.findUserInfoById(Integer.valueOf(userId)); //获取userInfo
				//获取预产期
				Date expectedDate = userInfo.getExpectedDateOfConfinement();
				UserFetalmoveRecord fetalmove = new UserFetalmoveRecord();
				fetalmove.setUserInfo(userInfo);
				//获取总记录数
				int countNum = userFetalmoveRecordService.getCount(fetalmove);
				UserViewVO viewVO = new UserViewVO();
				viewVO.setTotalTestCount(countNum);
				model.addAttribute("viewVO", viewVO);
				int pageNum = 1;
				if(StringUtils.isNotBlank(params.getPageNum()) && StringUtils.isNumeric(params.getPageNum())){//带分页查询
					pageNum = Integer.valueOf(params.getPageNum());
				}
				page.setPageNo(pageNum);
				params.setPageNum(String.valueOf(pageNum));
				Page<UserFetalmoveRecord> pageList = userFetalmoveRecordService.findByConditionWithPage(fetalmove, page, params); //分页查询
				List<UserFetalmoveRecord> userFetalList = pageList.getResult();
				if(userFetalList.size() > 0){
					//获取胎动的数据
					List<UserViewVO> voList = new ArrayList<UserViewVO>();
					for(int i=0;i<userFetalList.size();i++){
						UserViewVO vo = new UserViewVO();
						//计算孕周和孕天
						String addTimeStr = TimeUtils.getTimeStampNumberFormat(userFetalList.get(i).getStart_time(),"yyyy-MM-dd HH:mm:ss");
						Date addTime = TimeUtils.convertDate(addTimeStr, "yyyy-MM-dd HH:mm:ss");
						int[] arr = TimeUtils.getPregnantWeekAndDay(addTime, expectedDate);
						vo.setPregnantWeek(arr[0]);//孕周
						vo.setPregnantDay(arr[1]);//孕天
						vo.setMonitorTime(TimeUtils.formatStringDate(addTimeStr,"yyyy-MM-dd"));//监测时间
						//判断测试时间属于那个时间段
						int result = TimeUtils.judgeTime(userFetalList.get(i).getStart_time());
						if(result ==1 ){
							vo.setMonitorAm(userFetalList.get(i).getFetal_move_value());
						}else if(result == 2){
							vo.setMonitorPm(userFetalList.get(i).getFetal_move_value());
						}else if(result == 3){
							vo.setMonitorNight(userFetalList.get(i).getFetal_move_value());
						}
						voList.add(vo);
					}
					model.addAttribute("fetalmoveList", voList);
					//拼接表格要动态显示的数据
					StringBuffer buffer_now = new StringBuffer();
					StringBuffer buffer_twelve = new StringBuffer();
					StringBuffer buffer_time = new StringBuffer();
					buffer_now.append("[");
					buffer_twelve.append("[");
					buffer_time.append("[");
					for(int i =userFetalList.size()-1; i>=0;i--){
						UserViewVO vo_twelve = new UserViewVO();
						//判断测试时间属于那个时间段
						int result = TimeUtils.judgeTime(userFetalList.get(i).getStart_time());
						if(result ==1 ){
							vo_twelve.setMonitorAm(userFetalList.get(i).getFetal_move_value());
						}else if(result == 2){
							vo_twelve.setMonitorPm(userFetalList.get(i).getFetal_move_value());
						}else if(result == 3){
							vo_twelve.setMonitorNight(userFetalList.get(i).getFetal_move_value());
						}
						//获取当前记录的12小时总的胎动数
						int count=0;//总次数
						if(vo_twelve.getMonitorAm()>0){
							count++;
						}
						if(vo_twelve.getMonitorPm()>0){
							count++;
						}
						if(vo_twelve.getMonitorNight()>0){
							count++;
						}
						System.out.println(count+"------------");
						int total_twelve = (vo_twelve.getMonitorAm()+vo_twelve.getMonitorPm()+vo_twelve.getMonitorNight())*(count==1?12:count==2?6:count==3?4:0);
						System.out.println(total_twelve+"------------");
						//此处转换为毫秒数
						long millionSeconds =userFetalList.get(i).getAdd_time().getTime(); //TimeUtils.getMillisecond(userFetalList.get(i).getAdd_time());
						if(i == 0){
							buffer_time.append(millionSeconds);
							buffer_now.append(userFetalList.get(i).getFetal_move_value());
							buffer_twelve.append(total_twelve);
						}else{
							buffer_time.append(millionSeconds+",");
							buffer_now.append(userFetalList.get(i).getFetal_move_value()+",");
							buffer_twelve.append(total_twelve+",");
						}
					}
					buffer_now.append("]");
					model.addAttribute("fetalBuffer", buffer_now);
					buffer_twelve.append("]");
					model.addAttribute("buffer_twelve", buffer_twelve);
					buffer_time.append("]");
					model.addAttribute("buffer_time", buffer_time);
				}
				model.addAttribute("page", page);
				model.addAttribute("params", params);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("monitorFetalMovement error"+e.getMessage());
		}finally{
			if("jh".equals(params.getCssType())){
				return "syncdata/monitorFetal";
			}else{
				return "syncdata/jcsj";
			}
		}
	
	}
	
	/**打印胎心报告**/
	@RequestMapping(value="/printHeartReport")
	public @ResponseBody String printHeartReport(@ModelAttribute UserPrintVO print) throws IOException{
		//1.获取文件服务器地址
		String filePath = Consts.BASE_FILE_URL;
		//2.开始生成打印报告文件 //通过胎心记录id查询数据库中的数据
		int heartId = 0;
		if(StringUtils.isNotBlank(print.getHeartId()) && StringUtils.isNumeric(print.getHeartId())){
			heartId = Integer.valueOf(print.getHeartId());
		}
		UserHeartrateRecord heart = userHeartrateRecordService.get(heartId);
		//通过用户id和胎心报告类型查询用户胎心报告文件
		if(null != heart){
			UserHeartrateReportFile report = new UserHeartrateReportFile();
			report.setUserId(heart.getUserInfo().getId());
			if(StringUtils.isNotBlank(print.getPaperSpeed()) &&  StringUtils.isNumeric(print.getPaperSpeed())){
				report.setType(Integer.valueOf(print.getPaperSpeed()));
			}
			report.setHeartId(heartId);
			report.setMonitorUserName(print.getName());
			if(StringUtils.isNotBlank(print.getOffset())){
				report.setOffsets(print.getOffset());
			}else{
				report.setOffsets("0-0");
			}
			List<UserHeartrateReportFile> reportList = userHeartrateReportFileService.findReportByCondition(report);
			if(reportList.size() > 0){//说明以前已经生成过报告，不需要再生成
				return filePath+reportList.get(0).getReportFile();
			}else{//以前没生成过报告
				//配置打印时间
				String startPrintTime = print.getStartPrintTime();
				Timestamp startPrint = TimeUtils.getCurrentTime(startPrintTime, "yyyy-MM-dd HH:mm:ss");
				Long time = TimeUtils.convertDate(startPrintTime,"yyyy-MM-dd HH:mm:ss").getTime();
				/*print.setStartPrintTime(String.valueOf(time));*/
				//配置监测时间
				String monitorTime = print.getMonitorTime();
				int monitor = 0;
				if(StringUtils.isNotBlank(monitorTime) && StringUtils.isNumeric(monitorTime)){
					monitor = Integer.valueOf(monitorTime);
				}
				String startTime = TimeUtils.transferLongToDate(time, "yyyy-MM-dd HH:mm:ss");
				monitorTime = TimeUtils.transferLongToDate(time+monitor*1000, "HH:mm:ss");
				String monitorLength = "共"+monitor/60+"分"+monitor%60+"秒";
				String monitorTimeLength = startTime+"~"+monitorTime+" "+monitorLength;
				print.setMonitorTime(monitorTimeLength);
				int[] uterusArr = null,heartArr = null,fetalMoveArr = null;
				//配置胎心数据
				if(StringUtils.isNotBlank(print.getHeartData())){
					String[] heartData = print.getHeartData().split(",");
					heartArr = ObjectUtils.stringArrToIntArr(heartData);
					print.setHeartArr(heartArr);
				}
				//配置胎动数据
				if(StringUtils.isNotBlank(print.getFetalMoveData())){
					String[] fetalMoveData = print.getFetalMoveData().split(",");
					fetalMoveArr = ObjectUtils.stringArrToIntArr(fetalMoveData);
					print.setFetalMoveArra(fetalMoveArr);
				}
				//配置宫缩数据
				if(StringUtils.isNotBlank(print.getUterusData())){
					String[] uterusData = print.getUterusData().split(",");
					uterusArr = ObjectUtils.stringArrToIntArr(uterusData);
					print.setUterusArra(uterusArr);
				}
				GenerateSFYMonitorPDF pdf = new GenerateSFYMonitorPDF(heartArr,uterusArr,fetalMoveArr);
				//选择区间打印
				String offset = print.getOffset();
				if(StringUtils.isNotBlank(offset)){
					if(offset.startsWith("-")){
						offset = offset.substring(1);
					}
					String[] offsets = offset.split("-");
					report.setOffsets(offset);
					pdf.generate(Integer.valueOf(offsets[0]), Integer.valueOf(offsets[1]), Integer.valueOf(print.getPaperSpeed()), startPrint,print);
				}else{
					report.setOffsets("0-0");
					pdf.generate(0, 4000, Integer.valueOf(print.getPaperSpeed()), startPrint,print);
				}
				//3.将打印报告上传到服务器
				File file = new File(Const.MONITOR_REPORT_FILE_PATH);
				InputStream inputStream = new FileInputStream(file);
				String path = FastdfsUpload.upoladFile("report.pdf", inputStream);
				//保存文件地址到数据库
				report.setReportFile(path);
				report.setUserId(heart.getUserInfo().getId());
				report.setMonitorUserName(print.getName());
				report.setAddTime(TimeUtils.getCurrentTime());
				report.setType(Integer.valueOf(print.getPaperSpeed()));
				if(StringUtils.isNotBlank(path) && !"null".equals(path)){
					userHeartrateReportFileService.persist(report);
				}
				//3.将胎心报告在文件服务器上生成的地址作为结果传回
				logger.info("upload heartrate report path: "+path);
				return filePath+path;
			}
		}else{
			return "";
		}
	}
	
	/***获取孕妇当前孕期**/
	/**通过绑定记录表中的预产期获取当前孕周*/
	public String setCurrentPregnant(UserInfo userInfo){
		String currentpPregnant = "";
		//获取预产期
		Date expectedDate = userInfo.getExpectedDateOfConfinement();
		if(null != expectedDate){
			//计算当前的孕周与孕天
			int[] pregnant = TimeUtils.getPregnantWeekAndDay(new Date(), expectedDate);
			currentpPregnant = pregnant[0]+"周"+pregnant[1]+"天";
		}
		return currentpPregnant;
		
	}
	
	/**获取孕妇在整个保健期总的具体日期*/
	public StringBuffer getTotalData(StringBuffer buffer,String showType,Date expectedDate,Date babyBirth){
		buffer.append("[");
		String nowTime = ""; 
		//比较当前日期与预产期的大小
		int size = 281;
		if(null != babyBirth){
			if(babyBirth.before(expectedDate)){//宝宝生日在预产期之前 说明是早产
				size -= TimeUtils.getSeparatedDays(babyBirth, expectedDate);
			}else{//说明是晚产
				size += TimeUtils.getSeparatedDays(expectedDate, babyBirth);
			}
		}
		for(int i=size-1;i>=0;i--){
			nowTime = TimeUtils.getBefourOrAfter(-i,expectedDate, "yyyy:MM:dd");
			//拼接要显示的数据
			if(i == 0){
				buffer.append(nowTime);
			}else{
				buffer.append(nowTime+",");
			}
		}
		buffer.append("]");
		return buffer;
	}
	
	
	/**体重展示数据用户基本信息*/
	@SuppressWarnings({ "rawtypes"})
	public UserViewVO setUserViewVO(BaseService baseservice,UserWeightRecord weight,UserExtraInfo userExtra,UserInfo user,boolean isWeight,Params params){
		//获取预产期
		UserViewVO viewVO = new UserViewVO();
		Date expectedDate = user.getExpectedDateOfConfinement();
		if(null != expectedDate){
			//计算当前的孕周与孕天
			int[] pregnant = TimeUtils.getPregnantWeekAndDay(new Date(), expectedDate);
			String expected = TimeUtils.converStringDate(expectedDate, "yyyy-MM-dd");
			viewVO.setPregnantWeek(pregnant[0]);
			viewVO.setPregnantDay(pregnant[1]);
			viewVO.setExpectedDate(expected);
		}
		//组装vo
		viewVO.setName(userExtra.getRealName());
		viewVO.setPhone(user.getMobile());
		viewVO.setAge(userExtra.getAge()+"");
		//设置测量的总次数
		int count = userWeightRecordService.getCount(userExtra.getUserInfo().getId());
		viewVO.setTotalTestCount(count);
		//计算测量的频率（平均测量时间）
		if(isWeight){
			Double  BMI  = WeightUtils.getBmiByWeightAndHeight(userExtra);
			BMI =  (double)Math.round(BMI*100)/100  ;
			viewVO.setBMI(BMI);
			//设置体重状态
			viewVO.setWeightState(WeightUtils.getWeightState(BMI));
			List<UserWeightRecord> list = userWeightRecordService.findByCondtion(weight,params);
			if(list.size() > 0){
				Date testEnd = list.get(0).getTest_time();
				Date testStart = list.get(list.size()-1).getTest_time();
				int separatedDays = TimeUtils.getSeparatedDays(testStart, testEnd);
				int averageTesttime= separatedDays/count;
				viewVO.setAverageTesttime(averageTesttime);
				Integer state = list.get(0).getWeight_state();
				if(state == null ){
					viewVO.setState("");
				}else if(state == 0){
					viewVO.setState("偏瘦");
				}else if(state == 1){
					viewVO.setState("标准");
				}else if(state == 2){
					viewVO.setState("偏胖");
				}else if(state == 3){
					viewVO.setState("肥胖");
				}
				
			}
			
		}
		return viewVO;
	}
	
	/**
	 * 组装图表健康曲线X轴
	 * @param buffer   要组装的数据buffer
	 * @param timeFlag 时间查询标记
	 * @param showType 图表显示的形式（按月显示，按天显示，按孕周显示）
	 * @param expectedDate 预产期
	 * @return
	 */
	public StringBuffer getXData(StringBuffer buffer,String timeFlag,String showType,Date expectedDate,String startTime,String endTime){
		buffer.append("[");
		String nowTime = ""; 
		Date currentDate = new Date();	
		//比较当前日期与预产期的大小
		if(!expectedDate.after(currentDate)){//说明当前日期在预产期之后
			currentDate = expectedDate;
		}
		int size = 281;
		if("day".equals(showType) || "month".equals(showType)){//以天展示
			if(StringUtils.isNotBlank(timeFlag) && StringUtils.isNumeric(timeFlag)){
				size = Integer.valueOf(timeFlag);
			}
			if(StringUtils.isNotBlank(startTime)){//以时间段查询
				Date startDate = TimeUtils.convertDate(startTime, "yyyy-MM-dd");
				if(StringUtils.isNotBlank(endTime)){
					currentDate = TimeUtils.convertDate(endTime, "yyyy-MM-dd");
				}
				size = TimeUtils.getSeparatedDays(startDate, currentDate);
			}
			if(size == 281 && expectedDate.after(new Date())){//说明查的是整个预产期的280天 当前日期需改为预产期那天
				currentDate = expectedDate;
			}
			for(int i=size-1;i>=0;i--){
				nowTime = TimeUtils.getBefourOrAfter(-i,currentDate, "yyyy:MM:dd");
				//拼接要显示的数据
				if(i == 0){
					buffer.append(nowTime);
				}else{
					buffer.append(nowTime+",");
				}
			}
		}
		buffer.append("]");
		return buffer;
	}

	
	/**显示基本的信息的UserViewVO***/
	public UserViewVO setCommonViewVO(UserExtraInfo userExtra,UserInfo user){
		UserViewVO viewVO = new UserViewVO();
		 //获得预产期
		Date expectedDate = user.getExpectedDateOfConfinement();
		if(null != expectedDate){//计算当前的孕周与孕天
			int[] pregnant = TimeUtils.getPregnantWeekAndDay(new Date(), expectedDate);
			String expected = TimeUtils.converStringDate(expectedDate, "yyyy-MM-dd");
			viewVO.setPregnantWeek(pregnant[0]);
			viewVO.setPregnantDay(pregnant[1]);
			viewVO.setExpectedDate(expected);
		}//组装vo
		viewVO.setName(userExtra.getRealName());
		viewVO.setPhone(user.getMobile());
		viewVO.setAge(userExtra.getAge()+"");
		return viewVO;
	}
	
	/**去掉开头的0*/
	public String removeFirstZero(String str){
		if(str.startsWith("0")){
			str = str.substring(1);
		}
		return str;
	}
}
