package com.hogwarts.students.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String msg){

        super(msg);
    }
}
