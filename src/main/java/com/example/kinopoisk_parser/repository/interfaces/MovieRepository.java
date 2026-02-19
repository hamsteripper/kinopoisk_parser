package com.example.kinopoisk_parser.repository.interfaces;

import com.example.kinopoisk_parser.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository; // @Repository is a specialization of @Component

import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {}
