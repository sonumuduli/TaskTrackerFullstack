package com.devtiro.tasks.domain.dto;

import java.util.List;
import java.util.UUID;



public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double process,
        List<TaskDto> tasks 
		) {

}
