package com.achernyshev.workflowservice.workflow;

import com.achernyshev.workflowservice.workflow.definition.WorkflowDefinition;
import com.achernyshev.workflowservice.workflow.definition.WorkflowDefinitionRepository;
import com.achernyshev.workflowservice.workflow.definition.WorkflowStepDefinition;
import com.achernyshev.workflowservice.workflow.status.WorkflowStatus;
import com.achernyshev.workflowservice.workflow.step.WorkflowStep;
import com.achernyshev.workflowservice.workflow.step.WorkflowStepRepository;
import com.achernyshev.workflowservice.workflow.step.status.WorkflowStepStatus;

public class WorkflowService {

    private WorkflowDefinitionRepository definitionRepository;

    private WorkflowRepository workflowRepository;

    private WorkflowStepRepository workflowStepRepository;

    public Workflow createWorkflow(String definitionId) {
        WorkflowDefinition definition = definitionRepository.findById(definitionId);

        Workflow workflow = new Workflow();
        workflow.setDefinitionId(definitionId);
        workflow.setStatus(WorkflowStatus.CREATED);
        workflow.setCurrentStepIndex(0);
        workflow = workflowRepository.save(workflow);

        WorkflowStepDefinition stepDef = definition.getSteps().getFirst();

        WorkflowStep step = new WorkflowStep();
        step.setWorkflowId(workflow.getId());
        step.setStepKey(stepDef.getStepKey());
        step.setStepIndex(stepDef.getStepIndex());
        step.setStatus(WorkflowStepStatus.ACTIVE);
        workflowStepRepository.save(step);

        return workflow;
    }
}
