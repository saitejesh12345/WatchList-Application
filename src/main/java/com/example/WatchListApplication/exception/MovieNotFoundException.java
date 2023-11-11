package com.example.WatchListApplication.exception;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String name) {
        super("movie not found with name:"+ name);
    }
}
