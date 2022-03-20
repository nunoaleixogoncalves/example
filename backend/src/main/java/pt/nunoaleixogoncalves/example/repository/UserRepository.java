package pt.nunoaleixogoncalves.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.nunoaleixogoncalves.example.model.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUuid(UUID uuid);
}
