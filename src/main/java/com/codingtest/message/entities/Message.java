package com.codingtest.message.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long messageId;

    @Column(name = "destination_number", nullable = false)
    private Long destinationNumber;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "message_time", nullable = false)
    private Long messageTime;

    @Column(name = "message_status", nullable = false)
    private Boolean messageStatus;

    @ManyToOne()
    @JoinColumn(name="account_id")
    private Account account;
}
