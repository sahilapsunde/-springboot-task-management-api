package com.sahil.taskapi.service;
import com.sahil.taskapi.model.Task;
import com.sahil.taskapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "task-events";
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public Task findById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found: " + id));
    }
    public Task create(Task task) {
        Task saved = taskRepository.save(task);
        kafkaTemplate.send(TOPIC, "TASK_CREATED:" + saved.getId() + ":" + saved.getTitle());
        return saved;
    }
    public Task update(Long id, Task updatedTask) {
        Task existing = findById(id);
        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setStatus(updatedTask.getStatus());
        existing.setAssignedTo(updatedTask.getAssignedTo());
        Task saved = taskRepository.save(existing);
        kafkaTemplate.send(TOPIC, "TASK_UPDATED:" + saved.getId());
        return saved;
    }
    public void delete(Long id) {
        taskRepository.deleteById(id);
        kafkaTemplate.send(TOPIC, "TASK_DELETED:" + id);
    }
}
