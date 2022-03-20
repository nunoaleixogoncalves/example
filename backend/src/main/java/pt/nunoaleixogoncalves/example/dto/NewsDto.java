package pt.nunoaleixogoncalves.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {

    UUID uuid;
    @NotBlank
    String title;
    @NotBlank
    String message;
    @NotNull
    UUID authorUuid;
    Long numLikes;

    // TODO probably needs a type of news like enum or other table to relate the type of news
}
