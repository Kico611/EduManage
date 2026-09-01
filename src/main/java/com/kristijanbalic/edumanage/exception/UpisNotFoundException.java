package com.kristijanbalic.edumanage.exception;

public class UpisNotFoundException extends RuntimeException {

    public UpisNotFoundException(Long id) {
        super("Enrollment not found with id: " + id);
    }
}