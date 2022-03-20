package pt.nunoaleixogoncalves.example.websockets;

import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Service
@Lazy
public class WebsocketsEvents implements IWebSocketsEvents {

    @Override
    public void connect(StompHeaderAccessor accessor, String token) {
    }

    @Override
    public void disconnect(StompHeaderAccessor acessor) {
    }

    @Override
    public void subscribe(StompHeaderAccessor accessor) {
    }

    @Override
    public void unsubscribe(StompHeaderAccessor accessor) {
    }
}
