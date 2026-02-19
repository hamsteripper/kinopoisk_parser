package com.example.kinopoisk_parser.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity(name = "MovieVotes")
@Getter
@Setter
@AllArgsConstructor
@Table(name = "movie_votes")
public class MovieVotes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "kp", nullable = false)
    private int kp;

    @Column(name = "imdb", nullable = false)
    private int imdb;

    @Column(name = "film_critics", nullable = false)
    private int filmCritics;

    @Column(name = "russian_film_critics", nullable = false)
    private int russianFilmCritics;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updateAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Movie movie;

    public MovieVotes() {

    }
}
