package pt.nunoaleixogoncalves.example.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pt.nunoaleixogoncalves.example.dto.NewsDto;
import pt.nunoaleixogoncalves.example.service.NewsService;
import pt.nunoaleixogoncalves.example.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@RestController
@RequestMapping("news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;
    private final UserService userService;

    @Secured("ROLE_USER")
    @GetMapping
    public ResponseEntity<Page<NewsDto>> getNewsPage(
            @PageableDefault(sort = {"numLikes"}, direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        return ResponseEntity.ok(this.newsService.getNewsPage(pageable));
    }

    @Secured("ROLE_USER")
    @GetMapping("all")
    public ResponseEntity<List<NewsDto>> getNews() {
        return ResponseEntity.ok().body(this.newsService.getNews());
    }

    @Secured("ROLE_USER")
    @GetMapping("{uuid}")
    public ResponseEntity<NewsDto> getNews(@PathVariable UUID uuid) {
        return ResponseEntity.ok().body(this.newsService.getNewsByUuid(uuid));
    }

    @Secured("ROLE_USER")
    @PostMapping
    public ResponseEntity<NewsDto> createNews(@RequestBody @Valid NewsDto news) {
        return ResponseEntity.created(
                URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("news").toUriString())).body(this.newsService.saveNews(news));
    }

    @Secured("ROLE_USER")
    @PutMapping
    public ResponseEntity<NewsDto> updateNews(@RequestBody @Valid NewsDto news) {
        return ResponseEntity.ok().body(this.newsService.updateNews(news));
    }

    @Secured("ROLE_USER")
    @PostMapping("{uuid}/upvote")
    public ResponseEntity<?> upvote(@PathVariable UUID uuid) {
        this.newsService.upvoteNews(this.userService.userLogged().getUuid(), uuid);
        return ResponseEntity.ok().build();
    }

}
