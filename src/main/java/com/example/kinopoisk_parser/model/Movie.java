package com.example.kinopoisk_parser.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

    @DateTimeFormat(pattern = "yyyy")
    // For serializing to/from JSON
    @JsonFormat(pattern = "yyyy")
    private LocalDate year;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieRating> movieRatings = new ArrayList<>();

    public void addMovieRating(MovieRating movieRating){
        movieRatings.add(movieRating);
        movieRating.setMovie(this);
    }

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieVotes> movieVotes = new ArrayList<>();
    public void addMovieVotes(MovieVotes movieVote){
        movieVotes.add(movieVote);
        movieVote.setMovie(this);
    }

    public Movie() {

    }
}
