package com.jumper.hospital.utils;
/**
 * 常量配置
 * @author rent
 *
 */
public class Consts {

	
	public static String BASE_FILE_URL = "http://image.jumper-health.com";
	/**消息推送**/
	public static String PUSH_MESSAGE_URL = "http://192.168.2.124:8081/mobile/push";
	/** 体重管理数据初始化请求地址 **/
	public static String WEIGHT_REQUEST_URL = "http://112.74.13.124:7002/health/import/data";
	/** 用于Http请求之间加密的秘钥协定 **/
	public static String APP_KEY = "JUMPER2014API";
	/** 用户Http请求参数的加密 **/
	public static String PARAM_KEY = "*JUMPER*";
	/** 布尔值false对应的0 **/
	public static Integer FALSE = 0;
	/** 布尔值true对应的1 **/
	public static Integer TRUE = 1;
	/** 版本号 v+日期+版本 **/
	public static String VERSION = "V160506.1.2";
	/** 文件上传进度session存放 **/
	public static String PROGRESS = "upload_progress";
	/** 课堂推送tag **/
	public static String CLASS_PUSH_TAG = "hosp_%s_chan_%s_perid_%s";
	/** APP用户默认密码，这里是123456的MD5加密结果 **/
	public static String APP_DEFAULT_PASSWORD = "E10ADC3949BA59ABBE56E057F20F883E";
	/** 后台出错日志记录TAG **/
	public static String ERROR_TAG = "**ERROR**";
	/** 院内胎监生成APP用户短信 **/
	public static String INSIDE_REGISTER = "本宝宝告诉你，你已经是天使医生的注册用户了，账号是：%s，初始密码：%s，请及时登录APP修改密码哦。可在应用宝平台搜索“天使医生”或点击http://image.jumper-health.com/user.html 下载。";
	/** 院内胎监生成APP用户短信 **/
	public static String INSIDE_REGISTER_YW = "本宝宝告诉你，你已经是义乌妇幼的注册用户了，账号是：%s，初始密码：%s，请及时登录APP修改密码哦。可在应用宝平台搜索“义乌妇幼” 下载。";
	/** 陕西省身份地址 **/
	public static Integer SHANXI_PROVINCE = 20;
	/************************************************ 分割线 *********************************************************/
	
	/**APP下载路径**/
	public static String MOBILE_DOWNLOAD_URL="http://www.jumper-health.com";
	/** 常用时间格式化一 **/
	public static String FORMAT_TIME_ONE = "yyyy-MM-dd HH:mm:ss";
	/** 常用时间格式化二 **/
	public static String FORMAT_TIME_TWO = "yyyy-MM-dd HH:mm";
	/** 常用时间格式化三 **/
	public static String FORMAT_TIME_THREE = "yyyy-MM-dd";
	
	/** 登陆失败等待时间 **/
	public static int loginFailureLockTime = 10;
	/** 账户登陆失败锁定次数 **/
	public static int accountLockCount = 6;
	/** 常规监控Redis存放值，格式为monitor:normal:1 最后数字为当前消费订单ID **/
	public static String NORMAL_MONITOR_KEY = "monitor:normal:";
	/** 实时监控排队Redis数据存放key值 **/
	public static String MONITOR_QUEUE_KEY = "live:queue:";
	/** 模块名key **/
	public static String MODULE = "module";
	/** 父级菜单key **/
	public static String MENU = "menu";
	/** 子菜单key **/
	public static String SUBMENU = "submenu";
	
	/** 远程监护模块，用户前端界面控制 **/
	public static String REMOTE_MODULE = "remote";
	/** 监控用护模块 **/
	public static String USER_MODULE = "user";
	/** 用户列表 **/
	public static String USER_MENU_LIST = "userList";
	/** 今日用户 **/
	public static String USER_SUBMENU_TODAY = "userToday";
	/** 全部用户 **/
	public static String USER_SUBMENU_ALL = "userAll";
	/** 失效用户 **/
	public static String USER_SUBMENU_DISABLE = "userDisable";
	/** 医院管理模块，用户前端界面控制 **/
	public static String HOSPITAL_MODULE = "hospital";
	/** 资讯管理模块，用户前端界面控制 **/
	public static String INFORMATION_MODULE="information";
	/** 服务设置 **/
	public static String HOSPITAL_MENU_SERVICE = "service";
	/*操作日志*/
	public static final String HOSPITAL_OPERATE_LOG = "operateLog";
	/** 医院管理员列表 **/
	public static String HOSPITAL_SUBMENU_ADMIN = "manager";
	/** 医院管理员列表 **/
	public static String HOSPITAL_SUBMENU_SUBORDINATE = "subordinate";
	/** 监控服务设置 **/
	public static String HOSPITAL_SUBMENU_REMOTE = "remoteService";
	/** 医院体重管理 **/
	public static String HOSPITAL_SUBMENU_WEIGHT = "weightService";
	/** 医院孕妇学校服务 **/
	public static String HOSPITAL_SUBMENU_SCHOOL = "schoolService";
	/** 医院孕妇学校服务 **/
	public static String HOSPITAL_SUBMENU_LEASE = "leaseService";
	/** 医院孕妇学校服务 **/
	public static String HOSPITAL_SUBMENU_CLASS = "classService";
	/**医院网络诊室**/
	public static String HOSPITAL_SUBMENU_NETWORK_INTE = "networkService";
	/** 个人信息隐私设置 **/
	public static String HOSPITAL_SUBMENU_PRIVACY = "personalPrivacyService";
	/** 设备业务 */
	public static String HOSPITAL_MENU_DEVICE = "device";
	/** 设备基本信息 */
	public static String HOSPITAL_SUBMENU_DEVICEINFO = "deviceInfo";
	/** 设备基本信息 */
	public static String HOSPITAL_SUBMENU_MARKETINGSET = "marketingSet";
	
	/** 报告模块 **/
	public static String REPORT_MODULE = "report";
	/** 待审核报告 **/
	public static String REPORT_WAIT_AUDIT = "audit";
	/** 已完成报告 **/
	public static String REPORT_HAS_FINISH = "finish";
	
	/** 高危项目模块 **/
	public static String HIGHRISK_MODULE = "highrisk";
	/** 体重营养项目模块 **/
	public static String WEIGHT_NUTRITION_MODULE = "weight";
	
	/** 孕妇学校模块 **/
	public static String SCHOOL_MODULE = "school";
	/** 孕妇学校课程库 **/
	public static String SCHOOL_MENU_LIBRARY = "library";
	/** 孕妇学校课程表 **/
	public static String SCHOOL_MENU_SCHEDULE = "schedule";
	/** 孕妇学校下线线课程库 **/
	public static String SCHOOL_SUBMENU_LIBRARY = "offlib";
	/** 孕妇学校线上线课程库 **/
	public static String SCHOOL_SUBMENU_SCHEDULE = "onlib";
	/** 课程表线下课程 **/
	public static String SCHOOL_SUBMENU_OFFLINE = "offLine";
	/** 课程表线上课程 **/
	public static String SCHOOL_SUBMENU_ONLINE = "onLine";
	/************************************************* 权限管理模块 ******************************************************/
	public static String AUTH_MODULE = "auth";
	/** 管理员列表 **/
	public static String AUTH_MENU_ADMIN_LIST = "adminList";
	/** 角色列表 **/
	public static String AUTH_MENU_ROLE_LIST = "roleList";
	/** 模块列表 **/
	public static String AUTH_MENU_MODULE_LIST = "moduleList";

	/************************************************* 家庭医生模块 ******************************************************/
	public static String FAMILY_MODULE = "family";
    /** 待审核报告 **/
    public static String FAMILY_MENU_WAITAUDIT_REPORT = "waitAudit";
    /** 用户信息 **/
    public static String FAMILY_MENU_USERINFO_LIST = "userinfo";
    /** 医生账号管理 **/
    public static String FAMILY_MENU_DOCTOR_ACCOUNT = "doctor";

	/************************************************ 分割线 *********************************************************/
	
	/** ajax请求参数错误返回值 **/
	public static String PARAMS_ERROR = "paramsError";
	/** 医院id为空**/
	public static String HOSPITALID_ISNULL = "hospitalIdIsNull";
	/** ajax请求异常返回值 **/
	public static String ERROR = "error";
	/** ajax请求成功返回值 **/
	public static String SUCCESS = "success";
	/** ajax请求失败返回值 **/
	public static String FAILED = "failed";
	/** 手机号码被占用 **/
	public static String MOBILE_EXIST = "mobileExist";
	/** 修改密码原密码输入错误 **/
	public static String OLD_PASS_ERROR = "passError";
	/** 数据重复添加 **/
	public static String REPEAT = "repeat";
	/** 返回结果超过最大条数 **/
	public static String OVER_LARGEST="overLargest";
	/**  时间上出现了重复   **/
	public static String TIME_OVERLAP="timeOverlap";
	/** 被占用 **/
	public static String EXIST = "exist";
	/** 推送失败 **/
	public static String PUSH_ERROR = "pushError";
	/** 超过允许添加纪录范围 **/
	public static String OUT_COUNT = "outCount";
	/************************************************ 分割线 *********************************************************/
	
	/** 聊天URL **/
	public static String CHAT_URL = "http://192.168.2.124:9999";
	/** FastDFS连接地址 10.116.44.63**/
	public static String FASTDFS_ADDRESS = "112.74.74.133";
	/** FastDFS连接端口 **/
	public static String FASTDFS_PORT = "22122";
	/** 孕妇学校二维码扫描以后请求的地址 **/
	public static String COURSE_QR_URL = "http://10.0.3.3:8083/school/offlineSign?detail_id=";
	/** PDF中文字体文件地址 **/
	public static String PDF_FONT_PATH = "F://";
	/** 线下课程默认图片   **/
	public static String DEFAULT_IMAGE="/group1/M00/01/6A/CgADQ1bY3XuAXPIRAABfSQOz7Ns273.png";
	/** 线上课程默认图片 **/
	public static String DEFAULT_ONLINE_IMAGE = "/group1/M00/01/A1/CgADQ1bqCWaAFGJKAAG_2WncFH4135.jpg";
	/** 退款请求的地址 **/
	public static String REFUND_REQUEST = "http://angeldoctor.6655.la:30709/notify/handler/refund";
	/** 社康链接**/
	public static String  COMMUNITY_HOSPITAL_URL="";
	/** 孕期管理 **/
	public static String PREGNANT_MODULE = "pregnant";
	/** 档案管理 **/
	public static String PREGNANT_ARCHIVE_MODULE = "archive";
	
	/** socketSession存储的用户信息 **/
	public static String WEBSOCKET_USERNAME = "su";
	
	/** 云随访成功提示 **/
	public static String VISITSUCCESS = "success";
	
	/**建海绑定接口地址*/
	public static String PS_JH_BIND_VISIT = "/doctor/bindHosVisitorAccount.do";
	/** 建海获取随访首页地址*/
	public static String PS_JH_VISIT_INDEX = "/doctor/getVisitorIndex.do";
	/**修改云随访角色*/
	public static String PS_JH_VISIT_EDITROLE = "/doctor/editVisitRole.do";
	/** 胎心电话*/
	public static String PS_JH_VISIT_CALL = "/doctor/callToPatient.do";
	
	
	/**************分割线 ***********************/
	/** 医院问诊IM地址*/
	public static String CONSULT_IM_URL = "http://192.168.0.3:8081/common";
	/**预警记录访问路径**/
	public static String WARNING_URL = "http://192.168.0.3:8081/monitor";
	/**预警记录访问路径**/
	public static String AUTOSCORE_URL = "http://192.168.0.3:8080/monitor";
	/** 设备租赁 */
	public static String DEVICE_URL = "http://mobile.tsys91.com:8080/lease";
	
	/**=====================================================**/
	/**推送版本**/
	public static String JPUSH_VERSION = "4.0";
	/** #是否是测试 应用于推送用户识别 如果为TRUE则是用户追加test  false则用户ID不追加 **/
	public static String IS_DEBUG = "1";
	
	/** 医院问诊退款  用户短信 **/
	public static String HOSPITAL_SMS_DOCTER = "您好，订单号为：%s的医院问诊订单已经拒绝退款。详情见：%sAPP。";
	
	//义乌对应的角色名称
	public static String YWFY_ROLE_ID="22";
}
