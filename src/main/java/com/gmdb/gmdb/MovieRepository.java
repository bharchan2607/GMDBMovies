package com.gmdb.gmdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    public MovieEntity findByTitle(String title);

    @Query("select userReview.starRating from MovieEntity where title=?1")
    public List<Integer> findByRating(String title);
}
