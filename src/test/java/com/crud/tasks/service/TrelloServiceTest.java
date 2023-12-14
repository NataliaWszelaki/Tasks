package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    private static final String SUBJECT = "Tasks: New Trello Card";

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    void shouldCreateTrelloCard() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Name", "Description", "top", "123qwe");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(new CreatedTrelloCardDto("123","New card", "https://test.test"));
        when(adminConfig.getAdminMail()).thenReturn("test@test.test");

        //When
        CreatedTrelloCardDto newCard = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals("123", newCard.getId());
        assertEquals("New card", newCard.getName());
        assertEquals("https://test.test", newCard.getShortUrl());
        assertNull(newCard.getBadgesDto());
    }
}