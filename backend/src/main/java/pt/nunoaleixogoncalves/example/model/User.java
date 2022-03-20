package pt.nunoaleixogoncalves.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uuid"}, name = "users_unique_uuid"),
        @UniqueConstraint(columnNames = {"username"}, name = "users_unique_username")}
)
public class User extends EntityBase {

    @NotBlank
    @Column(nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id")}
    )
    private Collection<Role> roles = new ArrayList<>();
}
