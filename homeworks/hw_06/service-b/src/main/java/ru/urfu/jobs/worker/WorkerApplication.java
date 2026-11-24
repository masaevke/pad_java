package ru.urfu.jobs.worker;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorkerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkerApplication.class, args);
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
