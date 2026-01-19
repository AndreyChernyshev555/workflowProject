package com.achernyshev.workflowservice.workflow.definition;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "workflow_definitions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "workflowDefinition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("stepIndex ASC")
    private List<WorkflowStepDefinition> steps; // список шагов шаблона
}
