package com.gmdb.gmdb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
