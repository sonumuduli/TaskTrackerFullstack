package com.devtiro.tasks.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.devtiro.tasks.domain.entity.TaskList;

public interface TaskListService {
	List<TaskList> listTaskList();
	TaskList createTaskList(TaskList taskList);
	Optional<TaskList> getTaskList(UUID id);
	TaskList updateTaskList(UUID taskListId,TaskList taskList );
	void deleteTaskList(UUID taskListId);
}
