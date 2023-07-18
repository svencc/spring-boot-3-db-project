package com.example.dbdemo.controller;

import com.example.dbdemo.dto.MessageDto;
import com.example.dbdemo.entity.Message;
import com.example.dbdemo.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class MessageControllerTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageController controllerUnderTest;


    @Test
    void testListMessages() {
        // Arrange
        Message message1 = Message.builder()
                .id(1L)
                .message("Hello")
                .build();

        Message message2 = Message.builder()
                .id(2L)
                .message("World")
                .build();

        List<Message> messages = List.of(message1, message2);

        when(messageRepository.findAll()).thenReturn(messages);

        // Act
        ResponseEntity<List<MessageDto>> response = controllerUnderTest.listMessages();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(messages.size(), response.getBody().size());
        verify(messageRepository, times(1)).findAll();
    }

    @Test
    void testCreateMessage() {
        // Arrange
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Test Message");

        Message savedMessage = new Message();
        savedMessage.setId(1L);
        savedMessage.setMessage(messageDto.getMessage());

        when(messageRepository.save(any(Message.class))).thenReturn(savedMessage);

        // Act
        ResponseEntity<MessageDto> response = controllerUnderTest.createMessage(messageDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedMessage.getId(), response.getBody().getId());
        assertEquals(savedMessage.getMessage(), response.getBody().getMessage());
        verify(messageRepository, times(1)).save(any(Message.class));
    }

}