package com.kristijanbalic.edumanage.exception;

public class InvalidGradeException extends RuntimeException {

    public InvalidGradeException(Integer grade) {
        super("Grade must be between 1 and 5. Received: " + grade);
    }
}