package eu.fondy.task.controller;


import eu.fondy.task.dto.ChangeRequestDto;
import eu.fondy.task.dto.ChangeResponseDto;
import eu.fondy.task.service.ChangeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/change")
@AllArgsConstructor
public class ChangeController {

    private final ChangeService changeService;

    @GetMapping("/{id}")
    public ResponseEntity<ChangeResponseDto> getChange(@PathVariable UUID id) {
        return changeService.getChange(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public List<ChangeResponseDto> getAllChanges() {
        return changeService.getAllChanges();
    }


    @PostMapping
    public ChangeResponseDto executeChange(@Valid @RequestBody ChangeRequestDto dto) {
        return changeService.executeChange(dto);
    }

}
