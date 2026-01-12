package com.achernyshev.workflowservice.workflow.definition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkflowDefinitionRepository extends JpaRepository<WorkflowDefinition, UUID> {
    WorkflowDefinition findById(String definitionId);
}

