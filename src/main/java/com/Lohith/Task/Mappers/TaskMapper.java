package com.Lohith.Task.Mappers;

import com.Lohith.Task.domain.entities.Task;
import com.Lohith.Task.domain.entities.dto.TaskDto;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}
