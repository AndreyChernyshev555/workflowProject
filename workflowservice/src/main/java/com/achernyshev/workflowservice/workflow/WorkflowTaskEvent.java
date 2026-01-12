package com.achernyshev.workflowservice.workflow;

import java.util.UUID;

public record WorkflowTaskEvent(UUID workflowId, int stepIndex){ }

