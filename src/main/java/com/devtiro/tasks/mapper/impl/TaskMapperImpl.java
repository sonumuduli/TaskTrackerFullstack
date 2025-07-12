package com.devtiro.tasks.mapper.impl;

import org.springframework.stereotype.Component;

import com.devtiro.tasks.domain.dto.TaskDto;
import com.devtiro.tasks.domain.entity.Task;
import com.devtiro.tasks.mapper.TaskMapper;

@Component
public class TaskMapperImpl implements TaskMapper  {

	@Override
	public Task fromDto(TaskDto taskDto) {
		return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
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
                task.getPriority(),
                task.getStatus()
        );
	}

}