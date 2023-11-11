package com.example.WatchListApplication.exception;

public class DirectorNotFoundException extends RuntimeException{
    public DirectorNotFoundException(String name) {
        super("director not found with name:"+ name);
    }
}
