package com.example.WatchListApplication.service;

import com.example.WatchListApplication.exception.DirectorNotFoundException;
import com.example.WatchListApplication.exception.MovieNotFoundException;
import com.example.WatchListApplication.model.Director;
import com.example.WatchListApplication.model.Movie;
import com.example.WatchListApplication.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {


    @Autowired
    MovieRepository movieRepository;
    public String addMovie(Movie movie) {
       return movieRepository.addMovie(movie);
    }

    public String  addDirector(Director director) {
        return movieRepository.addDirector(director);
    }


    public String addMovieDirectorPair(String movie_name, String director_name) {
       Optional<Movie> movieOptional = movieRepository.getMovieeByName(movie_name);
        Optional<Director> directorOptional = movieRepository.getDirectorrByName(director_name);

        if (movieOptional.isEmpty()) {
            throw new MovieNotFoundException(movie_name);
        }

        if (directorOptional.isEmpty()) {
            throw new DirectorNotFoundException(director_name);
        }
        Movie movie = movieOptional.get();
        Director director = directorOptional.get();

        movie.setDirector(director);
        return "Movie-Director pair added successfully.";
    }

    public Movie getMovieByName(String name) {
        Optional<Movie> movieOptional = movieRepository.getMovieeByName(name);
        if (movieOptional.isEmpty()) {
            throw new MovieNotFoundException(name);
        }
        return movieOptional.get();
    }

    public Director getDirectorByName(String name) {
        Optional<Director> directorOptional = movieRepository.getDirectorrByName(name);
        if (directorOptional.isEmpty()) {
            throw new DirectorNotFoundException(name);
        }
        return directorOptional.get();
    }

    public List<String> getMoviesByDirectorName(String director) {
        List<String> movieNames = movieRepository.getMoviesByDirectorName(director);
        if (movieNames.isEmpty()) {
            throw new DirectorNotFoundException(director);
        }
        return movieNames;
    }
    public List<String> findAllMovies() {
        List<String> movieNames = movieRepository.findAllMovies();
        if (movieNames.isEmpty()) {
            throw new MovieNotFoundException("No movies found");
        }
        return movieNames;
    }

    public void deleteDirectorByName(String name) {
        Optional<Director> directorOptional = movieRepository.getDirectorrByName(name);
        if (directorOptional.isEmpty()) {
            throw new DirectorNotFoundException("Director not found.");
        }
        Director director = directorOptional.get();
        movieRepository.deleteDirector(director);
    }

    public void deleteAllDirectors() {
        List<Director> directors = movieRepository.getAllDirectors();
        for (Director director : directors) {
            movieRepository.deleteDirector(director);
        }
    }


}
