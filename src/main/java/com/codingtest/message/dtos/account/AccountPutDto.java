package com.codingtest.message.dtos.account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountPutDto {
    private String username;
    private String encodedPassword;
}
