package com.jumper.hospital.listener;
import java.io.File;
import java.io.IOException;
/**
 * 项目初始化赋值
 * @author rent
 */
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.jumper.hospital.utils.Const;
import com.jumper.hospital.utils.Consts;

public class StartupApplicationContextListener implements ServletContextListener {
	public static final String R_PATH = "/config/config.properties";
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			ServletContext servletContext = servletContextEvent.getServletContext();
			Properties config = new Properties();
			//config.load(servletContext.getResourceAsStream("/WEB-INF/classes/config.properties"));
			
			String path = System.getenv("WEBAPP_APP_HOSTPITALADMIN_CONF")+R_PATH;
			if(!new File(path).exists()){
				throw new RuntimeException("not found file "+path);
			}
			Resource resource = new FileSystemResource(path); //new ClassPathResource(path, ConfigProUtils.class);
			try {
				config = PropertiesLoaderUtils.loadProperties(resource);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			Consts.VERSION = config.getProperty("VERSION");
			Consts.BASE_FILE_URL = config.getProperty("BASE_FILE_URL");
			String chat_url = StringUtils.trimToEmpty(config.getProperty("CHAT_URL"));
			if(StringUtils.isNotBlank(chat_url)){
				Consts.CHAT_URL = chat_url;
			}
			servletContext.setAttribute("CHAT_URL", Consts.CHAT_URL);
			servletContext.setAttribute("BASE_PATH", Consts.BASE_FILE_URL);
			Consts.PUSH_MESSAGE_URL = config.getProperty("PUSH_MESSAGE_URL");
			servletContext.setAttribute("PUSH_MESSAGE_URL", Consts.PUSH_MESSAGE_URL);
			
			Consts.FASTDFS_ADDRESS = config.getProperty("FASTDFS_ADDRESS");
			Consts.FASTDFS_PORT = config.getProperty("FASTDFS_PORT");
			
			Consts.PDF_FONT_PATH = config.getProperty("PDF_FONT_PATH");
			Consts.COURSE_QR_URL = config.getProperty("COURSE_QR_URL");
			Consts.WEIGHT_REQUEST_URL = config.getProperty("WEIGHT_REQUEST_URL");
			Consts.DEFAULT_IMAGE = config.getProperty("DEFAULT_IMAGE");
			Consts.DEFAULT_ONLINE_IMAGE = config.getProperty("DEFAULT_ONLINE_IMAGE");
			
			Consts.REFUND_REQUEST = config.getProperty("REFUND_REQUEST");
			
			Consts.COMMUNITY_HOSPITAL_URL = config.getProperty("COMMUNITY_HOSPITAL_URL");
			
			//初始化文件服务地址,报告打印字体,报告文件路径
			Const.MONITOR_PDF_FONT_PATH = config.getProperty("MONITOR_PDF_FONT_PATH");
			Const.MONITOR_REPORT_FILE_PATH = config.getProperty("MONITOR_REPORT_FILE_PATH");
			/** 云随访**/
			//云随访上线时间
			Const.CLOUD_VISIT_TIME = config.getProperty("CLOUD_VISIT_TIME");
			//随访PS地址
			Const.PS_VISIT_HOST_URL = config.getProperty("PS_VISIT_HOST_URL");
			// 孕妇学校链接地址
			Const.SCHOOL_URL = config.getProperty("SCHOOL_URL");
			//云端高危项目链接
			Const.HIGHRISK_URL = config.getProperty("HIGHRISK_URL");
			//云端体重营养项目链接
			Const.WEIGHT_NUTRITION_URL = config.getProperty("WEIGHT_NUTRITION_URL");
			Const.ACCESS_KEY = config.getProperty("ACCESS_KEY");
			Const.OBJECT_ID = config.getProperty("OBJECT_ID");
			Const.RESET_USER_ID = config.getProperty("RESET_USER_ID");
			//后续增加
			Consts.JPUSH_VERSION = config.getProperty("JPUSH_VERSION","").trim();
			Consts.CONSULT_IM_URL = config.getProperty("CONSULT_IM_URL","http://192.168.0.3:8081/common").trim();
			Consts.DEVICE_URL = config.getProperty("DEVICE_URL","http://mobile.tsys91.com:8080/lease").trim();
			Consts.IS_DEBUG = config.getProperty("IS_DEBUG","0").trim();
			//jpush推送
			Const.MASTER_SECRET = config.getProperty("MASTER_SECRET","82f779d50004c599d6d18130").trim();
			Const.APP_KEY = config.getProperty("APP_KEY","503302f84d85a7fc2e263e35").trim();
			Const.IS_ALL = config.getProperty("IS_ALL","0").trim();
			//悦康（延大医院）报告推送相关参数
			Const.YKAPPID = config.getProperty("YKAPPID","jb1r5e116n102bdc9d").trim();
			Const.YKKEY = config.getProperty("YKKEY","ec6af06d911ea5221n81eea7fx1en071").trim();
			Const.YK_HOSPITAL_KEY = config.getProperty("YK_HOSPITAL_KEY","24E9EE6A7").trim();
			//增加义乌妇幼短信签名
			Const.SMS_SESSION_KEY = config.getProperty("SMS_SESSION_KEY","ByqGSM49AgEGBSuBht*FrUYPl6GMO4ptxJTgCAv48kwHQAwF0CocT*Z4txgK7JcavBfxkQpnKz85hS+Ci4dmhRANCAARL6YljBDHyDVhkx0CAB2YKAAATdjtx").trim();
			Const.SMS_SESSION_SECRET = config.getProperty("SMS_SESSION_SECRET","503302f84d85a7fc2e263e35").trim();
			Const.YWHOSPITAL_ID = config.getProperty("YWHOSPITAL_ID","8569").trim();
			//义乌对应的角色名称
			Consts.YWFY_ROLE_ID = config.getProperty("YWFY_ROLE_ID","22").trim();
			//预警记录URL
			Consts.WARNING_URL = config.getProperty("WARNING_URL","http://192.168.0.3:8081/monitor").trim();
			//自动评分URL
			Consts.AUTOSCORE_URL = config.getProperty("AUTOSCORE_URL","http://192.168.0.3:8080/monitor").trim();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
