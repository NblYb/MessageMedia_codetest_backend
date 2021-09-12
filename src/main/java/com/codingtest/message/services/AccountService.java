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
    private final AuthorityRepository authorityRepository;
    private final AccountMapper accountMapper;

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

    public List<AccountGetDto> getAllAccounts(){
        return accountRepository.findAll().stream()
                .map(accountMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public String findUsernameById(Long accountId){
        Account account = accountRepository.getById(accountId);
        return account.getUsername();
    }

    public AccountGetDto changePassword(String userName, String encodedPassowrd) {
        Account account = new Account();
        AccountGetDto previousAccount = findAccountByUsername(userName);
        account.setAccountId(previousAccount.getAccountId());
        account.setUsername(previousAccount.getUsername());
        account.setEncodedPassword(encodedPassowrd);
        return accountMapper.fromEntity(accountRepository.save(account));
    }

    public Set<Authority> findAuthoritiesByAccountId(Long accountId){
        Account account = accountRepository.getById(accountId);
        return account.getAuthorities();
    }

    public Set<Authority> getUserAuthority(){
        return Stream.of(authorityRepository.getById(1L)).collect(Collectors.toSet());
    }

    public void deleteAccount(Long accountId){
        accountRepository.deleteById(accountId);
    }
}
