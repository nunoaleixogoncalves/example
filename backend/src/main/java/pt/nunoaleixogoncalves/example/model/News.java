package pt.nunoaleixogoncalves.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.nunoaleixogoncalves.example.listeners.NewsEntityListener;

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
@Table(name = "news", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uuid"}, name = "news_unique_uuid")}
)
@EntityListeners(NewsEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"likes"})
public class News extends EntityBase {

    @NotBlank(message = "Title may not be blank")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Title may not be blank")
    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "author_fk_user"), name = "author_id", nullable = false, updatable = false)
    private User author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "news_users",
            joinColumns = {@JoinColumn(name = "news_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")}
    )
    private Collection<User> likes = new ArrayList<>();

    // @Transient just on model
    @Column(name = "num_likes")
    private Long numLikes = 0L;

    /*public Long getNumLikes() {
        return (long) this.getLikes().size();
    }
    public void setNumLikes() {
        this.numLikes = (long) this.getLikes().size();
    }*/
}
