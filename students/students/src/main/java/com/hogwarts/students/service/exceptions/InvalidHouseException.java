package com.hogwarts.students.service.exceptions;

public class InvalidHouseException extends RuntimeException {
    public InvalidHouseException(String message) {
        super(message);
    }
}

