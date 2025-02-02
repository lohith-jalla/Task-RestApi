package com.Lohith.Task.Services.impl;

import com.Lohith.Task.Repositories.TaskListRepository;
import com.Lohith.Task.Services.TaskListService;
import com.Lohith.Task.domain.entities.TaskList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceimpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceimpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null!= taskList.getId()){
            throw new IllegalArgumentException("Task list already has an ID!");
        }
        if(null == taskList.getTitle() || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task list title cannot be empty!");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskListById(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    @Transactional
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(null == taskList.getId()){
            throw new IllegalArgumentException("Task list does not have an ID!");
        }

        if(!Objects.equals(taskList.getId(),taskListId)){
            throw new IllegalArgumentException("Attempting to change taskList ID , it is not permitted");
        }

        TaskList existingTaskList=taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("Task list does not exist"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);

    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}
