package com.jumper.hospital.websocket;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;

import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.vo.Principal;

@Component
public class SystemWebSocketHandler implements WebSocketHandler {

	private static final Logger logger;
	private static final ArrayList<WebSocketSession> users;
	
	static {
		logger = Logger.getLogger(SystemWebSocketHandler.class);
        users = new ArrayList<WebSocketSession>();
    }
	
	/** 连接断开 **/
	public void afterConnectionClosed(WebSocketSession socketSession, CloseStatus closeStatus) throws Exception {
		if(users.contains(socketSession)){
			users.remove(socketSession);
			logger.info("websocket connection closed......");
		}
	}

	/** webSocket连接成功 **/
	public void afterConnectionEstablished(final WebSocketSession socketSession)throws Exception {
		users.add(socketSession);
		Principal principal = (Principal) socketSession.getAttributes().get(Consts.WEBSOCKET_USERNAME);
		if(null != principal){
			/** 这里可以处理未读取消息 **/
			System.out.println("用户:"+principal.getUsername()+",连接成功！,医院Id"+principal.getHospitalId()+",用户Id:"+principal.getId());
		}
	}

	public void handleMessage(WebSocketSession socketSession, WebSocketMessage<?> message) throws Exception {
		Principal principal = (Principal) socketSession.getAttributes().get(Consts.WEBSOCKET_USERNAME);
		if(null != principal){
			System.out.println("用户："+principal.getUsername()+" 登陆成功！,医院Id:"+principal.getHospitalId()+",用户Id:"+principal.getId());
			
		}
	}
	
	/** 处理发送失败 **/
	public void handleTransportError(WebSocketSession socketSession, Throwable exception) throws Exception {
		if(users.contains(socketSession)){
			users.remove(socketSession);
			logger.info("websocket connection closed......the exception message is : "+exception.getMessage());
		}
	}
	
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
     * 给所有在线用户发送消息
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * 给某个用户发送消息
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String message) {
    	JSONObject json = JSONObject.parseObject(message);
    	Integer doctorId = json.getJSONObject("data").getInteger("doctorId");
        for (WebSocketSession user : users) {
            try {
            	Principal p =  (Principal) user.getAttributes().get(Consts.WEBSOCKET_USERNAME);
            	if(p.getHospitalId().intValue() == doctorId.intValue()){
            		if (user.isOpen()) {
            			user.sendMessage(new TextMessage(message));
            		}
            	}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
