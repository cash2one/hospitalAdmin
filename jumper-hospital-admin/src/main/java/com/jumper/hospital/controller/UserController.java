package com.jumper.hospital.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UserInfoDao;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.MonitorOrder;
import com.jumper.hospital.entity.MonitorUserInfo;
import com.jumper.hospital.entity.PayOrderInfo;
import com.jumper.hospital.entity.PayRefundAnnal;
import com.jumper.hospital.entity.PayRefundRemark;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.service.MonitorOrderService;
import com.jumper.hospital.service.MonitorOrderSpecService;
import com.jumper.hospital.service.MonitorSettingService;
import com.jumper.hospital.service.MonitorUserInfoService;
import com.jumper.hospital.service.PayOrderInfoService;
import com.jumper.hospital.service.PayRefundAnnalService;
import com.jumper.hospital.service.UserExtraInfoService;
import com.jumper.hospital.service.UserInfoService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HBSmsCodeUtils;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VoMonitorOrder;
import com.jumper.hospital.vo.VoMonitorSetting;
import com.jumper.hospital.vo.VoMonitorUserAutoComplete;
import com.jumper.hospital.vo.pay.VoLinePayInfo;

import cn.com.jumper.anglesound.dubbo.BaseDataResult;
import cn.com.jumper.anglesound.dubbo.DubboPaymentService;
/**
 * 订单管理controller
 * @author rent
 * @date 2015-10-21
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private MonitorOrderService monitorOrderServiceImpl;
	@Autowired
	private MonitorOrderSpecService monitorOrderSpecServiceImpl;
	@Autowired
	private MonitorSettingService monitorSettingServiceImpl;
	@Autowired
	private MonitorUserInfoService monitorUserInfoServiceImpl;
	@Autowired
	private PayRefundAnnalService payRefundAnnalServiceImpl;
	@Autowired
	private PayOrderInfoService payOrderInfoServiceImpl;
	@Autowired
	private DubboPaymentService dubboPaymentService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserExtraInfoService userExtraInfoService;
	@Autowired
	private UserInfoDao userInfoDaoImpl;
	
	/**
	 * 监控首页
	 * @param model
	 * @param page 分页用对象
	 * @param type 查询类型
	 * @param remoteType 监控类型
	 * @param searchKey 搜索key
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	@RequestMapping("index")
	public String index(ModelMap model, Page<MonitorOrder> page, Integer type, String searchKey, String startTime, 
			String endTime, Integer remoteType, Integer expireType, String invalidStartTime, String invalidEndTime,Integer payChannels){
		int usedCount = 0;
		
		Admin admin = getLoginAdminInfo();
		//Integer hospitalId = admin.getHospitalInfo().getId();
		//String returnUrl = ArrayUtils.checkIsLINEHospital(hospitalId) ? "ordertrial/index" : "user/index";
		//String returnUrl = "ordertrial/index";
		if(remoteType==null){
			remoteType=1;
		}
		//暂时处理 ，如果为空则设置默认为没有开通
		Boolean isPayment=admin.getHospitalInfo().getIsPayment();
		if(null==isPayment){
			isPayment=false;
		}
		String returnUrl = isPayment ? "ordertrial/index" : "user/index";
		
		Page<MonitorOrder> pageData = null;
		
		if (returnUrl.equals("ordertrial/index")) {
			if(type == null) {
				type = 0; 
			}
			if(expireType == null) {
				expireType = 0;
			}
			pageData = monitorOrderSpecServiceImpl.findOrderList(type, searchKey, startTime, endTime, page,
					admin.getHospitalInfo().getId(), remoteType, expireType, invalidStartTime, invalidEndTime,payChannels);
			
			if (admin.getHospitalInfo().getIsAutonomy()==1 && type == 0) {
				usedCount = monitorOrderSpecServiceImpl.countOrderUsedToday(admin.getHospitalInfo().getId());
			} else if (admin.getHospitalInfo().getIsAutonomy()==1 && type == 2) {
				usedCount = monitorOrderSpecServiceImpl.countOrderInvalid(admin.getHospitalInfo().getId(), invalidStartTime, invalidEndTime);
			}
			
		} else {
			if(type == null){
				type = 0; 
			}
			pageData = monitorOrderServiceImpl.findOrderList(type, searchKey, startTime, endTime, page,
					admin.getHospitalInfo().getId(), remoteType, expireType, invalidStartTime, invalidEndTime);
		}
		//获取医院支付信息
		BaseDataResult result = dubboPaymentService.getHospitalPayWay(admin.getHospitalInfo().getId());
		if(result.getCode() == Consts.TRUE){
			model.put("payWay", result.getData());
		}

		
		/** 请求参数反传到前端做控制 **/
		model.put("admin", admin);
		model.put("page", pageData);
		model.put("type", type);
		model.put("usedCount", usedCount);
		model.put("searchKey", searchKey);
		model.put("startTime", startTime);
		model.put("endTime", endTime);
		model.put("remoteType", remoteType);
		model.put("expireType", expireType);
		model.put("invalidStartTime", invalidStartTime);
		model.put("invalidEndTime", invalidEndTime);
		model.put("payChannels", payChannels);
		String submenu = type == 0 ? Consts.USER_SUBMENU_TODAY : (type == 1 ? Consts.USER_SUBMENU_ALL : Consts.USER_SUBMENU_DISABLE);
		pageLocation(model, Consts.USER_MODULE, Consts.USER_MENU_LIST, submenu);
		return returnUrl;
	}
	
	/**
	 * 获取医院监控服务
	 */
	@RequestMapping("getService")
	public void getMonitorService(HttpServletResponse response){
		Admin admin = getLoginAdminInfo();
		if(admin != null){
			Integer hospitalId = admin.getHospitalInfo().getId();
			List<VoMonitorSetting> list = monitorSettingServiceImpl.findByHospitalIdAndRemoteType(hospitalId);
			JsonUtils.render(response, list);
		}
		JsonUtils.render(response, "error");
	}
	
	/**
	 * 自动补全查询用户信息
	 * @param q jquery-autocomplement 默认参数
	 */
	@RequestMapping("find")
	public void findUserByMobile(HttpServletResponse response, String q){
		List<VoMonitorUserAutoComplete> list = monitorUserInfoServiceImpl.findUserAutoComplete(q);
		if(ArrayUtils.isNotEmpty(list)){
			JsonUtils.render(response, list);
		}
	}
	
	@RequestMapping("check")
	public void checkMobileIsExist(String mobile, String userName, Integer age){
		MonitorUserInfo userInfo = monitorUserInfoServiceImpl.doCheckMobileIsExist(mobile);
		if(userInfo != null){
			if(userInfo.getRealName().equals(userName) && userInfo.getAge() == age){
				JsonUtils.renderString(getResponse(), "{\"exist\":"+true+", \"same\":"+true+"}");
			}else{
				JsonUtils.renderString(getResponse(), "{\"exist\":"+true+", \"same\":"+false+"}");
			}
		}
		JsonUtils.renderString(getResponse(), "{\"exist\":"+false+", \"same\":"+false+"}");
	}
	
	/**
	 * 编辑用户订单
	 * @param user 监控用户
	 * @param remoteOption 监控服务ID
	 */
	@RequestMapping("edit")
	public void addOrder(MonitorUserInfo user, Integer orderId, Integer remoteOption, String edcDate){
		/** 新增 **/
		if(orderId == null){
			try {
				if(remoteOption == null){
					JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
					return;
				}
				
				Admin admin = getLoginAdminInfo();
				if(admin == null){
					JsonUtils.render(getResponse(), Consts.FAILED);
					return;
				}
				String result = monitorOrderServiceImpl.addMonitorOrder(user, remoteOption, admin.getHospitalInfo().getId(), edcDate);
				JsonUtils.render(getResponse(), result);
			} catch (Exception e) {
				e.printStackTrace();
				JsonUtils.render(getResponse(), Consts.FAILED);
				return;
			}
		}else{
			try {
				MonitorUserInfo userInfo = monitorUserInfoServiceImpl.get(user.getId());
				BeanUtils.copy(user, userInfo);
				userInfo.setEdc(TimeUtils.getTimestampDate(edcDate, Consts.FORMAT_TIME_THREE));
				/** 这里需要重新绑定，因为用户有可能修改手机号码 **/
				
				//修改的时候同步用户信息到app begin
				UserInfo appUser = userInfoDaoImpl.findByMobile(userInfo.getMobile());
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
						appUser.setUserExitInfo(userExitInfo);
					}
				}else{
					appUser = userInfoDaoImpl.createInsideAppUser(userInfo,edcDate);//去创建APP用户
				}
				//修改的时候同步用户信息到app end
				
				monitorUserInfoServiceImpl.save(userInfo);
				JsonUtils.render(getResponse(), "success");
			} catch (Exception e) {
				e.printStackTrace();
				JsonUtils.render(getResponse(), "failed");
			}
		}
		
	}
	
	/**
	 * 医院退费
	 * @param orderId 订单ID
	 */
	@RequestMapping("back")
	public void backMoney(Integer orderId){
		try {
			if(orderId == null){
				JsonUtils.render(getResponse(), Consts.FAILED);
			}
			MonitorOrder order = monitorOrderServiceImpl.get(orderId);
			order.setIsEffective(true);
			order.setExpireTime(TimeUtils.getCurrentTime());
			String hospitalName = order.getMonitorHospitalId().getHospitalId().getName();
			String content = "您在"+hospitalName+"购买的"+order.getRemoteType()+"已办理退费。如有疑问请联系医院";
			
			boolean flag = HBSmsCodeUtils.sendSmsMsgCommon(content, order.getMonitorUserId().getMobile(), 
					hospitalName, 49);;
			if(!flag){
				logger.info("监护订单退费，发送短信失败，手机号码："+order.getMonitorUserId().getMobile());
			}
			monitorOrderServiceImpl.save(order);
			JsonUtils.render(getResponse(), Consts.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	
	/**
	 * 修改订单信息，获取订单详情
	 * @param orderId 订单ID
	 */
	@RequestMapping("info")
	public void orderDetail(Integer orderId){
		if(orderId == null){
			JsonUtils.render(getResponse(), "paramError");
		}
		MonitorOrder order = monitorOrderServiceImpl.get(orderId);
		VoMonitorOrder voOrder = monitorOrderServiceImpl.convertOrderDetail(order);
		JsonUtils.render(getResponse(), voOrder);
	}
	
	/**
	 * http调用获取服务人数
	 * @param id 医院ID
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/count/{id}")
	public void getRemoteCount(@PathVariable Integer id){
		Integer count = monitorOrderServiceImpl.getRemoteCountByHospital(id);
		JsonUtils.render(getResponse(), count);
	}
	@RequestMapping("inside")
	public void doChgInsideRemoteOrder(Integer id){
		String result = monitorOrderServiceImpl.doChgInsideOrder(id);
		JsonUtils.render(getResponse(), result);
	}
	
	/**
	 * 退费列表
	 * @param model
	 * @param page
	 * @return
	 */ 
	@RequestMapping("payRefundAnnal")
	public String payRefundAnnal(ModelMap model, Page<PayRefundAnnal> page, Integer type, String startTime, 
			String endTime, Integer remoteType, Integer expireType) {
		if (type == null) {
			type = 0; 
		}
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo(); 
		//获得医院id和医院信息aa
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		 
		Page<PayRefundAnnal> pageData = payRefundAnnalServiceImpl.findPayRefundAnnalList(page, hospitalInfo.getId(), startTime, endTime);
		
		model.addAttribute("page", pageData);
		model.addAttribute("type", type);
		model.put("remoteType", remoteType);
		model.put("expireType", expireType);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		
		String submenu = type == 0 ? Consts.USER_SUBMENU_TODAY : (type == 1 ? Consts.USER_SUBMENU_ALL : Consts.USER_SUBMENU_DISABLE);
		pageLocation(model, Consts.USER_MODULE, Consts.USER_MENU_LIST, submenu);
		return "ordertrial/payRefundAnnalList";
	}
	
	/**
	 * 订单详情
	 * @param model
	 * @param orderId
	 * @param type
	 * @param startTime
	 * @param endTime
	 * @param remoteType
	 * @param expireType
	 * @return
	 */
	@RequestMapping("orderDetail")
	public String orderDetail(ModelMap model, Integer orderId, Integer type, String startTime, 
			String endTime, Integer remoteType, Integer expireType) {
		
		//获取订单
		MonitorOrder monitorOrder = monitorOrderServiceImpl.get(orderId);
		
		PayOrderInfo payOrderInfo =null;
		//获取支付信息
		if(monitorOrder!=null){
			payOrderInfo = payOrderInfoServiceImpl.getPayOrderInfoValid(monitorOrder.getOrderId());
		}
		
		PayRefundAnnal refundInfo=null;
		PayRefundAnnal appealInfo =null;
		//获取申诉/退款信息
		if(payOrderInfo!=null){
			refundInfo = payRefundAnnalServiceImpl.findPayRefund(payOrderInfo.getId(), 1 + "");
			appealInfo = payRefundAnnalServiceImpl.findPayRefund(payOrderInfo.getId(), 2 + "");
		}
		
		//返回前端模型
		model.put("order", monitorOrder);
		model.put("payInfo", payOrderInfo);
		model.put("refundInfo", refundInfo);
		model.put("appealInfo", appealInfo);
		model.put("startTime", startTime);
		model.put("endTime", endTime);
		model.put("remoteType", remoteType);
		model.put("expireType", expireType);
		
		String submenu = type == 0 ? Consts.USER_SUBMENU_TODAY : (type == 1 ? Consts.USER_SUBMENU_ALL : Consts.USER_SUBMENU_DISABLE);
		pageLocation(model, Consts.USER_MODULE, Consts.USER_MENU_LIST, submenu);
		return "ordertrial/orderDetailList";
	}
	
	/**
	 * 线上支付，提交退费审核
	 * @param refundId
	 * @param status
	 * @param reason
	 */
	@RequestMapping(value = "addPayRefundAnnal", method = RequestMethod.POST)
	public void addPayRefundAnnal(Integer refundId,Integer status,String reason) {
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		
		PayRefundRemark remark = new PayRefundRemark();
		remark.setUserId(admin.getId() + "");
		remark.setUserName(admin.getName());
		remark.setRemark(reason);
		remark.setRemarkDate(new Date());
		
		String result = payRefundAnnalServiceImpl.validPayRefundAnnal(refundId, status, remark);
		
		JsonUtils.render(getResponse(), result);
	}
	
	/**
	 * 编辑用户订单，开通线上支付
	 * @param user 监控用户
	 * @param remoteOption 监控服务ID
	 */
	@RequestMapping("editOrderNew")
	public void editOrderNew(HttpServletRequest request, MonitorUserInfo user, Integer orderId, Integer remoteOption, String edcDate, Integer channelWay,Integer payId){
		/** 新增 **/
		if(orderId == null){
			try {
				if(remoteOption == null){
					JsonUtils.render(getResponse(), Consts.PARAMS_ERROR);
					return;
				}
				Admin admin = getLoginAdminInfo();
				if(admin == null){
					JsonUtils.render(getResponse(), Consts.FAILED);
					return;
				}
				String basePath = request.getServletContext().getRealPath("/");
				
				VoLinePayInfo order = monitorOrderSpecServiceImpl.addMonitorOrder(user, remoteOption, admin.getHospitalInfo().getId(), edcDate, channelWay,basePath,payId);
				
				if (channelWay != 0) {
					//添加二维码
					String qrCode = order.getQr_code();
					Map<String, Object> result = new HashMap<String, Object>();
					if (StringUtils.isNotEmpty(qrCode)) {
						result.put("result", Consts.SUCCESS);
						//result.put("qrCode", Consts.BASE_FILE_URL + qrCode);
						result.put("qrCode", qrCode);
						result.put("orderId", order.getOrder_no());
						result.put("channel", channelWay);
						result.put("fee", order.getTotal_fee());
					} else {
						result.put("result", Consts.FAILED);
					}
					JsonUtils.render(getResponse(), result);
				} else {
					JsonUtils.render(getResponse(), order.getReturn_code() == Consts.TRUE ? Consts.SUCCESS : Consts.FAILED);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				JsonUtils.render(getResponse(), Consts.FAILED);
				return;
			}
		}else{
			try {
				MonitorUserInfo userInfo = monitorUserInfoServiceImpl.get(user.getId());
				BeanUtils.copy(user, userInfo);
				userInfo.setEdc(TimeUtils.getTimestampDate(edcDate, Consts.FORMAT_TIME_THREE));
				
				//修改的时候同步用户信息到app begin
				UserInfo appUser = userInfoDaoImpl.findByMobile(userInfo.getMobile());
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
				}else{
					appUser = userInfoDaoImpl.createInsideAppUser(userInfo,edcDate);//去创建APP用户
				}
				//修改的时候同步用户信息到app end
				
				monitorUserInfoServiceImpl.save(userInfo);
				JsonUtils.render(getResponse(), "success");
			} catch (Exception e) {
				e.printStackTrace();
				JsonUtils.render(getResponse(), "failed");
			}
		}
		
	}
	
	/**
	 * 医院退费,修改订单状态
	 * @param orderId 订单ID
	 */
	@RequestMapping("backNew")
	public void backMoneyNew(Integer orderId, Integer channel){
		try {
			if(orderId == null){
				JsonUtils.render(getResponse(), Consts.FAILED);
			}
			
			//获取当前登陆人员信息
			Admin admin = this.getLoginAdminInfo();
			
			PayRefundRemark remark = new PayRefundRemark();
			remark.setUserId(admin.getId() + "");
			remark.setUserName(admin.getName());
			remark.setRemarkDate(new Date());
			
			String result = payRefundAnnalServiceImpl.validPayRefundAnnal(orderId, remark, channel);

			//添加退费短信提醒,暂时不加
			MonitorOrder order = monitorOrderServiceImpl.get(orderId);
			order.setIsEffective(true);
			order.setExpireTime(TimeUtils.getCurrentTime());
			String hospitalName = order.getMonitorHospitalId().getHospitalId().getName();
			String content = "您在"+hospitalName+"购买的"+order.getRemoteType()+"已办理退费。如有疑问请联系医院";
			boolean flag = HBSmsCodeUtils.sendSmsMsgCommon(content, order.getMonitorUserId().getMobile(), 
					hospitalName, 49);
			if(!flag){
				logger.info("监护订单退费，发送短信失败，手机号码："+order.getMonitorUserId().getMobile());
			}
			
			JsonUtils.render(getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
	
	/**
	 * 获取用户名字
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/userInfo/{id}")
	public void getUserInfo(@PathVariable Integer id){
		UserInfo userInfo = userInfoService.get(id);
		if(userInfo!=null){
			UserExtraInfo extraInfo =  userExtraInfoService.findByUserId(id);
			if(extraInfo!=null){
				JsonUtils.render(getResponse(), extraInfo);
			}else{
				JsonUtils.render(getResponse(), userInfo);
			}
		}else{
			JsonUtils.render(getResponse(), Consts.FAILED);
		}
	}
}
