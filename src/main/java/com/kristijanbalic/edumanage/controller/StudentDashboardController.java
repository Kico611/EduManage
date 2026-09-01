package com.kristijanbalic.edumanage.controller;

import com.kristijanbalic.edumanage.entity.Student;
import com.kristijanbalic.edumanage.repository.UserRepository;
import com.kristijanbalic.edumanage.security.User;
import com.kristijanbalic.edumanage.service.UpisService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentDashboardController {

    private final UserRepository userRepository;
    private final UpisService upisService;

    public StudentDashboardController(UserRepository userRepository,
                                      UpisService upisService) {
        this.userRepository = userRepository;
        this.upisService = upisService;
    }

    @GetMapping("/student/dashboard")
    public String dashboard(Authentication authentication,
                            Model model) {

        User user = userRepository
                .findByUsername(authentication.getName())
                .orElseThrow();

        Student student = user.getStudent();

        if (student == null) {
            throw new IllegalStateException(
                    "Student account is not linked to a student profile."
            );
        }

        model.addAttribute("student", student);
        model.addAttribute(
                "upisi",
                upisService.getUpisiForStudent(student.getId())
        );

        return "studentDashboard";
    }
}