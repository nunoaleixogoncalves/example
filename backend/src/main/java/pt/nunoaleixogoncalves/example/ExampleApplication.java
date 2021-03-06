package pt.nunoaleixogoncalves.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import pt.nunoaleixogoncalves.example.model.Role;
import pt.nunoaleixogoncalves.example.model.User;
import pt.nunoaleixogoncalves.example.service.UserService;

import java.util.ArrayList;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // this is easier for demo but migrations probably better
    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role("ROLE_USER"));
            userService.saveRole(new Role("ROLE_ADMIN"));

            userService.saveUser(new User("admin", "Administrator", "admin", new ArrayList<>()));
            userService.saveUser(new User("nuno", "Nuno", "12345", new ArrayList<>()));
            userService.saveUser(new User("teste", "teste", "12345", new ArrayList<>()));
            userService.saveUser(new User("teste2", "teste2", "12345", new ArrayList<>()));
            userService.saveUser(new User("teste3", "teste3", "12345", new ArrayList<>()));
            userService.saveUser(new User("teste4", "teste4", "12345", new ArrayList<>()));
            userService.saveUser(new User("teste5", "teste5", "12345", new ArrayList<>()));
            userService.saveUser(new User("teste6", "teste6", "12345", new ArrayList<>()));
            userService.saveUser(new User("teste7", "teste7", "12345", new ArrayList<>()));
            userService.saveUser(new User("teste8", "teste8", "12345", new ArrayList<>()));

            userService.addRoleToUser("admin", "ROLE_USER");
            userService.addRoleToUser("admin", "ROLE_ADMIN");

            userService.addRoleToUser("nuno", "ROLE_USER");
            userService.addRoleToUser("teste", "ROLE_USER");
            userService.addRoleToUser("teste2", "ROLE_USER");
            userService.addRoleToUser("teste3", "ROLE_USER");
            userService.addRoleToUser("teste4", "ROLE_USER");
            userService.addRoleToUser("teste5", "ROLE_USER");
            userService.addRoleToUser("teste6", "ROLE_USER");
            userService.addRoleToUser("teste7", "ROLE_USER");
            userService.addRoleToUser("teste8", "ROLE_USER");
        };
    }
}
