package com.example.WatchListApplication.exception;

public class MovieAlreadyExistException extends RuntimeException{
    public MovieAlreadyExistException(String name) {
        super("movie with name:" + name +"Already exists");
    }
}
