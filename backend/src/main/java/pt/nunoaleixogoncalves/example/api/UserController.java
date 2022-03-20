package pt.nunoaleixogoncalves.example.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pt.nunoaleixogoncalves.example.dto.RoleUserForm;
import pt.nunoaleixogoncalves.example.model.User;
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
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // TODO composite roles in better in some cases,
    // so you dont have to set all roles to a power user

    // all is present because its easy to check data and this is just an example
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("all")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("{uuid}")
    public ResponseEntity<User> getUser(@PathVariable UUID uuidUser) {
        return ResponseEntity.ok().body(userService.getUserByUuid(uuidUser));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return ResponseEntity.created(
                URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("user").toUriString())).body(this.userService.saveUser(user));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok().body(this.userService.saveUser(user));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("add/role")
    public ResponseEntity<?> addRole(@RequestBody @Valid RoleUserForm userRole) {
        this.userService.addRoleToUserByUuid(userRole.getUserUuid(), userRole.getRoleUuid());
        return ResponseEntity.ok().build();
    }

}
