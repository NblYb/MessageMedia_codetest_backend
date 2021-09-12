package com.codingtest.message.dtos.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageGetDto {
    private Long messageId;
    private Integer destinationNumber;
    private String message;
    private Integer messageTime;
    private Boolean messageStatus;
}
