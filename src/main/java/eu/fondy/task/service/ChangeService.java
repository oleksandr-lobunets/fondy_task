package eu.fondy.task.service;

import eu.fondy.task.dto.ChangeRequestDto;
import eu.fondy.task.dto.ChangeResponseDto;
import eu.fondy.task.entity.ChangeResult;

import java.util.Optional;
import java.util.UUID;

public interface ChangeService {

    ChangeResponseDto calculate(ChangeRequestDto requestDto);

    Optional<ChangeResult> getChange(UUID id);

    void delete(UUID id);
}
