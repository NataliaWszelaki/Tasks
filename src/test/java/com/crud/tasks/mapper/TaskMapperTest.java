package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTask() {

        //Given
        TaskDto taskDto = new TaskDto(123L, "Buy the Christmas tree", "ASAP");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());
    }

    @Test
    void mapToTaskDto() {

        //Given
        Task task = new Task(1L, "Christmas Tree", "Buy");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(1L, taskDto.getId());
        assertEquals("Christmas Tree", taskDto.getTitle());
        assertEquals("Buy", taskDto.getContent());
    }

    @Test
    void shouldMapToDtoTaskListEmpty() {

        //Given
        List<Task> taskList = new ArrayList<>();

        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(0, taskDtos.size());
    }
}