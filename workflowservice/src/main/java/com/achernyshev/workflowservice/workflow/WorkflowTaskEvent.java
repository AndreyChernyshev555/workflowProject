package com.achernyshev.workflowservice.workflow;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class WorkflowTaskEvent {
    private final UUID workflowId;
    private final int stepIndex;
    private final String workflowName;
    private final String stepName;
}

