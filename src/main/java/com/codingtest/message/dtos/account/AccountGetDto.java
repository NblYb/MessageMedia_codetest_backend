package com.codingtest.message.dtos.account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountGetDto {
    private Long accountId;
    private String username;
    private String encodedPassword;
}
