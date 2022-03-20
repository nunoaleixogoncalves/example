package pt.nunoaleixogoncalves.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import pt.nunoaleixogoncalves.example.mappers.NewsMapper;
import pt.nunoaleixogoncalves.example.model.News;
import pt.nunoaleixogoncalves.example.websockets.MessageDto;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Service
@Lazy
public class NotificationService {

    public static final String SUBSCRIBE_NOTIFICATION_NEWS_URL = "/topic/news";
    private static final String SUBSCRIBE_NOTIFICATION_NEWS_URL_PAYLOAD = "10_likes";

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NewsMapper newsMapper;

    @Lazy
    @Autowired
    public NotificationService(SimpMessagingTemplate simpMessagingTemplate, NewsMapper newsMapper) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.newsMapper = newsMapper;
    }

    public void sendNotificationNews(News news) {
        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCommit() {
                        MessageDto payload = new MessageDto(SUBSCRIBE_NOTIFICATION_NEWS_URL_PAYLOAD, newsMapper.toDto(news));
                        simpMessagingTemplate.convertAndSend(SUBSCRIBE_NOTIFICATION_NEWS_URL,
                                payload);
                    }
                });
    }
}
