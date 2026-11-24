package ru.urfu.jobs.api;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobsApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobsApplication.class, args);
    }

    @Bean
    Queue tasks() {
        return new Queue("tasks");
    }

    @Bean
    Queue results() {
        return new Queue("results");
    }
}
