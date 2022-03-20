package pt.nunoaleixogoncalves.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.nunoaleixogoncalves.example.model.Role;
import pt.nunoaleixogoncalves.example.model.User;
import pt.nunoaleixogoncalves.example.repository.RoleRepository;
import pt.nunoaleixogoncalves.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> {
            log.error("user {} not found", username);
            throw new UsernameNotFoundException("User not found!");
        });
        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
        user.getRoles().forEach(role -> {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }

    public User saveUser(User user) {
        log.info("Saving new user {}", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public Role saveRole(Role role) {
        log.info("Saving new role {}", role.getName());
        return this.roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> {
            log.error("user {} not found", username);
            throw new UsernameNotFoundException("User not found!");
        });
        Role role = this.roleRepository.findByName(roleName);
        log.info("Add role {} to user {}", role.getName(), user.getUsername());
        user.getRoles().add(role);
    }

    public void addRoleToUserByUuid(UUID uuidUser, UUID uuidRole) {
        User user = this.userRepository.findByUuid(uuidUser).orElseThrow(() -> {
            log.error("user {} not found", uuidUser);
            throw new UsernameNotFoundException("User not found!");
        });
        Role role = this.roleRepository.findByUuid(uuidRole).orElseThrow(() -> {
            log.error("role {} not found", uuidRole);
            throw new RuntimeException("Role not found!");
        });
        log.info("Add role {} to user {}", role.getName(), user.getUsername());
        user.getRoles().add(role);
    }

    public User getUser(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> {
            log.error("user {} not found", username);
            throw new UsernameNotFoundException("User not found!");
        });
    }

    public Role getRole(String roleName) {
        return this.roleRepository.findByName(roleName);
    }

    public User getUserByUuid(UUID uuid) {
        return this.userRepository.findByUuid(uuid).orElseThrow(() -> {
            log.error("user {} not found", uuid);
            throw new UsernameNotFoundException("User not found!");
        });
    }

    public Role getRoleByUuid(UUID uuid) {
        return this.roleRepository.findByUuid(uuid).orElseThrow(() -> {
            log.error("user {} not found", uuid);
            throw new RuntimeException("Role not found!");
        });
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User userLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return this.userRepository.findByUsername(currentPrincipalName).orElseThrow(() -> {
            log.error("user {} not found", currentPrincipalName);
            throw new UsernameNotFoundException("user not found!");
        });
    }
}