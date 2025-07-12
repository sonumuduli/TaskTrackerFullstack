package com.devtiro.tasks.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devtiro.tasks.domain.dto.TaskDto;
import com.devtiro.tasks.domain.entity.Task;
import com.devtiro.tasks.mapper.TaskMapper;
import com.devtiro.tasks.services.TaskService;

@RestController
@RequestMapping(path="/task-lists/{task_list_id}/tasks")
public class TaskController {
	
	private final TaskService taskService;
	private final TaskMapper taskMapper;
	public TaskController(TaskService taskService, TaskMapper taskMapper) {
		super();
		this.taskService = taskService;
		this.taskMapper = taskMapper;
	}
	
	@GetMapping
	public List<TaskDto> ListTask(@PathVariable("task_list_id") UUID taskListId){
		
		return taskService.listTask(taskListId)
				             .stream()
				             .map(taskMapper:: toDto)
				             .toList();
		
	}
	
	 @PostMapping
	    public TaskDto createTask(
	            @PathVariable("task_list_id") UUID taskListId,
	            @RequestBody TaskDto taskDto){
	        Task createdTask = taskService.createTask(
	                taskListId,
	                taskMapper.fromDto(taskDto)
	        );
	        return taskMapper.toDto(createdTask);
	    }
	 
	 @GetMapping(path="/{task_id}")
	public Optional<TaskDto> getTask(@PathVariable("task_list_id")UUID taskListId,
			                         @PathVariable("task_id") UUID taskId){
		 
		 return taskService.getTask(taskListId,taskId).map(taskMapper::toDto);
		 
	 }

	 
	 @PutMapping(path="/{task_id}")
	 public TaskDto updateTask(@PathVariable("task_list_id")UUID taskListId,
             @PathVariable("task_id") UUID taskId,@RequestBody TaskDto taskDto){
            	 
            	 Task updatedTask= taskService.updateTask(
            			 taskListId,
            			 taskId,
            			 taskMapper.fromDto(taskDto));
            	 
            	 return taskMapper.toDto(updatedTask);
             }
	 @DeleteMapping(path="/{task_id}")
	 public void deleteTask(@PathVariable("task_list_id")UUID taskListId,
             @PathVariable("task_id") UUID taskId) {
		 
		 taskService.deleteTask(taskListId,taskId);
		 
	 }
	
	}
