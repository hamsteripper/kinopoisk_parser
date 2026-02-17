package com.example.kinopoisk_parser.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "Movie")
@Getter
@Setter
//@AllArgsConstructor
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

    @DateTimeFormat(pattern = "yyyy")
    // For serializing to/from JSON
//    @JsonFormat(pattern = "yyyy")
    private LocalDate year;



    @OneToMany(mappedBy = "movie")
    private Set<MovieRating> movieRatings;

    @OneToMany(mappedBy = "movie")
    private Set<MovieVotes> movieVotes;

    public Movie(int position, String originalName, LocalDate year) {
        this.position = position;
        this.originalName = originalName;
        this.year = year;
    }

    public Movie() {

    }
}
