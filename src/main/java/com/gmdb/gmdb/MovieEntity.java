package com.gmdb.gmdb;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@EqualsAndHashCode
@ToString
@Getter
@Entity
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String director;
    private String actors;
    private String releaseYear;
    private String description;
    private Double starRating;

    public MovieEntity(String title, String director, String actors, String releaseYear, String description, Double starRating) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.releaseYear = releaseYear;
        this.description = description;
        this.starRating = starRating;
    }

    public MovieEntity() {
    }
}
