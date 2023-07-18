package com.example.dbdemo.controller;

import com.example.dbdemo.dto.MessageDto;
import com.example.dbdemo.entity.Message;
import com.example.dbdemo.repository.MessageRepository;
import com.example.dbdemo.repository.SenderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class MessageControllerTest {

    @Mock
    private SenderRepository senderRepository;
    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageController controllerUnderTest;


    @Test
    void testListMessages() {
        // Arrange
        final Message message1 = Message.builder()
                .id(1L)
                .message("Hello")
                .build();

        final Message message2 = Message.builder()
                .id(2L)
                .message("World")
                .build();

        final List<Message> messages = List.of(message1, message2);

        when(messageRepository.findAll()).thenReturn(messages);

        // Act
        final ResponseEntity<List<MessageDto>> response = controllerUnderTest.listMessages();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(messages.size(), response.getBody().size());
        verify(messageRepository, times(1)).findAll();
    }

    @Test
    void testCreateMessage() {
        // Arrange
        final MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Test Message");

        final Message savedMessage = new Message();
        savedMessage.setId(1L);
        savedMessage.setMessage(messageDto.getMessage());

        when(messageRepository.save(any(Message.class))).thenReturn(savedMessage);

        // Act
        final ResponseEntity<MessageDto> response = controllerUnderTest.createMessage(messageDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedMessage.getId(), response.getBody().getId());
        assertEquals(savedMessage.getMessage(), response.getBody().getMessage());
        verify(messageRepository, times(1)).save(any(Message.class));
    }

}