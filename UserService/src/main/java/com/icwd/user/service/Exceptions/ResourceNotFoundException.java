package com.icwd.user.service.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not found on the server!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
