package com.example.kinopoisk_parser.controller;

import com.example.kinopoisk_parser.model.Movie;
import com.example.kinopoisk_parser.repository.MovieRepositoryImpl;
import com.example.kinopoisk_parser.service.DownloadFromKinopoisk;
import com.example.kinopoisk_parser.service.JsonNodeKinopoiskParser;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class MainController {

    private final JsonNodeKinopoiskParser jsonNodeKinopoiskParser;
    private final DownloadFromKinopoisk downloadFromKinopoisk;
    private final MovieRepositoryImpl movieRepository;

    public MainController(DownloadFromKinopoisk downloadFromKinopoisk, JsonNodeKinopoiskParser jsonNodeKinopoiskParser, MovieRepositoryImpl movieRepository) {
        this.downloadFromKinopoisk = downloadFromKinopoisk;
        this.jsonNodeKinopoiskParser = jsonNodeKinopoiskParser;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/")
    public ModelAndView mainControllerF(Model model) {

        // Get json from url
//        JsonNode jsonNode = this.downloadFromKinopoisk.downloadTop250();

        // Get json from file
        File jsonFile = new File("src/main/resources/response.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonFile);

        // Json parser
        List<Movie> movies = this.jsonNodeKinopoiskParser.parse(jsonNode);

        // Save to bd
        this.movieRepository.saveAll(movies);

        model.addAttribute("movies", movies);

        return new ModelAndView("movieTable.html");
    }

}