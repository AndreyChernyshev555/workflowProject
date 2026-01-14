package com.achernyshev.workflowservice.workflow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class WorkflowEventsListener {

    private final WorkflowRepository workflowRepository;

    private final WorkflowService workflowService;

    @KafkaListener(topics = "workflow-step-task-end", groupId = "workflow-service")
    public void onTaskEnded(WorkflowTaskEvent event) {
        log.info("Ended step: {}", event);

        Optional<Workflow> workflowData = workflowRepository.findById(event.getWorkflowId());
        workflowData.ifPresent(workflowService::setNextStep);
    }
}
