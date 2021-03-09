package com.zorg.zombies;

import static utils.functions.Value.with;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Map;

@SpringBootApplication
public class ZombiesApplication {

    public static final String ENTRY = "/conn";

    public static void main(String[] args) {
        SpringApplication.run(ZombiesApplication.class, args);
    }

    @Bean
    @ConditionalOnMissingBean(WebSocketHandlerAdapter.class)
    public WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    public HandlerMapping handlerMapping(GameWebSocketHandler gameWebSocketHandler) {
        return with(new SimpleUrlHandlerMapping(), handlerMapping -> {
            handlerMapping.setUrlMap(Map.of(ENTRY, gameWebSocketHandler));
            handlerMapping.setOrder(1);
        });
    }

}
