package com.Lohith.Task.controllers;


import com.Lohith.Task.Mappers.TaskMapper;
import com.Lohith.Task.Services.TaskService;
import com.Lohith.Task.domain.entities.Task;
import com.Lohith.Task.domain.entities.dto.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="/task-lists/{task_list_id}/tasks")
public class TasksController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;


    public TasksController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto>  listTasks(@PathVariable("task_list_id")UUID taskListId) {
        return taskService.listTasks(taskListId).stream()
                .map(taskMapper::toDto)
                .toList();

    }

    @PostMapping()
    public TaskDto createTask(@PathVariable("task_list_id")UUID taskListId, @RequestBody TaskDto taskDto) {
        Task createdTask=taskService.createTask(taskListId,taskMapper.fromDto(taskDto));
        return taskMapper.toDto(createdTask);
    }

    @GetMapping(path="{task_id}")
    public Optional<TaskDto> getTAsk(
            @PathVariable("task_list_id")UUID taskListId,
            @PathVariable("task_id")UUID taskId
    ) {
       return taskService.getTask(taskListId,taskId).map(taskMapper::toDto);
    }

    @PutMapping(path="/{task_id}")
    public TaskDto updateTask(
            @PathVariable("task_list_id")UUID taskListId,
            @PathVariable("task_id")UUID taskId,
            @RequestBody TaskDto taskDto
    ){
        Task updatedTask=taskService.updateTask(
                taskListId,
                taskId,
                taskMapper.fromDto(taskDto)
        );
        return taskMapper.toDto(updatedTask);
    }

    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(
            @PathVariable("task_list_id")UUID taskListId,
            @PathVariable("task_id")UUID taskId
    ){
        taskService.deleteTask(taskListId,taskId);
    }
}
