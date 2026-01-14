package com.achernyshev.taskservice.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskEventsListener {

//    private final WorkflowRepository workflowRepository;
//
//    private final WorkflowStepRepository workflowStepRepository;
//
//    private final WorkflowService workflowService;
//
//    @KafkaListener(topics = "workflow-step-task-end", groupId = "workflow-service")
//    public void onTaskEnded(WorkflowTaskEvent event) {
//        log.info("Ended step: {}", event);
//
//        Workflow affectedWorkflow = workflowRepository.findById(event.getWorkflowId()).get();
//        Workflow updatedWorkflow = workflowService.setNextStep(affectedWorkflow);
//    }
}
