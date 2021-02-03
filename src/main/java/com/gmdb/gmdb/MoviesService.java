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


    public Movies getMovieByTitle(String title) throws MovieNotFoundException {
        MovieEntity movie = repository.findByTitle(title);
        if(movie == null){
            throw new MovieNotFoundException("Movie Doesn't Exist");
        }
        return mapToMovies(movie);
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

    public Movies acceptStarRating(String title, Integer starRating) {
        MovieEntity movie = repository.findByTitle(title);

        if(movie != null) {
           // List<Integer> rating = repository.findByRating(title);
            List<Integer> rating = movie.getUserReview()
                    .stream()
                    .map(val -> val.getStarRating())
                    .collect(Collectors.toList());
            movie.getUserReview().add(new UserReviewEntity(starRating, null));
            if(!movie.getUserReview().isEmpty()){
                rating.add(starRating);
                double averageRating = rating.stream()
                        .mapToDouble(val -> val)
                        .average().getAsDouble();
                movie.setStarRating(averageRating);
            }
            movie = repository.save(movie);
            return mapToMovies(movie);
        }
        return null;
    }
}
