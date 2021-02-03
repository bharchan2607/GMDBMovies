package com.gmdb.gmdb;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MoviesServiceTest {

    @Mock
    MovieRepository movieRepository;
    @InjectMocks
    MoviesService moviesService;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void getAllMovies(){
        when(movieRepository.findAll()).thenReturn(List.of(new MovieEntity("The Avengers","Joss Whedon",
                "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity"
                ,0D)));
        List<Movies> actualMovies = moviesService.getAllMovies();
        assertEquals(List.of(new Movies("The Avengers","Joss Whedon",
                "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity"
                ,0D)),actualMovies);
        verify(movieRepository,times(1)).findAll();
    }

}
