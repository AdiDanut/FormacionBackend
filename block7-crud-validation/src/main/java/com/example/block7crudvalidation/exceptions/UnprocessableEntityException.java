package com.example.block7crudvalidation.exceptions;

public class UnprocessableEntityException extends RuntimeException {
    public UnprocessableEntityException(CustomError message) {
        super(String.valueOf(message));
    }
}

