package com.jumper.hospital.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ReportPushUtil {
	
	private static final Logger logger = Logger.getLogger(ReportPushUtil.class);
	/**
	 * 
	 * @param app_id 京柏分配第三方商户号
	 * @param report_url
	 * @param jumper_order_no
	 * @param order_no
	 * @param time_end
	 * @return
	 * @throws Exception
	 */
	public static boolean push(String push_url, String app_id, String report_url, String jumper_order_no, String order_no, String time_end) throws Exception{
		if(StringUtils.isBlank(push_url) || StringUtils.isBlank(report_url)){
			logger.info("京柏生成报告通知失败！通知地址为空！");
			return false;
		}
		if(StringUtils.isBlank(report_url)){
			logger.info("京柏生成报告通知失败！报告地址为空！");
			return false;
		}
		JSONObject biz_content = new JSONObject();
		biz_content.put("report_url", Const.BASE_FILE_URL+report_url);
		biz_content.put("jumper_order_no", jumper_order_no);
		biz_content.put("order_no", order_no);
		biz_content.put("time_end", time_end);
		String combinedSign = "biz_content=" + biz_content + "&key=" + Const.YKKEY;
		String mySign = MD5EncryptUtils.getMd5Value(combinedSign);
		
		String param = "";
		param += "result_code=" + "SUCCESS";
		param += "&app_id=" + app_id;
		param += "&format=" + "JSON";
		param += "&charset=" + "UTF-8";
		param += "&sign_type=" + "MD5";
		param += "&biz_content=" + biz_content.toJSONString();
		param += "&sign=" + mySign;
		String result = HttpRequestUtils.sendPost(push_url, param);
		logger.info("京柏生成报告通知！通知地址："+push_url+"，通知参数："+param);
		if(StringUtils.isBlank(result)){
			logger.info("京柏生成报告通知第三方异常！第三方返回数据为空，商户号："+Const.YKAPPID+"，商户订单号："+order_no);
			return false;
		}
		JSONObject resultData = null;
		try {
			resultData = (JSONObject)JSON.parse(result);
		} catch (Exception e) {
			logger.info("京柏生成报告通知第三方异常！第三方返回参数不合法，商户号："+Const.YKAPPID+"，商户订单号："+order_no+"，返回参数："+result);
			return false;
		}
		boolean flag = "SUCCESS".equalsIgnoreCase(resultData.getString("result_code"));
		if(flag){
			logger.info("京柏生成报告通知第三方成功！商户号："+Const.YKAPPID+"，商户订单号："+order_no+"，返回参数："+resultData.toJSONString());
			return true;
		}else{
			logger.info("京柏生成报告通知第三方失败！商户号："+Const.YKAPPID+"，商户订单号："+order_no+"，返回参数："+resultData.toJSONString());
			return false;
		}
	}

	public static void main(String[] args) throws Exception {
		String push_url = "http://api.beierbaby.com/api/eshop/jumper/remotemonitor/report";
		String app_id= Const.YKAPPID;
		String report_url = "http://image.jumper-health.com/group1/M00/21/6E/cEpKhVhBbkOANEcWAADEvVl5JyE413.PDF"; 
		String jumper_order_no = ""; 
		String order_no = "201702200001";
		String time_end = TimeUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss");
		ReportPushUtil.push(push_url, app_id, report_url, jumper_order_no, order_no, time_end);
	
	}
}
