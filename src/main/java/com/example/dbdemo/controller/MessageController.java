package com.example.dbdemo.controller;

import com.example.dbdemo.dto.MessageDto;
import com.example.dbdemo.entity.Message;
import com.example.dbdemo.mapper.MessageMapper;
import com.example.dbdemo.repository.MessageRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Tag(name = "Message")
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    @NonNull
    private final MessageRepository messageRepository;

    @Operation(
            summary = "List all messages.",
            description = "Returns all"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O.K.")
    })
    @GetMapping(path = "")
    public ResponseEntity<List<MessageDto>> listMessages() {
        log.info("Requested GET /message");

        final List<MessageDto> allMessages = messageRepository.findAll().stream()
                .map(MessageMapper.INSTANCE::toDto)
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .cacheControl(CacheControl.noCache())
                .body(allMessages);
    }

    @Operation(
            summary = "List all messages.",
            description = "Returns all"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O.K.")
    })
    @PostMapping(path = "")
    public ResponseEntity<MessageDto> createMessage(
            @RequestBody final MessageDto message
    ) {
        log.info("Requested POST /message");

        final Message newMessage = Message.builder()
                .message(message.getMessage())
                .build();
        final Message savedMessage = messageRepository.save(newMessage);

        return ResponseEntity
                .status(HttpStatus.OK)
                .cacheControl(CacheControl.noCache())
                .body(MessageMapper.INSTANCE.toDto(savedMessage));
    }

}
