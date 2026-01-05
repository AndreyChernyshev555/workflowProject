package com.achernyshev.workflowservice.workflow.step;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workflow-steps")
public class WorkflowStepController {

    private final WorkflowStepRepository stepRepository;

    public WorkflowStepController(WorkflowStepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    @PostMapping
    public ResponseEntity<WorkflowStep> createStep(@RequestBody WorkflowStep step) {
        WorkflowStep saved = stepRepository.save(step);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkflowStep>> getAllSteps() {
        return ResponseEntity.ok(stepRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowStep> getStepById(@PathVariable UUID id) {
        return stepRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/workflow/{workflowId}")
    public ResponseEntity<List<WorkflowStep>> getStepsByWorkflow(@PathVariable UUID workflowId) {
        return ResponseEntity.ok(stepRepository.findByWorkflowId(workflowId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkflowStep> updateStep(@PathVariable UUID id, @RequestBody WorkflowStep updated) {
        return stepRepository.findById(id)
                .map(existing -> {
                    existing.setStepKey(updated.getStepKey());
                    existing.setStatus(updated.getStatus());
                    existing.setStartedAt(updated.getStartedAt());
                    existing.setEndedAt(updated.getEndedAt());
                    existing.setWorkflowId(updated.getWorkflowId());
                    WorkflowStep saved = stepRepository.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStep(@PathVariable UUID id) {
        if (stepRepository.existsById(id)) {
            stepRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
