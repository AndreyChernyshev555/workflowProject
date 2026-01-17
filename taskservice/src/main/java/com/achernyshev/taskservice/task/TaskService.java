package com.achernyshev.taskservice.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskService {
    private final TaskEventsProducer taskEventsProducer;

    public void onTaskCompleted(Task completedTask) {
        taskEventsProducer.endTaskStep(
                completedTask.getWorkflowId(),
                completedTask.getCurrentStepIndex(),
                completedTask.getLabel().split(" ")[1],
                completedTask.getLabel().split(" ")[2]
        );
    }
}
