package com.jumper.hospital.utils.visit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.NullArgumentException;
import org.apache.log4j.Logger;


/**
 * http请求工具类
 */
public class HttpClient {
	protected static Logger logger = Logger.getLogger(HttpClient.class);
	
	Map<String,Object> params;
	String url;
	Map<String,String> headers = new HashMap<String,String>();
	
	public HttpClient(String url) {
		this(url,null);
	}
	/**
     * @param url　提交的url
     * @param params　提交的参数
	 */
	public HttpClient(String url, Map<String, Object> params) {
		super();
		this.params = params;
		this.url = url;
		if(url == null){
			throw new NullArgumentException("url");
		}
	}

	public enum Method{
		POST,GET;
	}
	
	private HttpURLConnection  getUrlConnection(Method method) throws IOException  {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(this.url).openConnection();
		} catch (IOException e) {
			throw  e;
		}
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setConnectTimeout(5000);//连接超时 单位毫秒
		conn.setReadTimeout(5000);//读取超时 单位毫秒
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		conn.setRequestProperty("contentEncoding", "UTF-8");
		conn.setRequestProperty("contentType", "utf-8");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		conn.setRequestMethod(method.toString());
		for(Map.Entry<String, String> e:headers.entrySet()){
			 conn.addRequestProperty(e.getKey(), e.getValue());
		}
		if(method == Method.GET){
			if(params!=null && !params.isEmpty()){
				if(url.lastIndexOf("?")>0){
					url=url+"&"+getParamsStr(params);
				}else{
					url=url+"?"+getParamsStr(params);
				}
				logger.info("the url is "+url);
			}
		}else if(method == Method.POST){
			conn.setDoInput(true);
		}
		return conn;
	}
	
	/**
	 * @param url /usr/list.do
	 * @param params 请求的参数
	 * @param 返回请求的内容
	 * @throws IOException 
	 */
	public  String post() throws IOException {
	    HttpURLConnection conn = null;
		try {
			conn = getUrlConnection(Method.POST);
		    conn.connect();
		    if(params!=null && !params.isEmpty()){
		    	//String paramsJoin = getParamsStr(params);
		    	String paramsJoin = getParamsStr(this.params);
		    	logger.info("the params is "+paramsJoin);
		    	conn.getOutputStream().write(paramsJoin.getBytes());// 输入参数
		    }
		    int responseCode= conn.getResponseCode();
		    if(responseCode == 200 || responseCode == 302){
		    	InputStream ins=conn.getInputStream();
				return readStringFromStream(ins);
				//return new String(readInputStream(ins));
			}
			logger.error("responseCode:"+conn.getResponseCode()+",url:"+url);
		} catch (IOException e) {
			throw e;
		}finally{
			if(conn != null ){
				conn.disconnect();
			}
		}
		return null;
	}
	public  String readStringFromStream(InputStream in)      throws IOException {
	     /*   StringBuilder sb = new StringBuilder();
	        for (int i = in.read(); i != -1; i = in.read()) {
	            sb.append((char) i);
	        }
	        in.close();
	        return sb.toString();*/
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			byte[] data = outStream.toByteArray();// 网页的二进制数据
			outStream.close();
			in.close();
			return new String(data,"UTF-8");
		}
	/**
	 * @throws IOException 
	 * http get请求
	 * 
	 * @param url
	 *            /usr/list.do?ui=1或者/usr/list.do
	 * @param params
	 *            　请求的参数
	 * @param 返回请求的内容
	 * @throws
	 */
	public  String get() throws IOException {
		HttpURLConnection conn = null;
		InputStream ins = null;
		try {
			conn  = getUrlConnection(Method.GET);
			conn.connect();
			int responseCode= conn.getResponseCode();
			if(responseCode == 200 || responseCode == 302){
				ins = conn.getInputStream();
				return readStringFromStream(ins);
			}
			logger.info("responseCode:"+conn.getResponseCode()+",url:"+url);
		}catch (IOException e) {
			throw e;
		}finally{
			if(conn != null ){
				conn.disconnect();
			}
		}
		return null;
	}

	public String getParamsStr(Map<String, Object> params){
		StringBuilder sBuilder = new StringBuilder();
		for(Map.Entry<String, Object> e:params.entrySet()){
			sBuilder.append(e.getKey()).append("=").append(e.getValue()).append("&");
		}
		if(sBuilder.length() > 0){
			sBuilder.deleteCharAt(sBuilder.length()-1);
		}
		return sBuilder.toString();
	}
	
	/**
	 * 设置 http  header 属性
	 */
	public HttpClient addHeader(String property,String value){
		headers.put(property, value);
		return this;
	}
}
