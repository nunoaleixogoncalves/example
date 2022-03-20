package pt.nunoaleixogoncalves.example.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pt.nunoaleixogoncalves.example.model.News;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CustomSpringEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final News news) {
        log.info("Publishing custom event.");
        CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, news);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
