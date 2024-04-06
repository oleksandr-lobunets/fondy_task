package eu.fondy.task.service.impl;


import eu.fondy.task.dto.ChangeRequestDto;
import eu.fondy.task.dto.ChangeResponseDto;
import eu.fondy.task.entity.ChangeResult;
import eu.fondy.task.exception.InsufficientChangeException;
import eu.fondy.task.repository.ChangeResultRepository;
import eu.fondy.task.service.ChangeService;
import eu.fondy.task.utils.ObjectConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ChangeServiceImpl implements ChangeService {

    private final ChangeResultRepository repository;


    @Override
    public Optional<ChangeResponseDto> getChange(final UUID id) {
        return repository.findById(id)
                .map(ObjectConverter::toDto);
    }

    @Override
    public List<ChangeResponseDto> getAllChanges() {
        return repository.findAll()
                .stream().map(ObjectConverter::toDto)
                .toList();
    }

    @Override
    public ChangeResponseDto executeChange(final ChangeRequestDto requestDto) {

        if (isChangeExisting(requestDto.getId()))
            throw new InsufficientChangeException("Change for id='%s' already calculated"
                    .formatted(requestDto.getId().toString()));

        Map<DenominationType, Map<Integer, Integer>> calculatedChange = calculateChange(requestDto.getPence());

        ChangeResult changeResult = new ChangeResult();
        changeResult.setExternalID(requestDto.getId());
        changeResult.setPenceSubmitted(requestDto.getPence());
        changeResult.setPenceBreakdown(calculatedChange.get(DenominationType.PENCE));
        changeResult.setPoundsBreakdown(calculatedChange.get(DenominationType.POUND));

        ChangeResult stored = repository.save(changeResult);
        return ObjectConverter.toDto(stored);
    }

    private boolean isChangeExisting(UUID id) {
        Optional<ChangeResult> existing = repository.findById(id);
        return existing.isPresent();
    }


    private Map<DenominationType, Map<Integer, Integer>> calculateChange(int initialValue) {
        int[] denominationPounds = {50, 20, 10, 5, 2, 1};
        int[] denominationPence = {50, 20, 10, 5, 2, 1};

        int remaining = initialValue;
        Map<Integer, Integer> poundsResult = new HashMap<>();
        Map<Integer, Integer> penceResult = new HashMap<>();

        for (int i : denominationPounds) {
            int value = remaining / (i * 100);
            poundsResult.put(i, value);
            remaining -= value * i * 100;
        }

        for (int i : denominationPence) {
            int value = remaining / i;
            penceResult.put(i, value);
            remaining -= value * i;
        }

        if (remaining > 0) {
            log.error("Change calculation failed for value {}. Remaining: {} pence.", initialValue, remaining);
            throw new InsufficientChangeException("Change calculation failed for value {}. Remaining more than 0 pence.");
        }

        return Map.of(DenominationType.POUND, poundsResult,
                DenominationType.PENCE, penceResult);
    }


    private enum DenominationType {
        POUND,
        PENCE

    }

}
