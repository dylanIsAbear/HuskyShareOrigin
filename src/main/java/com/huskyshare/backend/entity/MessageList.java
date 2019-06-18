package com.huskyshare.backend.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * A user's all chating objects
 * User -> {MessageList, ...}
 * MessageList -> {ChatHistory, ...}
 * ChatHistory -> {Chat, ...}
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "message_list")
public class MessageList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "to_user_id")
    private Long to;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private User user;

    @OneToMany(mappedBy = "messageList", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
    private List<ChatHistory> chatHistory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ChatHistory> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(List<ChatHistory> chatHistory) {
        this.chatHistory = chatHistory;
    }
}
