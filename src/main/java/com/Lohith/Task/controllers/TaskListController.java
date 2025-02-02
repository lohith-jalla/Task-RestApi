package com.Lohith.Task.controllers;

import com.Lohith.Task.Mappers.TaskListMapper;
import com.Lohith.Task.Services.TaskListService;
import com.Lohith.Task.domain.entities.TaskList;
import com.Lohith.Task.domain.entities.dto.TaskListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="/task-lists")
public class TaskListController {

    private final  TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists(){
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping()
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
        TaskList createdTaskList=taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);
    }

    @GetMapping(path="/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId){
        return taskListService.getTaskListById(taskListId).map(taskListMapper::toDto);
    }

    @PutMapping(path="/{task_list_id}")
    public TaskListDto updateTaskList(
            @PathVariable("task_list_id") UUID taskListID,
            @RequestBody TaskListDto taskListDto
    ){
        TaskList updatedTaskList=taskListService.updateTaskList(taskListID, taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(updatedTaskList);
    }

    @DeleteMapping(path="/{task_list_id}")
    public void deleteTaskList(
            @PathVariable("task_list_id") UUID taskListID
    ){
        taskListService.deleteTaskList(taskListID);
    }


}

















