package com.jumper.hospital.websocket;
/**
 * Spring4.0 - WebSocket with SockJS
 * @author rent
 * @date 2015-11-26
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	/** 注册需要监听的WebSocket **/
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	/** 提供符合W3C标准的Websocket数据  **/
        registry.addHandler(systemWebSocketHandler(), "/chat").addInterceptors(new WebSocketHandshakeInterceptor());
    }

    @Bean
    public WebSocketHandler systemWebSocketHandler(){
        return new SystemWebSocketHandler();
    }
    
}
