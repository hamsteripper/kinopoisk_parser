package com.example.kinopoisk_parser.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class MainController {

    public MainController() {
    }

    @GetMapping("/")
    public String mainControllerF() throws IOException {

        return "ok";
    }

}