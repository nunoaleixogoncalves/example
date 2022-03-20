package pt.nunoaleixogoncalves.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uuid"}, name = "roles_unique_uuid"),
        @UniqueConstraint(columnNames = {"name"}, name = "roles_unique_name")}
)
public class Role extends EntityBase {

    @NotBlank
    @Column(nullable = false)
    private String name;
}
