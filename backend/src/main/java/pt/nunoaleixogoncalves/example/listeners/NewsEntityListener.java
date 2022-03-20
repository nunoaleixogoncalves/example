package pt.nunoaleixogoncalves.example.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pt.nunoaleixogoncalves.example.event.CustomSpringEventPublisher;
import pt.nunoaleixogoncalves.example.model.News;
import pt.nunoaleixogoncalves.example.service.NotificationService;

import javax.persistence.PostUpdate;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Slf4j
public class NewsEntityListener {

    @Autowired
    private CustomSpringEventPublisher customSpringEventPublisher;
    @Autowired
    private NotificationService notificationService;

    @PostUpdate
    private void broadcastNews(News news) {
        log.info("POST UPDATE: " + news + " - " + news.getAuthor().getUsername());
        if (news.getNumLikes() == 10) {
            log.info("10 LIKES" + news.getLikes() + " - " + news.getAuthor().getUsername());
            // spring events
            this.customSpringEventPublisher.publishCustomEvent(news);

            // websocket
            this.notificationService.sendNotificationNews(news);
        }
    }
}
