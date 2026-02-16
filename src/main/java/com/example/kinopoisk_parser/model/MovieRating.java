package com.example.kinopoisk_parser.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "MovieRating")
@Getter
@Setter
@AllArgsConstructor
@Table(name = "movie_rating")
public class MovieRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "kp", nullable = false)
    private float kp;

    @Column(name = "imdb", nullable = false)
    private float imdb;

    @Column(name = "film_critics", nullable = false)
    private float filmCritics;

    @Column(name = "russian_film_critics", nullable = false)
    private float russianFilmCritics;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Movie movie;

    public MovieRating() {

    }
}
