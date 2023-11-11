package com.example.WatchListApplication.exception;

public class DirectorAlreadyExistExceptionn extends RuntimeException{
    public DirectorAlreadyExistExceptionn(String name) {
        super("director with name:" + name +"Already exists");
    }
}
