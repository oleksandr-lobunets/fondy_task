package eu.fondy.task.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
@Builder
public class ChangeRequestDto {

    @NotNull (message = "Invalid pence value: pence should not be empty")
    @Min(value = 1, message = "Invalid pence value: value should be equals or more than 1")
    private Integer pence;
    @NotNull (message = "id is not specified")
    private UUID id;
}
