package com.Lohith.Task.Mappers.impl;

import com.Lohith.Task.Mappers.TaskMapper;
import com.Lohith.Task.domain.entities.Task;
import com.Lohith.Task.domain.entities.dto.TaskDto;
import org.springframework.stereotype.Component;


@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
            taskDto.id(),
            taskDto.title(),
            taskDto.description(),
            taskDto.dueDate(),
            taskDto.status(),
            taskDto.proirity(),
            null,
            null,
            null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getProirity(),
                task.getStatus()
        );
    }
}
