package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    void shouldSaveTask() {

        //Given
        Task task = new Task(1L, "Title", "Content");

        //When
        dbService.saveTask(task);

        //Then
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void shouldGetAllTaskEmptyList() {

        //Given
        List<Task> taskListEmpty = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(taskListEmpty);

        //When
        List<Task> emptyList = dbService.getAllTasks();

        //Then
        assertEquals(new ArrayList<>(), emptyList);
    }

    @Test
    void shouldNotGetTask() {

        //Given
        when(taskRepository.findById(123L)).thenReturn(Optional.empty());


        //When & Then
        assertThrows(TaskNotFoundException.class, () -> dbService.getTask(123L));
    }

    @Test
    void shouldDeleteTask() throws TaskNotFoundException {

        //When
        dbService.deleteTask(123L);

        //Then
        verify(taskRepository, times(1)).deleteById(123L);

    }

}