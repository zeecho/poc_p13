package com.openclassrooms.yourcaryourway.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Message")
@Data
public class Message {
    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, name = "sent_at")
    private Date sentAt;

    @Column(nullable = false, name = "is_read")
    private Boolean isRead;
}
