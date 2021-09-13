package com.codingtest.message.controllers;

import com.codingtest.message.dtos.account.AccountGetDto;
import com.codingtest.message.dtos.account.AccountPostDto;
import com.codingtest.message.dtos.account.AccountPutDto;
import com.codingtest.message.services.AccountService;
import com.codingtest.message.utils.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
@Import(AccountController.class)
@ContextConfiguration(classes = {Utilities.class})
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Utilities utilities;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAccountGivenUsernameExists() throws Exception {
        AccountGetDto accountGetDto = utilities.buildAccountGetDto(1L,
                "testAccount",
                "password");
        BDDMockito.given(accountService.findAccountByUsername("testAccount")).willReturn(accountGetDto);
        this.mockMvc.perform(get("/register/testAccount"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.accountId").value(1L))
                .andExpect(jsonPath("$.username").value("testAccount"))
                .andExpect(jsonPath("$.encodedPassword").value("password"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAccountAddingNewAccount() throws Exception {
        AccountGetDto accountGetDto = utilities.buildAccountGetDto(1L,
                "testAccount",
                "password");
        AccountPostDto accountPostDto = utilities.buildAccountPostDto("testAccount",
                "password");
        BDDMockito.given(accountService.createAccount(accountPostDto)).willReturn(accountGetDto);
        this.mockMvc.perform(post("/register").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountPostDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.accountId").value(1L))
                .andExpect(jsonPath("$.username").value("testAccount"))
                .andExpect(jsonPath("$.encodedPassword").value("password"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAccountWhenChangePassword() throws Exception {
        AccountGetDto accountGetDto = utilities.buildAccountGetDto(1L,
                "testAccount",
                "password");
        AccountPutDto accountPutDto = utilities.buildAccountPutDto("testName", "password");
        BDDMockito.given(accountService.changePassword(accountPutDto)).willReturn(accountGetDto);
        this.mockMvc.perform(put("/register").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountPutDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.accountId").value(1L))
                .andExpect(jsonPath("$.username").value("testAccount"))
                .andExpect(jsonPath("$.encodedPassword").value("password"));
    }

}
