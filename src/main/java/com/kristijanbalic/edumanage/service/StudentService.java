package com.kristijanbalic.edumanage.service;

import com.kristijanbalic.edumanage.entity.Student;
import com.kristijanbalic.edumanage.repository.StudentRepository;
import com.kristijanbalic.edumanage.repository.UpisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UpisRepository upisRepository;

    public StudentService(StudentRepository studentRepository,
                          UpisRepository upisRepository) {
        this.studentRepository = studentRepository;
        this.upisRepository = upisRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid student ID: " + id));
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

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