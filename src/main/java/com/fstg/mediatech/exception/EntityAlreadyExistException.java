package com.fstg.mediatech.exception;

public class EntityAlreadyExistException extends RuntimeException{

    public EntityAlreadyExistException() {
    }

    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
