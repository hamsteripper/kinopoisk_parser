package com.example.kinopoisk_parser.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpRetryException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class DownloadFromKinopoisk {

    @Value("${kinopoisk.config.url}")
    private String url;
    @Value("${kinopoisk.config.token}")
    private String token;

    public DownloadFromKinopoisk(){
    }

    public JsonNode downloadTop250() {

        // 0. Create url
        String resultUrl = (this.url + "/movie?lists=top250&limit=250").replace("//movie", "/movie");

        // 1. Create client
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

        // 2. Create request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(resultUrl))
                .header("X-API-KEY", this.token)
                .header("Accept", "application/json")
                .GET().build();

        try {

            // 3. Send request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new HttpRetryException(response.body(), response.statusCode());
            }

            // 4. Calculate response
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readTree(response.body());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
