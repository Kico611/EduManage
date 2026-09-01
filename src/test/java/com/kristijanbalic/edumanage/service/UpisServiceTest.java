package com.kristijanbalic.edumanage.service;

import com.kristijanbalic.edumanage.entity.Kolegij;
import com.kristijanbalic.edumanage.entity.Student;
import com.kristijanbalic.edumanage.entity.Upis;
import com.kristijanbalic.edumanage.exception.DuplicateEnrollmentException;
import com.kristijanbalic.edumanage.exception.InvalidGradeException;
import com.kristijanbalic.edumanage.exception.KolegijNotFoundException;
import com.kristijanbalic.edumanage.exception.StudentNotFoundException;
import com.kristijanbalic.edumanage.exception.UpisNotFoundException;
import com.kristijanbalic.edumanage.repository.KolegijRepository;
import com.kristijanbalic.edumanage.repository.StudentRepository;
import com.kristijanbalic.edumanage.repository.UpisRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpisServiceTest {

    @Mock
    private UpisRepository upisRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private KolegijRepository kolegijRepository;

    @InjectMocks
    private UpisService upisService;

    private Student student;
    private Kolegij kolegij;
    private Upis upis;

    @BeforeEach
    void setUp() {

        student = new Student();
        student.setId(1L);
        student.setIme("Marko Marić");
        student.setBrojIndeksa("IB001");

        kolegij = new Kolegij();
        kolegij.setId(1L);
        kolegij.setNaziv("Programming");

        upis = new Upis();
        upis.setId(1L);
        upis.setStudent(student);
        upis.setKolegij(kolegij);
        upis.setOcjena(4);
    }

    @Test
    void shouldReturnAllEnrollments() {

        when(upisRepository.findAll())
                .thenReturn(List.of(upis));

        List<Upis> result =
                upisService.getAllUpisi();

        assertEquals(1, result.size());
        assertEquals(upis, result.get(0));

        verify(upisRepository).findAll();
    }

    @Test
    void shouldReturnAllStudents() {

        when(studentRepository.findAll())
                .thenReturn(List.of(student));

        List<Student> result =
                upisService.getAllStudents();

        assertEquals(1, result.size());
        assertEquals(student, result.get(0));

        verify(studentRepository).findAll();
    }

    @Test
    void shouldReturnAllCourses() {

        when(kolegijRepository.findAll())
                .thenReturn(List.of(kolegij));

        List<Kolegij> result =
                upisService.getAllKolegiji();

        assertEquals(1, result.size());
        assertEquals(kolegij, result.get(0));

        verify(kolegijRepository).findAll();
    }

    @Test
    void shouldReturnEnrollmentById() {

        when(upisRepository.findById(1L))
                .thenReturn(Optional.of(upis));

        Upis result =
                upisService.getUpisById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(student, result.getStudent());
        assertEquals(kolegij, result.getKolegij());

        verify(upisRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenEnrollmentDoesNotExist() {

        when(upisRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                UpisNotFoundException.class,
                () -> upisService.getUpisById(99L)
        );

        verify(upisRepository).findById(99L);
    }

    @Test
    void shouldCreateEnrollmentFromEntity() {

        when(upisRepository.existsByStudentIdAndKolegijId(
                1L,
                1L
        )).thenReturn(false);

        when(upisRepository.save(upis))
                .thenReturn(upis);

        Upis result =
                upisService.createUpis(upis);

        assertNotNull(result);
        assertEquals(student, result.getStudent());
        assertEquals(kolegij, result.getKolegij());

        verify(upisRepository)
                .existsByStudentIdAndKolegijId(
                        1L,
                        1L
                );

        verify(upisRepository).save(upis);
    }

    @Test
    void shouldThrowExceptionForDuplicateEnrollmentFromEntity() {

        when(upisRepository.existsByStudentIdAndKolegijId(
                1L,
                1L
        )).thenReturn(true);

        assertThrows(
                DuplicateEnrollmentException.class,
                () -> upisService.createUpis(upis)
        );

        verify(
                upisRepository,
                never()
        ).save(any(Upis.class));
    }

    @Test
    void shouldUpdateGrade() {

        when(upisRepository.findById(1L))
                .thenReturn(Optional.of(upis));

        when(upisRepository.save(any(Upis.class)))
                .thenAnswer(invocation ->
                        invocation.getArgument(0)
                );

        Upis result =
                upisService.updateOcjena(
                        1L,
                        5
                );

        assertEquals(5, result.getOcjena());

        verify(upisRepository).findById(1L);
        verify(upisRepository).save(upis);
    }

    @Test
    void shouldAllowNullGrade() {

        when(upisRepository.findById(1L))
                .thenReturn(Optional.of(upis));

        when(upisRepository.save(any(Upis.class)))
                .thenAnswer(invocation ->
                        invocation.getArgument(0)
                );

        Upis result =
                upisService.updateOcjena(
                        1L,
                        null
                );

        assertNull(result.getOcjena());

        verify(upisRepository).save(upis);
    }

    @Test
    void shouldThrowExceptionForGradeAboveFive() {

        assertThrows(
                InvalidGradeException.class,
                () -> upisService.updateOcjena(
                        1L,
                        6
                )
        );

        verify(
                upisRepository,
                never()
        ).findById(anyLong());

        verify(
                upisRepository,
                never()
        ).save(any());
    }

    @Test
    void shouldThrowExceptionForGradeBelowOne() {

        assertThrows(
                InvalidGradeException.class,
                () -> upisService.updateOcjena(
                        1L,
                        0
                )
        );

        verify(
                upisRepository,
                never()
        ).save(any());
    }

    @Test
    void shouldDeleteEnrollment() {

        when(upisRepository.findById(1L))
                .thenReturn(Optional.of(upis));

        upisService.deleteUpis(1L);

        verify(upisRepository).findById(1L);
        verify(upisRepository).delete(upis);
    }

    @Test
    void shouldReturnEnrollmentsForStudent() {

        when(upisRepository.findByStudentId(1L))
                .thenReturn(List.of(upis));

        List<Upis> result =
                upisService.getUpisiForStudent(1L);

        assertEquals(1, result.size());
        assertEquals(student, result.get(0).getStudent());

        verify(upisRepository)
                .findByStudentId(1L);
    }

    @Test
    void shouldCreateEnrollmentUsingIds() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(kolegijRepository.findById(1L))
                .thenReturn(Optional.of(kolegij));

        when(upisRepository.existsByStudentIdAndKolegijId(
                1L,
                1L
        )).thenReturn(false);

        when(upisRepository.save(any(Upis.class)))
                .thenAnswer(invocation ->
                        invocation.getArgument(0)
                );

        Upis result =
                upisService.createUpis(
                        1L,
                        1L,
                        5
                );

        assertNotNull(result);
        assertEquals(student, result.getStudent());
        assertEquals(kolegij, result.getKolegij());
        assertEquals(5, result.getOcjena());

        verify(studentRepository).findById(1L);
        verify(kolegijRepository).findById(1L);

        verify(upisRepository)
                .existsByStudentIdAndKolegijId(
                        1L,
                        1L
                );

        verify(upisRepository)
                .save(any(Upis.class));
    }

    @Test
    void shouldThrowExceptionWhenStudentDoesNotExist() {

        when(studentRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                StudentNotFoundException.class,
                () -> upisService.createUpis(
                        99L,
                        1L,
                        5
                )
        );

        verify(studentRepository).findById(99L);

        verify(
                kolegijRepository,
                never()
        ).findById(anyLong());

        verify(
                upisRepository,
                never()
        ).save(any());
    }

    @Test
    void shouldThrowExceptionWhenCourseDoesNotExist() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(kolegijRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                KolegijNotFoundException.class,
                () -> upisService.createUpis(
                        1L,
                        99L,
                        5
                )
        );

        verify(studentRepository).findById(1L);
        verify(kolegijRepository).findById(99L);

        verify(
                upisRepository,
                never()
        ).save(any());
    }

    @Test
    void shouldThrowDuplicateExceptionWhenCreatingUsingIds() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(kolegijRepository.findById(1L))
                .thenReturn(Optional.of(kolegij));

        when(upisRepository.existsByStudentIdAndKolegijId(
                1L,
                1L
        )).thenReturn(true);

        assertThrows(
                DuplicateEnrollmentException.class,
                () -> upisService.createUpis(
                        1L,
                        1L,
                        5
                )
        );

        verify(
                upisRepository,
                never()
        ).save(any());
    }

    @Test
    void shouldThrowInvalidGradeWhenCreatingUsingIds() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(kolegijRepository.findById(1L))
                .thenReturn(Optional.of(kolegij));

        when(upisRepository.existsByStudentIdAndKolegijId(
                1L,
                1L
        )).thenReturn(false);

        assertThrows(
                InvalidGradeException.class,
                () -> upisService.createUpis(
                        1L,
                        1L,
                        7
                )
        );

        verify(
                upisRepository,
                never()
        ).save(any());
    }
}