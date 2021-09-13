package com.codingtest.message.services;

import com.codingtest.message.dtos.account.*;
import com.codingtest.message.entities.Account;
import com.codingtest.message.entities.Authority;
import com.codingtest.message.mappers.AccountMapper;
import com.codingtest.message.repositories.AccountRepository;
import com.codingtest.message.repositories.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AuthorityRepository authorityRepository;

    public AccountGetDto createAccount(AccountPostDto accountPostDto){
        Account account = accountMapper.toEntity(accountPostDto);
        account.setAuthorities(getUserAuthority());
        Account dbAccount = accountRepository.save(account);
        return accountMapper.fromEntity(dbAccount);
    }

    public AccountGetDto findAccountByUsername(String username){
        Account account = accountRepository.findByUsername(username);
        return accountMapper.fromEntity(account);
    }

    public AccountGetDto changePassword(AccountPutDto accountPutDto) {
        Account account = new Account();
        AccountGetDto previousAccount = findAccountByUsername(accountPutDto.getUsername());
        account.setAccountId(previousAccount.getAccountId());
        account.setUsername(previousAccount.getUsername());
        account.setEncodedPassword(accountPutDto.getEncodedPassword());
        return accountMapper.fromEntity(accountRepository.save(account));
    }


    public Set<Authority> getUserAuthority(){
        return Stream.of(authorityRepository.getById(1L)).collect(Collectors.toSet());
    }

    public void deleteAccount(Long accountId){
        accountRepository.deleteById(accountId);
    }
}
