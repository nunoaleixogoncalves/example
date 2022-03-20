package pt.nunoaleixogoncalves.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Data
@MappedSuperclass
public abstract class EntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID uuid;

    @PrePersist
    private void setUuid() {
        this.setUuid(randomUUID());
    }
}
