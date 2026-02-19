package com.example.kinopoisk_parser.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Movie")
@Getter
@Setter
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "position", nullable = false)
    private int position;

    @Column(name = "original_name", nullable = false)
    private String originalName;

    @Column(name = "original_id", nullable = false, unique = true)
    private Long originalId;

    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieRating> movieRatings = new ArrayList<>();

    public void addMovieRating(MovieRating movieRating){
        this.movieRatings.add(movieRating);
        movieRating.setMovie(this);
    }

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieVotes> movieVotes = new ArrayList<>();

    public void addMovieVotes(MovieVotes movieVote){
        this.movieVotes.add(movieVote);
        movieVote.setMovie(this);
    }

    public Movie() {

    }
}
