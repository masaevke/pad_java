package ru.urfu.jobs.worker;

import java.time.Instant;
import java.util.UUID;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JobWorker {
    private static final Logger log = LoggerFactory.getLogger(JobWorker.class);
    private final RabbitTemplate rabbit;
    private final ObjectMapper json;
    private final long demoAckDelayMs;

    public JobWorker(RabbitTemplate rabbit, ObjectMapper json,
                     @Value("${JOB_DEMO_ACK_DELAY_MS:0}") long demoAckDelayMs) {
        this.rabbit = rabbit;
        this.json = json;
        this.demoAckDelayMs = demoAckDelayMs;
    }

    @RabbitListener(queues = "tasks")
    public void multiply(String message) throws JsonProcessingException, InterruptedException {
        Task task = json.readValue(message, Task.class);
        Thread.sleep(2500);
        int result = task.a() * task.b();
        log.info("Job {}: {} * {} = {}", task.id(), task.a(), task.b(), result);

        JobCompleted completed = new JobCompleted(task.id(), result, Instant.now().toString());
        String event = json.writeValueAsString(completed);
        rabbit.invoke(channel -> {
            channel.convertAndSend("", "results", event);
            channel.waitForConfirmsOrDie(5000);
            return null;
        });
        if (demoAckDelayMs > 0) {
            log.info("Job {}: result published; delaying ack for {} ms", task.id(), demoAckDelayMs);
            Thread.sleep(demoAckDelayMs);
        }
        // Spring acknowledges the task when this method returns successfully.
    }

    private record Task(UUID id, int a, int b) {}

    private record JobCompleted(UUID id, int result, String completedAt) {}
}
