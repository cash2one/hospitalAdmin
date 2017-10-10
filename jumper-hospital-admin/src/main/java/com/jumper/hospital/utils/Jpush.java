package com.jumper.hospital.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-6-27
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public class Jpush {
	
	protected static final Logger log = LoggerFactory.getLogger(Jpush.class);
	
	/**
	 * 标题
	 */
	public static String TITLE = ""; 
	/**
	 * 提示
	 */
	public static String ALERT = "";
	/**
	 * 消息内容
	 */
	public static String MSG_CONTENT = "";
	//标示
	public static String EXTRA_VALUE = "";
	//构建JPushClient对象
	private static JPushClient jpushClient  = null;
	
	private static String userId = "";
	
	private static Integer TYPE = 0;
	
	private static boolean isALL =false; 
	
	/**
	 * 发送推送消息，推送给指定用户（单推）
	 * @version 1.0
	 * @createTime 2016-6-27,下午4:04:35
	 * @updateTime 2016-6-27,下午4:04:35
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param masterSecret 极光官网上注册应用时生成的 masterSecret
	 * @param appKey 极光官网上注册应用时生成的 appKey
	 * @return 
	 */
	public static void sendPush(String userIds,String title, String content, Integer type, String extra_value) {
		TITLE = title;
		ALERT = content;
		EXTRA_VALUE = extra_value;
		userId = userIds;
		TYPE = type;
		//创建JpushClient对象
		jpushClient = new JPushClient(Const.MASTER_SECRET, Const.APP_KEY);
		isALL = Const.IS_ALL.trim().equals("0")?false:true;
		log.info("jpushClient:"+jpushClient);
		//推送对象为所有平台，别名为alias的用户
		PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras();
		log.info("payload:"+payload);
		try {
			log.info("开始推送消息......"+userIds);
			//开始推送消息
			PushResult result = jpushClient.sendPush(payload);
			log.info("推送成功，返回结果："+result);
		} catch(APIConnectionException ex) {
			log.error("连接错误，稍后重试，错误信息：", ex);
		} catch(APIRequestException ex) {
			log.error("请求服务器错误，错误信息：", ex);
			log.info("HTTP Status: " + ex.getStatus());
			log.info("Error Code: " + ex.getErrorCode());
			log.info("Error Message: " + ex.getErrorMessage());
			log.info("Msg ID: " + ex.getMsgId());
		}
	}
	
	/**
	 * 推送给所有的用户（全推）
	 * @param title
	 * @param content
	 * @param extra_value
	 */
	public static void sendPush(String title, String content, Integer type, String extra_value) {
		TITLE = title;
		ALERT = content;
		EXTRA_VALUE = extra_value;
		TYPE = type;
		//创建JpushClient对象
		jpushClient = new JPushClient(Const.MASTER_SECRET, Const.APP_KEY);
		isALL = Const.IS_ALL.trim().equals("0")?false:true;
		log.info("jpushClient:"+jpushClient);
		//推送对象为所有平台，所有的用户
		PushPayload payload = buildPushObject_all_audienceMore_messageToAll();
		log.info("payload:"+payload);
		try {
			log.info("开始推送消息......");
			//开始推送消息
			PushResult result = jpushClient.sendPush(payload);
			log.info("推送成功，返回结果："+result);
		} catch(APIConnectionException ex) {
			log.error("连接错误，稍后重试，错误信息：", ex);
		} catch(APIRequestException ex) {
			log.error("请求服务器错误，错误信息：", ex);
			log.info("HTTP Status: " + ex.getStatus());
			log.info("Error Code: " + ex.getErrorCode());
			log.info("Error Message: " + ex.getErrorMessage());
			log.info("Msg ID: " + ex.getMsgId());
		}
	}
	
	/**
	 * 推送对象：所有平台，所有设备，内容为 ALERT的通知
	 * @version 1.0
	 * @createTime 2016-6-27,下午4:33:52
	 * @updateTime 2016-6-27,下午4:33:52
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll(ALERT);
    }
	
	/**
	 * 推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT
	 * @version 1.0
	 * @createTime 2016-6-27,下午6:10:56
	 * @updateTime 2016-6-27,下午6:10:56
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	public static PushPayload buildPushObject_all_alias_alert() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("extras", EXTRA_VALUE);
        return PushPayload.newBuilder()
            .setPlatform(Platform.all())
            .setAudience(Audience.alias(userId))
            .setNotification(Notification.alert(ALERT))
            .build();
		/*return PushPayload.newBuilder()  
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(userId))
                .setNotification(Notification.newBuilder()
                        .setAlert(ALERT)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(TITLE).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extras", EXTRA_VALUE).build())
                        .build())
                .build();*/
    }
	
	/**
	 * 推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE
	 * @version 1.0
	 * @createTime 2016-6-27,下午6:11:54
	 * @updateTime 2016-6-27,下午6:11:54
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("extras", EXTRA_VALUE);
        return PushPayload.newBuilder()
            .setPlatform(Platform.android())
//            .setAudience(Audience.tag("tag1"))
            //.setAudience(Audience.all())
            .setAudience(Audience.alias("10048test"))
            .setNotification(Notification.android(ALERT, TITLE, map))
            .build();
	}
	
	/**
	 * 推送对象：平台是 iOS，推送目标是 "tag1", "tag_all" 的交集，推送内容同时包括通知与消息 - 通知信息是 ALERT，角标数字为 5，通知声音为 "happy"，并且附加字段 from = "JPush"
	 * 消息内容是 MSG_CONTENT。通知是 APNs 推送通道的，消息是 JPush 应用内消息通道的。APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
	 * @version 1.0
	 * @createTime 2016-6-27,下午6:12:52
	 * @updateTime 2016-6-27,下午6:12:52
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
            .setPlatform(Platform.ios())
            //.setAudience(Audience.tag("tag1"))
            .setAudience(Audience.alias("6028test"))
            .setNotification(Notification.newBuilder()
                    .addPlatformNotification(IosNotification.newBuilder()
                            .setAlert(ALERT)
                            .setBadge(5)
                            .setSound("happy.caf")
                            .addExtra("from", "JPush")
                            .build()).build())
             .setMessage(Message.content(MSG_CONTENT))
             .setOptions(Options.newBuilder()
                     .setApnsProduction(isALL)
                     .build()).build();
    }
	
	/**
	 * 推送对象：平台是所有平台，推送目标是指定用户，使用别名推送
	 * 推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush
	 * @version 1.0
	 * @createTime 2016-6-27,下午6:13:48
	 * @updateTime 2016-6-27,下午6:13:48
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
		return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.alias(userId))
                        .build())
                .setNotification(Notification.newBuilder()
                        .setAlert(ALERT)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                        		.addExtra("id", EXTRA_VALUE)
                        		.addExtra("type", TYPE)
                        		.addExtra("content", ALERT)
                                .setTitle(TITLE).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("id", EXTRA_VALUE)
                                .addExtra("type", TYPE)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                     .setApnsProduction(isALL)
                     .build())
                .build();
    }
	
	/**
	 * 推送对象：平台是所有平台，推送目标是指定用户，使用别名推送
	 * 推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush
	 * @version 1.0
	 * @createTime 2016-6-27,下午6:13:48
	 * @updateTime 2016-6-27,下午6:13:48
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	public static PushPayload buildPushObject_all_audienceMore_messageToAll() {
		return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert(ALERT)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                        		.addExtra("id", EXTRA_VALUE)
                        		.addExtra("type", TYPE)
                                .setTitle(TITLE).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("id", EXTRA_VALUE)
                                .addExtra("type", TYPE)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                		.setApnsProduction(isALL)
                		.build())
                .build();
    }

	public static void main(String[] args) {
		sendPush("11846test", "alibaba", "chifanla", 14, "nihaoa");
	}
}
