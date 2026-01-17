package com.achernyshev.workflowservice.workflow;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WorkflowEventsProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void createTaskForStep(
            UUID workflowId,
            int stepIndex,
            String workflowName,
            String stepName
    ) {
        WorkflowTaskEvent event = new WorkflowTaskEvent(workflowId, stepIndex, workflowName, stepName);
        kafkaTemplate.send("workflow-step-task-start", event);
    }
}