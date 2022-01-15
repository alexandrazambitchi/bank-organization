package com.example.proiect.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(){
        super("The client doesn't exist");
    }
}
