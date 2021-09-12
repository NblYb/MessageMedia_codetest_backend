package com.codingtest.message.dtos.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
public class AccountMessageGetDto {
    private Long accountId;
    private String username;
    private Set<MessageGetDto> messages;
}
