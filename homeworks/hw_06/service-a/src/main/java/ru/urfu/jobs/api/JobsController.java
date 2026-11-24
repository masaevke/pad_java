package ru.urfu.jobs.api;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class JobsController {
    private final Map<UUID, Job> jobs = new ConcurrentHashMap<>();
    private final RabbitTemplate rabbit;
    private final ObjectMapper json;

    public JobsController(RabbitTemplate rabbit, ObjectMapper json) {
        this.rabbit = rabbit;
        this.json = json;
    }

    @PostMapping("/jobs")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Job create(@RequestBody JsonNode request) throws JsonProcessingException {
        int a = digit(request.path("a"), "a");
        int b = digit(request.path("b"), "b");
        UUID id = UUID.randomUUID();
        Job accepted = new Job(id, "ACCEPTED", null);
        String message = json.writeValueAsString(new Task(id, a, b));

        jobs.put(id, accepted);
        try {
            rabbit.invoke(channel -> {
                channel.convertAndSend("", "tasks", message);
                channel.waitForConfirmsOrDie(5000);
                return null;
            });
        } catch (AmqpException exception) {
            jobs.remove(id, accepted);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "Could not publish the task", exception);
        }
        return accepted;
    }

    @GetMapping("/jobs/{id}")
    public Job get(@PathVariable UUID id) {
        Job job = jobs.get(id);
        if (job == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
        }
        return job;
    }

    @RabbitListener(queues = "results")
    public void complete(String message) throws JsonProcessingException {
        JobCompleted completed = json.readValue(message, JobCompleted.class);
        jobs.computeIfPresent(completed.id(), (id, job) -> {
            if ("DONE".equals(job.status())) {
                return job;
            }
            return new Job(id, "DONE", completed.result());
        });
    }

    private int digit(JsonNode value, String field) {
        if (!value.isIntegralNumber() || !value.canConvertToInt()
                || value.intValue() < 0 || value.intValue() > 9) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    field + " must be an integer from 0 to 9");
        }
        return value.intValue();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Job(UUID id, String status, Integer result) {}

    private record Task(UUID id, int a, int b) {}

    private record JobCompleted(UUID id, int result, String completedAt) {}
}
