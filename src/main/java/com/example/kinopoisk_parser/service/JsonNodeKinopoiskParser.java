package com.example.kinopoisk_parser.service;

import com.example.kinopoisk_parser.model.Movie;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonNodeKinopoiskParser {

    public JsonNodeKinopoiskParser() {
    }

    public List<Movie> parse(JsonNode jsonNode) {

        List<Movie> list = new ArrayList<>();

        JsonNode movies = jsonNode.get("docs");
        if (movies.isArray()) {
            for (JsonNode movie : movies) {
                Movie movieObj = new Movie();
                movieObj.setPosition(movie.get("id").asInt());
                movieObj.setOriginalName(movie.get("alternativeName").asString());
                movieObj.setYear(LocalDate.of(movie.get("year").asInt(), Month.JANUARY, 1));

                list.add(movieObj);
            }
        }

        return list;
    }
}
