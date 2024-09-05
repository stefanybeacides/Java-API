package com.hogwarts.students.service.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg){

        super(msg);
    }
}
