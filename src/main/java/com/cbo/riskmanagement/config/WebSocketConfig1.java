//package com.cbo.riskmanagement.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.converter.DefaultContentTypeResolver;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/topic"); // Enables a simple in-memory message broker
//        config.setApplicationDestinationPrefixes("/app");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS(); // WebSocket endpoint
//    }
//
//    @Override
//    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
//        registry.setMessageSizeLimit(8192); // Adjust the buffer size as per your needs
//        registry.setSendBufferSizeLimit(8192); // Adjust the buffer size as per your needs
//        registry.setSendTimeLimit(10000); // Adjust the time limit as per your needs
//    }
//}