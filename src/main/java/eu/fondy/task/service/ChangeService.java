package eu.fondy.task.service;

import eu.fondy.task.dto.ChangeRequestDto;
import eu.fondy.task.dto.ChangeResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChangeService {

    ChangeResponseDto executeChange(ChangeRequestDto requestDto);

    Optional<ChangeResponseDto> getChange(UUID id);

    List<ChangeResponseDto> getAllChanges();
}
