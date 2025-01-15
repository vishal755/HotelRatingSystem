package com.lcwd.hotel.HotelService.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("resource is not found in server");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

}
