package com.jumper.hospital.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @author rent
 * @date 2015-08-13
 * @desc json简单封装
 */
public class JsonUtils {
	protected static Logger logger = Logger.getLogger(JsonUtils.class);
	/**
	 * 直接输出Json内容的简便函数(此函数会直接将hibernate懒加载对象加载，数据量多，慎用，转换vo即可)
	 * eg.
	 * render("text/plain", "hello", "encoding:GBK");
	 * render("text/plain", "hello", "no-cache:false");
	 * render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @desc public static final Object parse(String text); // 把 JSON 文本 parse 为 JSONObject 或者 JSONArray 
	 * public static final JSONObject parseObject(String text)；  //  把 JSON 文本 parse 成 JSONObject 
	 * public static final <T> T parseObject(String text, Class<T> clazz); // 把 JSON 文本 parse 为 JavaBean 
	 * public static final JSONArray parseArray(String text); // 把 JSON 文本 parse 成 JSONArray 
	 * public static final <T> List<T> parseArray(String text, Class<T> clazz); //把 JSON 文本 parse 成 JavaBean 集合 
	 * public static final String toJSONString(Object object); // 将 JavaBean 序列化为 JSON 文本 
	 * public static final String toJSONString(Object object, boolean prettyFormat); // 将 JavaBean 序列化为带格式的 JSON 文本 
	 * public static final Object toJSON(Object javaObject); 将 JavaBean 转换为 JSONObject 或者 JSONArray 。
	 */
	public static void render(final HttpServletResponse res, final Object object) {
		res.setHeader( "Pragma", "no-cache" );
		res.addHeader( "Cache-Control", "must-revalidate" );
		res.addHeader( "Cache-Control", "no-cache" );
		res.addHeader( "Cache-Control", "no-store" );

		res.addHeader( "Accept-Language", "no-store" );
		res.setDateHeader("Expires", 0);
		res.setHeader("ContentType", "text/json");  
		res.setContentType("text/plain;charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		try {
			String json = JSON.toJSONString(object);
			res.getWriter().write(json);
			res.getWriter().flush();
			res.getWriter().close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static void renderString(final HttpServletResponse res, final String content) {
		//HttpServletResponse response = initResponseHeader(res, contentType, headers);
		res.setHeader( "Pragma", "no-cache" );
		res.addHeader( "Cache-Control", "must-revalidate" );
		res.addHeader( "Cache-Control", "no-cache" );
		res.addHeader( "Cache-Control", "no-store" );

		res.addHeader( "Accept-Language", "no-store" );
		res.setDateHeader("Expires", 0);
		res.setHeader("ContentType", "text/json");  
		res.setContentType("text/plain;charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		try {
			res.getWriter().write(content);
			res.getWriter().flush();
			res.getWriter().close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static <T> List<T> convertToList(List<String> dataList, Class<T> t){
		try {
			List<T> list = new ArrayList<T>();
			if(dataList != null && !dataList.isEmpty()){
				for(String str : dataList){
					list.add(JSON.parseObject(str, t));
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取服务器json文件内容
	 * @param myurl 传入的URL 仅json
	 * @return json字符串
	 * @throws Exception
	 */
	public static String getJsonFileStr(String myurl) {
		try {
			URL url = new URL(myurl);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream(), "UTF-8"));
			String s = "";
			StringBuilder sb = new StringBuilder();
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return String.valueOf("");
		}
	}

	/**
	 * 
	 * @author yanghuanzhi
	 * @date 2016年8月18日09:28:59
	 * @Description: 把对象转化成json字符串
	 */
	public static String write2String(Object value){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			logger.error("write parse json fail!",e);

		}
		return null;
	}
	public static Map<String,Object> readObject2Map(String jsonStr){
		
		ObjectMapper mapper = new ObjectMapper();
		//解析器支持解析单引号
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES,true);
		//解析器支持解析结束符
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		try {
			 jsonMap = mapper.readValue(jsonStr,new TypeReference<Map<String,Object>>() { }); //转换为HashMap对象
		} catch (Exception e) {
			logger.error("read parse json fail!",e);
		} //转换为HashMap对象
		return jsonMap;
	}
	 public static <T> T toObject(String content,  com.fasterxml.jackson.core.type.TypeReference<T> valueTypeRef)  {
		 ObjectMapper mapper = new ObjectMapper();
		//解析器支持解析单引号
		mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
		//解析器支持解析结束符
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
        //	mapper.getTypeFactory()
    	try {
			return mapper.readValue(content,  valueTypeRef);
		} catch (JsonParseException e) {
			logger.error("JsonParseException ... content:"+content,e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException ... content:"+content,e);
		} catch (IOException e) {
			logger.error("IOException",e);
		}
    	return null;
	}
	 
	 
	  /***
	     * 将对象转换为HashMap
	     * @param object
	     * @return
	     */
	    public static HashMap<String, Object> toHashMap(Object object)
	    {
	        HashMap<String, Object> data = new HashMap<String, Object>();
	        JSONObject jsonObject = JsonUtils.toJSONObject(object);
	        Iterator<?> it = jsonObject.keys();
	        while (it.hasNext())
	        {
	            String key = String.valueOf(it.next());
	            Object value = jsonObject.get(key);
	            data.put(key, value);
	        }

	        return data;
	    }
	    /***
	     * 将对象转换为JSON对象
	     * @param object
	     * @return
	     */
	    public static JSONObject toJSONObject(Object object)
	    {
	        return JSONObject.fromObject(object);
	    }
	    
	    /***
	     * 将JSON对象数组转换为传入类型的List
	     * @param 
	     * @param jsonArray
	     * @param objectClass
	     * @return
	     */
	    @SuppressWarnings({ "unchecked", "deprecation" })
		public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass)
	    {
	        return JSONArray.toList(jsonArray, objectClass);
	    }
	    
	    
	    
	    /***
	     * 将对象转换为List>
	     * @param object
	     * @return
	     */
	    // 返回非实体类型(Map)的List
	    public static List<Map<String, Object>> toList(Object object)
	    {
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        JSONArray jsonArray = JSONArray.fromObject(object);
	        for (Object obj : jsonArray)
	        {
	            JSONObject jsonObject = (JSONObject) obj;
	            Map<String, Object> map = new HashMap<String, Object>();
	            Iterator<?> it = jsonObject.keys();
	            while (it.hasNext())
	            {
	                String key = (String) it.next();
	                Object value = jsonObject.get(key);
	                map.put((String) key, value);
	            }
	            list.add(map);
	        }
	        return list;
	    }

}
