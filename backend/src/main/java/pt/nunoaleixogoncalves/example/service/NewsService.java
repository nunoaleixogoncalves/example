package pt.nunoaleixogoncalves.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.nunoaleixogoncalves.example.dto.NewsDto;

import java.util.List;
import java.util.UUID;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
public interface NewsService {
    NewsDto saveNews(NewsDto news);

    void upvoteNews(UUID usernameUuid, UUID newsUuid);

    NewsDto getNewsByUuid(UUID uuid);

    List<NewsDto> getNews();

    Page<NewsDto> getNewsPage(Pageable pageable);

    NewsDto updateNews(NewsDto news);
}