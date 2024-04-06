package eu.fondy.task.controller;


import eu.fondy.task.dto.ChangeRequestDto;
import eu.fondy.task.dto.ChangeResponseDto;
import eu.fondy.task.service.ChangeService;
import eu.fondy.task.utils.ObjectConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/change")
@AllArgsConstructor
public class ChangeController {

    private final ChangeService changeService;

    @GetMapping("/{id}")
    public ResponseEntity<ChangeResponseDto> getChange(@PathVariable UUID id) {
        return changeService.getChange(id)
                .map(c -> ResponseEntity.ok(ObjectConverter.toDto(c)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ChangeResponseDto doChange(@RequestBody ChangeRequestDto dto) {

        return changeService.calculate(dto);
    }


    @DeleteMapping("/{id}")
    public void deleteChange(@PathVariable UUID id) {
        changeService.delete(id);
    }
}
