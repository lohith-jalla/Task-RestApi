package com.Lohith.Task.Services.impl;

import com.Lohith.Task.Repositories.TaskListRepository;
import com.Lohith.Task.Repositories.TaskRepository;
import com.Lohith.Task.Services.TaskService;
import com.Lohith.Task.domain.entities.Task;
import com.Lohith.Task.domain.entities.TaskList;
import com.Lohith.Task.domain.entities.TaskProirity;
import com.Lohith.Task.domain.entities.TaskStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceimpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceimpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }


    @Override
    public List<Task> listTasks(UUID taskListID) {
        return taskRepository.findByTaskListId(taskListID);
    }

    @Transactional
    @Override
    public Task createTask(UUID taskListID, Task task) {
        if(null != task.getId()){
            throw new IllegalArgumentException("Task already has an ID!");
        }

        if(null == task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task title cannot be empty!");
        }

        TaskProirity taskProirity= Optional.ofNullable(task.getProirity())
                .orElse(TaskProirity.MEDIUM);

        TaskStatus taskStatus= TaskStatus.OPEN;

        TaskList taskList=taskListRepository.findById(taskListID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task list ID provided!"));

        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskProirity,
                taskList,
                now,
                now
        );
        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskID) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskID);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListID, UUID taskID, Task task) {
        if(null == task.getId()){
            throw new IllegalArgumentException("Task does not found!");
        }
        if(!Objects.equals(taskID , task.getId())){
            throw new IllegalArgumentException("Task ID cannot be changed!");
        }
        if(null == task.getProirity()){
            throw new IllegalArgumentException("Task must have a valid Priority");
        }
        if(null == task.getStatus()){
            throw new IllegalArgumentException("Task must have a valid Status");
        }
        Task existingTask= taskRepository.findByTaskListIdAndId(taskListID, taskID)
                .orElseThrow(() -> new IllegalArgumentException("Task not Found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setProirity(task.getProirity());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Override
    @Transactional
    public void deleteTask(UUID taskListID, UUID taskID) {
        taskRepository.deleteByTaskListIdAndId(taskListID,taskID);
    }
}
