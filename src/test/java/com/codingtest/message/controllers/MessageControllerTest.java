package com.codingtest.message.controllers;

import com.codingtest.message.dtos.account.AccountGetDto;
import com.codingtest.message.dtos.message.AccountMessageGetDto;
import com.codingtest.message.dtos.message.MessageGetDto;
import com.codingtest.message.dtos.message.MessagePostDto;
import com.codingtest.message.entities.Account;
import com.codingtest.message.entities.Message;
import com.codingtest.message.services.MessageService;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
@Import(MessageController.class)
@ContextConfiguration(classes = {Utilities.class})
public class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Utilities utilities;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnMessageGivenUsername() throws Exception {

        MessageGetDto messageGetDto = utilities.buildMessageGetDto(1L,
                1L,
                "test",
                1L,
                true);
        Set<MessageGetDto> messageSet = new HashSet<>();
        messageSet.add(messageGetDto);
        AccountMessageGetDto accountMessage = utilities.buildAccountMessageGetDto(1L,
                "testAccount",
                messageSet);
        BDDMockito.given(messageService.findMessagesByUsername("testAccount")).willReturn(accountMessage);
        this.mockMvc.perform(get("/message/testAccount").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.accountId").value(1L))
                .andExpect(jsonPath("$.username").value("testAccount"))
                .andExpect(jsonPath("$.messages").exists());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnMessageAfterCreatingNewMessage() throws Exception {

        MessageGetDto messageGetDto = utilities.buildMessageGetDto(1L,
                1L,
                "test",
                1L,
                true);
        MessagePostDto messagePostDto = utilities.buildMessagePostDto(1L,
                "test",
                1L);
        BDDMockito.given(messageService.addNewMessage(messagePostDto, "testAccount")).willReturn(messageGetDto);
        this.mockMvc.perform(post("/message/testAccount").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(messagePostDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.messageId").value(1L))
                .andExpect(jsonPath("$.destinationNumber").value(1L))
                .andExpect(jsonPath("$.message").value("test"))
                .andExpect(jsonPath("$.messageTime").value(1L))
                .andExpect(jsonPath("$.messageStatus").value(true));
    }
}
