package com.example.block7crudvalidation.Exceptions;

public class UnprocessableEntityException extends RuntimeException {
    public UnprocessableEntityException(CustomError message) {
        super(String.valueOf(message));
    }
}

