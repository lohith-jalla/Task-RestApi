package com.Lohith.Task.Mappers;

import com.Lohith.Task.domain.entities.TaskList;
import com.Lohith.Task.domain.entities.dto.TaskListDto;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
