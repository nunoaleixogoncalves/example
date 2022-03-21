package pt.nunoaleixogoncalves.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.nunoaleixogoncalves.example.dto.NewsDto;
import pt.nunoaleixogoncalves.example.exception.ExampleException;
import pt.nunoaleixogoncalves.example.mappers.NewsMapper;
import pt.nunoaleixogoncalves.example.model.News;
import pt.nunoaleixogoncalves.example.model.User;
import pt.nunoaleixogoncalves.example.repository.NewsRepository;
import pt.nunoaleixogoncalves.example.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class NewsService {

    private final UserService userService;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final NewsMapper newsMapper;

    public NewsDto saveNews(NewsDto news) {
        // TOTO verify title or something unique for the post (type, title or something like that)
        // check if the uuiduser on dto is from the user logged
        if (!news.getAuthorUuid().equals(this.userService.userLogged().getUuid())) {
            throw new ExampleException(HttpStatus.BAD_REQUEST, "users conflit");
        }
        return this.newsMapper.toDto(this.newsRepository.save(this.newsMapper.toEntity(news)));
    }

    public void upvoteNews(UUID usernameUuid, UUID newsUuid) {
        News news = this.newsRepository.findByUuid(newsUuid).orElseThrow(() -> {
            log.error("news {} not found", newsUuid);
            throw new ExampleException(HttpStatus.NOT_FOUND, "News not found!");
        });
        User user = this.userRepository.findByUuid(usernameUuid).orElseThrow(() -> {
            log.error("user {} not found", usernameUuid);
            throw new ExampleException(HttpStatus.NOT_FOUND, "User not found!");
        });

        // comment if you wanna check upvotes faster
        if (news.getLikes().contains(user)) {
            log.error("user {} already upvote this post", usernameUuid);
            throw new ExampleException(HttpStatus.CONFLICT, "User already upvoted this!");
        }
        news.getLikes().add(user);
        news.setNumLikes((long) news.getLikes().size());

        // if you want to broadcast on service not on @PostUpdate
        /*try {
            log.info("POST UPDATE: " + news + " - " + news.getAuthor().getUsername());
            if (news.getLikes().size() == 10) {
                log.info("10 LIKES" + news.getLikes() + " - " + news.getAuthor().getUsername());
                // spring events
                this.customSpringEventPublisher.publishCustomEvent(news);

                // websocket
                this.notificationService.sendNotificationNews(news);
            }
        } catch (Exception e) {
            log.error("error on send notifications: ", e.getMessage());
        }*/
    }

    public NewsDto getNewsByUuid(UUID uuid) {
        return this.newsMapper.toDto(this.newsRepository.findByUuid(uuid).orElseThrow(() -> {
            log.error("news {} not found", uuid);
            throw new ExampleException(HttpStatus.NOT_FOUND, "News not found!");
        }));
    }

    public List<NewsDto> getNews() {
        return this.newsRepository.findAll().stream()
                .map(this.newsMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<NewsDto> getNewsPage(Pageable pageable) {
        Page<News> clientes = this.newsRepository.findAll(pageable);
        return clientes.map(this.newsMapper::toDto);
    }

    public NewsDto updateNews(NewsDto newsDto) {
        News news = this.newsRepository.findByUuid(newsDto.getUuid()).orElseThrow(() -> {
            log.error("news {} not found", newsDto.getUuid());
            throw new ExampleException(HttpStatus.NOT_FOUND, "News not found!");
        });

        if (!news.getAuthor().getUuid().equals(this.userService.userLogged().getUuid())) {
            log.error("error permission denied", news.getUuid());
            throw new ExampleException(HttpStatus.FORBIDDEN, "No permissions to edit News");
        }
        this.newsMapper.updateToEntityFromDto(newsDto, news);

        return this.newsMapper.toDto(news);
    }
}