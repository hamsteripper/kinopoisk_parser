package com.example.kinopoisk_parser.service;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
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

    public JsonNode downloadTop250() {

        // 1. Создание клиента
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

        String url = (this.url + "/movie?lists=top250").replace("//movie", "/movie");

        // 2. Создание запроса
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-API-KEY", this.token)
                .header("Accept", "application/json")
                .GET().build();

        try {

            // 3. Отправка запроса и получение ответа
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Response Body: " + response.body());
                throw new HttpRetryException(response.body(), response.statusCode());
            }

            // 4. Обработка ответа
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.body());

            return jsonNode;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return null;
        }



//        RestTemplate restTemplate = new RestTemplate();
//        // The exchange method is flexible for different HTTP methods and response types
//        ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(this.url + "/movie?lists=top250", JsonNode.class);
//
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            return responseEntity.getBody(); // Returns the parsed JSON as a JsonNode
//        } else {
//            // Handle error cases
//            throw new RuntimeException("Failed to download JSON from URL: " + url + " Status code: " + responseEntity.getStatusCode());
//        }

    }
}
