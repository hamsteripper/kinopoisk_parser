package com.example.kinopoisk_parser.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity(name = "MovieRating")
@Getter
@Setter
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

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updateAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Movie movie;

    public MovieRating() {

    }
}
