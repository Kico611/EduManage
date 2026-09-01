package com.kristijanbalic.edumanage.controller.api;

import com.kristijanbalic.edumanage.dto.StudentRequestDto;
import com.kristijanbalic.edumanage.dto.StudentResponseDto;
import com.kristijanbalic.edumanage.entity.Student;
import com.kristijanbalic.edumanage.mapper.StudentMapper;
import com.kristijanbalic.edumanage.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentRestController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentRestController(StudentService studentService,
                                 StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {

        List<StudentResponseDto> students = studentService.getAllStudents()
                .stream()
                .map(studentMapper::toResponseDto)
                .toList();

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(
            @PathVariable Long id) {

        Student student = studentService.getStudentById(id);

        return ResponseEntity.ok(
                studentMapper.toResponseDto(student)
        );
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(
            @Valid @RequestBody StudentRequestDto requestDto) {

        Student student = studentMapper.toEntity(requestDto);

        Student savedStudent = studentService.saveStudent(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentMapper.toResponseDto(savedStudent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDto requestDto) {

        Student student = studentMapper.toEntity(requestDto);

        Student updatedStudent =
                studentService.updateStudent(id, student);

        return ResponseEntity.ok(
                studentMapper.toResponseDto(updatedStudent)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
}