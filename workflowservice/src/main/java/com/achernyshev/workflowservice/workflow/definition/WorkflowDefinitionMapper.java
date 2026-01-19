package com.achernyshev.workflowservice.workflow.definition;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = WorkflowStepDefinitionMapper.class
)
public interface WorkflowDefinitionMapper {

    WorkflowDefinitionDto toDto(WorkflowDefinition entity);

    WorkflowDefinition toEntity(WorkflowDefinitionDto dto);
}
