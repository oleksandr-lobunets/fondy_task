package eu.fondy.task.utils;

import eu.fondy.task.dto.ChangeResponseDto;
import eu.fondy.task.entity.ChangeResult;

public class ObjectConverter {

    private ObjectConverter(){}


    public static ChangeResponseDto toDto(ChangeResult changeResult) {

        return ChangeResponseDto.builder()
                .externalID(changeResult.getExternalID())
                .penceSubmitted(changeResult.getPenceSubmitted())
                .pounds(changeResult.getPoundsBreakdown())
                .pence(changeResult.getPenceBreakdown())
                .dateTime(changeResult.getCreatedAt())
                .build();
    }

}
