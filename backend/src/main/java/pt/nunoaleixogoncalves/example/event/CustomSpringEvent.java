package pt.nunoaleixogoncalves.example.event;

import org.springframework.context.ApplicationEvent;
import pt.nunoaleixogoncalves.example.model.News;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
public class CustomSpringEvent extends ApplicationEvent {
    private News news;

    public CustomSpringEvent(Object source, News news) {
        super(source);
        this.news = news;
    }

    public News getNews() {
        return news;
    }
}