package com.gmdb.gmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Movies {

    @JsonIgnoreProperties
    private Long id;

    private String title;
    private String director;
    private String actors;
    private String releaseYear;
    private String description;
    private Double starRating;

    public Movies(String title, String director, String actors, String releaseYear, String description, Double starRating) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.releaseYear = releaseYear;
        this.description = description;
        this.starRating = starRating;
    }
}
