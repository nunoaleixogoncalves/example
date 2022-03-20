package pt.nunoaleixogoncalves.example.websockets;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Configuration
@Lazy
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final IWebSocketsEvents webSocketsEvents;

    public WebSocketConfig(ApplicationContext ctx) {
        this.webSocketsEvents = ctx.getBean(IWebSocketsEvents.class);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websockets").setAllowedOrigins("*");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @SneakyThrows
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                assert accessor != null;
                if (StompCommand.CONNECT.equals(accessor.getCommand()) && accessor.getNativeHeader(AUTHORIZATION) != null) {
                    final List<String> authorization = accessor.getNativeHeader(AUTHORIZATION);
                    // TODO needs token verification
                    log.info("TOKEN websocket: " + authorization.get(0));
                    final String token = authorization.get(0);
                    webSocketsEvents.connect(accessor, token);
                }
                if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
                    webSocketsEvents.disconnect(accessor);
                }
                if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
                    webSocketsEvents.subscribe(accessor);
                }
                if (StompCommand.UNSUBSCRIBE.equals(accessor.getCommand())) {
                    webSocketsEvents.unsubscribe(accessor);
                }
                return message;
            }
        });
    }
}