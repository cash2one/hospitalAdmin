package com.jumper.hospital.service.impl;
/**
 * 问诊退费处理service
 * @author rent
 * @date 2016-04-06
 */
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalDoctorOrderRefundDao;
import com.jumper.hospital.dao.HospitalDoctorServicesOrderDao;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalConsServiceOrder;
import com.jumper.hospital.entity.HospitalDoctorOrderRefund;
import com.jumper.hospital.entity.HospitalDoctorServicesOrder;
import com.jumper.hospital.entity.NewsUserMessage;
import com.jumper.hospital.service.HospitalConsServiceOrderService;
import com.jumper.hospital.service.HospitalDoctorOrderRefundService;
import com.jumper.hospital.service.NewsUserMessageService;
import com.jumper.hospital.utils.Const;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HBSmsCodeUtils;
import com.jumper.hospital.utils.HttpRequestUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VOConsultRefund;
import com.jumper.hospital.vo.VORefundData;

import com.alibaba.fastjson.JSON;

@Service
public class HospitalDoctorOrderRefundServiceImpl extends BaseServiceImpl<HospitalDoctorOrderRefund, Integer> implements HospitalDoctorOrderRefundService {

	@Autowired
	private HospitalDoctorOrderRefundDao hospitalDoctorOrderRefundDaoImpl;
	@Autowired
	private HospitalDoctorServicesOrderDao hospitalDoctorServicesOrderDaoImpl;
	@Autowired
	private HospitalConsServiceOrderService hospitalConsServiceOrderServiceImpl;
	@Autowired
	private NewsUserMessageService newsUserMessageServiceImpl;
	
	@Override
	public BaseDao<HospitalDoctorOrderRefund, Integer> getBaseDAO() {
		return hospitalDoctorOrderRefundDaoImpl;
	}

	@Override
	public Page<VOConsultRefund> getConsultRefundDataList(Integer type, Integer state, Integer hospitalId, String orderId, Page<VOConsultRefund> page) {
		try {
			Page<VOConsultRefund> pageData = hospitalDoctorOrderRefundDaoImpl.getConsultRefundDataList(type, state, hospitalId, orderId, page);
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String doRefundRefuse(Admin admin, Integer id, String reason) {
		try {
			HospitalDoctorOrderRefund refund = hospitalDoctorOrderRefundDaoImpl.get(id);
			refund.setRefuseReason(reason);
			refund.setRefundFailTime(TimeUtils.getCurrentTime());
			refund.setOperator(admin.getName());
			refund.setState(4);
			HospitalDoctorOrderRefund refundEntity = hospitalDoctorOrderRefundDaoImpl.save(refund);
			
			HospitalConsServiceOrder order = hospitalConsServiceOrderServiceImpl.get(refund.getConsOrderId());
			order.setStatus(5);
			HospitalConsServiceOrder conResult = hospitalConsServiceOrderServiceImpl.save(order);
			
			String content = "您好，订单号为"+order.getOrderId()+"的医院问诊订单已经拒绝退款。详情见：天使医生APP";
			log.info("医院问诊订单退款短信开始-->");
			if(Const.YWHOSPITAL_ID.equals(order.getHospitalInfo().getId())){
				HBSmsCodeUtils.sendSmsMsgCommon(String.format(Consts.HOSPITAL_SMS_DOCTER, 
						new Object[]{order.getOrderId(), order.getHospitalInfo().getName()}),  
						order.getUserInfo().getMobile().trim(), 
						order.getHospitalInfo().getName(), order.getHospitalInfo().getId());
			}else{
				HBSmsCodeUtils.sendSmsMsgCommon(content, order.getUserInfo().getMobile(), 
						order.getHospitalInfo().getName(), order.getHospitalInfo().getId());
			}
			log.info("医院问诊订单退款短信结束-->");
			
			newsUserMessageServiceImpl.save(new NewsUserMessage(refund.getId(), order.getUserInfo().getId(), 3));
			
			if(refundEntity == null || conResult == null){
				return Consts.FAILED;
			}
			return Consts.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public String doRefund(Admin admin, Integer id, Integer type) {
		try {
			if(id == null || id == 0){
				return Consts.PARAMS_ERROR;
			}
			Integer payType = 0;
			HospitalDoctorOrderRefund refund = hospitalDoctorOrderRefundDaoImpl.get(id);
			if(refund.getType() == 1 || refund.getType() == 3){
				Integer orderId = refund.getServicesId();
				if(orderId != null && orderId != 0){
					HospitalDoctorServicesOrder order = hospitalDoctorServicesOrderDaoImpl.get(orderId);
					if(order != null){
						/** 支付类型，1：支付宝，2：微信 **/
						payType = order.getPayType();
					}
				}
			}else if(refund.getType() == 2){
				Integer orderId = refund.getConsOrderId();
				if(orderId != null && orderId != 0){
					HospitalConsServiceOrder order = hospitalConsServiceOrderServiceImpl.get(orderId);
					if(order != null){
						/** 支付类型，1：支付宝，2：微信 **/
						payType = order.getPayType();
					}
				}
			}
			if(payType == 2){
				/** 此处微信，直接调用退费接口 **/
				String result = HttpRequestUtils.sendPost(Consts.REFUND_REQUEST, "tranId="+refund.getTransactionId());
				if(StringUtils.isNotEmpty(result)){
					VORefundData data = JSON.parseObject(result, VORefundData.class);
					if(data == null){
						return "后台处理失败！";
					}
					if(data.getReturn_code().equalsIgnoreCase(Consts.SUCCESS)){
						if(data.getResult_code().equalsIgnoreCase(Consts.SUCCESS)){
							return Consts.SUCCESS;
						}
					}
				}
			}else if(payType == 1){
				refund.setOperator(admin.getName());
				refund.setState(type);
				HospitalDoctorOrderRefund result = save(refund);
				if(result != null){
					return Consts.SUCCESS;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
		return Consts.FAILED;
	}

}
