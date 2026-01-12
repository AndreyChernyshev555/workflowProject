package com.achernyshev.workflowservice.workflow.step;

import java.time.Instant;
import java.util.UUID;

import com.achernyshev.workflowservice.workflow.step.status.WorkflowStepStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "workflow_steps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowStep {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String definitionId;

    @Column(nullable = false)
    private UUID workflowId;

    @Column(nullable = false)
    private String stepKey;

    @Column(nullable = false)
    private int stepIndex;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkflowStepStatus status;

    private Instant startedAt;
    private Instant endedAt;

}