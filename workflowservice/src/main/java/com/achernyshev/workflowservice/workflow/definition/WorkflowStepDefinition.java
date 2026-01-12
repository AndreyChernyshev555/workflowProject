package com.achernyshev.workflowservice.workflow.definition;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "workflow_step_definitions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowStepDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String stepKey;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int stepIndex;

    private String type;

    @ManyToOne
    @JoinColumn(name = "workflow_definition_id")
    private WorkflowDefinition workflowDefinition;
}
