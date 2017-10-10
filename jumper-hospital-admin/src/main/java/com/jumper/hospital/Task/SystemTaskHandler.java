package com.jumper.hospital.Task;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.jumper.hospital.controller.BaseController;
import com.jumper.hospital.entity.InqHospitalConsultant;
import com.jumper.hospital.entity.MonitorOrderConsumerTemp;
import com.jumper.hospital.service.InqHospitalConsultantService;
import com.jumper.hospital.service.MonitorOrderConsumerTempService;
import com.jumper.hospital.service.SchoolCourseAppointService;
import com.jumper.hospital.service.SchoolCourseDetailService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.utils.visit.HttpClient;
import com.jumper.hospital.vo.DataMsg;
import com.jumper.hospital.vo.PageData;
import com.jumper.hospital.vo.TodayMsg;
import com.jumper.hospital.vo.VOConsultantReplyInfo;

@Component(value="taskHandler")
public class SystemTaskHandler extends BaseController{

	private static final Logger logger = Logger.getLogger(SystemTaskHandler.class);
	
	@Autowired
	private MonitorOrderConsumerTempService monitorOrderConsumerTempServiceImpl;
	@Autowired
	private SchoolCourseDetailService schoolCourseDetailServiceImpl;
	@Autowired
	private SchoolCourseAppointService schoolCourseAppointServiceImpl;
	@Autowired
	private InqHospitalConsultantService inqHospitalConsultantService;
	
	public void clearTempConsumerOrder(){
		logger.info("开始执行清理实时监测临时订单数据任务！");
		List<MonitorOrderConsumerTemp> consumerList = monitorOrderConsumerTempServiceImpl.findAll();
		if(ArrayUtils.isNotEmpty(consumerList)){
			for(MonitorOrderConsumerTemp order : consumerList){
				if(order != null){
					Timestamp applyTime = order.getApplyTime();
					boolean flag = TimeUtils.isExpire(applyTime);
					/** 过期则删除 **/
					if(flag){
						monitorOrderConsumerTempServiceImpl.delete(order);
					}
				}
			}
		}
		logger.info("临时订单清理完毕！");
	}
	
	public void handlerOffLineCourseOutDate(){
		try {
			schoolCourseDetailServiceImpl.changeOutDateRecord();
			schoolCourseAppointServiceImpl.changeOutOfDateAppoint();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("更新孕妇学校过期课程处理异常！");
		}
	}
	
	
	public void clearAskData(){
		try{
			//获取到所有的资讯列表信息
			List<InqHospitalConsultant> pageData = inqHospitalConsultantService.findTodayAllConsultList();
			if(pageData!=null&&pageData.size()>0){
				//循环所有的资讯
				for (InqHospitalConsultant consult : pageData) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("busCode", "50101");//50101
					map.put("fromUserId",consult.getHospitalInfo().getId());  //医院
					map.put("fromAccountType", 3);
					map.put("toUserId",  consult.getUserInfo().getId());   // 用户
					map.put("toAccountType",2);
					map.put("startTime","");
					map.put("endTime", "");
					map.put("pageNo", 1);
		 			map.put("pageSize", 20);
					map.put("msgId", "");
					
					HttpClient httpClient = new HttpClient(Consts.CONSULT_IM_URL+"/im/get_msg_userid",map);
					String post = httpClient.post();
					
					DataMsg datamsg = new DataMsg();
					PageData page1 = new PageData();
					TodayMsg bean = JSONArray.parseObject(post, TodayMsg.class);
					if(bean.getMsg()==1){
						page1 = bean.getData();
						datamsg = page1.getPages();
						List<VOConsultantReplyInfo> voclist = datamsg.getDataList();
						String vosendChatid =null;
						boolean bl = false;
						for (VOConsultantReplyInfo voInfo : voclist) {
							if(null==vosendChatid){
								vosendChatid =voInfo.getSendChatId();
							}else{
								if(!vosendChatid.equals(voInfo.getSendChatId())){
									bl = true;
									break;
								}
							}
						}
						
						Date addTime = consult.getAddTime();
						String bls = TimeUtils.isExpired(addTime);
						if(bl){
							if(bls.equals("过期")){
								inqHospitalConsultantService.updateStatus(consult.getId(), 5);
							}else{
								inqHospitalConsultantService.updateStatus(consult.getId(), 3);
							}
						}else{
							if(bls.equals("退款")){
								inqHospitalConsultantService.updateStatus(consult.getId(), 5);
							}
							
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
