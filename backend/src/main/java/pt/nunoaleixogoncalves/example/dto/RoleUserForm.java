package pt.nunoaleixogoncalves.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserForm {
    @NotNull
    private UUID userUuid;
    @NotNull
    private UUID roleUuid;
}
