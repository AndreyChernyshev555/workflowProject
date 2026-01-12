package com.achernyshev.workflowservice.workflow;

import java.time.Instant;
import java.util.UUID;

import com.achernyshev.workflowservice.workflow.status.WorkflowStatus;

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
@Table(name = "workflows")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Workflow {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String definitionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkflowStatus status;

    @Column(nullable = false)
    private int currentStepIndex;

    @Column(nullable = false)
    private String label;

    private Instant startedAt;
    private Instant endedAt;
}
