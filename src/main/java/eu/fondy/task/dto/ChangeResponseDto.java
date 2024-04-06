package eu.fondy.task.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;


@Builder
@Data
public class ChangeResponseDto {

    private int penceSubmitted;
    private UUID externalID;
    private Map<Integer, Integer> pounds;
    private Map<Integer, Integer> pence;
    private LocalDateTime dateTime;

}
