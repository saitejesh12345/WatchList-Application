package com.example.WatchListApplication.controller;


import com.example.WatchListApplication.exception.DirectorAlreadyExistExceptionn;
import com.example.WatchListApplication.exception.DirectorNotFoundException;
import com.example.WatchListApplication.exception.MovieAlreadyExistException;
import com.example.WatchListApplication.exception.MovieNotFoundException;
import com.example.WatchListApplication.model.Director;
import com.example.WatchListApplication.model.Movie;
import com.example.WatchListApplication.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        try {
            movieService.addMovie(movie);
            return new ResponseEntity("Movie added Successfully", HttpStatus.OK);
        }catch(MovieAlreadyExistException ex){
            return new ResponseEntity("Unable to add movie as it Already exists.",HttpStatus.valueOf(400));
        }
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        try {
            movieService.addDirector(director);
            return new ResponseEntity("Directory added Successfully", HttpStatus.OK);
        }catch(DirectorAlreadyExistExceptionn ex){
            return new ResponseEntity("Unable to add director as it Already exists.",HttpStatus.valueOf(400));
        }
    }


    @PutMapping("/add-movie-director-pair")
    public ResponseEntity  addMovieDirectorPair(@RequestParam String movie_name,@RequestParam String  director_name){
        try {
            String movie1 = movieService.addMovieDirectorPair(movie_name, director_name);
            return new ResponseEntity(movie1, HttpStatus.OK);
        }
        catch(Exception ex){
            return  new ResponseEntity("movie_name or director_name not in Records",HttpStatus.valueOf(500)) ;
        }
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name) {
        try {
            Movie movie = movieService.getMovieByName(name);
            return new ResponseEntity(movie, HttpStatus.OK);
        } catch (MovieNotFoundException ex) {
            return  new ResponseEntity("movie not found",HttpStatus.valueOf(500));
        }
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name) {
        try {
            Director director = movieService.getDirectorByName(name);
            return new ResponseEntity(director, HttpStatus.OK);
        } catch (DirectorNotFoundException ex) {
            return  new ResponseEntity("director not found",HttpStatus.valueOf(500));
        }
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director) {
        try {
            List<String> movieNames = movieService.getMoviesByDirectorName(director);
            return new ResponseEntity(movieNames, HttpStatus.OK);
        } catch (DirectorNotFoundException ex) {
            return  new ResponseEntity("directors not found",HttpStatus.valueOf(500));
        }
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        try {
            List<String> movieNames = movieService.findAllMovies();
            return new ResponseEntity(movieNames, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String name) {
        try {
            movieService.deleteDirectorByName(name);
            return new ResponseEntity("Director and their movies deleted successfully",HttpStatus.OK);
        } catch (DirectorNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        try {
            movieService.deleteAllDirectors();
            return ResponseEntity.ok("All directors and their movies deleted successfully.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
