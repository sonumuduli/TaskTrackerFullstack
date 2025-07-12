package com.devtiro.tasks.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devtiro.tasks.domain.entity.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList, UUID> {

}
