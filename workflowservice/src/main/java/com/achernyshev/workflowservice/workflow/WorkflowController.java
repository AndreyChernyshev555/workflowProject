package com.achernyshev.workflowservice.workflow;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workflows")
@RequiredArgsConstructor
public class WorkflowController {

    private final WorkflowRepository workflowRepository;

    private final WorkflowService workflowService;

    @PostMapping
    public ResponseEntity<UUID> createWorkflow(@RequestBody WorkflowRequest request) {
        Workflow saved = workflowService.createWorkflow(request.getDefinitionId(), request.getLabel());
        return new ResponseEntity<>(saved.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Workflow>> getAllWorkflows() {
        return ResponseEntity.ok(workflowRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workflow> getWorkflowById(@PathVariable UUID id) {
        return workflowRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workflow> updateWorkflow(@PathVariable UUID id, @RequestBody Workflow updated) {
        return workflowRepository.findById(id)
                .map(existing -> {
                    existing.setDefinitionId(updated.getDefinitionId());
                    existing.setStatus(updated.getStatus());
                    existing.setCurrentStepIndex(updated.getCurrentStepIndex());
                    existing.setStartedAt(updated.getStartedAt());
                    existing.setEndedAt(updated.getEndedAt());
                    Workflow saved = workflowRepository.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkflow(@PathVariable UUID id) {
        if (workflowRepository.existsById(id)) {
            workflowRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

