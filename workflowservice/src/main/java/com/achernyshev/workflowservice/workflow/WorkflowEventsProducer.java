package com.achernyshev.workflowservice.workflow;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WorkflowEventsProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void createTaskForStep(UUID workflowId, int stepIndex) {
        WorkflowTaskEvent event = new WorkflowTaskEvent(workflowId, stepIndex);
        kafkaTemplate.send("task-events", event);
    }
}