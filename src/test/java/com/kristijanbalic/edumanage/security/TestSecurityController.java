package com.kristijanbalic.edumanage.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestSecurityController {

    @GetMapping("/students")
    @ResponseBody
    public String students() {
        return "students";
    }

    @GetMapping("/profesors")
    @ResponseBody
    public String profesors() {
        return "profesors";
    }

    @GetMapping("/courses")
    @ResponseBody
    public String courses() {
        return "courses";
    }

    @GetMapping("/upisi")
    @ResponseBody
    public String upisi() {
        return "upisi";
    }

    @GetMapping("/student/dashboard")
    @ResponseBody
    public String studentDashboard() {
        return "student-dashboard";
    }

    @GetMapping("/api/test")
    @ResponseBody
    public String apiTest() {
        return "api-test";
    }

    @GetMapping("/home")
    @ResponseBody
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    @ResponseBody
    public String login() {
        return "login";
    }
}