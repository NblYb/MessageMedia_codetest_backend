package com.codingtest.message.dtos.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessagePostDto {
    private Long destinationNumber;
    private String message;
    private Integer messageTime;
}
