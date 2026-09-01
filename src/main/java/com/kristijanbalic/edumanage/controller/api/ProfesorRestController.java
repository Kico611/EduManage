package com.kristijanbalic.edumanage.controller.api;

import com.kristijanbalic.edumanage.dto.ProfesorRequestDto;
import com.kristijanbalic.edumanage.dto.ProfesorResponseDto;
import com.kristijanbalic.edumanage.entity.Profesor;
import com.kristijanbalic.edumanage.mapper.ProfesorMapper;
import com.kristijanbalic.edumanage.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professors")
public class ProfesorRestController {

    private final ProfesorService profesorService;
    private final ProfesorMapper profesorMapper;

    public ProfesorRestController(ProfesorService profesorService,
                                  ProfesorMapper profesorMapper) {
        this.profesorService = profesorService;
        this.profesorMapper = profesorMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProfesorResponseDto>> getAll() {

        List<ProfesorResponseDto> profesori =
                profesorService.getAllProfesori()
                        .stream()
                        .map(profesorMapper::toResponseDto)
                        .toList();

        return ResponseEntity.ok(profesori);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponseDto> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                profesorMapper.toResponseDto(
                        profesorService.getProfesorById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<ProfesorResponseDto> create(
            @Valid @RequestBody ProfesorRequestDto dto) {

        Profesor profesor = profesorMapper.toEntity(dto);

        Profesor saved =
                profesorService.saveProfesor(profesor, List.of());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(profesorMapper.toResponseDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody ProfesorRequestDto dto) {

        Profesor profesor = profesorMapper.toEntity(dto);

        Profesor updated =
                profesorService.updateProfesor(id, profesor);

        return ResponseEntity.ok(
                profesorMapper.toResponseDto(updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        profesorService.deleteProfesor(id);

        return ResponseEntity.noContent().build();
    }
}