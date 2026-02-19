package com.example.kinopoisk_parser.controller;

import com.example.kinopoisk_parser.model.Movie;
import com.example.kinopoisk_parser.repository.MovieRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Slf4j
@RestController
public class MainController {

    private final MovieRepositoryImpl movieRepository;

    public MainController(MovieRepositoryImpl movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/")
    public ModelAndView mainControllerF(
            Model model,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {

        Iterable<Movie> movies;

        if (date == null) {
            movies = this.movieRepository.find(1, 10);
        } else {
            movies = this.movieRepository.findByDate(date, 1, 10);
        }

        model.addAttribute("movies", movies);

        return new ModelAndView("movieTable.html");
    }

}