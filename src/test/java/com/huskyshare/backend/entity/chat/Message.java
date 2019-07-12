package com.huskyshare.backend.entity.chat;


import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Table("messages")
public class Message {

    @PrimaryKeyColumn(name = "username", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String username;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PrimaryKeyColumn(name = "chatRoomId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private Long chatRoomId;

    @PrimaryKeyColumn(name = "date", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    @CreationTimestamp
    private Timestamp date;

    private String sendUser;
    private String receiveUser;
    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message1 = (Message) o;

        if (getUsername() != null ? !getUsername().equals(message1.getUsername()) : message1.getUsername() != null)
            return false;
        if (getId() != null ? !getId().equals(message1.getId()) : message1.getId() != null) return false;
        if (getChatRoomId() != null ? !getChatRoomId().equals(message1.getChatRoomId()) : message1.getChatRoomId() != null)
            return false;
        if (getDate() != null ? !getDate().equals(message1.getDate()) : message1.getDate() != null) return false;
        if (getSendUser() != null ? !getSendUser().equals(message1.getSendUser()) : message1.getSendUser() != null)
            return false;
        if (getReceiveUser() != null ? !getReceiveUser().equals(message1.getReceiveUser()) : message1.getReceiveUser() != null)
            return false;
        return getMessage() != null ? getMessage().equals(message1.getMessage()) : message1.getMessage() == null;
    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getChatRoomId() != null ? getChatRoomId().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getSendUser() != null ? getSendUser().hashCode() : 0);
        result = 31 * result + (getReceiveUser() != null ? getReceiveUser().hashCode() : 0);
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        return result;
    }
}
