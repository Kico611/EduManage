package com.kristijanbalic.edumanage.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            StudentNotFoundException.class,
            ProfesorNotFoundException.class,
            KolegijNotFoundException.class,
            UpisNotFoundException.class
    })
    public String handleNotFoundException(RuntimeException exception,
                                          Model model) {

        model.addAttribute("errorTitle", "Resource Not Found");
        model.addAttribute("errorMessage", exception.getMessage());

        return "error";
    }
    @ExceptionHandler(InvalidGradeException.class)
    public String handleInvalidGradeException(
            InvalidGradeException exception,
            Model model) {

        model.addAttribute("errorTitle", "Invalid Grade");
        model.addAttribute("errorMessage", exception.getMessage());

        return "error";
    }
    @ExceptionHandler(DuplicateEnrollmentException.class)
    public String handleDuplicateEnrollmentException(
            DuplicateEnrollmentException exception,
            Model model) {

        model.addAttribute("errorTitle", "Duplicate Enrollment");
        model.addAttribute("errorMessage", exception.getMessage());

        return "error";
    }
}