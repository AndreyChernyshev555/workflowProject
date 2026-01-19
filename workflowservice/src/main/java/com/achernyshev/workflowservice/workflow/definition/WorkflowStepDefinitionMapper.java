package com.achernyshev.workflowservice.workflow.definition;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkflowStepDefinitionMapper {

    WorkflowStepDefinitionDto toDto(WorkflowStepDefinition entity);

    @Mapping(target = "workflowDefinition", ignore = true)
    WorkflowStepDefinition toEntity(WorkflowStepDefinitionDto dto);
}