package com.devtiro.tasks.mapper;

import com.devtiro.tasks.domain.dto.TaskDto;
import com.devtiro.tasks.domain.entity.Task;

public interface TaskMapper {
	
	Task fromDto(TaskDto taskDto);
	TaskDto toDto(Task task);

}
