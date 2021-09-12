package com.codingtest.message.mappers;

import com.codingtest.message.dtos.message.AccountMessageGetDto;
import com.codingtest.message.dtos.message.MessageGetDto;
import com.codingtest.message.dtos.message.MessagePostDto;
import com.codingtest.message.entities.Account;
import com.codingtest.message.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    AccountMessageGetDto fromAccountEntity(Account account);

    Message toEntity(MessagePostDto messagePostDto);

    MessageGetDto fromEntity(Message message);
}
