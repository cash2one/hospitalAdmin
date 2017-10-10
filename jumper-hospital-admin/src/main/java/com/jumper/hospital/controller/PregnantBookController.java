package com.jumper.hospital.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.Hospital;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.NewsMessage;
import com.jumper.hospital.entity.NewsUserMessage;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.entity.UserPregnantMedicalHistory;
import com.jumper.hospital.entity.UsersBookBasicInfo;
import com.jumper.hospital.entity.UsersBookFirstCheckInfo;
import com.jumper.hospital.entity.UsersBookHusbandInfo;
import com.jumper.hospital.service.HospitalInfoService;
import com.jumper.hospital.service.NewsMyMessageSevice;
import com.jumper.hospital.service.NewsUserMessageService;
import com.jumper.hospital.service.UserInfoService;
import com.jumper.hospital.service.UserPregnantMedicalHistoryService;
import com.jumper.hospital.service.UsersBookBasicInfoService;
import com.jumper.hospital.service.UsersBookFirstCheckInfoService;
import com.jumper.hospital.service.UsersBookHusbandInfoService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HttpRequestUtils;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.MsgBean;
import com.jumper.hospital.vo.VoUserInfo;

import com.jumper.common.web.ReturnMsg;

@Controller
@RequestMapping("/pregnantBook")
public class PregnantBookController extends BaseController {
	@Autowired
	private UsersBookBasicInfoService usersBookBasicInfoService;
	@Autowired
	private UsersBookHusbandInfoService usersBookHusbandInfoService;
	@Autowired
	private UsersBookFirstCheckInfoService usersBookFirstCheckInfoService;
	@Autowired
	private UserPregnantMedicalHistoryService userPregnantMedicalHistoryService;
	@Autowired
	private UserInfoService userInfoServiceImpl;
	@Autowired
	private NewsMyMessageSevice newsMyMessageSevice;
	@Autowired
	private NewsUserMessageService newsUserMessageService;
	@Autowired
	private HospitalInfoService hospitalInfoService;
	//利用logger获取提示信息
	private final static Logger logger = Logger.getLogger(PregnantBookController.class);
	
	//获取所有医院信息
	@ResponseBody
	@RequestMapping(value = "/allHospital", method = RequestMethod.POST)
	public void getAllHospitalInfo(ModelMap model,HttpServletRequest req){
		String hospitalName = req.getParameter("hospitalName");
		List<Hospital> list =  userInfoServiceImpl.findAllHospitalInfo(hospitalName);
		JsonUtils.render(getResponse(), list);
	}
	
	//获取新增用户数据
	@ResponseBody
	@RequestMapping(value = "/userCount", method = RequestMethod.POST)
	public Object UserCount(ModelMap model,HttpServletRequest req){
		//获得医院id和医院信息
		Integer hospitalId =  Integer.parseInt(req.getParameter("hospitalId"));
		
		int allUserCount = userInfoServiceImpl.allUserCount(hospitalId);
		int todayCount = userInfoServiceImpl.todayNewUserCount(hospitalId);
		Map<String , Object> result = new HashMap<String, Object>();
		result.put("allUserCount", allUserCount);
		result.put("todayCount", todayCount);
		
		return result;
	}
	
	//条件查询获取新增用户数据
		@ResponseBody
		@RequestMapping(value = "/newUserCount", method = RequestMethod.POST)
		public Object condUserCount(ModelMap model,HttpServletRequest req){
			//获得医院id和医院信息
			Integer hospitalId =  Integer.parseInt(req.getParameter("hospitalId"));
			String startTime = req.getParameter("startTime");
			String endTime = req.getParameter("endTime");
			int newCount = userInfoServiceImpl.newUserCount(startTime,  endTime ,hospitalId);
			Map<String , Object> result = new HashMap<String, Object>();
			result.put("newCount", newCount);
			
			return result;
		}
		
		
		
	//获取孕期档案列表
	@RequestMapping("/pregnantBookList")
	public String pregnantBookList(ModelMap model,Page<UsersBookBasicInfo> page,HttpServletRequest req){
		String keyWords = req.getParameter("keyWords");
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Page<UsersBookBasicInfo> pageData = usersBookBasicInfoService.findPregnantBookList(page,keyWords,hospitalInfo.getId());
		model.addAttribute("pregnantBookList", pageData);
		model.addAttribute("keyWords", keyWords);
		pageLocation(model, "hospital", "user", "pregnant");
		return "user/pregnantBookList";
	}
	
	@RequestMapping("/lookDetails")
	public String lookDetails(ModelMap model,HttpServletRequest req){
		//获取用户id
		String ids = req.getParameter("uid");
		int id = Integer.parseInt(ids);
		//获取孕妇基本信息
		UsersBookBasicInfo usersBookBasicInfo = usersBookBasicInfoService.findUsersBookBasicInfo(id);
		//获取孕妇丈夫基本信息
		UsersBookHusbandInfo usersBookHusbandInfo = usersBookHusbandInfoService.findUsersBookHusbandInfo(id);
		//获取初次产检信息表
		UsersBookFirstCheckInfo usersBookFirstCheckInfo = usersBookFirstCheckInfoService.findUsersBookFirstCheckInfo(id);
		//user_id  +  type=0  ====>   遗传病史id
		String geneticHistoryStr = "";
		List<UserPregnantMedicalHistory> userPregnantMedicalHistoryList0 = userPregnantMedicalHistoryService.findPregnantMedicalHistory(id,0);
		for (UserPregnantMedicalHistory userPregnantMedicalHistory1 : userPregnantMedicalHistoryList0) {
			geneticHistoryStr += userPregnantMedicalHistoryService.findGeneticHistoryById(userPregnantMedicalHistory1.getObjectId());
		}
		
		//user_id  +  type=1  ====>   既往病史id
		String pastHistoryStr = "";
		List<UserPregnantMedicalHistory> userPregnantMedicalHistoryList1 = userPregnantMedicalHistoryService.findPregnantMedicalHistory(id,1);
		for (UserPregnantMedicalHistory userPregnantMedicalHistory2 : userPregnantMedicalHistoryList1) {
			pastHistoryStr += userPregnantMedicalHistoryService.findUserPastHistoryById(userPregnantMedicalHistory2.getObjectId());
		}
		//user_id  +  type=2  ====>   孕产史id
		String maternalHistoryStr = "";
		List<UserPregnantMedicalHistory> userPregnantMedicalHistoryList2 = userPregnantMedicalHistoryService.findPregnantMedicalHistory(id,2);
		for (UserPregnantMedicalHistory userPregnantMedicalHistory3 : userPregnantMedicalHistoryList2) {
			maternalHistoryStr += userPregnantMedicalHistoryService.findMaternalHistoryById(userPregnantMedicalHistory3.getObjectId());
		}
		model.addAttribute("usersBookBasicInfo", usersBookBasicInfo);
		model.addAttribute("usersBookHusbandInfo", usersBookHusbandInfo);
		model.addAttribute("usersBookFirstCheckInfo", usersBookFirstCheckInfo);
		model.addAttribute("geneticHistoryStr", geneticHistoryStr);
		model.addAttribute("pastHistoryStr", pastHistoryStr);
		model.addAttribute("maternalHistoryStr", maternalHistoryStr);
		pageLocation(model, "hospital", "user", "pregnant");
		return "user/pregnantBookDetails";
	}
	/**
	 * 重置档案，退回用户修改
	 * @param model
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping("/resetUserInfoBook")
	public String resetUserInfoBook(ModelMap model,Page<UsersBookBasicInfo> page,HttpServletRequest req){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		String ids = req.getParameter("uid");
		int id = Integer.parseInt(ids);
		//将档案打回
		if(hospitalInfo!=null){
			usersBookBasicInfoService.updateUsersBookBasicInfo(hospitalInfo.getId(),id);
			/**添加推送给用户*/
			String result = HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, "id="+hospitalInfo.getId()+"&content=您好，您的孕期档案已退回，请及时修改！&title="+hospitalInfo.getName()+"&language=cn&Msg_type=12&user_type=1&user_msg="+id);
			Gson gson = new Gson();
			MsgBean msgBean = gson.fromJson(result, new TypeToken<MsgBean>() {}.getType());
			if(msgBean.getMsg()==1){
				logger.info("hospital send push message to user to edit UsersBookBasicInfo successful by userid :"+id);
				logger.info("push message success ,the result is "+result);
			}else{
				logger.error("hospital send push message to user to edit UsersBookBasicInfo failed by userid :"+id);
				logger.error("push message fail,the result is "+result);
			}
		}
		return "redirect:pregnantBookList";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("userList")
	public String bindUserInfo(ModelMap model, Page<UserInfo> page, String searchKey, Integer type){
		Admin admin = getLoginAdminInfo();
		Page<VoUserInfo> pageData = userInfoServiceImpl.findBindUserListByHospital(page, admin.getHospitalInfo().getId(), searchKey, type);
		//获取该医院用户的所有孕周
		List<Integer> weekList = userInfoServiceImpl.findWeekList(admin.getHospitalInfo().getId());
		List<Integer> babyList = userInfoServiceImpl.findBabyList(admin.getHospitalInfo().getId());
		
		//根据预产期测孕周
		List<Integer> weeks = userInfoServiceImpl.getPregnantWeeks(admin.getHospitalInfo().getId());
		List<String> babys = userInfoServiceImpl.getBabyAges(admin.getHospitalInfo().getId());
		
		model.put("pregnantWeekList", weeks.toString());
		model.put("babyList", babys.toString());
		model.put("page", pageData);
		model.put("type", type);
		model.put("hospitalId", admin.getHospitalInfo().getId());
		model.put("searchKey", searchKey);
		pageLocation(model, "hospital", "user", "list");
		return "hospital/userList";
	}
	
	@RequestMapping("userDetail")
	public String bindUserDetail(ModelMap model, Integer id){
		try {
			UserInfo user = userInfoServiceImpl.get(id);
			String week = "";
			if(user != null && user.getUserExitInfo() != null){
				if(user.getUserExitInfo().getCurrentIdentity() == 0){
					if(user.getExpectedDateOfConfinement() != null){
						week = String.valueOf(TimeUtils.getPregnantWeek(user.getExpectedDateOfConfinement())[0]);
					}
				}else{
					if(user.getUserExitInfo().getBabyBirthday() != null){
						int[] birth = TimeUtils.getBabyAgeAndMonth(user.getUserExitInfo().getBabyBirthday());
						week = birth[0]+"岁"+birth[1]+"月";
					}
				}
			}
			model.put("week", week);
			model.put("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageLocation(model, "hospital", "user", "list");
		return "hospital/userDetail";
	}
	
	@ResponseBody
	@RequestMapping("/checkPregnantWeek")
	public String checkPregnantWeek(HttpServletRequest req){
		String pregnantWeek = req.getParameter("pregnantWeek");
		if(pregnantWeek!=null&&!"".equals(pregnantWeek)){
			if("全部用户".equals(pregnantWeek)){
				return "N";
			}else{
				List<NewsMessage> messageList = newsMyMessageSevice.findMessagesByPregnantWeek(pregnantWeek);
				if(messageList!=null&&messageList.size()>0){
					return "Y";
				}else{
					return "N";
				}
			}
		}else{
			return "N";
		}
	}
	
	@ResponseBody
	@RequestMapping("/pushMyMessage")
	public String pushMyMessage(){
		HttpServletRequest req = getRequest();
		String select1s = req.getParameter("select1");
		String select2s = req.getParameter("select2");
		String content = req.getParameter("content");
		Admin admin = getLoginAdminInfo();
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		int b[] = new int[2];
		String a[] = new String[2];
		int select2 = -3;
		int flag = -1;
		String pregnant_Week = "";
		if(select1s!=null&&!"".equals(select1s)){
			int select1 = Integer.parseInt(select1s);
			if(select2s!=null&&!"".equals(select2s)){
				if(select2s.contains("_")){
					a = select2s.split("_");
					b[0] = Integer.parseInt(a[0]);
					b[1] = Integer.parseInt(a[1]);
					pregnant_Week = "宝宝"+b[0]+"岁"+b[1]+"月";
				}else{
					select2 = Integer.parseInt(select2s);
					if(select1==1&&select2==-1){
						pregnant_Week = "所有怀孕用户";
					}else if(select1==1&&select2!=-1){
						pregnant_Week = "孕"+select2+"周";
					}else if(select1==2&&select2==-1){
						pregnant_Week = "所有已生宝宝用户";
					}
					
				}
				
				if(select1==-1){
					flag = 0;//所有用户
				}else if(select1==1){
					flag = 1;//怀孕用户
				}else if(select1==2){
					flag = 2;//已生宝宝用户
				}
				//查询对应用户
				Map<Integer,Date> maps = userInfoServiceImpl.findUserIdsAndDate(hospitalInfo.getId(),flag); 
				
				List<Integer> userIds = new ArrayList<Integer>();
				try {
					if(maps!=null&&maps.size()>0){
						if(select1==-1){
							for(int key : maps.keySet()){
								userIds.add(key);
							}
						}else if(select1==1){
							if(select2==-1){
								for(int key : maps.keySet()){
									userIds.add(key);
								}
							}else{
								for(int key : maps.keySet()){
									int c[] = TimeUtils.getPregnantWeek(maps.get(key));
									if(c[0]==39&&c[1]==6&&c[2]==280){
										
									}else{
										if(!"".equals(maps.get(key))&&maps.get(key)!=null&&TimeUtils.getPregnantWeek(maps.get(key))[0]==select2){
											userIds.add(key);
										}
									}
									
								}
							}
							
						}else if(select1==2){
							if(select2==-1){
								for(int key : maps.keySet()){
									userIds.add(key);
								}
							}else{
								for(int key : maps.keySet()){
									if(!"".equals(maps.get(key))&&maps.get(key)!=null&&TimeUtils.getBabyAgeAndMonth(maps.get(key))[0]==b[0]&&TimeUtils.getBabyAgeAndMonth(maps.get(key))[1]==b[1]){
										userIds.add(key);
									}
								}
							}
						}
						//增加消息、推送
						if(userIds!=null&&userIds.size()>0){
							//新增一条消息
							int newsId = 0;
							
							try {
								NewsMessage news = newsMyMessageSevice.saveMyMessage(content,admin,hospitalInfo,select1,pregnant_Week);
								newsId = news.getId();
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							//新增用户消息记录
							for(int i=0;i<userIds.size();i++){
								NewsUserMessage newsUserMessage = new NewsUserMessage();
								newsUserMessage.setMsgId(newsId);
								newsUserMessage.setUserId(userIds.get(i));
								newsUserMessage.setType(0);//0代表医院消息
								try {
									newsUserMessageService.save(newsUserMessage);
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							
							if(Consts.JPUSH_VERSION.equals("4.0")){
									for(int i=0;i<userIds.size();i++){
										//单推
										//Jpush.sendPush(userIds.get(i)+(Consts.IS_DEBUG.trim().equals("0")?"test":""), hospitalInfo.getName(), content, 14 , hospitalInfo.getId().toString());
										Map<String, Object> map = new HashMap<String, Object>();
										map.put("msgTitle", hospitalInfo.getName());
										map.put("receiver", userIds.get(i));
										map.put("msgContent", content);
										map.put("type", "14");
										map.put("appid", "0003");
										map.put("hospId", hospitalInfo.getId());
										map.put("url", "");
										map.put("id", "");
										HttpRequestUtils.sendPost(Consts.CONSULT_IM_URL+"/push/send_jpush_receiver_msg", net.sf.json.JSONObject.fromObject(map));
									}
							}else{
								//推送
								if(select1==-1){
									//如果是该医院的所有孕妇，user_type=2
									String result= HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, "id="+hospitalInfo.getId()+"&content=+"+content+"&title="+hospitalInfo.getName()+"消息"+"&language=cn&Msg_type=14&user_type=2&user_msg="+9999);
									Gson gson = new Gson();
									MsgBean msgBean = gson.fromJson(result, new TypeToken<MsgBean>() {}.getType());
									if(msgBean.getMsg()==1){
										return "Y";
									}
								}else{
									//如果是按孕周推，user_type=1
									for(int i=0;i<userIds.size();i++){
										String results= HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, "id="+hospitalInfo.getId()+"&content=+"+content+"&title="+hospitalInfo.getName()+"消息"+"&language=cn&Msg_type=14&user_type=1&user_msg="+userIds.get(i));
										Gson gson = new Gson();
										MsgBean msgBean = gson.fromJson(results, new TypeToken<MsgBean>() {}.getType());
										if(msgBean.getMsg()!=1){
											return "N";
										}
									}
								}
							}
							return "Y";
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "empty";
			}
					
		}
		return "N";
	}
	@RequestMapping("pushMessageforHttp")
	@ResponseBody
	public ReturnMsg<Boolean> pushMessageforHttp(NewsMessage newsMessage,NewsUserMessage newsUserMessage,Integer hospitalId){
		if(newsMessage.getContent() == null){
			return new ReturnMsg<Boolean>(ReturnMsg.FAIL,"content is null",false);
		}else if(newsMessage.getAddEmp() == null){
			return new ReturnMsg<Boolean>(ReturnMsg.FAIL,"addEmp is null",false);
		}else if(newsUserMessage.getUserId() == null){
			return new ReturnMsg<Boolean>(ReturnMsg.FAIL,"userId is null",false);
		}else if(newsUserMessage.getType() == null){
			return new ReturnMsg<Boolean>(ReturnMsg.FAIL,"type is null",false);
		}
		if(hospitalId != null){
			HospitalInfo hospitalInfo = hospitalInfoService.getHospitalInfo(hospitalId);
			newsMessage.setHospitalInfo(hospitalInfo);
		}
		NewsMessage myMessage = newsMyMessageSevice.saveMyMessageforHttp(newsMessage);
		if(myMessage == null){
			return new ReturnMsg<Boolean>(ReturnMsg.FAIL,"save newsMessage is error",false);
		}
		newsUserMessage.setMsgId(myMessage.getId());
		newsUserMessage.setType(0);
		newsUserMessageService.save(newsUserMessage);
		return new ReturnMsg<Boolean>(ReturnMsg.SUCCESS,"push success",true);
	}
	
	public static void main(String[] args) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msgTitle", "消息推送");
		//map.put("receiver", "10008");
		map.put("receiver", "10481");
		map.put("extraField", "consumerId"+"");
		map.put("msgContent", "您在深圳市宝安区妇幼保健院进行的远程监测报告单已生成！");
		map.put("url", "");
		map.put("type", "3");
		map.put("id", "");
		map.put("appid", "0003");
		map.put("hospId", "8569");
		//String reslutJsonStr =HttpPost.doPost(Consts.CONSULT_IM_URL+"/push/send_jpush_receiver_msg",map);
		String reslutJsonStr =HttpRequestUtils.sendPost(Consts.CONSULT_IM_URL+"/push/send_jpush_receiver_msg", net.sf.json.JSONObject.fromObject(map));
		System.out.println(reslutJsonStr);
	}
	
}
