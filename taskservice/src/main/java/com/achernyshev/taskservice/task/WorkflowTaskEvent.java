package com.achernyshev.taskservice.task;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowTaskEvent {
    private UUID workflowId;
    private int stepIndex;
    private String workflowName;
    private String stepName;
}

