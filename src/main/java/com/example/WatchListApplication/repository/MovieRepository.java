package com.example.WatchListApplication.repository;

import com.example.WatchListApplication.model.Director;
import com.example.WatchListApplication.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class MovieRepository {

   public Map<String,Movie> moviedb = new HashMap<>(); //temp database

    public HashMap<String, Director> directorDb = new HashMap<>();
    public String  addMovie(Movie movie) {
        moviedb.put(movie.getName(),movie);
        return "SUCCESS";
    }

    public String addDirector(Director director) {
        directorDb.put(director.getName(),director);
        return "SUCCESS";
    }

    public Optional<Movie> getMovieeByName(String name){
         if(moviedb.containsKey(name)){
             return Optional.of(moviedb.get(name));
         }
         return Optional.empty();
    }

    public Optional<Director> getDirectorrByName(String name) {
        if (directorDb.containsKey(name)) {
            return Optional.of(directorDb.get(name));
        }
        return Optional.empty();
    }

    public List<String> getMoviesByDirectorName(String director) {
        List<String> movieNames = new ArrayList<>();
        for (Movie movie : moviedb.values()) {
            if (movie.getDirector().getName().equals(director)) {
                movieNames.add(movie.getName());
            }
        }
        return movieNames;
    }
    public List<String> findAllMovies() {
       return new ArrayList<>(moviedb.keySet());

    }

    public String  deleteDirector(Director director) {
        directorDb.remove(director.getName());
        return "SUCCESS";
    }

    public List<Director> getAllDirectors() {
        return new ArrayList<>(directorDb.values());
    }

}
