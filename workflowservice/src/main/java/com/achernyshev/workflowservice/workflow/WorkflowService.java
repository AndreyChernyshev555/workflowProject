package com.achernyshev.workflowservice.workflow;

import com.achernyshev.workflowservice.workflow.definition.WorkflowDefinition;
import com.achernyshev.workflowservice.workflow.definition.WorkflowDefinitionRepository;
import com.achernyshev.workflowservice.workflow.definition.WorkflowStepDefinition;
import com.achernyshev.workflowservice.workflow.status.WorkflowStatus;
import com.achernyshev.workflowservice.workflow.step.WorkflowStep;
import com.achernyshev.workflowservice.workflow.step.WorkflowStepRepository;
import com.achernyshev.workflowservice.workflow.step.status.WorkflowStepStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkflowService {

    private final WorkflowDefinitionRepository definitionRepository;

    private final WorkflowRepository workflowRepository;

    private final WorkflowStepRepository workflowStepRepository;

    private final WorkflowEventsProducer workflowEventsProducer;

    public WorkflowStep createWorkflowStep(WorkflowStepDefinition stepDef, Workflow workflow) {
        WorkflowStep step = new WorkflowStep();

        step.setWorkflowId(workflow.getId());
        step.setStepKey(stepDef.getStepKey());
        step.setStepIndex(stepDef.getStepIndex());
        step.setStatus(WorkflowStepStatus.ACTIVE);

        return step;
    }

    public Workflow createWorkflow(String definitionId, String label) {
        var definitionOptional = definitionRepository.findById(definitionId);
        WorkflowDefinition definition = null;
        if (definitionOptional.isPresent())  definition = definitionOptional.get();
        else return null;

        Workflow workflow = new Workflow();
        workflow.setDefinitionId(definitionId);
        workflow.setStatus(WorkflowStatus.RUNNING);
        workflow.setCurrentStepIndex(0);
        workflow.setLabel(label);
        workflow = workflowRepository.save(workflow);

        WorkflowStepDefinition stepDef = definition.getSteps().getFirst();

        WorkflowStep step = createWorkflowStep(stepDef, workflow);
        workflowStepRepository.save(step);
        workflowEventsProducer.createTaskForStep(
                workflow.getId(),
                workflow.getCurrentStepIndex(),
                workflow.getLabel(),
                stepDef.getName()
        );
        return workflow;
    }

    public void setNextStep(Workflow workflow) {
        var definitionOptional = definitionRepository.findById(workflow.getDefinitionId());
        WorkflowDefinition definition = null;
        if (definitionOptional.isPresent())  definition = definitionOptional.get();
        else return;

        WorkflowStep madeStep = workflowStepRepository.findByWorkflowIdAndStepIndex(
                workflow.getId(),
                workflow.getCurrentStepIndex()
        );

        madeStep.setStatus(WorkflowStepStatus.COMPLETED);
        workflowStepRepository.save(madeStep);

        if ((workflow.getCurrentStepIndex() + 1) == definition.getSteps().size()) {
            workflow.setStatus(WorkflowStatus.COMPLETED);
            workflowRepository.save(workflow);
            return;
        }

        final int newStepIndex = workflow.getCurrentStepIndex() + 1;
        workflow.setCurrentStepIndex(newStepIndex);

        WorkflowStepDefinition stepDef = definition.getSteps().get(newStepIndex);
        WorkflowStep step = createWorkflowStep(stepDef, workflow);
        workflowStepRepository.save(step);
        workflowEventsProducer.createTaskForStep(
                workflow.getId(),
                workflow.getCurrentStepIndex(),
                workflow.getLabel(),
                stepDef.getName()
        );
    }
}
