package com.example.block7crudvalidation.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(CustomError message) {
        super(String.valueOf(message));
    }
}

