package com.example.CRUD.Controller;

import com.example.CRUD.Model.Student;
import com.example.CRUD.Repository.StudentRepository;
import com.example.CRUD.Repository.UpisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UpisRepository upisRepository;

    @GetMapping
    public String getStudents(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "Student";
    }

    @GetMapping("/new")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "Student";
    }

    @PostMapping
    public String saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        return "editStudent";
    }

    @PostMapping("/{id}/edit")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            studentRepository.save(student);
        }
        return "redirect:/students";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        upisRepository.deleteByStudentId(id);
        studentRepository.deleteById(id);
        return "redirect:/students";
    }

}