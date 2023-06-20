package com.example.digitalbnkingbackend.exeptions;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message ){
        super(message);
    }
}
