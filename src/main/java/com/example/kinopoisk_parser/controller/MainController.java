package com.example.kinopoisk_parser.controller;

import com.example.kinopoisk_parser.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class MainController {

//    public MainController() {
//        int a = 10;
//    }

    @GetMapping("/")
    public ModelAndView mainControllerF(Model model) {

        List<Movie> movies = Arrays.asList(
                new Movie(1, "name1", LocalDate.parse("2023-01-16")),
                new Movie(2, "name2", LocalDate.parse("2023-01-15"))
        );
        model.addAttribute("movies", movies);

        return new ModelAndView("movieTable.html");
    }

}