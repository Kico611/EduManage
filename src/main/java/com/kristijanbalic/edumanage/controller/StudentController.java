package com.kristijanbalic.edumanage.controller;

import com.kristijanbalic.edumanage.entity.Student;
import com.kristijanbalic.edumanage.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "Student";
    }

    @GetMapping("/new")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "Student";
    }

    @PostMapping
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "editStudent";
    }

    @PostMapping("/{id}/edit")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute Student student) {
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}