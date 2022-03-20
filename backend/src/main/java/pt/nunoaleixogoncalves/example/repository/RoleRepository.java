package pt.nunoaleixogoncalves.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.nunoaleixogoncalves.example.model.Role;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    Optional<Role> findByUuid(UUID uuid);
}
