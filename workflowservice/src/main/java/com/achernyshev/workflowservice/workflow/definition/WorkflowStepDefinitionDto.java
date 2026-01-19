package com.achernyshev.workflowservice.workflow.definition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowStepDefinitionDto {

    private String id;
    private String stepKey;
    private String name;
    private int stepIndex;
    private String type;
}