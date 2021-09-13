package com.codingtest.message.dtos.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageGetDto {
    private Long messageId;
    private Long destinationNumber;
    private String message;
    private Long messageTime;
    private Boolean messageStatus;
}
