package com.gmdb.gmdb;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoviesService {

    MovieRepository repository;

    public MoviesService(MovieRepository repository){
        this.repository = repository;
    }

    public List<Movies> getAllMovies() {
        return repository.findAll()
                .stream()
                .map(this::mapToMovies)
                .collect(Collectors.toList());
    }

    private Movies mapToMovies(MovieEntity movieEntity) {
        return new Movies(
                movieEntity.getId(),
                movieEntity.getTitle(),
                movieEntity.getDirector(),
                movieEntity.getActors(),
                movieEntity.getReleaseYear(),
                movieEntity.getDescription(),
                movieEntity.getStarRating()
        );
    }
}
