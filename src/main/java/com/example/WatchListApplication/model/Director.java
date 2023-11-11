package com.example.WatchListApplication.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Director {
    private String name;
    private  int numberOfMovies;
    private double imdbRating;
}
