package com.Lohith.Task.domain.entities.dto;

import com.Lohith.Task.domain.entities.TaskProirity;
import com.Lohith.Task.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskProirity proirity,
        TaskStatus status
) {
}
