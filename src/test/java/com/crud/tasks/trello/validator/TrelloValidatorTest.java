package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloValidatorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    void shouldValidateTrelloBoards() {

        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("12", "test", null));

        //When
        List<TrelloBoard> validatedList = trelloValidator.validateTrelloBoards(trelloBoardList);

        //Then
        assertEquals(0, validatedList.size());
    }
}