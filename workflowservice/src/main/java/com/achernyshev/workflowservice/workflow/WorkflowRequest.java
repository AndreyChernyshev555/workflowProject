package com.achernyshev.workflowservice.workflow;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class WorkflowRequest {
    private final String definitionId;
    private final String label;
}

