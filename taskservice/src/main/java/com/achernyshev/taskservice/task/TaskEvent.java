package com.achernyshev.taskservice.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class TaskEvent {
    private final UUID workflowId;
    private final int stepIndex;
    private final String workflowName;
    private final String stepName;
}

