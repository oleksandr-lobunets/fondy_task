package eu.fondy.task.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
@Builder
public class ChangeRequestDto {

    private Integer pence;
    private UUID id;
}
