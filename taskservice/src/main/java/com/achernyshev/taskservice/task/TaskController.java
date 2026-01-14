package com.achernyshev.taskservice.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable UUID id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody Task updatedTask) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setType(updatedTask.getType());
                    existingTask.setStatus(updatedTask.getStatus());
                    existingTask.setWorkflowId(updatedTask.getWorkflowId());
                    existingTask.setCurrentStepIndex(updatedTask.getCurrentStepIndex());
                    existingTask.setAssigneeId(updatedTask.getAssigneeId());
                    existingTask.setPayload(updatedTask.getPayload());
                    existingTask.setCreatedAt(updatedTask.getCreatedAt());
                    existingTask.setCompletedAt(updatedTask.getCompletedAt());

                    Task saved = taskRepository.save(existingTask);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
