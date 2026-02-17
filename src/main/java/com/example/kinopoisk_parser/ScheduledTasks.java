package com.example.kinopoisk_parser;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Scheduled(cron = "0 * * * * *")
    public void runHourlyTask() {
        System.out.println("The task is completed");
    }
}