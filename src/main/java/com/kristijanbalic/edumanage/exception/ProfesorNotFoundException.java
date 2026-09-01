package com.kristijanbalic.edumanage.exception;

public class ProfesorNotFoundException extends RuntimeException {

    public ProfesorNotFoundException(Long id) {
        super("Profesor not found with id: " + id);
    }
}