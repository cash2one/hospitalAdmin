package com.jumper.hospital.websocket;
/**
 * spring4-websocket-interceptor 拦截请求，获取参数信息
 * @author rent
 * @date 2015-12-15
 */
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.vo.Principal;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Exception exception) {

	}

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
            Subject currentUser = SecurityUtils.getSubject();
            Principal principal = (Principal) currentUser.getPrincipal();
            if(null != principal){
            	attributes.put(Consts.WEBSOCKET_USERNAME, principal);
            }
            if(request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {
                request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");
            }
        }
        return true;
	}

}
