package com.kristijanbalic.edumanage.controller.api;

import com.kristijanbalic.edumanage.dto.KolegijRequestDto;
import com.kristijanbalic.edumanage.dto.KolegijResponseDto;
import com.kristijanbalic.edumanage.entity.Kolegij;
import com.kristijanbalic.edumanage.mapper.KolegijMapper;
import com.kristijanbalic.edumanage.service.KolegijService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class KolegijRestController {

    private final KolegijService kolegijService;
    private final KolegijMapper kolegijMapper;

    public KolegijRestController(KolegijService kolegijService,
                                 KolegijMapper kolegijMapper) {
        this.kolegijService = kolegijService;
        this.kolegijMapper = kolegijMapper;
    }

    @GetMapping
    public ResponseEntity<List<KolegijResponseDto>> getAll() {

        List<KolegijResponseDto> kolegiji =
                kolegijService.getAllKolegiji()
                        .stream()
                        .map(kolegijMapper::toResponseDto)
                        .toList();

        return ResponseEntity.ok(kolegiji);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KolegijResponseDto> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                kolegijMapper.toResponseDto(
                        kolegijService.getKolegijById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<KolegijResponseDto> create(
            @Valid @RequestBody KolegijRequestDto dto) {

        Kolegij kolegij = kolegijMapper.toEntity(dto);

        List<Long> profesoriIds =
                dto.getProfesoriIds() != null
                        ? dto.getProfesoriIds()
                        : List.of();

        Kolegij saved =
                kolegijService.createKolegij(
                        kolegij,
                        profesoriIds
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(kolegijMapper.toResponseDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KolegijResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody KolegijRequestDto dto) {

        Kolegij kolegij = kolegijMapper.toEntity(dto);

        List<Long> profesoriIds =
                dto.getProfesoriIds() != null
                        ? dto.getProfesoriIds()
                        : List.of();

        Kolegij updated =
                kolegijService.updateKolegij(
                        id,
                        kolegij,
                        profesoriIds
                );

        return ResponseEntity.ok(
                kolegijMapper.toResponseDto(updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        kolegijService.deleteKolegij(id);

        return ResponseEntity.noContent().build();
    }
}