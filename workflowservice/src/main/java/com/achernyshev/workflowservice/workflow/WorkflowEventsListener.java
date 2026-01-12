package com.achernyshev.workflowservice.workflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WorkflowEventsListener {
    @KafkaListener(topics = "workflow-events", groupId = "workflow-service")
    public void listen(WorkflowTaskEvent event) {
        log.info("Ended step: {}", event.stepIndex());
    }
}
