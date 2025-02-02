package com.Lohith.Task.domain.entities.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {

}
