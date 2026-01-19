package com.achernyshev.workflowservice.workflow.definition;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowDefinitionDto {

    private String id;
    private String name;
    private String description;
    private List<WorkflowStepDefinitionDto> steps;
}