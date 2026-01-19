package com.achernyshev.taskservice.task;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TaskEventsProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void endTaskStep(
            UUID workflowId,
            int stepIndex,
            String workflowName,
            String stepName
    ) {
        WorkflowTaskEvent event = new WorkflowTaskEvent(workflowId, stepIndex, workflowName, stepName);
        kafkaTemplate.send("workflow-step-task-end", event);
    }
}