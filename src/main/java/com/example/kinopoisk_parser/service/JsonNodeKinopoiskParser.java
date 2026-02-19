package com.example.kinopoisk_parser.service;

import com.example.kinopoisk_parser.model.Movie;
import com.example.kinopoisk_parser.model.MovieRating;
import com.example.kinopoisk_parser.model.MovieVotes;
import com.example.kinopoisk_parser.repository.MovieRepositoryImpl;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JsonNodeKinopoiskParser {

    private final MovieRepositoryImpl movieRepository;

    public JsonNodeKinopoiskParser(MovieRepositoryImpl movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> parse(JsonNode jsonNode) {

        // Create result list
        List<Movie> list = new ArrayList<>();

        JsonNode movies = jsonNode.get("docs");
        int i = 1;
        if (movies.isArray()) {
            for (JsonNode movie : movies) {

                // Find or create movie entity
                Movie movieObj = Optional.ofNullable(this.movieRepository.findByOriginalId((long) movie.get("id").asInt())).orElse(new Movie());

                // Update movie entity
                movieObj.setPosition(i++);
                movieObj.setOriginalName(movie.get("alternativeName").asString());
                movieObj.setYear(LocalDate.of(movie.get("year").asInt(), Month.JANUARY, 1));
                movieObj.setOriginalId((long) movie.get("id").asInt());

                // Create MovieRating
                MovieRating movieRating = new MovieRating();
                movieRating.setKp(movie.get("rating").get("kp").asFloat());
                movieRating.setImdb(movie.get("rating").get("imdb").asFloat());
                movieRating.setFilmCritics(movie.get("rating").get("filmCritics").asFloat());
                movieRating.setRussianFilmCritics(movie.get("rating").get("russianFilmCritics").asFloat());

                // Create MovieVotes
                MovieVotes movieVote = new MovieVotes();
                movieVote.setKp(movie.get("votes").get("kp").asInt());
                movieVote.setImdb(movie.get("votes").get("imdb").asInt());
                movieVote.setFilmCritics(movie.get("votes").get("filmCritics").asInt());
                movieVote.setRussianFilmCritics(movie.get("votes").get("russianFilmCritics").asInt());

                // Add movieRating
                movieObj.addMovieRating(movieRating);
                // Add movieObj
                movieObj.addMovieVotes(movieVote);

                // Add to result list
                list.add(movieObj);
            }
        }

        return list;
    }
}
