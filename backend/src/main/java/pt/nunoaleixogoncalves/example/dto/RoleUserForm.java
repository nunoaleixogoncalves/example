package pt.nunoaleixogoncalves.example.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Data
public class RoleUserForm {
    @NotNull
    private UUID userUuid;
    @NotNull
    private UUID roleUuid;
}
