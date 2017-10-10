package com.jumper.hospital.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

/**
 * 
 * @author wt
 * @date 2015-10-13
 * @desc 昊博短信Utils类
 */
public class HBSmsCodeUtils {

	// http://124.172.184.135:8080/mt?un=star&pw=123456&da=13612345678&sm=C4E3BAC3&dc=15&rd=1
	static String urlAddress;
	static String uname;
	static String pwd;
	static int dc = 15;// 0表示英文，8表示UCS2，15表示中文
	private final static Logger logger = Logger.getLogger(HBSmsCodeUtils.class);
	/**
	 * 
	 * @param msgContent
	 * @param mobile 支持群发，群发后面加';'即可
	 * @return true 发送成功；false 发送失败。
	 */
	static {
		initKeyValues();
	}

	public static boolean sendSmsMsg(String msgContent, String mobile) {
		// 组建请求
		String straddr = urlAddress + "mt?un=" + uname + "&pw=" + pwd + "&da="
				+ mobile + "&dc=" + dc + "&sm=" + str2HexStr(msgContent);
		logger.info("send HB message info url address is :\n" + straddr);
		String returnMsg = HttpPost.doGet(straddr);
		if (returnMsg.contains("r")) {
			String code = returnMsg.substring(2, returnMsg.length()).trim();
			if (!StringUtils.isNumeric(code)) {
				logger.error("code number error by code :" + code);
				return false;
			} else {
				String errorMsg = HBSmsErrorCode
						.getName(Integer.parseInt(code));
				logger.error("send HB message info failed,the error msg is:"
						+ errorMsg + "; the error code is :" + code);
				System.out.println(errorMsg);
				return false;
			}
		}
		logger.info("send HB message info successful! mobile is :" + mobile);
		return true;
	}

	/** 获取短信剩余数量 */
	public static int getMsgCount() {
		// 组建请求
		String straddr = urlAddress + "bi?un=" + uname + "&pw=" + pwd;
		String returnMsg = HttpPost.doGet(straddr);
		logger.info("the return message:" + returnMsg);
		try {
			if (returnMsg != "") {
				String[] data = returnMsg.split("&");
				String[] temp = data[1].split("=");
				// System.out.println((Integer.parseInt(temp[1].trim())));
				return (Integer.parseInt(temp[1].trim()));
			}
		} catch (Exception e) {
			logger.error("user get count error by :" + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 字符串转换成十六进制字符串
	 * 
	 * @param String
	 *            str 待转换的ASCII字符串
	 * @return str转Hex
	 */
	public static String str2HexStr(String str) {
		try {
			char[] chars = "0123456789ABCDEF".toCharArray();
			StringBuilder sb = new StringBuilder("");
			byte[] bs = str.getBytes("gbk");
			int bit;
			for (int i = 0; i < bs.length; i++) {
				bit = (bs[i] & 0x0f0) >> 4;
				sb.append(chars[bit]);
				bit = bs[i] & 0x0f;
				sb.append(chars[bit]);
				// sb.append();
			}
			return sb.toString().trim();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void initKeyValues() {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = HBSmsCodeUtils.class.getResourceAsStream("/config.properties");
			prop.load(is);
			urlAddress = prop.getProperty("HB_URL_ADDRESS");
			uname = prop.getProperty("HB_SMS_USER");
			pwd = prop.getProperty("HB_SMS_PWD");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is != null){
					is.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		//System.setProperty("java.net.preferIPv4Stack", "true");  
		//sendSmsMsg("【天使医生】本宝宝告诉你，你已经是天使医生的注册用户了，账号是：18820200672，初始密码：123456，请及时登录APP修改密码哦。可在应用宝平台下载“天使医生”或点击http://image.jumper-health.com/user.html 下载。", "18820200672");
		
		/*String content  = String.format(Consts.INSIDE_REGISTER_YW, new Object[]{"13480131932", "123456"});
		HashMap<String, String> json =new HashMap<String,String>();
		json.put("hospId", Const.YWHOSPITAL_ID);
		json.put("hospName", "义乌妇幼");
		json.put("mobile", "13480131932");
		json.put("content", content);
		json.put("SMS_SESSION_KEY", Const.SMS_SESSION_KEY);
		String sign=null;
		try {
			sign = getSignature(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<String, Object> json1 =new HashMap<String,Object>();
		json1.put("hospId", "49");
		json1.put("hospName", "天使医生");
		json1.put("content", content);
		json1.put("mobile", "13480131932");
		json.put("appid", Const.YWHOSPITAL_APPID);
		json.put("req_sign", sign);
		String url = "http://192.168.0.2:8081/common/sms/send_smsbyhb";
		String string =HttpRequestUtils.sendPost(url, JSONObject.fromObject(json));
		System.out.println(string);*/
		
		
		
		String order ="";
		String content = "您好，订单号为"+order+"的医院问诊订单已经拒绝退款。详情见：天使医生APP";
		sendSmsMsgCommon(content, "13480131932", "天使医生", 49);
	}

	/**
	 * 基础平台短信
	 * @param msgContent
	 * @param mobile
	 * @return
	 */
	public static boolean sendSmsMsgCommon(String msgContent, String mobile,String hospitalName,Integer hospitalId) {
		HashMap<String, String> json =new HashMap<String,String>();
		json.put("hospId", hospitalId+"");
		json.put("hospName", hospitalName);
		json.put("mobile", mobile);
		json.put("content", msgContent);
		json.put("SMS_SESSION_KEY", Const.SMS_SESSION_KEY);
		String sign=null;
		try {
			sign = getSignature(json);
		} catch (IOException e) {
			logger.error("getSignature error :" + e);
		}
		json.put("appid", Const.YWHOSPITAL_APPID);
		json.put("req_sign", sign);
		String url = Consts.CONSULT_IM_URL+"/sms/send_smsbyhb";
		String returnMsg =HttpRequestUtils.sendPost(url, JSONObject.fromObject(json));
		Map<String,Object> msg = JsonUtils.toHashMap(returnMsg);
		if(msg.get("msg").equals(1)){
			logger.error("send HB message info successful :" + msg.get("msgbox") +"=== mobile is="+ mobile);
			return true;
		}
		logger.info("send HB message info error! mobile is :" + mobile);
		return false;
	}
	/**
	 * 签名生成算法
	 * @param HashMap<String,String> params 请求参数集，所有参数必须已转换为字符串类型,params中需要的的参数：hospId，hospName，mobile，content，SMS_SESSION_KEY。
	 * @param String secret 签名密钥
	 * @return 签名
	 * @throws IOException
	 */
	public static String getSignature(HashMap<String,String> params) throws IOException
	{
	    // 先将参数以其参数名的字典序升序进行排序
	    Map<String, String> sortedParams = new TreeMap<String, String>(params);
	    Set<Entry<String, String>> entrys = sortedParams.entrySet();
	 
	    // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
	    StringBuilder basestring = new StringBuilder();
	    for (Entry<String, String> param : entrys) {
	        basestring.append(param.getKey()).append("=").append(param.getValue());
	    }
	    //获取密钥
	    String SMS_SESSION_SECRET = Const.SMS_SESSION_SECRET;
	    basestring.append(SMS_SESSION_SECRET);
	    
	    logger.info("SMS basestring:"+basestring);
	    
	    // 使用MD5对待签名串求签
	    byte[] bytes = null;
	    try {
	        MessageDigest md5 = MessageDigest.getInstance("MD5");
	        bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
	    } catch (GeneralSecurityException ex) {
	        throw new IOException(ex);
	    }
	 
	    // 将MD5输出的二进制结果转换为小写的十六进制
	    StringBuilder sign = new StringBuilder();
	    for (int i = 0; i < bytes.length; i++) {
	        String hex = Integer.toHexString(bytes[i] & 0xFF);
	        if (hex.length() == 1) {
	            sign.append("0");
	        }
	        sign.append(hex);
	    }
	    return sign.toString();
	}
}
