package com.achernyshev.taskservice.task;

import java.util.UUID;

public class WorkflowTaskEventDto {
    private UUID workflowId;
    private int stepIndex;
    private String workflowName;
    private String stepName;

    // getters и setters
    public UUID getWorkflowId() { return workflowId; }
    public void setWorkflowId(UUID workflowId) { this.workflowId = workflowId; }
    public int getStepIndex() { return stepIndex; }
    public void setStepIndex(int stepIndex) { this.stepIndex = stepIndex; }
    public String getWorkflowName() { return workflowName; }
    public void setWorkflowName(String workflowName) { this.workflowName = workflowName; }
    public String getStepName() { return stepName; }
    public void setStepName(String stepName) { this.stepName = stepName; }
}