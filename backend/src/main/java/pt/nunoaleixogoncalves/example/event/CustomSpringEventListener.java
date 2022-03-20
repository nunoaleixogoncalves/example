package pt.nunoaleixogoncalves.example.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Component
@Slf4j
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {
    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        log.info("Received spring custom event - title: " + event.getNews().getTitle() + " - message: " + event.getNews().getMessage());
    }
}
