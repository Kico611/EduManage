package com.kristijanbalic.edumanage.exception;

public class KolegijNotFoundException extends RuntimeException {

    public KolegijNotFoundException(Long id) {
        super("Course not found with id: " + id);
    }
}