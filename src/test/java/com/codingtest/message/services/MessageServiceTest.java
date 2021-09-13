package com.codingtest.message.services;

import com.codingtest.message.dtos.account.AccountGetDto;
import com.codingtest.message.dtos.message.AccountMessageGetDto;
import com.codingtest.message.dtos.message.MessageGetDto;
import com.codingtest.message.dtos.message.MessagePostDto;
import com.codingtest.message.entities.Account;
import com.codingtest.message.entities.Message;
import com.codingtest.message.mappers.MessageMapper;
import com.codingtest.message.mappers.MessageMapperImpl;
import com.codingtest.message.repositories.AccountRepository;
import com.codingtest.message.repositories.MessageRepository;
import com.codingtest.message.utils.Utilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {MessageMapperImpl.class, Utilities.class})
public class MessageServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MessageRepository messageRepository;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private Utilities utility;

    MessageService messageService;

    @BeforeEach
    void setup() {
        messageService = new MessageService(accountRepository, messageRepository, messageMapper);
    }

    @Test
    public void shouldReturnAccountGivenUsername() {
        Account account = utility.buildAccount(1L,"testName","password");
        when(accountRepository.findByUsername("testName")).thenReturn(account);
        AccountMessageGetDto accountMessageGetDto = messageService.findMessagesByUsername("testName");
        assertNotNull(accountMessageGetDto);
        assertEquals(account.getAccountId(), accountMessageGetDto.getAccountId());
    }

    @Test
    public void shouldReturnMessageAfterAddingNewMessage() {
        Account account = utility.buildAccount(1L,"testName","password");
        MessagePostDto messagePostDto = utility.buildMessagePostDto(1L, "test", 1L);
        Message message = utility.buildMessage(1L, 1L, "test", 1L, true);
        when(accountRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(account));
        when(accountRepository.findByUsername(any())).thenReturn(account);
        when(messageRepository.save(any())).thenReturn(message);
        MessageGetDto messageGetDto = messageService.addNewMessage(messagePostDto, "testName");
        assertNotNull(messageGetDto);
        assertEquals(message.getMessageId(), messageGetDto.getMessageId());
    }

}
