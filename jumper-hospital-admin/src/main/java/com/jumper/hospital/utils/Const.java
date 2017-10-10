package com.jumper.hospital.utils;


public class Const {
	public static String ORDER_NODISCUSS_TIMEOUT;//订单未评价超时
	public static int PERSON_MIN_ORDER_DAYS;//人员最少预约时长
	public static String REAL_BASE_PATH;
	public static String BASE_PATH;
	public static String PROGECT_NAME;
	public static String COPY_RIGHT;
	public static int PAGE_SIZE;

	public static String PARAMS_KEYS = "*JUMPER*";		//用于加密传递参数据KEY
	public static String SIGNED_KEYS = "JUMPER2014API";	//用于MD5加密密钥的值

	public static String USER_IMAGE_DIR = "";			//用户头像上传图片目录
	public static String USER_RECORDS_DIR= "";			//用户胎心测试文件上传目录
	public static String MUSICS_DIR= "";					//存放Mp3文件


	public static String BASE_FILE_URL = "";			//用户上传图片文件的URL地址

	public static String UPLOAD_DIR = "";				//文件上传url

	public static String  SHOP_URL_ADDRESS= "";			//商铺的URL


	public static String SMS_USER = "";					//发送短信网关的用户名
	public static String SMS_PASSWORD = "";				//发关短信网关的密码


	public static int TOTAL_WEEKS = 40;					//孕期时间40周
	public static int TOTAL_DAYS = 280;					//孕期的总天数是280天

	public static int SUCCESS = 1;						//接口请求处理成功
	public static int FAILED = 0;
	
	/************孕期管理,监测数据****************/
	public static String  MONITOR_REPORT_FILE_PATH = "";		//报告文件存放位置
	public static String  HOSPITAL_NAME = "天使医院";	  //胎心报告医院名称
	public static String REPORT_TITLE = "电脑胎儿监护图";     //胎心报告副标题
	public static String MONITOR_PDF_FONT_PATH = ""; 			  //胎心报告字体文件地址
	public static String NO_ECG_IMAGE="";						//无心电数据默认图
	//上线部署时间
	public static String CLOUD_VISIT_TIME = "";
	//随访调PS地址
	public static String PS_VISIT_HOST_URL = "";
	// 云端孕妇学校连接地址
	public static String SCHOOL_URL = "";
	//孕妇学校access_key
	public static String ACCESS_KEY="A2F07C3DF8E2EA10168BCCE2DCC28DD2";
	//孕妇学校objectId
	public static String OBJECT_ID="1";
	
	//云端体重营养项目链接
	public static String WEIGHT_NUTRITION_URL = "";
	//云端高危项目链接
	public static String HIGHRISK_URL = "";
	
	//哪些账号可以重置密码
	public static String RESET_USER_ID="";
	/** 京柏分给悦康APPID **/
	public static String YKAPPID = "jb1r5e116n102bdc9d";
	/** 京柏分给悦康集成KEY **/
	public static String YKKEY = "ec6af06d911ea5221n81eea7fx1en071";
	/** 延安大学附属医院key**/
	public static String YK_HOSPITAL_KEY = "24E9EE6A7";
	/**
	 * 新增心电token
	 */
	public static String TOKEN = null;
	
	/** Jpush masterSecret**/
	public static String MASTER_SECRET = "82f779d50004c599d6d18130";
	/** Jpush appKey **/
	public static String APP_KEY = "503302f84d85a7fc2e263e35";
	/** Jpush 全推参数 标示 0代表不推 ios推内网    1 ios外网**/
	public static String IS_ALL = "0";
	
	/** 2017 07 21 新增义乌妇幼短信**/
	public static String SMS_SESSION_KEY = "ByqGSM49AgEGBSuBht*FrUYPl6GMO4ptxJTgCAv48kwHQAwF0CocT*Z4txgK7JcavBfxkQpnKz85hS+Ci4dmhRANCAARL6YljBDHyDVhkx0CAB2YKAAATdjtx";
	public static String SMS_SESSION_SECRET = "503302f84d85a7fc2e263e35";
	public static String YWHOSPITAL_ID = "8569";
	public static String YWHOSPITAL_APPID = "0004";//短信基础平台 标识
}
