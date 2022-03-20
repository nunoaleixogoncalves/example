package pt.nunoaleixogoncalves.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.nunoaleixogoncalves.example.model.News;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findByUuid(UUID uuid);
}
