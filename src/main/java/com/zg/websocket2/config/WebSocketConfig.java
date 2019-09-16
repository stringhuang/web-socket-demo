package com.zg.websocket2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // "/gs-guide-websocket"表示客户端连接服务端的地址，比如："http://localhost:8081/gs-guide-websocket"
        registry.addEndpoint("/gs-guide-websocket")
                .setAllowedOrigins("*")
                .withSockJS()
                .setHeartbeatTime(30000);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // "/topic"表示广播地址前缀，"/user"表示p2p地址前缀，两个需要客户端手动订阅
        registry.enableSimpleBroker("/topic", "/user");
        // "/app"表示客户端向服务端发送消息时的接口地址前缀
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }
}
