package com.kristijanbalic.edumanage.controller.api;

import com.kristijanbalic.edumanage.dto.UpisRequestDto;
import com.kristijanbalic.edumanage.dto.UpisResponseDto;
import com.kristijanbalic.edumanage.entity.Upis;
import com.kristijanbalic.edumanage.mapper.UpisMapper;
import com.kristijanbalic.edumanage.service.UpisService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class UpisRestController {

    private final UpisService upisService;
    private final UpisMapper upisMapper;

    public UpisRestController(UpisService upisService,
                              UpisMapper upisMapper) {
        this.upisService = upisService;
        this.upisMapper = upisMapper;
    }

    @GetMapping
    public ResponseEntity<List<UpisResponseDto>> getAll() {

        List<UpisResponseDto> upisi =
                upisService.getAllUpisi()
                        .stream()
                        .map(upisMapper::toResponseDto)
                        .toList();

        return ResponseEntity.ok(upisi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpisResponseDto> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                upisMapper.toResponseDto(
                        upisService.getUpisById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<UpisResponseDto> create(
            @Valid @RequestBody UpisRequestDto dto) {

        Upis saved =
                upisService.createUpis(
                        dto.getStudentId(),
                        dto.getKolegijId(),
                        dto.getOcjena()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(upisMapper.toResponseDto(saved));
    }

    @PatchMapping("/{id}/grade")
    public ResponseEntity<UpisResponseDto> updateGrade(
            @PathVariable Long id,
            @RequestParam(required = false)
            Integer ocjena) {

        Upis updated =
                upisService.updateOcjena(id, ocjena);

        return ResponseEntity.ok(
                upisMapper.toResponseDto(updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        upisService.deleteUpis(id);

        return ResponseEntity.noContent().build();
    }
}