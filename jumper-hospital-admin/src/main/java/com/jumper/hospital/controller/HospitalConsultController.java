package com.jumper.hospital.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jumper.hospital.Comparator.ComparatorIm;
import com.jumper.hospital.Comparator.ComparatorList;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalComments;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalService;
import com.jumper.hospital.entity.InqHospitalConsultant;
import com.jumper.hospital.entity.InqHospitalConsultantReply;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.service.HospitalCommentsService;
import com.jumper.hospital.service.HospitalDoctorOrderRefundService;
import com.jumper.hospital.service.HospitalVisitService;
import com.jumper.hospital.service.InqHospitalConsultantReplyService;
import com.jumper.hospital.service.InqHospitalConsultantService;
import com.jumper.hospital.service.UserInfoService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.FastdfsUpload;
import com.jumper.hospital.utils.HttpPost;
import com.jumper.hospital.utils.HttpRequestUtils;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.DataMsg;
import com.jumper.hospital.vo.MsgBean;
import com.jumper.hospital.vo.PageData;
import com.jumper.hospital.vo.TodayMsg;
import com.jumper.hospital.vo.UrlMsg;
import com.jumper.hospital.vo.UserData;
import com.jumper.hospital.vo.UserMsg;
import com.jumper.hospital.vo.VOChatHistory;
import com.jumper.hospital.vo.VOChatInit;
import com.jumper.hospital.vo.VOChatRecord;
import com.jumper.hospital.vo.VOConsultRefund;
import com.jumper.hospital.vo.VOConsultantReplyInfo;
import com.jumper.hospital.vo.VOInqHospitalConsultant;
import com.jumper.hospital.vo.VORedisMessageInfo;
import com.jumper.hospital.vo.VoInqHospitalTodayConsult;

@Controller
@RequestMapping("/consult")
public class HospitalConsultController extends BaseController {
	@Autowired
	private InqHospitalConsultantService inqHospitalConsultantService;
	@Autowired
	private InqHospitalConsultantReplyService inqHospitalConsultantReplyService;
	@Autowired
	private HospitalCommentsService hospitalCommentsService;
	@Autowired
	private HospitalVisitService hospitalVisitService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private HospitalDoctorOrderRefundService hospitalDoctorOrderRefundServiceImpl;
	
	//利用logger获取提示信息
	private final static Logger logger = Logger.getLogger(HospitalConsultController.class);
	
	//今日问诊列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/consultList")
	public String consultList(ModelMap model,Page<InqHospitalConsultant> page){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Page pageData = inqHospitalConsultantService.findTodayConsultList(page,hospitalInfo.getId());
		logger.info("number of consult people："+pageData.getResult().size());
		//判断当前时间和服务结束时间，如果当前时间大于服务结束时间，那么显示结束问诊按钮
		List<VoInqHospitalTodayConsult> todayConsult = new ArrayList<VoInqHospitalTodayConsult>();
		List<VoInqHospitalTodayConsult> sorttodayConsult = new ArrayList<VoInqHospitalTodayConsult>();
		if(pageData.getResult()!=null&&pageData.getResult().size()>0){
			for (InqHospitalConsultant consult : (List<InqHospitalConsultant>)pageData.getResult()) {
				String content = consult.getContent();
				Date addTime = consult.getAddTime();
				int isVoice = 0;
				//通过咨询id去查询用户最新问题
				List<VOConsultantReplyInfo> AllList = getAllList(consult.getId());
				if(AllList!=null&&AllList.size()>0){
					for(int i=AllList.size()-1;i>0;i--){
						VOConsultantReplyInfo info = AllList.get(i);
						if(info.getTalker()==0){
							if(!"".equals(info.getMsgContent())&&info.getMsgContent()!=null){
								content = info.getMsgContent();
							}else{
								if(info.getIsVoice()==1){
									content = "您有一条新的语音消息！";
									isVoice = 1;
								}else if(info.getIsVoice()==0){
									content = "您有一条新的图片消息！";
									isVoice = 2;
								}
							}
							if(info.getTime()!=null&&!"".equals(info.getTime())){
								addTime = TimeUtils.getTimestampDate(info.getTime(), "yyyy-MM-dd HH:mm:ss");
							}
							break;
						}
					}
				}
				VoInqHospitalTodayConsult todays = getVOInqHospitalTodayConsultant(consult, admin.getUsername(),content,addTime);
				todays.setIsVoice(isVoice);
				todayConsult.add(todays);
			}
			try {
				List<Map<String, Object>>  lmap = sort("101_yy_"+hospitalInfo.getId());
				for (int j = 0; j < lmap.size(); j++) {
					for (int i = 0; i < todayConsult.size(); i++) {
					if(lmap.get(j).get("consultantId").toString().equals((todayConsult.get(i).getId().toString()))){
						//更新是否已经回复，和获取是否回复的状态
						if("1".equals(lmap.get(j).get("yesOrNo").toString())){
							//已回复
							if(Integer.valueOf("2").equals(todayConsult.get(i).getStatus())){
								todayConsult.get(i).setStatus(3);
								inqHospitalConsultantService.updateStatus(todayConsult.get(i).getId(), 3);
							}
						}else if("0".equals(lmap.get(j).get("yesOrNo").toString())){
							//未回复
							todayConsult.get(i).setStatus(2);
						}
						sorttodayConsult.add(todayConsult.get(i));
					}
				}
			}
			} catch (Exception e) {
				logger.info(e);
			}
		}
		
	
		pageData.setResult(sorttodayConsult);
		model.addAttribute("counts", pageData.getTotalCount());
		model.addAttribute("consultList", pageData);
		model.addAttribute("admin", admin);
		model.addAttribute("hospitalId", hospitalInfo.getId());
		pageLocation(model, "consult", "today", null);
		
		return "hospital/todayConsultList";
	}
	
	/*	//获取今日问诊详情
	@RequestMapping("/lookTodayDetails")
	public String lookTodayDetails(ModelMap model,String id){
		logger.info("consId："+id);
		//从前台传过来问诊问题id
		if(StringUtils.isNumeric(id)){
			Integer idInt = Integer.parseInt(id);
			//通过问题id查询当前问题信息
			InqHospitalConsultant inqHospitalConsultant = inqHospitalConsultantService.findConsultById(idInt);
			model.addAttribute("consult", inqHospitalConsultant);
			//获取当前登陆人员信息
			Admin admin = this.getLoginAdminInfo();
			//获得医院id和医院信息
			HospitalInfo hospitalInfo = admin.getHospitalInfo();
			model.addAttribute("hospitalInfo", hospitalInfo);
			//通过问题id查询该问题对应的所有回复的信息
			List<InqHospitalConsultantReply> replys = inqHospitalConsultantReplyService.findReplysById(idInt);
			//判断当前时间和服务结束时间，如果当前时间大于服务结束时间，那么显示结束问诊按钮(此处是在今日问诊详情中的结束按钮)
			int endVisit = getIfEndVisit();
			model.addAttribute("endVisit", endVisit);
			model.addAttribute("replys", replys);
			pageLocation(model, "consult", "today", null);
			return "hospital/todayConsultDetails";
		}	
		return null;
	}*/
	@SuppressWarnings("unchecked")
	/**
	 * 3.0
	 * 查看和某个用户历史聊天消息
	 * @param model
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/lookTodayDetails")
	public String lookTodayDetails(ModelMap model,HttpServletRequest req) {
		try { 
		String ids = req.getParameter("id");
		String userId = req.getParameter("userId");
		String consultId = req.getParameter("consultId");
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		String hospitalName = admin.getHospitalInfo().getName();
		if(Consts.JPUSH_VERSION.equals("4.0")){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("busCode", "50101");//50101
			map.put("fromUserId",ids);  //医院
			map.put("fromAccountType", "3");
			map.put("toUserId",  userId);   // 用户
			map.put("toAccountType","2");
			map.put("startTime","");
			map.put("endTime", "");
			map.put("pageNo", "1");
 			map.put("pageSize", "6");
			map.put("msgId", "0");
			map.put("consultantId", consultId);
			//http://192.168.0.2:8081/common/im/sel_message?busCode=10151&sender=yf_34&recevrer=ys_137&startTime=&endTime=&pageNo=1&pageSize=20
			//HttpClient httpClient = new HttpClient(Consts.CONSULT_IM_URL+"/im/get_msg_userid",map);
			//String post =  httpClient.post();
			//HttpPost httpClient = new HttpPost();
			String json = JSONObject.toJSONString(map);  
			logger.info("发送消息get_msg_userid:"+json);
			String post =  HttpPost.doPost(Consts.CONSULT_IM_URL+"/im/get_msg_userid",map);
			logger.info("返回消息get_msg_userid:"+post);
			List<VOConsultantReplyInfo> AllList = new ArrayList<VOConsultantReplyInfo>();
			if(post != null && post != ""){
			
				Map<String,Object> result = JsonUtils.toHashMap(post);
				if(result.get("msg").equals(1)){
					TodayMsg bean = JSONArray.parseObject(post, TodayMsg.class);
					DataMsg datamsg = new DataMsg();
					PageData pageData = new PageData();
					pageData = bean.getData();
					datamsg = pageData.getPages();
					List<VOConsultantReplyInfo> voclist = datamsg.getDataList();
					
					//先过滤一遍已经撤回的消息
					for (int i=0;i<voclist.size();i++) {
						if(null==voclist.get(i).getMsgType()){
							voclist.remove(i);
						}
					}
					
					for (VOConsultantReplyInfo volist : voclist) {
							

						
						Map<String, Object> map1 = new HashMap<String, Object>(); 
						String sendChatid = volist.getSendChatId();
						map1.put("chatId", sendChatid);
						map1.put("openid", "yh_"+userId);
						//HttpClient httpClient2 = new HttpClient(Consts.CONSULT_IM_URL+"/accounts/sel_userid_chatid",map1);
						//String post2 = httpClient2.post();
						//HttpPost httpClient2 = new HttpPost();
						String post2 =  HttpPost.doPost(Consts.CONSULT_IM_URL+"/accounts/sel_userid_chatid",map1);
						
						UserMsg usermsg = JSONArray.parseObject(post2, UserMsg.class);
						if(usermsg.getMsg() == 1 ){
							UserData userData   =  usermsg.getData();
							String userChatId = userData.getUserId();
							//UserInfo user = userInfoService.findUserInfoById(Integer.parseInt(userChatId));
							if(userId!=null){
								UserExtraInfo uei = userInfoService.findUserExtra(Integer.parseInt(userId));
								volist.setTalker(0);
								volist.setUserName(uei.getRealName());
							}
						}else{
							Map<String, Object> map2 = new HashMap<String, Object>();
							//String recevrerChatId = volist.getRecevrerChatId();
							map2.put("chatId", sendChatid);
							map2.put("openid", "yy_"+ids);
							//HttpPost httpClient3 = new HttpPost();
							String post3 =  HttpPost.doPost(Consts.CONSULT_IM_URL+"/accounts/sel_userid_chatid",map2);
							//HttpClient httpClient3 = new HttpClient(Consts.CONSULT_IM_URL+"/accounts/sel_userid_chatid",map2);
							//String post3 = httpClient3.post();
							
							UserMsg doctorMsg = JSONArray.parseObject(post3, UserMsg.class);
							if(doctorMsg.getMsg() == 1 ){
								UserData userData1 = doctorMsg.getData();
								String userChatId1 = userData1.getUserId();
									if(ids.equals(userChatId1)){
										volist.setDoctorName(hospitalName);
										volist.setTalker(1);
									}else{
										volist.setDoctorName(hospitalName);
										volist.setTalker(1);
									}
								}
						
							}
						
						
						String msgType = volist.getMsgType();
						if(null!=msgType){
							if(msgType.equals("TIMImageElem")){
								String msgContent= volist.getMsgContent();
								if(StringUtils.containsAny(msgContent, "height")){
									UrlMsg msg = new UrlMsg();
									JSONArray arr=JSONArray.parseArray(msgContent);//先转化成json数组
									JSONObject js=JSONObject.parseObject(arr.getString(0).toString());//获取数组第一个json的字符串 并转化成json对象
									msg.setHeight(js.getString("height"));
									msg.setWidth(js.getString("width"));
									msg.setOriginal(js.getString("original"));
									JSONObject js2=JSONObject.parseObject(arr.getString(1).toString());
									msg.setTheight(js2.getString("height"));
									msg.setTwidth(js2.getString("width"));
									msg.setThumbnail(js2.getString("thumbnail"));
									volist.setUrlmsg(msg);
								}
							}
						}
					}
					AllList.addAll(voclist);
					ComparatorIm sort = new ComparatorIm();
					Collections.sort(AllList, sort);
					//解析 拆解im返回对象
					Map<String,Object> pl = null;
					if( result.get("data")!= null ){
						pl = JsonUtils.toHashMap( result.get("data"));
					}
					Map<String,Object> da = null;
					if(pl.get("pages")!= null){
						da  = JsonUtils.toHashMap( pl.get("pages")); 
					}
					if(!da.get("dataList").toString().equals("[]")){
						VOConsultantReplyInfo info = datamsg.getDataList().get(datamsg.getDataList().size()-1);
						if(info.getTime()!=null){
							model.addAttribute("lastTime", info.getTime());
						}
					}
				}
				
			}
			
			

			
			InqHospitalConsultant consultant=inqHospitalConsultantService.get(Integer.parseInt(consultId));
			String content="";
			int status = 0;
			int evaluate=consultant.getEvaluate();
			if (consultant.getStatus() !=null) {
				status = consultant.getStatus();
			}
			if(consultant.getEvaluate()==1)
			{
				HospitalComments comments = hospitalCommentsService.getCommentByConsId(Integer.parseInt(consultId));
				if(comments!=null)
				{
					if(comments.getStatisfaction()!=null){
						if(comments.getStatisfaction()==1){
							content = "解答专业    "+comments.getContent();
						}else if(comments.getStatisfaction()==2){
							content = "有点帮助    "+comments.getContent();
						}else{
							content = "帮助不大    "+comments.getContent();
						}
					}else if(comments.getContent()!=null&&!"".equals(comments.getContent())){
						content=comments.getContent();
					}
				}else{
					content = "无";
				}
			}else {
				content="用户暂未评论！";
			}
		
			
			
			
			int endVisit = getIfEndVisits(consultant);
			//计算还有多少服务时间剩余
			Date addTime = consultant.getAddTime();
			int hours = (int) ((new Date().getTime()-addTime.getTime())/(1000*60*60));
			int leftTime = 48-hours;
			model.addAttribute("leftTime", leftTime);
			model.addAttribute("endVisit", endVisit);
			model.addAttribute("consult", consultant);
			model.addAttribute("evaluate", evaluate);
			model.addAttribute("content", content);
			model.addAttribute("detail", AllList);
			model.addAttribute("status", status);
			if(evaluate==0&&status!=5){
				model.addAttribute("flag", "today");
				pageLocation(model, "consult", "today", null);
			}else{
				model.addAttribute("flag", "end");
				pageLocation(model, "consult", "end", null);
			}
		}else{
			List<VOConsultantReplyInfo> AllList = getAllList(Integer.parseInt(ids));
			InqHospitalConsultant consultant=inqHospitalConsultantService.get(Integer.parseInt(ids));
			String content="";
			int status = 0;
			int evaluate=consultant.getEvaluate();
			if (consultant.getStatus() !=null) {
				status = consultant.getStatus();
			}
			if(consultant.getEvaluate()==1)
			{
				HospitalComments comments = hospitalCommentsService.getCommentByConsId(Integer.parseInt(ids));
				if(comments!=null)
				{
					if(comments.getStatisfaction()!=null){
						if(comments.getStatisfaction()==1){
							content = "解答专业    "+comments.getContent();
						}else if(comments.getStatisfaction()==2){
							content = "有点帮助    "+comments.getContent();
						}else{
							content = "帮助不大    "+comments.getContent();
						}
					}else if(comments.getContent()!=null&&!"".equals(comments.getContent())){
						content=comments.getContent();
					}
				}else{
					content = "无";
				}
			}else {
				content="用户暂未评论！";
			}
			VOConsultantReplyInfo info = AllList.get(AllList.size()-1);
			if(info.getTime()!=null){
				model.addAttribute("lastTime", info.getTime());
			}
			int endVisit = getIfEndVisits(consultant);
			//计算还有多少服务时间剩余
			Date addTime = consultant.getAddTime();
			int hours = (int) ((new Date().getTime()-addTime.getTime())/(1000*60*60));
			int leftTime = 48-hours;
			model.addAttribute("leftTime", leftTime);
			model.addAttribute("endVisit", endVisit);
			model.addAttribute("consult", consultant);
			model.addAttribute("evaluate", evaluate);
			model.addAttribute("content", content);
			model.addAttribute("detail", AllList);
			model.addAttribute("status", status);
			if(evaluate==0&&status!=5){
				model.addAttribute("flag", "today");
				pageLocation(model, "consult", "today", null);
			}else{
				model.addAttribute("flag", "end");
				pageLocation(model, "consult", "end", null);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
		}
		return "hospital/todayConsultDetails";
	}

	//获取结束问诊列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/endList")
	public String endList(ModelMap model,Page<InqHospitalConsultant> page){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Page pageData = (inqHospitalConsultantService.findAllConsultList(page,hospitalInfo.getId()));
		List<VOInqHospitalConsultant> voInqConsultantList = new ArrayList<VOInqHospitalConsultant>();
		//封装问诊问题和评价列表
		for (InqHospitalConsultant inqHospitalConsultant :(List<InqHospitalConsultant>)pageData.getResult()) {
			VOInqHospitalConsultant voInqHospitalConsultant=getVOInqHospitalConsultant(inqHospitalConsultant,admin.getUsername());
			voInqConsultantList.add(voInqHospitalConsultant);
		}
		pageData.setResult(voInqConsultantList);
		//获取当前医院的所有用户的咨询问题（去掉已经删除和已经结束的）
		model.addAttribute("hospitalId",hospitalInfo.getId());
		model.addAttribute("consultList", pageData);
		pageLocation(model, "consult", "end", null);
		return "hospital/allEndConsultList";
	}
	
	
	//通过姓名或者电话查询今日未结束问诊信息
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/searchConsult")
	public String searchConsult(ModelMap model,HttpServletRequest req,Page<InqHospitalConsultant> page){
		String searchKey = req.getParameter("searchKey");
		logger.info("phone or name of users to search:"+searchKey);
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Page pageData = inqHospitalConsultantService.findTodayConsultListByConds(page,hospitalInfo.getId(),searchKey);
		List<VoInqHospitalTodayConsult> todayConsult = new ArrayList<VoInqHospitalTodayConsult>();
		List<VoInqHospitalTodayConsult> sorttodayConsult = new ArrayList<VoInqHospitalTodayConsult>();
		for (InqHospitalConsultant consult : (List<InqHospitalConsultant>)pageData.getResult()) {
			String content = consult.getContent();
			Date addTime = consult.getAddTime();
			VoInqHospitalTodayConsult todays = getVOInqHospitalTodayConsultant(consult, admin.getUsername(),content,addTime);
			todayConsult.add(todays);
		}
		try {
			List<Map<String, Object>>  lmap = sort("101_yy_"+hospitalInfo.getId());
					for (int j = 0; j < lmap.size(); j++) {
						for (int i = 0; i < todayConsult.size(); i++) {
						if(lmap.get(j).get("consultantId").toString().equals((todayConsult.get(i).getId().toString()))){
							//更新是否已经回复，和获取是否回复的状态
							if("1".equals(lmap.get(j).get("yesOrNo").toString())){
								//已回复
								if(Integer.valueOf("2").equals(todayConsult.get(i).getStatus())){
									todayConsult.get(i).setStatus(3);
									inqHospitalConsultantService.updateStatus(todayConsult.get(i).getId(), 3);
								}
							}else if("0".equals(lmap.get(j).get("yesOrNo").toString())){
								//未回复
								todayConsult.get(i).setStatus(2);
							}
							sorttodayConsult.add(todayConsult.get(i));
						}
					}
				 }
			} catch (Exception e) {
				logger.info(e);
			}
		pageData.setResult(sorttodayConsult);
		//判断当前时间和服务结束时间，如果当前时间大于服务结束时间，那么显示结束问诊按钮
		//int endVisit = getIfEndVisit();
		//model.addAttribute("endVisit", endVisit);
		model.addAttribute("counts", pageData.getTotalCount());
		model.addAttribute("consultList", pageData);
		model.addAttribute("keyWord", searchKey);
		model.addAttribute("hospitalId",hospitalInfo.getId());
		pageLocation(model, "consult", "today", null);
		return "hospital/todayConsultList";
			
	}
	
	//获取结束问诊的详细信息
/*	@RequestMapping(value="/lookAllEndDetails",produces="text/html;charset=UTF-8")
	public String lookAllEndDetails(ModelMap model,String id,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		//从前台传过来问诊问题id
		logger.info("endConsultId:"+id);
		if(StringUtils.isNumeric(id)){
			Integer idInt = Integer.parseInt(id);
			HospitalComments comments = hospitalCommentsService.getCommentByConsId(idInt);
			//通过问题id查询当前问题信息
			InqHospitalConsultant inqHospitalConsultant = inqHospitalConsultantService.findConsultById(idInt);
			model.addAttribute("consult", inqHospitalConsultant);
			//获取当前登陆人员信息
			Admin admin = this.getLoginAdminInfo();
			//获得医院id和医院信息
			HospitalInfo hospitalInfo = admin.getHospitalInfo();
			model.addAttribute("hospitalInfo", hospitalInfo);
			//通过问题id查询该问题对应的所有回复的信息
			List<InqHospitalConsultantReply> replys = inqHospitalConsultantReplyService.findReplysById(idInt);
			model.addAttribute("replys", replys);
			model.addAttribute("comment", comments);
			pageLocation(model, "consult", "end", null);
			return "hospital/allConsultDetails";
		}	
		return null;
	}*/
	
	/**
	 * 医院回复用户的问题
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/hospitalConsultReply")
	public String hospitalConsultReply(ModelMap model,HttpServletRequest req){
		String ids = req.getParameter("id");
		//获取回复内容
		String hospitalReply = req.getParameter("content");
		logger.info("hospitalReply:"+hospitalReply);
		if(StringUtils.isNumeric(ids)){
			Integer consId = Integer.parseInt(ids);
			InqHospitalConsultant inqHospitalConsultant = inqHospitalConsultantService.findConsultById(consId);
			//将回复信息添加到数据库中
			inqHospitalConsultantReplyService.addHospitalConsultantReply(inqHospitalConsultant,consId,hospitalReply);
			//更新回复后问题表中的数据
			inqHospitalConsultant.setStatus(3);
			inqHospitalConsultant.setUpdateTime(TimeUtils.getCurrentTime());
			inqHospitalConsultantService.updateHospitalConsult(inqHospitalConsultant);
			
			String result = HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, "id=" + inqHospitalConsultant.getId()
					+ "&content=" + hospitalReply + "&title=" + inqHospitalConsultant.getHospitalInfo().getName()
					+ "&language=cn&Msg_type=10&user_type=1&user_msg=" + inqHospitalConsultant.getUserInfo().getId());
			MsgBean bean = JSONArray.parseObject(result, MsgBean.class);
        	if(bean.getMsg() != 1){
        		logger.info("问诊推送失败！");
        	}else{
        		logger.info("问诊推送成功！");
        	}
			/**添加推送给用户*/
			/*boolean flag=sendPushMsgInfo(inqHospitalConsultant.getId(), hospitalReply, inqHospitalConsultant.getHospitalInfo().getName(), "cn", 10, 1, inqHospitalConsultant.getUserInfo().getId()+"");
			if(flag)
			{
				logger.info("hospital send push message to user successful by id :"+inqHospitalConsultant.getId());
			}else {
				logger.error("hospital send push message to user failed by id :"+inqHospitalConsultant.getId());
			}
			logger.info("hospital send push message to user ："+result);*/
        	//计算还有多少服务时间剩余
    		Date addTime = inqHospitalConsultant.getAddTime();
    		int hours = (int) ((new Date().getTime()-addTime.getTime())/(1000*60*60));
    		int leftTime = 48-hours;
    		model.addAttribute("leftTime", leftTime);
			model.addAttribute("consult", inqHospitalConsultant);
			//获取当前登陆人员信息
			Admin admin = this.getLoginAdminInfo();
			//获得医院id和医院信息
			HospitalInfo hospitalInfo = admin.getHospitalInfo();
			model.addAttribute("hospitalInfo", hospitalInfo);
			//通过问题id查询该问题对应的所有回复的信息
			List<InqHospitalConsultantReply> replys = inqHospitalConsultantReplyService.findReplysById(consId);
			//判断当前时间和服务结束时间，如果当前时间大于服务结束时间，那么显示结束问诊按钮
			int endVisit = getIfEndVisits(inqHospitalConsultant);
			model.addAttribute("endVisit", endVisit);
			model.addAttribute("replys", replys);
			
			 List<VOConsultantReplyInfo> AllList = getAllList(consId);
			InqHospitalConsultant consultant=inqHospitalConsultantService.get(consId);
			String content="";
			int status = 0;
			int evaluate=consultant.getEvaluate();
			if (consultant.getStatus() !=null) {
				status = consultant.getStatus();
			}
			if(consultant.getEvaluate()==1)
			{
				HospitalComments comments = hospitalCommentsService.getCommentByConsId(consId);
				if(comments!=null)
				{
					content=comments.getContent();
				}
			}else {
				content="用户暂未评论！";
			}
			VOConsultantReplyInfo info = AllList.get(AllList.size()-1);
			if(info.getTime()!=null){
				model.addAttribute("lastTime", info.getTime());
			}
			model.addAttribute("flag", "today");
			model.addAttribute("consult", consultant);
			model.addAttribute("evaluate", evaluate);
			model.addAttribute("content", content);
			model.addAttribute("detail", AllList);
			model.addAttribute("status", status);
			pageLocation(model, "consult", "today", null);
			return "hospital/todayConsultDetails";
		}
		return null;
		
	}
	
	/**
	 * 查看问题用户是否已评价lookIfEvaluate
	 * @param model
	 * @param req
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/lookIfEvaluate")
	public String lookIfEvaluate(HttpServletRequest req){
		String ids = req.getParameter("id");
		if(StringUtils.isNumeric(ids)){
			Integer consId = Integer.parseInt(ids);
			InqHospitalConsultant inqHospitalConsultant = inqHospitalConsultantService.findConsultById(consId);
			if(inqHospitalConsultant.getEvaluate()==1){
				return "yes";
			}else{
				return "no";
			}
		}
		return null;
	}
	
	//条件查询结束列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/searchEndConsult")
	public String searchEndConsult(ModelMap model,HttpServletRequest req,Page<InqHospitalConsultant> page){
		String searchKey = req.getParameter("searchKey");
		String visitType = req.getParameter("visitType");
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Page pageData = (inqHospitalConsultantService.findAllConsultListByConds(page,hospitalInfo.getId(),searchKey,visitType));
		List<VOInqHospitalConsultant> voInqConsultantList = new ArrayList<VOInqHospitalConsultant>();
		for (InqHospitalConsultant inqHospitalConsultant :(List<InqHospitalConsultant>)pageData.getResult()) {
			VOInqHospitalConsultant voInqHospitalConsultant=getVOInqHospitalConsultant(inqHospitalConsultant,admin.getUsername());
			voInqConsultantList.add(voInqHospitalConsultant);
		}
		pageData.setResult(voInqConsultantList);
		//获取当前医院的所有用户的咨询问题（去掉已经删除和已经结束的）
		model.addAttribute("consultList", pageData);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("visitType", visitType);
		model.addAttribute("admin",admin);
		model.addAttribute("hospitalId",hospitalInfo.getId());
		pageLocation(model, "consult", "end", null);
		return "hospital/allEndConsultList";		
	}
	/*//结束问诊
	@RequestMapping("/toEndConsult")
	public String toEndConsult(HttpServletRequest req){
		String ids = req.getParameter("id");
		Integer consId = Integer.parseInt(ids);
		InqHospitalConsultant inqHospitalConsultant = inqHospitalConsultantService.findConsultById(consId);
		inqHospitalConsultant.setStatus(5);
		inqHospitalConsultantService.updateHospitalConsult(inqHospitalConsultant);
		return "redirect:consultList";
	}*/
	@ResponseBody
	@RequestMapping("/toEndConsult1")
	public String toEndConsult1(HttpServletRequest req){
		String ids = req.getParameter("id");
		Integer consId = Integer.parseInt(ids);
		InqHospitalConsultant inqHospitalConsultant = inqHospitalConsultantService.findConsultById(consId);
		inqHospitalConsultant.setStatus(5);
		inqHospitalConsultant.setUpdateTime(TimeUtils.getCurrentTime());
		inqHospitalConsultantService.updateHospitalConsult(inqHospitalConsultant);
		return "success";
	}
	
	
	//提醒评价
	@ResponseBody
	@RequestMapping(value="/hospitalConsultRmind",produces="text/html;charset=UTF-8")
	public String hospitalConsultRmind(HttpServletRequest req){
		String content = "我们结合您所描述的症状，已经给了您治疗建议。采纳建议，并给我们评价吧！";
		String ids = req.getParameter("id");
		Integer consId = Integer.parseInt(ids);
		InqHospitalConsultant inqHospitalConsultant = inqHospitalConsultantService.findConsultById(consId);
		inqHospitalConsultant.setUpdateTime(TimeUtils.getCurrentTime());
		inqHospitalConsultantService.updateHospitalConsult(inqHospitalConsultant);
		//将回复信息添加到数据库中
		inqHospitalConsultantReplyService.addHospitalConsultantReply(inqHospitalConsultant,consId,content);
		return "success";
	}
	
	

	//封装咨询和评价
	public VOInqHospitalConsultant getVOInqHospitalConsultant(InqHospitalConsultant inqHospitalConsultant,String userName){
		VOInqHospitalConsultant voInqHospitalConsultant=new VOInqHospitalConsultant();
		HospitalComments comments = hospitalCommentsService.getCommentByConsId(inqHospitalConsultant.getId());
		if(comments!=null&&!StringUtils.isEmpty(comments.getContent())){
			if(comments.getStatisfaction()==1){
				voInqHospitalConsultant.setComment("解答专业    "+comments.getContent());
			}else if(comments.getStatisfaction()==2){
				voInqHospitalConsultant.setComment("有点帮助    "+comments.getContent());
			}else{
				voInqHospitalConsultant.setComment("帮助不大    "+comments.getContent());
			}
			
		}else if(comments==null){
			voInqHospitalConsultant.setComment("用户暂未评价！");
		}else{
			if(comments.getStatisfaction()==1){
				voInqHospitalConsultant.setComment("解答专业    ");
			}else if(comments.getStatisfaction()==2){
				voInqHospitalConsultant.setComment("有点帮助    ");
			}else{
				voInqHospitalConsultant.setComment("帮助不大    ");
			}
		}
		if(!StringUtils.isEmpty(inqHospitalConsultant.getUserInfo().getId()+"")){
			voInqHospitalConsultant.setUserId(inqHospitalConsultant.getUserInfo().getId());
		}
		
		if(!StringUtils.isEmpty(inqHospitalConsultant.getAddTime()+"")){
			voInqHospitalConsultant.setAskTime(inqHospitalConsultant.getAddTime());
		}
		if(!StringUtils.isEmpty(inqHospitalConsultant.getUpdateTime()+"")){
			voInqHospitalConsultant.setEndTime(inqHospitalConsultant.getUpdateTime());
		}
		voInqHospitalConsultant.setId(inqHospitalConsultant.getId());
		
		if(!StringUtils.isEmpty(inqHospitalConsultant.getUserInfo().getUserImg())){
			voInqHospitalConsultant.setImgUrl(inqHospitalConsultant.getUserInfo().getUserImg());
		}
		if(!StringUtils.isEmpty(inqHospitalConsultant.getUserInfo().getNickName())){
			voInqHospitalConsultant.setNickName(inqHospitalConsultant.getUserInfo().getUserExitInfo().getRealName());
		}
		if(!StringUtils.isEmpty(inqHospitalConsultant.getUserInfo().getMobile())){
			voInqHospitalConsultant.setPhone(inqHospitalConsultant.getUserInfo().getMobile());
		}
		if(!StringUtils.isEmpty(userName)){
			voInqHospitalConsultant.setAdminName(userName);
		}
		voInqHospitalConsultant.setType(inqHospitalConsultant.getPayHospitalId());
		return voInqHospitalConsultant;
	}
	
	//判断是否要显示结束问诊按钮
	@SuppressWarnings("static-access")
	public int getIfEndVisit(){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		HospitalService hospitalService = hospitalVisitService.findHospitalService(hospitalInfo.getId());
		int endVisit = 0;
		if(hospitalService!=null){
			//判断当前时间和服务结束时间，如果当前时间大于服务结束时间，那么显示结束问诊按钮
			Calendar ca = Calendar.getInstance();
			Calendar ca2 = Calendar.getInstance();
			ca.setTime(TimeUtils.getCurrentTime());
			if(hospitalService.getEndTime()!=null&&!"".equals(hospitalService.getEndTime())){
				ca2.setTime(hospitalService.getEndTime());
				/*try {
					if(TimeUtils.daysBetween(new Date(), hospitalService.getAddTime())==0){*/
						int h1 = ca.get(Calendar.HOUR_OF_DAY);
						int h2 = ca2.get(Calendar.HOUR_OF_DAY);
						if(h2==0){
							h2 = 24;
						}
						if(h1>h2){
							endVisit = 1;//显示结束问诊按钮
						}else if(h1==h2){
							int m1 = ca.get(ca.MINUTE);
							int m2 = ca2.get(ca2.MINUTE);
							if(m1>=m2){
								endVisit = 1;
							}else{
								endVisit = 2;
							}
						}else{
							endVisit = 2;
						}
						/*}else{
						endVisit = 1;//显示结束问诊按钮
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}*/
				
			}
			
			
		}
		return endVisit;
	}
	@SuppressWarnings("static-access")
	public int getIfEndVisits(InqHospitalConsultant consult){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		HospitalService hospitalService = hospitalVisitService.findHospitalService(hospitalInfo.getId());
		int endVisit = 0;
		if(hospitalService!=null){
			//判断当前时间和服务结束时间，如果当前时间大于服务结束时间，那么显示结束问诊按钮
			Calendar ca = Calendar.getInstance();
			Calendar ca2 = Calendar.getInstance();
			ca.setTime(TimeUtils.getCurrentTime());
			if(hospitalService.getEndTime()!=null&&!"".equals(hospitalService.getEndTime())){
				ca2.setTime(hospitalService.getEndTime());
				try {
					if(TimeUtils.daysBetween(new Date(), consult.getAddTime())==0){
						int h1 = ca.get(Calendar.HOUR_OF_DAY);
						int h2 = ca2.get(Calendar.HOUR_OF_DAY);
						if(h2==0){
							h2 = 24;
						}
						if(h1>h2){
							endVisit = 1;//显示结束问诊按钮
						}else if(h1==h2){
							int m1 = ca.get(ca.MINUTE);
							int m2 = ca2.get(ca2.MINUTE);
							if(m1>=m2){
								endVisit = 1;
							}else{
								endVisit = 2;
							}
						}else{
							endVisit = 2;
						}
					}else{
						endVisit = 1;//显示结束问诊按钮
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
			
			
		}
		return endVisit;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/searchEndConsultByType")
	public String searchEndConsultByType(ModelMap model,HttpServletRequest req,Page<InqHospitalConsultant> page){
		String visitType = req.getParameter("type");
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Page pageData = inqHospitalConsultantService.findAllConsultListByType(page,hospitalInfo.getId(),visitType);
		List<VOInqHospitalConsultant> voInqConsultantList = new ArrayList<VOInqHospitalConsultant>();
		for (InqHospitalConsultant inqHospitalConsultant :(List<InqHospitalConsultant>)pageData.getResult()) {
			VOInqHospitalConsultant voInqHospitalConsultant=getVOInqHospitalConsultant(inqHospitalConsultant,admin.getUsername());
			voInqConsultantList.add(voInqHospitalConsultant);
		}
		pageData.setResult(voInqConsultantList);
		//获取当前医院的所有用户的咨询问题（去掉已经删除和已经结束的）
		model.addAttribute("consultList", pageData);
		model.addAttribute("visitType", Integer.parseInt(visitType));
		pageLocation(model, "consult", "end", null);
		return "hospital/allEndConsultList";	
	}

	/**
	 *  医院回复推送
	 * @param id 推送消息id
	 * @param content 推送消息内容
	 * @param title 推送标题
	 * @param language 语言类别 cn/en/doc
	 * @param Msg_type 消息类型 推送消息的类型：0：表示普通提醒消息；1：医生回复推送提醒；2：新闻推送消息;3:春雨;4:添加咨询;5:预约挂号;6:私人医生；7:远程监控;8:监控PDF结果；9：医生审核通过;10:医院问诊;11:监测聊天窗口
     * @param user_type 推送类型：0：全部推送；1单推
	 * @param user 用户标记（即用户的ID）
	 * @return
	 */
	public boolean  sendPushMsgInfo(int id,String content,String title,String language,int Msg_type,int user_type,String ...user_msg){
		try {
			List<VORedisMessageInfo> list=new ArrayList<VORedisMessageInfo>();
			VORedisMessageInfo vo=new VORedisMessageInfo();
			vo.putValues(id, content, title, language,Msg_type,user_type,user_msg[0]);
			list.add(vo);
			ObjectMapper mapper = new ObjectMapper();
			 Writer strWriter = new StringWriter();
			 mapper.writeValue(strWriter, list);
			 final String retJSON = strWriter.toString();
//		 System.out.println(retJSON);
//	 String encodeString = URLEncoder.encode(retJSON, "UTF-8");
			 /**redis处理*/
			 //redisUtil.lpush("push",retJSON);
			 return true;
		}catch (Exception e) {
//			e.printStackTrace();
			logger.error("sendPushMsgInfo fialed,the error msg is :"+e.getMessage());
			return false;
		}
	}
	@ResponseBody
	@RequestMapping("/checkEvaluate")
	public String checkEvaluate(HttpServletRequest req){
		String ids = req.getParameter("consId");
		if(ids!=null&&!"".equals(ids)){
			InqHospitalConsultant inqHospitalConsultant = inqHospitalConsultantService.get(Integer.parseInt(ids));
			if(inqHospitalConsultant.getEvaluate()==1){
				return "Y";
			}else{
				return "N";
			}
		}
		return "error";
	}
	
	
	@SuppressWarnings("unchecked")
	//@RequestMapping("/freshChats")
	public void freshChats() throws IOException{
		HttpServletRequest req= getRequest();
		HttpServletResponse resp =  getResponse();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String consultIds = req.getParameter("consultId");
		String lastTime = req.getParameter("lastTime");
		String result = "";
		if(consultIds!=null&&!"".equals(consultIds)){
			int consultId = Integer.parseInt(consultIds);
			/*//根据咨询id查询最新的医院端回复
			InqHospitalConsultantReply reply_hospital = inqHospitalConsultantReplyService.findHospitalReplysById(consultId);
			if(reply_hospital!=null){
				InqHospitalConsultantReply reply = inqHospitalConsultantReplyService.findUserReplysById(consultId,reply_hospital);
			}
			//找出比医院端时间更新的记录*/
			InqHospitalConsultant consultant=inqHospitalConsultantService.get(consultId);
			/*List<VOConsultantReplyInfo> AllList = getAllList(consultId);
			VOConsultantReplyInfo replyInfo = AllList.get(AllList.size()-1);*/
			List<VOConsultantReplyInfo> replys = new ArrayList<VOConsultantReplyInfo>();
			List<InqHospitalConsultantReply> replyList = inqHospitalConsultantReplyService.findUserReplysById(consultId,lastTime);
			if(replyList!=null&&replyList.size()>0){
				for (InqHospitalConsultantReply hospitalConsultant : replyList) {
					VOConsultantReplyInfo VOconsultant=new VOConsultantReplyInfo();
					if(StringUtils.isNotEmpty(hospitalConsultant.getReplyContent()))
					{
						VOconsultant.setMsgContent(hospitalConsultant.getReplyContent());
					}
					if(StringUtils.isNotEmpty(hospitalConsultant.getReplyFileUrl()))
					{
						VOconsultant.setImg_url(new String[]{Consts.BASE_FILE_URL+hospitalConsultant.getReplyFileUrl()});
						if (hospitalConsultant.getReplyFileUrl().contains(".mp3")) { // 语音消息
							VOconsultant.setIsVoice(1);
						} else {
							VOconsultant.setIsVoice(0);
						}
					}
					VOconsultant.setTalker(hospitalConsultant.getTalker());
					VOconsultant.setTime(hospitalConsultant.getAddTime().toString());
					if(hospitalConsultant.getTalker()==0)
					{
						if(consultant.getUserInfo().getUserImg()!=null){
							if(consultant.getUserInfo().getUserImg().contains("http"))
								VOconsultant.setUser_img(consultant.getUserInfo().getUserImg());
							else{
								VOconsultant.setUser_img(Consts.BASE_FILE_URL+consultant.getUserInfo().getUserImg());
							}
						}
							
						if(!StringUtils.isEmpty(consultant.getUserInfo().getNickName()))	
							VOconsultant.setUserName(consultant.getUserInfo().getNickName());
						else{
							if(consultant.getUserInfo().getMobile()!=null)
								VOconsultant.setUserName("**"+consultant.getUserInfo().getMobile().substring(8,11));
							else {
								VOconsultant.setUserName("");
							}
						}
					}else {
						if(StringUtils.isNotEmpty(consultant.getHospitalInfo().getImgUrl()))
						{
							VOconsultant.setUser_img(Consts.BASE_FILE_URL+consultant.getHospitalInfo().getImgUrl());
						}else {
							VOconsultant.setUser_img(Consts.BASE_FILE_URL+"/group1/M00/02/A2/CnQsP1YZAoWATE-EAAAbX0KYupY423.png");
						}
						
						if(consultant.getHospitalInfo().getName()!=null)	
							VOconsultant.setUserName(consultant.getHospitalInfo().getName());
					}
					replys.add(VOconsultant);
				}
				ComparatorList sort = new ComparatorList();
				Collections.sort(replys, sort);
				for (VOConsultantReplyInfo consultantReplyInfo : replys) {
//					TimeUtils.getInterval(TimeUtils.getTimeStampNumberFormat(TimeUtils.getTimestampDate(consultantReplyInfo.getTime(), "yyyy-MM-dd HH:mm:ss")))
					consultantReplyInfo.setTime(TimeUtils.getTimestampDate(consultantReplyInfo.getTime(), "yyyy-MM-dd HH:mm:ss")+"");
				}
				Gson gson = new Gson();
//				String msgBean = gson.toJson(replys.get(0),  new TypeToken<VOConsultantReplyInfo>() {}.getType());
				String msg = gson.toJson(replys);
				logger.info("there is new message here :"+msg);
				result = msg;
			}
		}
		resp.getWriter().write(result);
		
	}
	

	private VoInqHospitalTodayConsult getVOInqHospitalTodayConsultant(
				InqHospitalConsultant consult, String username, String content, Date addTime) {
		VoInqHospitalTodayConsult vohosConsult = new VoInqHospitalTodayConsult();
		vohosConsult.setId(consult.getId());
		if(!StringUtils.isEmpty(username)){
			vohosConsult.setAdminName(username);
		}
		if(!StringUtils.isEmpty(consult.getContent())){//内容为最新内容
			vohosConsult.setContent(content);
		}
		if(!StringUtils.isEmpty(consult.getAddTime()+"")){
			vohosConsult.setAskTime(addTime);//变为修改时间
			vohosConsult.setEndVisit(getIfEndVisits(consult));
		}
		if(!StringUtils.isEmpty(consult.getStatus()+"")){
			vohosConsult.setStatus(consult.getStatus());
		}
		if(consult.getUserInfo()!=null&&consult.getUserInfo().getUserImg()!=null){
			vohosConsult.setImgUrl(consult.getUserInfo().getUserImg());
		}
		
		if(consult.getUserInfo()!=null){
			vohosConsult.setUserId(consult.getUserInfo().getId());
			UserExtraInfo extraInfo = consult.getUserInfo().getUserExitInfo();
			if(extraInfo!=null&&!StringUtils.isEmpty(extraInfo.getRealName())){
				vohosConsult.setNickName(extraInfo.getRealName());
			}else if(!StringUtils.isEmpty(consult.getUserInfo().getNickName())){
				vohosConsult.setNickName(consult.getUserInfo().getNickName());
			}
		}
		if(consult.getUserInfo()!=null&&!StringUtils.isEmpty(consult.getUserInfo().getMobile())){
			vohosConsult.setPhone(consult.getUserInfo().getMobile());
		}
		vohosConsult.setType(consult.getPayHospitalId());
		return vohosConsult;
	}
	
	@SuppressWarnings("unchecked")
	private List<VOConsultantReplyInfo> getAllList(int consultantId){
		List<VOConsultantReplyInfo> AllList = new ArrayList<VOConsultantReplyInfo>();
		// 咨询的问题列表
		InqHospitalConsultant consultant=inqHospitalConsultantService.get(consultantId);
		VOConsultantReplyInfo question=new VOConsultantReplyInfo();
		question.setMsgContent(consultant.getContent());
		if(StringUtils.isNotEmpty(consultant.getFileUrl()))
		{
			String file=consultant.getFileUrl();
			String []url=file.split(";");
			String []urlList=new String[url.length];
			for (int i=0;i<url.length;i++) {
				urlList[i]=Consts.BASE_FILE_URL+url[i];
			}
			question.setImg_url(urlList);
		}else {
			question.setImg_url(new String[0]);
		}
		question.setTalker(0);
		question.setTime(consultant.getAddTime().toString());
		if(!StringUtils.isEmpty(consultant.getUserInfo().getUserImg()))
		{
			if(consultant.getUserInfo().getUserImg().contains("http"))
				question.setUser_img(consultant.getUserInfo().getUserImg());
			else {
				question.setUser_img(Consts.BASE_FILE_URL+consultant.getUserInfo().getUserImg());
			}
			
		}
		if(!StringUtils.isEmpty(consultant.getUserInfo().getNickName()))	
			question.setUserName(consultant.getUserInfo().getNickName());
		else{
			if(!StringUtils.isEmpty(consultant.getUserInfo().getMobile()))
				question.setUserName("**"+consultant.getUserInfo().getMobile().substring(8,11));
			else {
				question.setUserName("");
			}
		}
		AllList.add(question);
		Set<InqHospitalConsultantReply> set=consultant.getConsultReplys();
		for (InqHospitalConsultantReply hospitalConsultant : set) {
			VOConsultantReplyInfo VOconsultant=new VOConsultantReplyInfo();
			if(StringUtils.isNotEmpty(hospitalConsultant.getReplyContent()))
			{
				VOconsultant.setMsgContent(hospitalConsultant.getReplyContent());
			}
			if(StringUtils.isNotEmpty(hospitalConsultant.getReplyFileUrl()))
			{
				VOconsultant.setImg_url(new String[]{Consts.BASE_FILE_URL+hospitalConsultant.getReplyFileUrl()});
				if (hospitalConsultant.getReplyFileUrl().contains(".mp3")) { // 语音消息
					VOconsultant.setIsVoice(1);
				} else {
					VOconsultant.setIsVoice(0);
				}
			}
			VOconsultant.setTalker(hospitalConsultant.getTalker());
			VOconsultant.setTime(hospitalConsultant.getAddTime().toString());
			if(hospitalConsultant.getTalker()==0)
			{
				if(consultant.getUserInfo().getUserImg()!=null){
					if(consultant.getUserInfo().getUserImg().contains("http"))
						VOconsultant.setUser_img(consultant.getUserInfo().getUserImg());
					else{
						VOconsultant.setUser_img(Consts.BASE_FILE_URL+consultant.getUserInfo().getUserImg());
					}
				}
					
				if(!StringUtils.isEmpty(consultant.getUserInfo().getNickName()))	
					question.setUserName(consultant.getUserInfo().getNickName());
				else{
					if(consultant.getUserInfo().getMobile()!=null)
						question.setUserName("**"+consultant.getUserInfo().getMobile().substring(8,11));
					else {
						question.setUserName("");
					}
				}
			}else {
				if(StringUtils.isNotEmpty(consultant.getHospitalInfo().getImgUrl()))
				{
					VOconsultant.setUser_img(Consts.BASE_FILE_URL+consultant.getHospitalInfo().getImgUrl());
				}else {
					VOconsultant.setUser_img(Consts.BASE_FILE_URL+"/group1/M00/02/A2/CnQsP1YZAoWATE-EAAAbX0KYupY423.png");
				}
				
				if(consultant.getHospitalInfo().getName()!=null)	
					VOconsultant.setUserName(consultant.getHospitalInfo().getName());
			}
			AllList.add(VOconsultant);
		}
		ComparatorList sort = new ComparatorList();
		Collections.sort(AllList, sort);
		for (VOConsultantReplyInfo consultantReplyInfo : AllList) {
//			TimeUtils.getInterval(TimeUtils.getTimeStampNumberFormat(TimeUtils.getTimestampDate(consultantReplyInfo.getTime(), "yyyy-MM-dd HH:mm:ss")))
			consultantReplyInfo.setTime(TimeUtils.getTimestampDate(consultantReplyInfo.getTime(), "yyyy-MM-dd HH:mm:ss")+"");
		}
		return AllList;
	}

	/**
	 * 退费首页
	 * @param model
	 * @param type
	 * @param state
	 * @param orderId
	 * @param page
	 * @return
	 */
	@RequestMapping("refund")
	public String refundIndex(ModelMap model, Integer type, Integer state, String orderId, Page<VOConsultRefund> page){
		if(type == null){
			type = 1;
			state = 0;
		}
		Admin admin = getLoginAdminInfo();
		Page<VOConsultRefund> pageData = hospitalDoctorOrderRefundServiceImpl.getConsultRefundDataList(type, state, admin.getHospitalInfo().getId(), orderId, page);
		model.put("page", pageData);
		model.put("orderId", orderId);
		model.put("refundType", type);
		model.put("state", state);
		pageLocation(model, "consult", "refund", null);
		return "consult/refund_index";
	}
	/**
	 * 进入问诊详情
	 * @param model
	 * @param conId
	 * @return
	 */
	@RequestMapping("inq")
	public String inqConsultDetail(ModelMap model, Integer conId){
		InqHospitalConsultant ihc = inqHospitalConsultantService.getInqConsultantByConOrderId(conId);
		Integer inqId = ihc == null ? 0 : ihc.getId();
		model.put("id", inqId);
		return "redirect:/consult/lookTodayDetails";
	}
	/**
	 * 拒绝退费
	 * @param id
	 * @param reason
	 */
	@RequestMapping("refuse")
	public void refundRefuse(Integer id, String reason){
		if(id == null || id == 0 || StringUtils.isEmpty(reason)){
			JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
			return;
		}
		String result = hospitalDoctorOrderRefundServiceImpl.doRefundRefuse(getLoginAdminInfo(), id, reason);
		JsonUtils.render(getResponse(), result);
	}
	/**
	 * 点击确定请求
	 * @param id
	 */
	@RequestMapping("execRefund")
	public void refundHandler(Integer id, Integer type){
		String result = hospitalDoctorOrderRefundServiceImpl.doRefund(getLoginAdminInfo(), id, type);
		JsonUtils.render(getResponse(), result);
	}
	
	
	@RequestMapping("index")
	public String index(ModelMap model, VOChatRecord vOChatRecord){
		System.out.println(vOChatRecord);
		model.addAttribute("vOChatRecord", vOChatRecord);
		return "hospital/index";
	}
	
	/**
	 * 初始化IM KEY
	 * @param id
	 */
	/*@RequestMapping("init_data")
	public void init_data(String imAccount){
		try {
			//获取医院登陆信息
			logger.info("imAccount参数值为:"+imAccount);
			Admin admin = getLoginAdminInfo();
			Integer hospitalId = admin.getHospitalInfo().getId();
			CommonReturnMsg hospitalMsg = chatUserService.getChatIdByUserId(hospitalId.toString(), 3);
			if (hospitalMsg.getMsg() == 0){
				UserBaseInfo userBaseInfo = new UserBaseInfo(admin.getHospitalInfo().getId().toString(),admin.getHospitalInfo().getName(), 1, admin.getHospitalInfo().getImgUrl(), 3);
				hospitalMsg = chatUserService.addChatByUserInfo(userBaseInfo);
			}
			ChatUserBo chatUserBo = (ChatUserBo) hospitalMsg.getData();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("imAccount", chatUserBo.getChatId());
			HttpClient httpClient = new HttpClient(Consts.CONSULT_IM_URL+"/base/init_data",params);
			String post = httpClient.post();
			JsonUtils.render(getResponse(), post);
		} catch (Exception e) {
			logger.info(e);
		}
	}*/
	/**
	 * 添加IM好友
	 * @param id
	 */
	/*@RequestMapping("friend_import")
	public void friend_import(HttpServletRequest request ,  String imAccount, String senderNickName, String senderHeadUrl, String friendAccount, String nickName, String headUrl){
		try {
			logger.info("接收参数值为:"+request.getParameterMap());
			//获取医院登陆信息
			Admin admin = getLoginAdminInfo();
			imAccount = StringUtils.isBlank(imAccount) ? "yy_"+admin.getHospitalInfo().getId() : imAccount;
			senderNickName = StringUtils.isBlank(senderNickName) ? admin.getHospitalInfo().getName() : senderNickName;
			senderHeadUrl = StringUtils.isBlank(senderHeadUrl) ? Consts.BASE_FILE_URL+admin.getHospitalInfo().getImgUrl() : senderHeadUrl;
			//拼装IM请求参数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("busCode", "50101");
			map.put("imAccount", imAccount);
			map.put("senderNickName", senderNickName);
			map.put("senderHeadUrl", senderHeadUrl);
			map.put("friendAccount", friendAccount);
			map.put("nickName", nickName);
			map.put("headUrl", headUrl);
			String jsonStr = JsonUtils.write2String(map);
			jsonStr = URLEncoder.encode(jsonStr, "UTF-8");
			String str = DesEncryptUtils.encrypt(jsonStr);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("imAccount", str);
			HttpClient httpClient = new HttpClient(Consts.CONSULT_IM_URL+"/base/friend_import",map);
			String post = httpClient.post();
			JsonUtils.render(getResponse(), post);
		} catch (Exception e) {
			logger.info(e);
		}
	}*/
	
    /**
	 * 聊天入口
	 * @param model
	 * @param init
	 * @return
	 */
/*	@RequestMapping("chat")
	public void chat(ModelMap model,VOChatInit init){
		System.out.println(init);
		try {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("param", JsonUtils.toJSONObject(init));
			String url ="http://192.168.0.2:8081/common/chat/init_data";
//			String url =Consts.CONSULT_IM_URL + "/chat/init_data";
			HttpClient httpClient = new HttpClient(url,map1);
			String post = httpClient.post();
			System.out.println(post);
			//装载到页面数据
			String jsonData= JsonUtils.toJSONObject(post).toString();
			JsonUtils.render(getResponse(), jsonData);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}*/
	
	/**
	 * 聊天入口,这个接口可以初始化账号,如果账号不存在可以添加一个IM账号
	 * @param model
	 * @param init
	 * @return
	 */
	@RequestMapping("chat")
	public void chat(ModelMap model,VOChatInit init){
		System.out.println(init);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			//map1=JsonUtils.toJSONObject(init);
			map.put("fromUserId", init.getFromUserId());
			map.put("fromNickName", init.getFromNickName());
			map.put("fromHeadUrl", init.getFromHeadUrl());
			map.put("fromUserType", init.getFromUserType().toString());
			map.put("toUserId", init.getToUserId());
			map.put("toNickName", init.getToNickName());
			map.put("toHeadUrl", init.getToHeadUrl());
			map.put("toUserType", init.getToUserType().toString());
			map.put("userType", init.getUserType().toString());
			map.put("color", init.getColor().toString());
			map.put("busCode", init.getBusCode().toString());
			map.put("consultantId", init.getConsultantId());
			
/*			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("param", JsonUtils.toJSONObject(map));*/
			
			//String param=JsonUtils.toJSONObject(map).toString();
			//HttpPost post = new HttpPost();
			String reslutJsonStr = HttpPost.doPost(Consts.CONSULT_IM_URL+"/chat/init_data?param="+JsonUtils.toJSONObject(map),"");
			
//			HttpClient httpClient = new HttpClient(Consts.CONSULT_IM_URL+"/chat/init_data",map1);
//			String post = httpClient.post();
			//装载到页面数据
			String jsonData= JsonUtils.toJSONObject(reslutJsonStr).toString();
			JsonUtils.render(getResponse(), jsonData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * 当前医院新增医院的IM用户 ，暂时不需要添加用户
	@RequestMapping("addIMAccount")
	public void addIMAccount(){
		try{
			Admin admin = getLoginAdminInfo();
			String hospitalId=admin.getHospitalInfo().getId().toString();
			String appId="101"; //医院的appid默认为101
			String openId="yy_"+hospitalId;
			String nickName=admin.getHospitalInfo().getName();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", hospitalId);
			map.put("appid", appId);
			map.put("openid", openId);
			map.put("nick", nickName);
			//HttpPost post = new HttpPost();
			String reslutJsonStr = HttpPost.doPost(Consts.CONSULT_IM_URL+"/accounts/add",map);
			Map<String,Object> resultMap=JsonUtils.toHashMap(reslutJsonStr);
			String resultData=resultMap.get("data").toString();
			JsonUtils.render(getResponse(), resultData);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
		}
	}*/
	
	/**
	 * 获取当前医院webIM登陆需要的信息
	 */
	@RequestMapping("getWebIMLoginInfo")
	public void getWebIMLoginInfo(){
		try{
			
			//获取chatID需要从IM那边获取
			Admin admin = getLoginAdminInfo();
			String hospitalId =admin.getHospitalInfo().getId().toString();
			String imAccount="101_yy_"+hospitalId;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("imAccount", imAccount);
			//HttpPost post = new HttpPost();
			String reslutJsonStr =HttpPost.doPost(Consts.CONSULT_IM_URL+"/base/init_data",map);
			Map<String,Object> reslutMap = JsonUtils.toHashMap(reslutJsonStr);
			String reslutData= reslutMap.get("data").toString();
			JsonUtils.render(getResponse(), reslutData);
		}catch (Exception e) {
			logger.info(e);
		}
	}
	
	
	/**
	 * 查询聊天记录
	 * @param id
	 */
	@RequestMapping("sel_message")
	public void sel_message(@ModelAttribute VOChatHistory vOChatHistory ){
		try {
			//System.out.println(request.getParameter("busCode")+"================="+request.getParameter("recevrer")+"=============="+request.getParameter("consultantId"));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("busCode", vOChatHistory.getBusCode());//50101(医院问诊)  60102(胎心及时问诊)
			map.put("sender",vOChatHistory.getSender());  //医院
			map.put("recevrer",vOChatHistory.getRecevrer());   // 用户
			map.put("startTime", vOChatHistory.getStartTime());
			map.put("endTime", vOChatHistory.getEndTime());
			map.put("pageNo", vOChatHistory.getPageNo() == null ? "1" : vOChatHistory.getPageNo().toString());
			map.put("pageSize", vOChatHistory.getPageSize() == null ? "10" : vOChatHistory.getPageSize().toString());
			map.put("msgId", vOChatHistory.getMsgId());
			map.put("consultantId",vOChatHistory.getConsultantId());
		
			//http://192.168.0.2:8081/common/im/sel_message?busCode=10151&sender=yf_34&recevrer=ys_137&startTime=&endTime=&pageNo=1&pageSize=20
			//HttpClient httpClient = new HttpClient(Consts.CONSULT_IM_URL+"/im/sel_message",map);
			//String post = httpClient.post();
			//HttpPost post = new HttpPost();
			String post3 = HttpPost.doPost(Consts.CONSULT_IM_URL+"/im/sel_message",map);
			System.out.println(post3);
			JsonUtils.render(getResponse(), post3);
		} catch (Exception e) {
			logger.info(e);
		}
	}
	
	//查看是否有回复
	//调用接口查看最新回复时间 如果为空 则没有回复如果有数据则有新的回复,这个是老方法，速度太慢了，废弃没用了
	@Deprecated
	public boolean isBack(String consultantId){
		//查询用户chatId
		try{
		String recevrer = null ;
		Admin admin = getLoginAdminInfo();
		Map<String, Object> map2 = new HashMap<String, Object>(); 
		map2.put("openid", "yy_"+admin.getHospitalInfo().getId());
		map2.put("accountsType","1");
		String post3 = HttpPost.doPost(Consts.CONSULT_IM_URL+"/accounts/sel",JsonUtils.toJSONObject(map2));
		if(post3 != null && post3 != ""){
			Map<String,Object> msg = JsonUtils.toHashMap(post3);
			
			if(msg.get("msg").equals(1)){
				Map<String,Object> data = JsonUtils.toHashMap(msg.get("data"));
				recevrer = (String) data.get("chatId");
			}
		}else{
			//如果没有则需要新生成chatId
			Map<String, Object> addmap = new HashMap<String, Object>(); 
			String id = admin.getHospitalInfo().getId().toString();
			String hospitalName = admin.getHospitalInfo().getName();
			String faceUrl = admin.getHospitalInfo().getImgUrl();
			addmap.put("userId", id);
			addmap.put("appid", 101);
			addmap.put("openid", "yy_"+id);
			addmap.put("nick", hospitalName);
			addmap.put("faceUrl", faceUrl);
			String addpost = HttpPost.doPost(Consts.CONSULT_IM_URL+"/accounts/add",addmap);
			//HttpClient addhttpClient = new HttpClient(Consts.CONSULT_IM_URL+"/accounts/add",addmap);
			//String addpost = addhttpClient.post();
			Map<String,Object> msg = JsonUtils.toHashMap(addpost);
			if(msg.get("msg").equals(1)){
				Map<String,Object> data = JsonUtils.toHashMap(msg.get("data"));
				recevrer = (String) data.get("openid");
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("busType", "1");//50101
		map.put("recevrer",recevrer);  //医院
		
		map.put("consultantId", consultantId);
		String postResult = HttpPost.doPost(Consts.CONSULT_IM_URL+"/im/sel_latest_reply",map);
		//HttpClient httpClient = new HttpClient(Consts.CONSULT_IM_URL+"/im/sel_latest_reply",map);
		//String post = httpClient.post();
		Map<String,Object> result = JsonUtils.toHashMap(postResult);
		if(result.get("msg").equals(1)){
			if(result.get("data").toString().equals("[]") ){
				return false ;
			}else{
				return true;
			}
		}
		}catch(Exception e){
			logger.info(e);
		}
		return false;
	}
	
	public List<Map<String, Object>> sort(String recevrer) throws Exception{
		Map<String, Object> addmap = new HashMap<String, Object>(); 
		addmap.put("busCode", "50101");
		addmap.put("recevrer", recevrer);
		String postResult = HttpPost.doPost(Consts.CONSULT_IM_URL+"/im/sel_latest_question",addmap);
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(postResult!=null && postResult!=""){
			result = JsonUtils.toHashMap(postResult);
			Object obj = result.get("data");
			list = JsonUtils.toList(obj);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
		
		return list;
	}
	//查询当前问题是否结束或者退费
	@RequestMapping("isReturns")
	public void isReturns(Integer  idInt) {
		Integer status=null;
			try{
				InqHospitalConsultant inqHospitalConsultant=inqHospitalConsultantService.findConsultById(idInt);
				status= inqHospitalConsultant.getStatus();
				JsonUtils.render(getResponse(), status);
			}catch (Exception e) {
					logger.info(e);
			}
		} 
	
	public static void main(String[] args) throws Exception {
		/*String param= "{ \"toUserType\":\"2\", \"color\":\"1\", \"toUserId\":\"11846\", \"busCode\":\"50101\", \"fromUserType\":\"3\", \"consultantId\":\"985\", \"toNickName\":\"乐乐\", \"fromUserId\":\"5157\", \"userType\":\"1\", \"fromNickName\":\"本人专属医院\" }";
		HttpClient httpClient = new HttpClient(Consts.CONSULT_IM_URL+"/chat/init_data?param="+param,null);
		String post = httpClient.post();
		//装载到页面数据
		String jsonData= JsonUtils.toJSONObject(post).toString();
		System.out.println(jsonData);*/
		
		/*try {
			//System.out.println(request.getParameter("busCode")+"================="+request.getParameter("recevrer")+"=============="+request.getParameter("consultantId"));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("busCode", "50101");//50101(医院问诊)  60102(胎心及时问诊)
			map.put("sender","101_yh_10491");  //医院
			map.put("recevrer","101_yh_10277");   // 用户
			map.put("content", "cnm");
			map.put("type", "1");
			map.put("groupId", "");
			map.put("consultantId","12315413123");
		
			//http://192.168.0.2:8081/common/im/sel_message?busCode=10151&sender=yf_34&recevrer=ys_137&startTime=&endTime=&pageNo=1&pageSize=20
			//HttpClient httpClient = new HttpClient(Consts.CONSULT_IM_URL+"/im/sel_message",map);
			//String post = httpClient.post();
			//HttpPost post = new HttpPost();
			Gson gson = new Gson();
			String s = gson.toJson(map,Gson.class);
			System.out.println(Consts.CONSULT_IM_URL);
			String post3 = HttpPost.doPost("http://192.168.0.2:8081/common/im/send_msg",s);
			System.out.println(post3);
		} catch (Exception e) {
			logger.info(e);
		}*/
		
		
		String s = "hello 慕课网";
		System.out.println(s.length());
	}
	/**
	 * 查询聊天记录
	 * @param id
	 */
	@RequestMapping("send_message")
	public void send_message(HttpServletRequest request ){
		try {
			//发送消息
			String sender = request.getParameter("sender");
			String recevrer = request.getParameter("recevrer");
			String busCode = request.getParameter("busCode");
			String consultantId =  request.getParameter("consultantId");
			String msgContent = request.getParameter("content");
			String type = request.getParameter("type");
			if(type.equals("2")){
				msgContent =  msgContent.substring(msgContent.indexOf("group")-1, msgContent.length());
				System.out.println(msgContent);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("busCode", busCode);//50101(医院问诊)  60102(胎心及时问诊)
			map.put("sender",sender);  //医院
			map.put("receiver",recevrer);   // 用户
			map.put("content", msgContent);
			map.put("type", type);
			map.put("consultantId",consultantId);
			String json = JSONObject.toJSONString(map);  
			logger.info("发送消息send_msg:"+json);
			String post3 = HttpPost.doPost(Consts.CONSULT_IM_URL+"/im/send_msg",map);
			logger.info("返回结果send_msg:"+post3);
			JsonUtils.render(getResponse(), post3);
		} catch (Exception e) {
			logger.info(e);
		}
	}
	
	/**
	 *	消息撤回
	 * @param id
	 */
	@RequestMapping("recall_message")
	public void recall_message(HttpServletRequest request,HttpServletResponse response){
		try {
			//消息撤回
			String sender = request.getParameter("sender");
			String receiver = request.getParameter("receiver");
			String type = request.getParameter("type");
			String busCode = request.getParameter("busCode");
			String consultantId = request.getParameter("consultantId");
			String msgContent = request.getParameter("content");//消息的ID
			Map<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("sender", sender);
			hashMap.put("receiver", receiver);
			hashMap.put("type", type);
			hashMap.put("busCode", busCode);
			hashMap.put("consultantId", consultantId);
			hashMap.put("content", "{\"type\":15,\"data\":{\"content\":\"回撤消息\",\"datas\":{\"backMsgId\":"+msgContent+"}}}");
			System.out.println(Consts.CONSULT_IM_URL+"/im/send_msg");
			String doPost = HttpPost.doPost(Consts.CONSULT_IM_URL+"/im/send_msg",hashMap);
			
			System.out.println(doPost);
			JsonUtils.render(getResponse(), doPost);
		} catch (Exception e) {
			logger.info(e);
		}
	}

  	@RequestMapping(value = { "upload" }, method = RequestMethod.POST)
	@ResponseBody
  	public Object upload(
  			@RequestParam(value = "file", required = true) MultipartFile file,
  			HttpServletRequest request,
  			HttpServletResponse response,
  			ModelMap model,
  			@RequestParam(value = "minWidth", required = true) Integer minWidth,
  			@RequestParam(value = "minHeight", required = true) Integer minHeight) {
  		PrintWriter out = null;
  		String showPath = null;
  		try {
  			/*response.reset();
  			out = response.getWriter();
  			response.setCharacterEncoding("UTF-8");
  			response.setContentType("text/html;charset=utf-8");*/
  			ObjectMapper mapper = new ObjectMapper();
  			String str = "";
  			String contentType = file.getContentType();

  			if ((StringUtils.isBlank(contentType))
  					|| (!contentType.startsWith("image"))) {
  				/*out.append(mapper.writeValueAsString(new ReturnMsg(0,
  						"请上传正确格式的图片")));*/
  				return   "false1";
  			}
  			BufferedImage image = ImageIO.read(file.getInputStream());
  			if ((null != image)
  					&& ((image.getHeight() < minWidth.intValue()) || (image
  							.getWidth() < minHeight.intValue()))) {
  				/*out.append(mapper.writeValueAsString(new ReturnMsg(0, "图片不能小于"
  						+ minWidth + "*" + minHeight)));*/
  				return   "false2";
  			}
  				//fastdfs 存储
  				String fpath = FastdfsUpload.upoladFile(file.getOriginalFilename(),file.getInputStream());
  				 showPath  =  fpath;
  			/*String showPath = saveCDN(file);*/
  			/*String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
  			String fileName ="/upload/"+ UUID.randomUUID().toString().replace("-","")+"."+prefix;
  			String tempPath = ConfigProUtils.get("update_img_dir")+File.separator;
  			file.transferTo(new File(tempPath+fileName));*/
  			//str = mapper.writeValueAsString();
  			logger.info("the mapper return:"+str);
  			//out.append(str);
  			//JsonUtils.render(getResponse(), new ReturnMsg<Object>(ReturnMsg.SUCCESS, "",showPath));
  		} catch (IOException e) {
  			logger.info("保存到文件服务器  失败,错误原因:"+e.getMessage()+";");
  		} finally {
  			if (out != null)
  				out.close();
  		}
		return Consts.BASE_FILE_URL+showPath;
  	}
}
