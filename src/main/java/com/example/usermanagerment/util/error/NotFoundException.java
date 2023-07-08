package com.example.usermanagerment.util.error;

public class NotFoundException extends RuntimeException{

    public NotFoundException (String message){
        super(message);
    }
}
