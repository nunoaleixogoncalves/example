package pt.nunoaleixogoncalves.example.service;

import pt.nunoaleixogoncalves.example.model.Role;
import pt.nunoaleixogoncalves.example.model.User;

import java.util.List;
import java.util.UUID;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    void addRoleToUserByUuid(UUID uuidUser, UUID uuidRole);

    User getUser(String username);

    Role getRole(String roleName);

    User getUserByUuid(UUID uuid);

    Role getRoleByUuid(UUID uuid);

    List<User> getUsers();

    User userLogged();
}