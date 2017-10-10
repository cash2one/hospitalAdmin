package com.jumper.hospital.controller;
/**
 * 监控服务设置相关业务contrller
 * @author rent
 * @date 2015-10-29
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorHospitalServiceTime;
import com.jumper.hospital.entity.MonitorSetting;
import com.jumper.hospital.entity.MonitorSettingOption;
import com.jumper.hospital.enums.PrintType;
import com.jumper.hospital.enums.RemoteType;
import com.jumper.hospital.enums.Time;
import com.jumper.hospital.enums.Week;
import com.jumper.hospital.service.HospitalInfoService;
import com.jumper.hospital.service.MonitorHospitalService;
import com.jumper.hospital.service.MonitorHospitalServiceTimeService;
import com.jumper.hospital.service.MonitorOperateLogServices;
import com.jumper.hospital.service.MonitorSettingOptionService;
import com.jumper.hospital.service.MonitorSettingService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VOMonitorHospitalServiceTime;

@Controller
@RequestMapping("rmService")
public class RemoteServiceController extends BaseController {

	@Autowired
	private MonitorHospitalService monitorHospitalServiceImpl;
	@Autowired
	private MonitorSettingOptionService monitorSettingOptionServiceImpl;
	@Autowired
	private MonitorSettingService monitorSettingServiceImpl;
	@Autowired
	private HospitalInfoService hospitalInfoServiceImpl;
	@Autowired
	private MonitorHospitalServiceTimeService monitorHospitalServiceTimeService;
	@Autowired
	private MonitorOperateLogServices monitorOperateLogServices;
	
	
	
	/**
	 * 监控服务首页
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(ModelMap model){
		Admin admin = getLoginAdminInfo();
		MonitorHospital monitorHospital = monitorHospitalServiceImpl.findMonitorHospitalByHospitalId(admin.getHospitalInfo().getId());
		if(monitorHospital == null){
			model.put("mHospital", null);
			pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_MENU_SERVICE, Consts.HOSPITAL_SUBMENU_REMOTE);
			return "hospital/monitorService";
		}
		//开通服务的时候初始化排班,之前的老排班数据没有初始化
		if(monitorHospital!= null){
			monitorHospitalServiceTimeService.initServiceTime(admin.getHospitalInfo().getId());
			List<VOMonitorHospitalServiceTime> monitorHospitalServiceTime= monitorHospitalServiceTimeService.getServiceTime(admin.getHospitalInfo().getId());
			//排期数据
			model.put("monitorTime", monitorHospitalServiceTime);
		}
		model.put("week", Week.getMapResult());
		model.put("time", Time.values());
		model.put("mHospital", monitorHospital);
		pageLocation(model, Consts.HOSPITAL_MODULE, Consts.HOSPITAL_MENU_SERVICE, Consts.HOSPITAL_SUBMENU_REMOTE);
		return "hospital/monitorService";
	}
	
	@RequestMapping("optionEdit")
	public void updateOption(MonitorSettingOption option, Integer settingId){
		if(settingId == null || settingId == 0){
			JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
			return;
		}
		boolean flag = monitorSettingOptionServiceImpl.saveOrUpdateOption(option, settingId);
		
		MonitorSetting monitorSetting=null;
		if(settingId!=null){
			monitorSetting= monitorSettingServiceImpl.get(settingId);
		}
		Admin admin=getLoginAdminInfo();
		if(flag){
			
			//修改用户id
			if(option.getId()!=null){
				//添加日志===>修改服务价格
				monitorOperateLogServices.addOperateLog(admin, "服务设置", "修改", 
						monitorSetting.getType().name(), "修改"+monitorSetting.getType().name()+",套餐为:"+option.getPrice()+"元"+option.getNumber()+"次");
			//新增用户id
			}else{
				//添加日志===>添加服务价格
				monitorOperateLogServices.addOperateLog(admin, "服务设置", "添加", 
						monitorSetting.getType().name(), "添加"+monitorSetting.getType().name()+"金额为:"+option.getPrice()+"元"+option.getNumber()+"次");
			}
			
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		}else{
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	
	@RequestMapping("optionDelete")
	public void optionDelete(Integer optionId){
		
		Admin admin=getLoginAdminInfo();
		MonitorSettingOption option=monitorSettingOptionServiceImpl.get(optionId);
		MonitorSetting monitorSetting=null;
		if(option.getId()!=null){
		   monitorSetting= monitorSettingServiceImpl.get(option.getMonitorSetId().getId());
		}
		if(monitorSetting!=null){
			monitorOperateLogServices.addOperateLog(admin, "服务设置", "删除", 
					monitorSetting.getType().name(), admin.getUsername()+"删除了"+monitorSetting.getType().name()+"的套餐金额为:"+option.getPrice()+"元"+option.getNumber()+"次");
		}
		
		try {
			if(optionId == null || optionId == 0){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			monitorSettingOptionServiceImpl.delete(optionId);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	
	@RequestMapping("operate")
	public void operateSetting(Integer settingId, Integer operateType){
		try {
			if(settingId == null || settingId == 0 || operateType == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			MonitorSetting setting = monitorSettingServiceImpl.get(settingId);
			if(setting == null){
				JsonUtils.render(getResponse(), Consts.FAILED);
				return;
			}
			HospitalInfo info = hospitalInfoServiceImpl.get(setting.getMonitorHospitalId().getHospitalId().getId());
			if(operateType == 0){
				boolean flag = monitorSettingServiceImpl.checkRoutineOrderIsUseFinish(info.getId(), setting.getType());
				if(!flag){
					JsonUtils.render(getResponse(), Consts.EXIST);
					return;
				}
				
				//院内监护
				if(1==setting.getType().ordinal()){
					//添加日志===> 关闭实时监护
					Admin admin=getLoginAdminInfo();
					monitorOperateLogServices.addOperateLog(admin, "服务设置", "关闭", "实时监护", "关闭实时监护");
				//院外监护
				}else if(2==setting.getType().ordinal()){
					//添加日志===> 关闭院内监护
					Admin admin=getLoginAdminInfo();
					monitorOperateLogServices.addOperateLog(admin, "服务设置", "关闭", "院内监护", "关闭院内监护");
				}
				setting.setIsOpen(false);
			}else{
				//院内监护
				if(1==setting.getType().ordinal()){
					//添加日志===> 开启院外监护
					Admin admin=getLoginAdminInfo();
					monitorOperateLogServices.addOperateLog(admin, "服务设置", "开启", "实时监护", "开启实时监护");
				//院外监护
				}else if(2==setting.getType().ordinal()){
					//添加日志===> 开启院外监护
					Admin admin=getLoginAdminInfo();
					monitorOperateLogServices.addOperateLog(admin, "服务设置", "开启", "院内监护", "开启院内监护");
				}
				setting.setIsOpen(true);
			}
			hospitalInfoServiceImpl.save(info);
			monitorSettingServiceImpl.save(setting);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	
	@RequestMapping("hospitalAdd")
	public void hospitalAdd(){
		try {
			Admin admin = getLoginAdminInfo();
			if(admin == null){
				JsonUtils.render(getResponse(), Consts.FAILED);
				return;
			}
			//初始化服务时间
			MonitorHospital hospital = new MonitorHospital();
			hospital.setHospitalId(admin.getHospitalInfo());
			hospital.setIsOpen(true);
			//兼容别的模块，老的数据还需要存
			hospital.setStartDay(Week.周一);
			hospital.setEndDay(Week.周日);
			hospital.setStartTime(Time.a.getDesc());
			hospital.setEndTime(Time.z.getDesc());
			
			hospital.setAddTime(TimeUtils.getCurrentTime());
			monitorHospitalServiceImpl.saveHospital(hospital);
			//开通服务的时候初始化排班
			monitorHospitalServiceTimeService.initServiceTime(admin.getHospitalInfo().getId());
			MonitorSetting setting = new MonitorSetting();
			setting.setIsOpen(false);
			setting.setMonitorHospitalId(hospital);
			setting.setType(RemoteType.常规监护);
			monitorSettingServiceImpl.save(setting);
			
			MonitorSetting realTime = new MonitorSetting();
			realTime.setIsOpen(false);
			realTime.setMonitorHospitalId(hospital);
			realTime.setType(RemoteType.实时监护);
			monitorSettingServiceImpl.save(realTime);
			
			MonitorSetting inside = new MonitorSetting();
			inside.setIsOpen(false);
			inside.setMonitorHospitalId(hospital);
			inside.setType(RemoteType.院内监护);
			monitorSettingServiceImpl.save(inside);
			
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	//老版本的修改排班数据
	@RequestMapping("timeUpdate")
	public void editTime(Integer id, Integer startDay, Integer endDay, String startTime, String endTime){
		try {
			if(id == null || startDay == null || endDay == null || StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			boolean flag = monitorHospitalServiceImpl.editMonitorHospital(id, startDay, endDay, startTime, endTime);
			if(flag){
				JsonUtils.render(getResponse(), Consts.SUCCESS);
				return;
			}
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	//新版本的修改排班数据
	@RequestMapping("modifyTime")
	public void modifyTime( @RequestBody ArrayList<VOMonitorHospitalServiceTime> VOlist){
		Admin admin = getLoginAdminInfo();
		Integer hospitalId= admin.getHospitalInfo().getId();
		try {
			MonitorHospitalServiceTime monitorHospitalServiceTime=null;
			for(VOMonitorHospitalServiceTime l:VOlist){
				monitorHospitalServiceTime=new MonitorHospitalServiceTime();
				//新增
				if("add".equals(l.getDataState())){
					monitorHospitalServiceTime.setDayOfWeek(l.getDayOfWeek());
					if(null!=l.getStartTime()){
						monitorHospitalServiceTime.setStartTime(l.getStartTime());
					}
					if(null!=l.getEndTime()){
						monitorHospitalServiceTime.setEndTime(l.getEndTime());
					}
					monitorHospitalServiceTime.setHospitalId(hospitalId);
					monitorHospitalServiceTime.setIsEnabled(true);
					monitorHospitalServiceTimeService.persist(monitorHospitalServiceTime);
				}
				//删除
				if("delete".equals(l.getDataState())){
					monitorHospitalServiceTimeService.delete(l.getId());
				}
				//修改,为了兼容状态 persisent 也跟更新下
				if("update".equals(l.getDataState())||"persisent".equals(l.getDataState())){
					monitorHospitalServiceTime.setId(l.getId());
					monitorHospitalServiceTime.setDayOfWeek(l.getDayOfWeek());
					if(null!=l.getStartTime()){
						monitorHospitalServiceTime.setStartTime(l.getStartTime());
					}
					if(null!=l.getEndTime()){
						monitorHospitalServiceTime.setEndTime(l.getEndTime());
					}
					monitorHospitalServiceTime.setHospitalId(hospitalId);
					monitorHospitalServiceTime.setIsEnabled(l.getIsEnabled());
					monitorHospitalServiceTimeService.edit(monitorHospitalServiceTime);
				}
				
			}
			
			
			
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	
	
	//开通胎心解读和院内胎心接口  is_hospital_nst:院内胎心（暂时）    is_doctor_nst： 胎心判读
	@RequestMapping("updateNst")
	public void updateNst(Boolean isDoctorNst){
		try {
			if(isDoctorNst == null){
				JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
				return;
			}
			Admin admin = getLoginAdminInfo();
			Integer hospitalId = admin.getHospitalInfo().getId();
			
			boolean flag = hospitalInfoServiceImpl.updateNstState(hospitalId,isDoctorNst);
			if(flag){
				JsonUtils.render(getResponse(), Consts.SUCCESS);
				return;
			}
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	/**
	 * 修改医院打印方式
	 * @param printType
	 */
	@RequestMapping("updatePrintType")	
	public void updatePrintType(String printType){
		if(StringUtils.isBlank(printType)){
			JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
			return;
		}
		try {
			Admin admin = getLoginAdminInfo();
			Integer hospitalId = admin.getHospitalInfo().getId();
			HospitalInfo info = hospitalInfoServiceImpl.get(hospitalId);
			if(printType.equals("1")){
				info.setPrintType(PrintType.MONITORINGCURVE);
				//添加日志===> 修改打印模式
				monitorOperateLogServices.addOperateLog(admin, "服务设置", "修改", "打印模式", "修改打印模式为:"+PrintType.MONITORINGCURVE.getName(1)+"模式");
			}if(printType.equals("0")){
				info.setPrintType(PrintType.ROUTINE);
				//添加日志===> 修改打印模式
				monitorOperateLogServices.addOperateLog(admin, "服务设置", "修改", "打印模式", "修改打印模式为:"+PrintType.MONITORINGCURVE.getName(0)+"模式");
			}	
			hospitalInfoServiceImpl.edit(info);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
		
	}
	

	
	//更新退回此时的时间
	@RequestMapping("updateReturnDeadline")
	public void updateReturnDeadline(Integer returnDeadline){
		try {
			Admin admin=getLoginAdminInfo();
			MonitorHospital monitorHospital = monitorHospitalServiceImpl.findMonitorHospitalByHospitalId(admin.getHospitalInfo().getId());
			boolean flag = monitorOperateLogServices.updateReturnDeadline(monitorHospital,returnDeadline);
			//添加日志===>修改报告时效
			monitorOperateLogServices.addOperateLog(admin, "服务设置", "修改", 
					"报告时效", "修改报告时效为:"+returnDeadline+"小时");
			
			if(flag){
				JsonUtils.render(getResponse(), Consts.SUCCESS);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			//JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	
	/**
	 * 保存预警上下限
	 * @param upperLimit
	 * @param lowerLimit
	 */
	@RequestMapping("updateWarning")	
	public void updateWarning(int upperLimit,int lowerLimit){
		try {
			Admin admin = getLoginAdminInfo();
			Integer hospitalId = admin.getHospitalInfo().getId();
			hospitalInfoServiceImpl.updateWarning(hospitalId,upperLimit,lowerLimit);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
			
			//添加日志===>修改预警上下限
			monitorOperateLogServices.addOperateLog(admin, "服务设置", "修改", 
					"胎心预警", "修改预警上下限,报警上限："+lowerLimit+" 报警下限："+upperLimit);
			
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	
	/**
	 * 保存是否短信通知和持续异常分钟数
	 * @param isCheck
	 * @param minute
	 */
	@RequestMapping("btWarningMessage")	
	public void btWarningMessage(boolean isCheck,Integer sel_minute){
		try {
			Admin admin = getLoginAdminInfo();
			Integer hospitalId = admin.getHospitalInfo().getId();
			hospitalInfoServiceImpl.updateBtWarningMessage(hospitalId,isCheck,sel_minute);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
			
			//String isCheckStr=isCheck?"开":"关";
			//添加日志===>修改预警上下限
			if(isCheck){//开
				monitorOperateLogServices.addOperateLog(admin, "服务设置", "修改", 
						"胎心预警", "短信通知:开启,持续异常："+sel_minute+"分钟");
			}else{//关
				monitorOperateLogServices.addOperateLog(admin, "服务设置", "修改", 
						"胎心预警", "短信通知:关闭");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	
	/**
	 * 设置评分方式
	 * @param scoringMethod
	 */
	@RequestMapping("settingScoringMethod")
	public void scoringMethod(Integer scoringMethod){
		try {
			Admin admin = getLoginAdminInfo();
			boolean flag  = monitorHospitalServiceImpl.updateScoringMethod(admin.getHospitalInfo().getId(),scoringMethod);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	
}
