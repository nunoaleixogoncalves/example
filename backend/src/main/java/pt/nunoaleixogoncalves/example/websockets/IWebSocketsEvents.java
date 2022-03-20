package pt.nunoaleixogoncalves.example.websockets;

import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */

public interface IWebSocketsEvents {
    void connect(StompHeaderAccessor accessor, String token);

    void disconnect(StompHeaderAccessor accessor);

    void subscribe(StompHeaderAccessor accessor);

    void unsubscribe(StompHeaderAccessor accessor);
}
