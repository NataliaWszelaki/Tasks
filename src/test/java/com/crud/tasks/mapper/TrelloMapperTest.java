package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void mapToBoards() {

        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1qa", "Books to read", false));
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("123", "Board Books", trelloListDto));

        //When
        List<TrelloBoard> trelloBoard = trelloMapper.mapToBoards(trelloBoardDto);

        //Then
        assertEquals(trelloBoardDto.get(0).getName(), trelloBoard.get(0).getName());
        assertEquals(trelloBoardDto.get(0).getLists().get(0).getName(), trelloBoard.get(0).getLists().get(0).getName());
        assertEquals(trelloBoardDto.size(), trelloBoard.size());
    }

    @Test
    void mapToBoardsDto() {

        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("asd", "Tasks to do for Christmas", false));
        trelloLists.add(new TrelloList("1sd", "Tasks to do before Christmas", true));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("123", "Christmas", trelloLists));


        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(1, trelloBoardDtos.size());
        assertEquals(2, trelloBoardDtos.get(0).getLists().size());
        assertEquals("Tasks to do before Christmas", trelloBoardDtos.get(0).getLists().get(1).getName());
        assertEquals("Christmas", trelloBoardDtos.get(0).getName());

    }

    @Test
    void mapToList() {

        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("123", "To Do", false));
        trelloListDtos.add(new TrelloListDto("qwe", "Done", true));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertEquals(2, trelloLists.size());
        assertTrue(trelloLists.get(1).isClosed());
        assertEquals("To Do", trelloLists.get(0).getName());

    }

    @Test
    void mapToListDto() {

        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("12", "Done", false));

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(1, trelloListDtos.size());
        assertFalse(trelloListDtos.get(0).isClosed());
        assertEquals(trelloLists.get(0).getId(), trelloListDtos.get(0).getId());
    }

    @Test
    void mapToCardDto() {

        //Given
        TrelloCard trelloCard = new TrelloCard("Gift", "For someone", "top", "1234qwer");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertNotNull(trelloCardDto);
        assertEquals("Gift", trelloCardDto.getName());
        assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
    }

    @Test
    void mapToCard() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Christmas", "Christmas tree ornaments", "bottom", "12");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals("Christmas tree ornaments", trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }
}