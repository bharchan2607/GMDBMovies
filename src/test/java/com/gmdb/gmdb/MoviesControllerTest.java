package com.gmdb.gmdb;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class MoviesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MovieRepository repository;

    @BeforeEach
    public void setup(){
        MovieEntity movie1 = new MovieEntity("The Avengers","Joss Whedon",
                "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity"
                ,0D);
        MovieEntity movie2 = new MovieEntity("Superman Returns",
                "Bryan Singer",
                "Brandon Routh, Kate Bosworth, Kevin Spacey, James Marsden",
                 "2006",
                 "Superman returns to Earth after spending five years in space examining his homeworld Krypton. But he finds things have changed while he was gone, and he must once again prove himself important to the world.",
                0D);
        MovieEntity movie3 = new MovieEntity("Steel",
                "Kenneth Johnson",
                "Shaquille O'Neal, Annabeth Gish, Judd Nelson, Richard Roundtree",
                 "1997",
                "A scientist for the military turns himself into a cartoon-like superhero when a version of one of his own weapons is being used against enemies.",
                0D);
        repository.save(movie1);
        repository.save(movie2);
        repository.save(movie3);

    }

    @Test
    public void getAllMovies() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.[0].title").value("The Avengers"))
                .andExpect(jsonPath("$.[1].description").value("Superman returns to Earth after spending five years in space examining his homeworld Krypton. But he finds things have changed while he was gone, and he must once again prove himself important to the world."))
                .andExpect(jsonPath("$.[2].releaseYear").value("1997"));

    }
    @Test
    public void getMovieByTitle() throws Exception {
        String movieName = "The Avengers";
        mockMvc.perform(get("/movies/"+movieName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Avengers"))
                .andExpect(jsonPath("$.description").value("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity"))
                .andExpect(jsonPath("$.releaseYear").value("2012"));

    }

    @Test
    public void getMovieByTitle_NonExistent() throws Exception {
        String movieName = "Superman";
        mockMvc.perform(get("/movies/"+movieName))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MovieNotFoundException))
                .andExpect(result ->
                         assertEquals("Movie Doesn't Exist",
                                 result.getResolvedException().getMessage()));
    }

    @Test
    public void acceptStarRating() throws Exception {
        String movieName="The Avengers";
        mockMvc.perform(post("/movies/"+movieName+"/"+5))
                .andExpect(status().isAccepted());


    }

}
