package com.devtiro.tasks.domain.dto;

import com.devtiro.tasks.domain.entity.TaskStatus;
import com.devtiro.tasks.domain.entity.TaskPriority;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
		UUID id,
		String title,
		String description,
		LocalDateTime dueDate,
		TaskPriority priority,
		TaskStatus status
		
		
		
		) {
	

}
