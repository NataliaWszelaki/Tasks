package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value="{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {
        Task task = service.getTaskById(taskId);
        return taskMapper.mapToTaskDto(task);
    }

    @DeleteMapping(value = "{taskId}")
    public void deleteTask(@PathVariable Long taskId) {

    }

    @PutMapping(value ="/task/{id}")
    public TaskDto updateTask(@PathVariable("id") String id, TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping("/post/{id}")
    public TaskDto createTask(@PathVariable("id") String id, TaskDto taskDto) {
        return new TaskDto(100L, "Created test title", "Test content - flowers");
    }
}
