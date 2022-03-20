package pt.nunoaleixogoncalves.example.mappers;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pt.nunoaleixogoncalves.example.exception.ExampleException;
import pt.nunoaleixogoncalves.example.model.User;
import pt.nunoaleixogoncalves.example.repository.UserRepository;

import java.util.UUID;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Component
@RequiredArgsConstructor
public class NewsMapperUtil {

    private final UserRepository userRepository;

    @Named("getAuthor")
    public User getAuthor(UUID uuid) {
        return this.userRepository.findByUuid(uuid).orElseThrow(() -> {
            throw new ExampleException(HttpStatus.NOT_FOUND, "User not found");
        });
    }
    
    @Named("getAuthorUuid")
    public UUID getAuthorUuid(User user) {
        return user.getUuid();
    }
}
