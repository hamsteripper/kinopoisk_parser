package com.example.kinopoisk_parser.repository.interfaces;

import com.example.kinopoisk_parser.model.MovieRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRatingRepository extends CrudRepository<MovieRating, Long> {}
