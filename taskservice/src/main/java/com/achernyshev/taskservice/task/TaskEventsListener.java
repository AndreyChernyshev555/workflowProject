package com.achernyshev.taskservice.task;

import com.achernyshev.taskservice.task.status.TaskStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskEventsListener {

    private final TaskRepository taskRepository;

    @KafkaListener(topics = "workflow-step-task-start", groupId = "workflow-service")
    public void onNewStep(WorkflowTaskEventDto event) {
        log.info("Started step: {}", event);
        Task newTask = new Task();
        newTask.setWorkflowId(event.getWorkflowId());
        newTask.setCurrentStepIndex(event.getStepIndex());
        LocalDate date = LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        newTask.setLabel(formattedDate + " " + event.getWorkflowName() + " " + event.getStepName());
        newTask.setStatus(TaskStatus.IN_PROGRESS);
        taskRepository.save(newTask);
    }
}
