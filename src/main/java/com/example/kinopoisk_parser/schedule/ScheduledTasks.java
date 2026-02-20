package com.example.kinopoisk_parser.schedule;

import com.example.kinopoisk_parser.model.Movie;
import com.example.kinopoisk_parser.repository.MovieRepositoryImpl;
import com.example.kinopoisk_parser.service.DownloadFromKinopoisk;
import com.example.kinopoisk_parser.service.JsonNodeKinopoiskParser;
import jakarta.transaction.Transactional;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;

import java.util.List;

@Component
public class ScheduledTasks {

    private final JsonNodeKinopoiskParser jsonNodeKinopoiskParser;
    private final MovieRepositoryImpl movieRepository;
    private final DownloadFromKinopoisk downloadFromKinopoisk;
    private final CacheManager cacheManager;

    public ScheduledTasks(
            DownloadFromKinopoisk downloadFromKinopoisk,
            JsonNodeKinopoiskParser jsonNodeKinopoiskParser,
            MovieRepositoryImpl movieRepository,
            CacheManager cacheManager
    ) {
        this.jsonNodeKinopoiskParser = jsonNodeKinopoiskParser;
        this.movieRepository = movieRepository;
        this.downloadFromKinopoisk = downloadFromKinopoisk;
        this.cacheManager = cacheManager;
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void runTask() {

        // Get json from url
        JsonNode jsonNode = this.downloadFromKinopoisk.downloadTop250();

        // Json parser
        List<Movie> movies = this.jsonNodeKinopoiskParser.parse(jsonNode);

        // Save to bd
        this.movieRepository.saveAll(movies);

        // Clear cache
        Cache cache = cacheManager.getCache("movies");
        if (cache != null) {
            cache.clear();
        }

        System.out.println("The task is completed");
    }
}