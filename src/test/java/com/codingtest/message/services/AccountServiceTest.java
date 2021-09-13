package com.codingtest.message.services;

import com.codingtest.message.dtos.account.AccountGetDto;
import com.codingtest.message.dtos.account.AccountPostDto;
import com.codingtest.message.dtos.account.AccountPutDto;
import com.codingtest.message.entities.Account;
import com.codingtest.message.entities.Authority;
import com.codingtest.message.mappers.AccountMapper;
import com.codingtest.message.mappers.AccountMapperImpl;
import com.codingtest.message.repositories.AccountRepository;
import com.codingtest.message.repositories.AuthorityRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AccountMapperImpl.class, Utilities.class})
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Mock
    private AuthorityRepository authorityRepository;

    @Autowired
    private Utilities utility;

    AccountService accountService;

    @BeforeEach
    void setup() {
        accountService = new AccountService(accountRepository, accountMapper, authorityRepository);
    }

    @Test
    public void shouldReturnAccountGivenUsername() {
        Account account1 = utility.buildAccount(1L,"testName","password");
        when(accountRepository.findByUsername("testName")).thenReturn(account1);
        AccountGetDto returnedAccount = accountService.findAccountByUsername("testName");
        assertNotNull(returnedAccount);
        assertEquals(account1.getAccountId(), returnedAccount.getAccountId());
    }

    @Test
    public void shouldReturnAccountAfterCreateNewAccount() {
        AccountPostDto account = utility.buildAccountPostDto("testName","password");
        Account accountEntity = utility.buildAccount(1L, "testName","password");
        Authority authority = utility.buildAuthority(1L, "Role_User");
        when(accountRepository.save(any())).thenReturn(accountEntity);
        when(authorityRepository.getById(any())).thenReturn(authority);
        AccountGetDto returnedAccount = accountService.createAccount(account);
        assertNotNull(returnedAccount);
        assertEquals(accountEntity.getAccountId(), returnedAccount.getAccountId());
    }

    @Test
    public void shouldReturnAccountAfterChangingPassword() {
        AccountPutDto account = utility.buildAccountPutDto("testName", "password");
        Account oldAccountEntity = utility.buildAccount(1L,"testName","password");
        Account newAccountEntity = utility.buildAccount(1L, "testName","new_password");
        Authority authority = utility.buildAuthority(1L, "Role_User");
        when(accountRepository.findByUsername("testName")).thenReturn(oldAccountEntity);
        when(accountRepository.save(any())).thenReturn(newAccountEntity);
        AccountGetDto returnedAccount = accountService.changePassword(account);
        assertNotNull(returnedAccount);
        assertEquals("new_password", returnedAccount.getEncodedPassword());
    }

    @Test
    public void NumberOfAccountsShouldBeLessAfterDeletion() {
        accountService.deleteAccount(306L);
        verify(accountRepository).deleteById(306L);
    }
}
