package com.codingtest.message.controllers;

import com.codingtest.message.dtos.message.AccountMessageGetDto;
import com.codingtest.message.dtos.message.MessageGetDto;
import com.codingtest.message.dtos.message.MessagePostDto;
import com.codingtest.message.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    @Autowired
    private final MessageService messageService;

    @GetMapping("/{username}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<AccountMessageGetDto> getMessagesByUsername(@PathVariable String username){
        return ResponseEntity.ok(messageService.findMessagesByUsername(username));
    }

    @PostMapping("/{username}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<MessageGetDto> addNewMessage(@RequestBody MessagePostDto messagePostDto,
                                                       @PathVariable String username) {
        return ResponseEntity.ok(messageService.addNewMessage(messagePostDto, username));
    }
}
