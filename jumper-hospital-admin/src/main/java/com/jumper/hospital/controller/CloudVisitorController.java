package com.jumper.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("visits")
public class CloudVisitorController extends BaseController{
//	@Autowired
//	private HospitalCloudVisitorService cloudVisitorService;
//	@Autowired
//	private AdminService adminServiceImpl;
	
//	private static final Logger logger = Logger.getLogger(CloudVisitorController.class);
//	@RequestMapping("index")
//	public String getCloudIndex(String hospitalId) {
//		try {
//			// 获取当前登录人信息
//			Admin loginInfo = getLoginAdminInfo();
//			String account = "";
//			String hospCode = "";
//			String url = "";
//			if (StringUtils.isNotBlank(hospitalId)) {
//				CloudVisitIndex cloudIndex = cloudVisitorService.getCloudIndex(loginInfo.getId(), hospitalId);
//				CloudVisitorAccount visitorAccount = cloudVisitorService.get(loginInfo.getId());
//				if (visitorAccount != null&& visitorAccount.getAccount() != null) {
//					account = visitorAccount.getAccount();
//				}
//				if (cloudIndex != null && cloudIndex.getHospCode() != null && cloudIndex.getHospCode() != null) {
//					hospCode = cloudIndex.getHospCode();
//					url = cloudIndex.getUrl();
//					if(account.length() > 0){
//						JsonUtils.render(getResponse(), url +"&hospCode=" + hospCode+"&account="+ account);
//						logger.info(url +"&hospCode=" + hospCode+"&account="+ account);
//					}
//				}
//			}
//		} catch (Exception e) {
//			logger.error("获取随访首页异常："+e);
//		}
//		return null;
//	}
//	
//	@RequestMapping("getCloudViditInfo")
//	public void queryCloudVisiInfo(Integer adminId){
//		logger.info("adminId is "+adminId);
//		if(adminId != null && adminId != 0){
//			CloudVisitorAccount visitInfo = cloudVisitorService.get(adminId);
//			if(visitInfo != null){
//				logger.info("visitInfo is "+visitInfo.toString());
//				//Integer visitorRole = visitInfo.getVisitorRole();
//				JsonUtils.render(getResponse(), visitInfo);
//				return;
//			}
//		}
//	}
//	
//	/**
//	 * 
//		 * @author yanghz
//		 * @date 2016-8-26 下午2:23:26
//		 * @Description: 监测页面电话功能
//		 * @param @param userId
//		 * @param @param userName
//		 * @param @param userMobile
//		 * @param @param hospitalId
//		 * @return void    返回类型 
//		 * @throws
//	 */
//	@RequestMapping("callToPatient")
//	public void callToPatient(String userId,String userName,String userMobile,String hospitalId){
//			try {
//				StringBuilder url = new StringBuilder();
//				String sourseId = "";
//				//登录人信息
//				Admin loginInfo = getLoginAdminInfo();
//				if(StringUtils.isNotBlank(hospitalId)){
//					CloudVisitIndex cloudIndex = cloudVisitorService.callToPatient(loginInfo.getId(), hospitalId);
//					CloudVisitorAccount visitorAccount = cloudVisitorService.get(loginInfo.getId());
//					if (visitorAccount != null&& visitorAccount.getSourseId() != null) {
//						sourseId = visitorAccount.getSourseId();
//					}
//					if (cloudIndex != null&& cloudIndex.getUrl() != null) {
//						url = url.append(cloudIndex.getUrl());
//					}
//					if(sourseId.length() > 0){
//						url.append("&sourseId=" + sourseId);
//					}
//					Map<String, Object> map = new HashMap<String, Object>();
//					map.put("url", url.append("&mobileNo=" + userMobile + "&patName="));
//					map.put("patName", userName);
//					
//					JsonUtils.render(getResponse(), map);
//					//JsonUtils.render(getResponse(), url.append("&mobileNo=" + userMobile).append("&patName=" + userName).toString());
//					//String str = "http://xuyunzhan.ngrok.cc/hug_interview/thirdParty/tsysAction!toMakeCall.htm?md5=e6ff6b24c79f7fc6a64dca8477bce799&time=1473316876201&partnerId=tianshiyisheng&partnerType=2&sourseId=130d274ac5104183adafba5971a29484&mobileNo=13760389056&patName=啦啦";
//					//JsonUtils.render(getResponse(), str);
//					return;
//				}
//			} catch (Exception e) {
//				logger.error("拨打电话异常信息："+e);
//			}
//			return;
//			
//	}
//	/**
//	 * 获取当前登录管理员信息
//	 * @return
//	 */
//	public Admin getLoginAdminInfo(){
//		Subject currentUser = SecurityUtils.getSubject();
//		if(currentUser == null || currentUser.getPrincipal() == null){
//			return null;
//		}
//		String user = currentUser.getPrincipal().toString();
//		Admin admin = adminServiceImpl.findByUsername(user);
//		if(admin != null){
//			return admin;
//		}
//		return null;
//	}
}
