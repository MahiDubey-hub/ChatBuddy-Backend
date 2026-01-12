package com.ai.Chatbot.config;

import com.ai.Chatbot.controller.GeminiWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final GeminiWebSocketHandler aiChatHandler;

    public WebSocketConfig(GeminiWebSocketHandler aiChatHandler){
        this.aiChatHandler = aiChatHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(aiChatHandler, "/chat").setAllowedOrigins("*");
    }
}
