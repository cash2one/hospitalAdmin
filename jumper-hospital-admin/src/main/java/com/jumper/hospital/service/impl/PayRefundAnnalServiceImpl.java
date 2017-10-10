package com.jumper.hospital.service.impl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.MonitorOrderSpecDao;
import com.jumper.hospital.dao.PayOrderInfoDao;
import com.jumper.hospital.dao.PayRefundAnnalDao;
import com.jumper.hospital.dao.PayRefundRemarkDao;
import com.jumper.hospital.entity.MonitorOrder;
import com.jumper.hospital.entity.PayOrderInfo;
import com.jumper.hospital.entity.PayRefundAnnal;
import com.jumper.hospital.entity.PayRefundRemark;
import com.jumper.hospital.service.PayRefundAnnalService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HttpRequestUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.MsgBean;

import cn.com.jumper.anglesound.dubbo.BaseDataResult;
import cn.com.jumper.anglesound.dubbo.DubboPaymentService;
@Service
public class PayRefundAnnalServiceImpl extends BaseServiceImpl<PayRefundAnnal, Integer> implements PayRefundAnnalService {

	private static final Logger logger = Logger.getLogger(PayRefundAnnalServiceImpl.class);
	
	@Autowired
	private PayRefundAnnalDao payRefundAnnalDaoImpl;
	
	@Autowired
	private PayRefundRemarkDao payRefundRemarkDaoImpl;
	
	@Autowired
	private MonitorOrderSpecDao monitorOrderSpecDaoImpl;
	@Autowired
	private PayOrderInfoDao payOrderInfoDaoImpl;
	
	@Autowired
	private DubboPaymentService dubboPaymentService;
	
	@Override
    public BaseDao<PayRefundAnnal, Integer> getBaseDAO()
    {
        return payRefundAnnalDaoImpl;
    }
	
	/**
	 * 分页获取退款列表
	 * @param page
	 * @param hospitalId
	 * @return
	 */
	public Page<PayRefundAnnal> findPayRefundAnnalList(Page<PayRefundAnnal> page, Integer hospitalId, String startTime, String endTime) {
		Page<PayRefundAnnal> resultPage = payRefundAnnalDaoImpl.findPayRefundAnnalList(page, hospitalId, startTime, endTime);
		return resultPage;
	}
	
	/**
	 * 退费列表入口-审核退费申请
	 * @param status
	 * @param reason
	 * @return
	 */
	@SuppressWarnings("unused")
	public String validPayRefundAnnal(Integer id, Integer status, PayRefundRemark remark) {
		int handStatus = 0;
		String consts=null;
		try {
			PayRefundAnnal payRefundAnnal = payRefundAnnalDaoImpl.get(id);
			remark.setRefundId(payRefundAnnal.getId());
			String orderNo = payRefundAnnal.getOrderId().getOrderNo();
			MonitorOrder mo = monitorOrderSpecDaoImpl.findOrder(orderNo); //获取订单信息
			PayOrderInfo payOrderInfo =payOrderInfoDaoImpl.getPayOrderInfoByOrderNo(orderNo);
			//修改退费状态
			if (status == 50) {
				if(orderNo == null && remark==null){
					consts= Consts.ERROR;
				}else {
					BaseDataResult base=dubboPaymentService.thePublicRefund(orderNo, Integer.valueOf(payRefundAnnal.getRefundType()), remark.getUserName(), remark.getRemark(), remark.getUserId());
					//如果成功，修改订单失效时间
					if(base.getCode()==1){
						mo.setExpireTime(TimeUtils.getCurrentTime());//订单失效时间
						payRefundAnnal.setRufundEndTime(TimeUtils.getCurrentTime());//退款结束时间
						payOrderInfo.setHandleState(3);
						payOrderInfo.setRefundEndTime(TimeUtils.getCurrentTime());//支付表退款结束时间
						payOrderInfo.setOrderEndTime(TimeUtils.getCurrentTime());//订单结束时间
						mo.setDealStates(2); //交易状态: 2已退费
					}
					
					return base.getCode() == 1 ? Consts.SUCCESS : Consts.FAILED;
				}
			} else if (status == -1) {
				//不同意退费，修改退费信息表
				payRefundAnnal.setStatus(status);
				payRefundAnnal.setHandleName(remark.getUserName());
				payRefundAnnal.setHandleRemark(remark.getRemark());
				payRefundAnnal.setRufundEndTime(TimeUtils.getCurrentTime());
				payRefundAnnal.setCurrentUserid(remark.getUserId());
				
				//退费备注表
				remark.setRemarkType(2); //2拒绝退费
				remark.setType(0); //退款
				remark.setRemark(remark.getRemark());
				remark.setUserId(remark.getUserId());
				remark.setUserName(remark.getUserName());
				payRefundRemarkDaoImpl.save(remark);
				
				//修改订单表支付表状态
				PayOrderInfo payOrder = payRefundAnnal.getOrderId();
				payOrder.setHandleState(-1);//申请失败
				payOrder.setRefundEndTime(TimeUtils.getCurrentTime());
				
				//修改订单状态
				MonitorOrder monitorOrder = monitorOrderSpecDaoImpl.findOrder(orderNo);
				monitorOrder.setDealStates(-1);	//交易状态申请失败
				consts=Consts.SUCCESS;
				
				String title = "退费失败";
				String userName = monitorOrder.getMonitorUserId().getRealName();
			    String content = userName+"，您的订单退费申请被拒绝";
			    Integer mId = monitorOrder.getId();
			    Integer userId = monitorOrder.getMonitorUserId().getUserId().getId();
			    
//	        	String result = HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, "id="+mId+"&content=+"+content+"&title="+title+"&language=cn&Msg_type=3&user_type=1&user_msg="+userId);
//	        	MsgBean bean = JSONArray.parseObject(result, MsgBean.class);
//	        	if(bean.getMsg() != 1){
//	        		logger.error("胎心监护医院端拒绝退费消息推送失败！");
//	        	}

			}
			return consts;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(Consts.ERROR_TAG+"审核退费申请错误，退费编号"+id+"异常原因："+e.getMessage());
			return Consts.ERROR;
		}
		
	}
	
	/**
	 * 订单列表-同意订单退费
	 * @param orderId
	 * @return
	 */
	public String validPayRefundAnnal(Integer orderId, PayRefundRemark remark, int channel) {
		try {
			MonitorOrder order = monitorOrderSpecDaoImpl.get(orderId);
			
			if (channel != 0){
				//查询支付记录
				PayOrderInfo payOrderInfo = payOrderInfoDaoImpl.getPayOrderInfoByOrderNo(order.getOrderId());
				if (payOrderInfo == null) {
					//不存在订单支付信息，直接返回错误
					return "订单状态错误！";
				}
				//查询是否存在退费记录
				PayRefundAnnal refundAnnal = payRefundAnnalDaoImpl.findPayRefund(payOrderInfo.getId(), 1+"");
				if(refundAnnal==null){
					PayRefundAnnal payRefundAnnal = new PayRefundAnnal();
					payRefundAnnal.setUserName(payOrderInfo.getUserName());
					payRefundAnnal.setServiceName(payOrderInfo.getDoctorName());
					payRefundAnnal.setStatus(0); //申请的初始状态
					//设置退款金额
					BigDecimal money = new BigDecimal(order.getMoney()).multiply(new BigDecimal(order.getLeftCount())).divide(new BigDecimal(order.getMonitorCount()), 2,BigDecimal.ROUND_HALF_UP);
					payRefundAnnal.setMoney(money.doubleValue());
					//1申请退款2申诉返款
					payRefundAnnal.setRefundType(1 + "");
					payRefundAnnal.setOrderId(payOrderInfo);
					payRefundAnnal.setIsDoctors(0);
					payRefundAnnal.setRefundOrigin("胎监医院端退费");
					payRefundAnnal.setRefundTime(TimeUtils.getCurrentTime());
					payRefundAnnal.setOrderNo(order.getOrderId());
					payRefundAnnal.setDoctorType(2);//医院
					payRefundAnnal.setIsDoctors(payOrderInfo.getOrderType());//平台创建
					payRefundAnnal.setServiceType(3);//添加胎心监护的退费
					payRefundAnnal.setDoctorId(payOrderInfo.getDoctorId());  //获取医院id
					payRefundAnnal.setCurrentUserid(remark.getUserId());
					payRefundAnnal.setIsAutonomy(payOrderInfo.getIsAutonomy());//是否是独立医院
					payRefundAnnal.setPayConfigId(payOrderInfo.getPayConfigId());//支付配置id
					payRefundAnnalDaoImpl.savePayRefundAnnal(payRefundAnnal);
					
					//线上退款
					BaseDataResult base= dubboPaymentService.thePublicRefund(order.getOrderId(), 1, remark.getUserName(), "胎监医院端退费", remark.getUserId());
					
					if(base.getCode()==1){
						payRefundAnnal.setRufundEndTime(TimeUtils.getCurrentTime());//退费表的退款结束时间
						// 更新订单支付记录申请/申诉信息
						payOrderInfo.setRefundMoney(money.doubleValue());
						payOrderInfo.setDealState(1);
						payOrderInfo.setHandleState(3);
						//退款时间,状态
						payOrderInfo.setRefundTime(TimeUtils.getCurrentTime());//申请退款开始时间
						payOrderInfo.setRefundEndTime(TimeUtils.getCurrentTime());//申请退款结束时间
						payOrderInfo.setOrderEndTime(TimeUtils.getCurrentTime());//订单结束时间
						payOrderInfoDaoImpl.update(payOrderInfo);
						order.setExpireTime(TimeUtils.getCurrentTime());//失效时间
						
						order.setDealStates(2);
						monitorOrderSpecDaoImpl.save(order);
					}
					return base.getCode() == 1 ? Consts.SUCCESS : Consts.FAILED;
				}else{
					return "该订单已经有退费记录";
				}
			} else {
				order.setDealStates(2); //交易状态: 2已退费
				order.setExpireTime(TimeUtils.getCurrentTime());
				return Consts.SUCCESS;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(Consts.ERROR_TAG+"审核退费申请错误，订单编号"+orderId+"异常原因："+e.getMessage());
			return Consts.ERROR;
		}

	}
	
	/**
	 * 查找订单的退款或申诉记录
	 * @param orderId
	 * @param refundType
	 * @return
	 */
	public PayRefundAnnal findPayRefund(Integer orderId, String refundType) {
		return payRefundAnnalDaoImpl.findPayRefund(orderId, refundType);
	}
	
	/**
	 * 发送消息
	 * 
	 *  id:monitor_order 表的ID
		content:消息内容，有字数限制，eg:张三，您的院内胎心监护订单退费已成功
		title:消息标题，eg：退费成功/申述成功
		language:cn 固定写法，天使医生APP就传cn
		Msg_type：3 该值用于APP区分消息类型，这里传入3即可
		user_type：1 0：全部推送，1：单个用户推送，这里传入1
		user_msg: 用户ID，user_info 表的ID，表示推送给哪个用户
	 * 
	 * @param orderNo
	 */
	public boolean newsPush(String title,String content,int id,int userid){
		try {
			boolean flag = false;
			if(0!= id && 0!= userid){
				StringBuilder params = new StringBuilder("id="+id);
				params.append("&content="+content);
				params.append("&title="+title);
				params.append("&language=cn&Msg_type=3&user_type=1");
				params.append("&user_msg="+userid);
				logger.info("退款发送消息参数:"+params.toString());
				String pushResult = HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, params.toString());
				MsgBean bean = JSONArray.parseObject(pushResult, MsgBean.class);
				if(bean.getMsg() == 1){
					flag = true;
				}
			}
			return flag;
		} catch (RuntimeException e) {
			logger.error("退款发送消息出错:"+e.getMessage());
			return false;
		}
	}
}
