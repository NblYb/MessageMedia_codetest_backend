package com.codingtest.message.services;

import com.codingtest.message.dtos.message.AccountMessageGetDto;
import com.codingtest.message.dtos.message.MessageGetDto;
import com.codingtest.message.dtos.message.MessagePostDto;
import com.codingtest.message.entities.Account;
import com.codingtest.message.entities.Message;
import com.codingtest.message.mappers.MessageMapper;
import com.codingtest.message.repositories.AccountRepository;
import com.codingtest.message.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final AccountRepository accountRepository;
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public AccountMessageGetDto findMessagesByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        return messageMapper.fromAccountEntity(account);
    }

    public MessageGetDto addNewMessage(MessagePostDto newMessage, String Username) {
        Message message = messageMapper.toEntity(newMessage);
        Optional<Account> destinationAccount = accountRepository.findById(newMessage.getDestinationNumber());
        if (destinationAccount.isPresent()) {
            message.setMessageStatus(true);
        } else {
            message.setMessageStatus(false);
        }
        Account sourceAccount = accountRepository.findByUsername(Username);
        message.setAccount(sourceAccount);
        return messageMapper.fromEntity(messageRepository.save(message));
    }
}
