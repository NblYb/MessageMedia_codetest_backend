package com.codingtest.message.mappers;

import com.codingtest.message.dtos.account.AccountGetDto;
import com.codingtest.message.dtos.account.AccountPostDto;
import com.codingtest.message.dtos.account.AccountPutDto;
import com.codingtest.message.dtos.message.AccountMessageGetDto;
import com.codingtest.message.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    AccountGetDto fromEntity(Account account);



    Account toEntity(AccountPostDto accountPostDto);

    void copy(AccountPutDto accountPutDto, @MappingTarget Account account);
}
