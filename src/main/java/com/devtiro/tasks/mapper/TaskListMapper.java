package com.devtiro.tasks.mapper;

import com.devtiro.tasks.domain.dto.TaskListDto;
import com.devtiro.tasks.domain.entity.TaskList;

public interface TaskListMapper {
	
	TaskList fromDto(TaskListDto taskListDto);
	
	TaskListDto toDto(TaskList TaskList);

}
