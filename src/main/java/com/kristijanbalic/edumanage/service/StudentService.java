package com.kristijanbalic.edumanage.service;

import com.kristijanbalic.edumanage.entity.Student;
import com.kristijanbalic.edumanage.exception.StudentNotFoundException;
import com.kristijanbalic.edumanage.repository.StudentRepository;
import com.kristijanbalic.edumanage.repository.UpisRepository;
import com.kristijanbalic.edumanage.repository.UserRepository;
import com.kristijanbalic.edumanage.security.Role;
import com.kristijanbalic.edumanage.security.User;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UpisRepository upisRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository,
                          UpisRepository upisRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {

        this.studentRepository = studentRepository;
        this.upisRepository = upisRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Transactional
    public Student saveStudent(Student student) {

        Student savedStudent = studentRepository.save(student);

        if (userRepository
                .findByUsername(savedStudent.getBrojIndeksa())
                .isEmpty()) {

            User user = new User();

            user.setUsername(savedStudent.getBrojIndeksa());
            user.setPassword(
                    passwordEncoder.encode("student123")
            );
            user.setRole(Role.STUDENT);
            user.setStudent(savedStudent);

            userRepository.save(user);
        }

        return savedStudent;
    }

    @Transactional
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = getStudentById(id);

        existingStudent.setIme(student.getIme());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAdresa(student.getAdresa());
        existingStudent.setBrojIndeksa(student.getBrojIndeksa());
        existingStudent.setGodinaUpisa(student.getGodinaUpisa());

        return studentRepository.save(existingStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {

        getStudentById(id);

        upisRepository.deleteByStudentId(id);
        studentRepository.deleteById(id);
    }
}