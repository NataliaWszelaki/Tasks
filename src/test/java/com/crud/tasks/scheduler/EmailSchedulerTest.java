package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailSchedulerTest {

    private static final String SUBJECT = "Tasks: Once a day email";

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private AdminConfig adminConfig;

    @Test
    void shouldSendInformationEmail() {

        //Given
        when(taskRepository.count()).thenReturn(123L);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(adminConfig, times(1)).getAdminMail();
    }
}