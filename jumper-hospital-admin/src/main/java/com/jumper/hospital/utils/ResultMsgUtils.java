package com.jumper.hospital.utils;


import java.io.StringWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
/**
 * 结果消息工具类
 * @author win
 *
 */
public class ResultMsgUtils {


	private static ObjectMapper mapper = new ObjectMapper();

	//出错输出JSON数据格式
	public static String errorMsg(String error_msg)
	{
		try
		{
			ReturnMsg ret= new ReturnMsg();
			ret.setMsg(0);
			ret.setMsgbox(error_msg);
			List<Object> obj = new ArrayList<Object>();
			ret.setData(obj);

			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, ret);
			String retJSON = strWriter.toString();

			//加密返回的JSON
			String encodeString = URLEncoder.encode(retJSON, "UTF-8");
			return DesEncryptUtils.Encrypt(encodeString, Const.PARAMS_KEYS);
		}
		catch(Exception ex)
		{
			return ex.getMessage();
		}

	}

	//正确输出JSON数据格式
	public static String successMsg(String tips,Object data)
	{
		try
		{
			ReturnMsg ret= new ReturnMsg();
			ret.setMsg(1);
			ret.setMsgbox(tips);
			ret.setData(data);

			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, ret);
			String retJSON = strWriter.toString();
			String encodeString = URLEncoder.encode(retJSON, "UTF-8");
			return DesEncryptUtils.Encrypt(encodeString, Const.PARAMS_KEYS);
		}
		catch(Exception ex)
		{
			return ex.getMessage();
		}
	}

	//处理返回消息的MAP格式
	public static String processMsg(Object data)
	{
		try
		{
			ReturnMsg ret =(ReturnMsg)data;
			if(ret.getData()==null)
			{
				List<Object> obj = new ArrayList<Object>();
				ret.setData(obj);
			}
			/**先暂时处理掉无数据的时候显示暂无数据，原因：当更新成功的时候，也是返回暂无数据*/
			/*if((ret.getMsg()==Const.SUCCESS))
			{
				if(ret.getData() instanceof Collection){
					if(((Collection) ret.getData()).size()==0){
						ret.setMsgbox("暂无数据！");
					}
				}
			}*/
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, ret);
			String retJSON = strWriter.toString();
			String encodeString = URLEncoder.encode(retJSON, "UTF-8");
			return DesEncryptUtils.Encrypt(encodeString, Const.PARAMS_KEYS);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return ex.getMessage();
		}
	}
	/**
	 * 使用Gson转换list
	 * @param data
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String processGsonToMsg(Object data)
	{
		try
		{
			ReturnMsg ret =(ReturnMsg)data;
			if((ret.getMsg()==Const.SUCCESS))
			{
				if(ret.getData() instanceof Collection){
					if(((Collection) ret.getData()).size()==0){
						ret.setMsgbox("暂无数据！");
					}
				}
			}

			Gson json=new Gson();
			String encodeString = URLEncoder.encode(json.toJson(ret), "UTF-8");
			return DesEncryptUtils.Encrypt(encodeString, Const.PARAMS_KEYS);
		}
		catch(Exception ex)
		{
			return ex.getMessage();
		}
	}

	/* public static void testCycleObject() {
	       VOProvinceInfo object = new VOProvinceInfo();
	       object.setMemberId("yajuntest");
	       object.setSex("male");
	       JsonConfig jsonConfig = new JsonConfig();
	       config.registerJsonValueProcessor(ApproveStateType.class,
	                new JsonValueProcessor() {
	                    @Override
	                    public Object processObjectValue(String key, Object value,
	                            JsonConfig jsonConfig) {
	                        // TODO Auto-generated method stub
	                        if (value instanceof ApproveStateType) {
	                            ApproveStateType tmpValue = (ApproveStateType) value;
	                            return tmpValue.getValue();
	                        }
	                        return value.toString();
	                    }
	                    @Override
	                    public Object processArrayValue(Object arg0, JsonConfig arg1) {
	                        // TODO Auto-generated method stub
	                        return null;
	                    }
	                });
	       JSONObject json = JSONObject.fromObject(object, jsonConfig);
	       System.out.println(json);
	   }

	   public static void main(String[] args) {
	              JsonTest.testCycleObject();
	   }

	*/

}