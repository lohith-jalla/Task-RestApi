package com.Lohith.Task.Services;

import com.Lohith.Task.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListID);
    Task createTask(UUID taskListID, Task task);
    Optional<Task> getTask(UUID taskListId,UUID taskID);
    Task updateTask(UUID taskListID, UUID taskID, Task task);
    void deleteTask(UUID taskListID, UUID taskID);
}
