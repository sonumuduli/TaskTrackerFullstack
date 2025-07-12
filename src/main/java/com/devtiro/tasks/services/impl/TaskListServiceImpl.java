package com.devtiro.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.devtiro.tasks.domain.entity.TaskList;
import com.devtiro.tasks.repository.TaskListRepository;
import com.devtiro.tasks.services.TaskListService;
@Service
public class TaskListServiceImpl implements TaskListService {

	private final TaskListRepository taskListRepository;
	
	public TaskListServiceImpl(TaskListRepository taskListRepository) {
		
		this.taskListRepository = taskListRepository;
	}

	@Override
	public List<TaskList> listTaskList() {
		
		return taskListRepository.findAll();
	}

	@Override
	public TaskList createTaskList(TaskList taskList) {
		 if(null != taskList.getId()) {
	            throw new IllegalArgumentException("Task list already has an ID!");
	        }
	        if(null == taskList.getTitle() || taskList.getTitle().isBlank()) {
	            throw new IllegalArgumentException("Task list title must be present!");
	        }

	        LocalDateTime now = LocalDateTime.now();
	        return taskListRepository.save(new TaskList(
	                null,
	                taskList.getTitle(),
	                taskList.getDescription(),
	                null,
	                now,
	                now
	        ));
	}

	@Override
	public Optional<TaskList> getTaskList(UUID id) {
		
		return taskListRepository.findById(id);
	}

	@Override
	public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
		if(null==taskList.getId()) {
			throw new IllegalArgumentException("task must hava id");
		}
		if(!Objects.equals(taskList.getId(),taskListId)) {
			throw new IllegalArgumentException("Attemptiing to change taskList id this is not permited"); 
		}
		TaskList existingTaskList =taskListRepository.findById(taskListId).orElseThrow(()->new IllegalArgumentException("taskList not found"));
		 existingTaskList.setTitle(taskList.getTitle());
	        existingTaskList.setDescription(taskList.getDescription());
	        existingTaskList.setUpdated(LocalDateTime.now());
	        return taskListRepository.save(existingTaskList);
	}

	@Override
	public void deleteTaskList(UUID taskListId) {
		taskListRepository.deleteById(taskListId);
		
	}
	

}
