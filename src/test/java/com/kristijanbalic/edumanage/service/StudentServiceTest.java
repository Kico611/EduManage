package com.kristijanbalic.edumanage.service;

import com.kristijanbalic.edumanage.entity.Student;
import com.kristijanbalic.edumanage.exception.StudentNotFoundException;
import com.kristijanbalic.edumanage.repository.StudentRepository;
import com.kristijanbalic.edumanage.repository.UpisRepository;
import com.kristijanbalic.edumanage.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private UpisRepository upisRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {

        student = new Student();

        student.setId(1L);
        student.setIme("Marko Marić");
        student.setEmail("marko@example.com");
        student.setAdresa("Mostar");
        student.setBrojIndeksa("IB001");
        student.setGodinaUpisa(2025);
    }

    @Test
    void shouldReturnAllStudents() {

        when(studentRepository.findAll())
                .thenReturn(List.of(student));

        List<Student> result =
                studentService.getAllStudents();

        assertEquals(1, result.size());

        assertEquals(
                "Marko Marić",
                result.get(0).getIme()
        );

        verify(studentRepository).findAll();
    }

    @Test
    void shouldReturnStudentById() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        Student result =
                studentService.getStudentById(1L);

        assertNotNull(result);

        assertEquals(
                1L,
                result.getId()
        );

        assertEquals(
                "IB001",
                result.getBrojIndeksa()
        );

        verify(studentRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenStudentDoesNotExist() {

        when(studentRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                StudentNotFoundException.class,
                () -> studentService.getStudentById(99L)
        );

        verify(studentRepository).findById(99L);
    }

    @Test
    void shouldUpdateStudent() {

        Student updatedStudent = new Student();

        updatedStudent.setIme("Ivan Ivić");
        updatedStudent.setEmail("ivan@example.com");
        updatedStudent.setAdresa("Sarajevo");
        updatedStudent.setBrojIndeksa("IB002");
        updatedStudent.setGodinaUpisa(2026);

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(studentRepository.save(any(Student.class)))
                .thenAnswer(invocation ->
                        invocation.getArgument(0)
                );

        Student result =
                studentService.updateStudent(
                        1L,
                        updatedStudent
                );

        assertEquals(
                "Ivan Ivić",
                result.getIme()
        );

        assertEquals(
                "ivan@example.com",
                result.getEmail()
        );

        assertEquals(
                "IB002",
                result.getBrojIndeksa()
        );

        assertEquals(
                2026,
                result.getGodinaUpisa()
        );

        verify(studentRepository).save(student);
    }

    @Test
    void shouldDeleteStudent() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        studentService.deleteStudent(1L);

        verify(upisRepository)
                .deleteByStudentId(1L);

        verify(studentRepository)
                .deleteById(1L);
    }
}