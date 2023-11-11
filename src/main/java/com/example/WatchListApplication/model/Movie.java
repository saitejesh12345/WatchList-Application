package com.example.WatchListApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    private String name;
    private int durationInMinutes;
    private double imdbRating;
    private Director director;
}
