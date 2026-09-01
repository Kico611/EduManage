package com.kristijanbalic.edumanage.exception;

public class DuplicateEnrollmentException extends RuntimeException {

    public DuplicateEnrollmentException(Long studentId, Long kolegijId) {
        super(
                "Student with id " + studentId +
                        " is already enrolled in course with id " + kolegijId
        );
    }
}