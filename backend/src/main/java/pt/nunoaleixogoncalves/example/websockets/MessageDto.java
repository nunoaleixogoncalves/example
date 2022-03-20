package pt.nunoaleixogoncalves.example.websockets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.nunoaleixogoncalves.example.dto.NewsDto;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private String text;
    private NewsDto news;
}