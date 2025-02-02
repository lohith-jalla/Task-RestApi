package com.Lohith.Task.Services;

import com.Lohith.Task.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskListById(UUID id);
    TaskList updateTaskList(UUID id, TaskList taskList);
    void deleteTaskList(UUID taskListId);
}
