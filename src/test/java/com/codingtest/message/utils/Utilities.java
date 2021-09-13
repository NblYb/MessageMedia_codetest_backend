package com.codingtest.message.utils;

import com.codingtest.message.dtos.account.AccountGetDto;
import com.codingtest.message.dtos.account.AccountPostDto;
import com.codingtest.message.dtos.account.AccountPutDto;
import com.codingtest.message.dtos.message.AccountMessageGetDto;
import com.codingtest.message.dtos.message.MessageGetDto;
import com.codingtest.message.dtos.message.MessagePostDto;
import com.codingtest.message.entities.Account;
import com.codingtest.message.entities.Authority;
import com.codingtest.message.entities.Message;

import java.util.Set;

public class Utilities {

    public AccountGetDto buildAccountGetDto(Long accountId,
                                            String username,
                                            String encodedPassword){
        AccountGetDto accountGetDto = new AccountGetDto();
        accountGetDto.setAccountId(accountId);
        accountGetDto.setUsername(username);
        accountGetDto.setEncodedPassword(encodedPassword);
        return accountGetDto;
    }

    public Account buildAccount(Long accountId,
                                 String username,
                                 String encodedPassword) {
        Account account = new Account();
        account.setAccountId(accountId);
        account.setUsername(username);
        account.setEncodedPassword(encodedPassword);
        return account;
    }

    public AccountPostDto buildAccountPostDto(String username,
                                              String encodedPassword){
        AccountPostDto accountPostDto = new AccountPostDto();
        accountPostDto.setUsername(username);
        accountPostDto.setEncodedPassword(encodedPassword);
        return accountPostDto;
    }

    public AccountPutDto buildAccountPutDto(String username, String encodedPassword){
        AccountPutDto accountPutDto = new AccountPutDto();
        accountPutDto.setUsername(username);
        accountPutDto.setEncodedPassword(encodedPassword);
        return accountPutDto;
    }

    public Authority buildAuthority(Long authorityId,
                                    String role){
        Authority authority = new Authority();
        authority.setAuthorityId(authorityId);
        authority.setAuthorityRole(role);
        return authority;
    }

    public MessageGetDto buildMessageGetDto(Long messageId,
                                            Long destinationNumber,
                                            String message,
                                            Long messageTime,
                                            Boolean messageStatus) {
        MessageGetDto messageGetDto = new MessageGetDto();
        messageGetDto.setMessageId(messageId);
        messageGetDto.setDestinationNumber(destinationNumber);
        messageGetDto.setMessage(message);
        messageGetDto.setMessageTime(messageTime);
        messageGetDto.setMessageStatus(messageStatus);
        return messageGetDto;
    }

    public Message buildMessage(Long messageId,
                                Long destinationNumber,
                                String message,
                                Long messageTime,
                                Boolean messageStatus) {
        Message builtMessage = new Message();
        builtMessage.setMessageId(messageId);
        builtMessage.setDestinationNumber(destinationNumber);
        builtMessage.setMessage(message);
        builtMessage.setMessageTime(messageTime);
        builtMessage.setMessageStatus(messageStatus);
        return builtMessage;
    }

    public AccountMessageGetDto buildAccountMessageGetDto(Long accountId, String username, Set<MessageGetDto> message) {
        AccountMessageGetDto accountMessageGetDto = new AccountMessageGetDto();
        accountMessageGetDto.setAccountId(accountId);
        accountMessageGetDto.setUsername(username);
        accountMessageGetDto.setMessages(message);
        return accountMessageGetDto;
    }

    public MessagePostDto buildMessagePostDto(Long destinationNumber,
                                              String message,
                                              Long messageTime) {
        MessagePostDto messagePostDto = new MessagePostDto();
        messagePostDto.setDestinationNumber(destinationNumber);
        messagePostDto.setMessage(message);
        messagePostDto.setMessageTime(messageTime);
        return messagePostDto;
    }
}
