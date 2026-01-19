package com.achernyshev.workflowservice.workflow.definition;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/definitions")
@RequiredArgsConstructor
public class WorkflowDefinitionController {

    private final WorkflowDefinitionRepository workflowDefinitionRepository;

    private final WorkflowDefinitionMapper mapper;

    @GetMapping
    public List<WorkflowDefinitionDto> getAll() {
        return workflowDefinitionRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public WorkflowDefinitionDto getById(@PathVariable String id) {
        var definitionOptional = workflowDefinitionRepository.findById(id);
        return definitionOptional.map(mapper::toDto).orElse(null);
    }

    @PostMapping
    public WorkflowDefinitionDto create(
            @RequestBody WorkflowDefinitionDto dto
    ) {
        var entity = mapper.toEntity(dto);

        if (entity.getSteps() != null) {
            entity.getSteps().forEach(step ->
                    step.setWorkflowDefinition(entity)
            );
        }

        return mapper.toDto(workflowDefinitionRepository.save(entity));
    }

    @PutMapping("/{id}")
    public WorkflowDefinitionDto update(
            @PathVariable String id,
            @RequestBody WorkflowDefinitionDto dto
    ) {
        var entity = mapper.toEntity(dto);
        entity.setId(id);

        if (entity.getSteps() != null) {
            entity.getSteps().forEach(step ->
                    step.setWorkflowDefinition(entity)
            );
        }

        return mapper.toDto(workflowDefinitionRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        workflowDefinitionRepository.deleteById(id);
    }
}


