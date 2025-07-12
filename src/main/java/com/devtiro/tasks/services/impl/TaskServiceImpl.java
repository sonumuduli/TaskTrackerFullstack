package com.devtiro.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.devtiro.tasks.domain.entity.Task;
import com.devtiro.tasks.domain.entity.TaskList;
import com.devtiro.tasks.domain.entity.TaskPriority;
import com.devtiro.tasks.domain.entity.TaskStatus;
import com.devtiro.tasks.repository.TaskListRepository;
import com.devtiro.tasks.repository.TaskRepository;
import com.devtiro.tasks.services.TaskService;
@Service
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final TaskListRepository taskListRepository;
	public TaskServiceImpl(TaskRepository taskRepository,TaskListRepository taskListRepository) {
		super();
		this.taskRepository = taskRepository;
		this.taskListRepository=taskListRepository;
	}
	@Override
	public List<Task> listTask(UUID taskListId) {
		
		return taskRepository.findByTaskListId(taskListId);
	}
	@Override
	public Task createTask(UUID taskListId, Task task) {
		
		if(null != task.getId()) {
            throw new IllegalArgumentException("Task list already has an ID!");
        }
		if(null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task list title must be present!");
        }
		
		TaskPriority taskPriority =Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
		
		TaskStatus taskStatus=TaskStatus.OPEN; 
		
		TaskList existingTaskList =taskListRepository.findById(taskListId)
				.orElseThrow(()->new IllegalArgumentException("taskList not found"));
		
		
		LocalDateTime now = LocalDateTime.now();
		Task taskToAbove= new Task(
				null,
				task.getTitle(),
				task.getDescription(),
				task.getDueDate(),
				taskStatus,
				taskPriority,
				existingTaskList,
				now,
				now
			); 
		return taskRepository.save(taskToAbove);
			
	}
	@Override
	public Optional<Task> getTask(UUID taskListId, UUID taskId) {
		
		return taskRepository.findByTaskListIdAndId(taskListId,taskId);
	}
	@Override
	public Task updateTask(UUID taskListId, UUID taskId, Task task) {
		if(null==task.getId())
		{
			throw new IllegalArgumentException("task must hava an id");
		}
		
		if(Objects.equals(taskId,task.getId())) {
			throw new IllegalArgumentException("taskId not match");
		}
		if(null==task.getPriority())
		{
			throw new IllegalArgumentException("task must have a valid priority");
		}
		if(null==task.getStatus())
		{
			throw new IllegalArgumentException("task must have a valid Status");
		}
		Task existingTask= taskRepository.findByTaskListIdAndId(taskListId,taskId)
				.orElseThrow(()->new IllegalArgumentException("task not found"));  
		
		existingTask.setTitle(task.getTitle());
		existingTask.setDescription(task.getDescription());
		existingTask.setDueDate(task.getDueDate());
		existingTask.setPriority(task.getPriority());
		existingTask.setStatus(task.getStatus());
		existingTask.setUpdated(LocalDateTime.now());
		 
		
		return taskRepository.save(existingTask);
		
	}
	
	@Override
	public void deleteTask(UUID taskListId, UUID taskId) {
		taskRepository.deleteByTaskListIdAndId(taskListId,taskId);
		
	}		

}
