package com.gmdb.gmdb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MoviesController {

    MoviesService service;

    public MoviesController(MoviesService service){
        this.service = service;
    }

    @GetMapping("/movies")
    public List<Movies> getAllMovies(){
        return service.getAllMovies();
    }

    @GetMapping("/movies/{title}")
    public Movies getMovieByTitle(@PathVariable String title) {
        return service.getMovieByTitle(title);
    }

    @PostMapping("/movies/{title}/{starRating}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Movies getMovieByTitle(@PathVariable String title,
                                  @PathVariable Integer starRating) {
        return service.acceptStarRating(title, starRating);
    }

}
