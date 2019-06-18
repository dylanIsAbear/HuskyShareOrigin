package com.huskyshare.backend.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "user_emotion")
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emotion_id")
    private Long id;

    @Column(name = "emotion_content", length = 500, nullable = false)
    private String content;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private User user;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createdTime;

    @OneToMany(mappedBy = "emotion", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
    private List<User> subscribedUser; //Users that will receive this emotion

//  @OneToMany(mappedBy = "emotion", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
//  private List<Tag> keywords;

    @OneToMany(mappedBy = "emotion", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
    private List<Picture> pictures;

    @OneToMany(mappedBy = "emotion", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
    private List<Reply> replyList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public List<User> getSubscribedUser() {
        return subscribedUser;
    }

    public void setSubscribedUser(List<User> subscribedUser) {
        this.subscribedUser = subscribedUser;
    }

//    public List<Tag> getKeywords() {
//        return keywords;
//    }
//
//    public void setKeywords(List<Tag> keywords) {
//        this.keywords = keywords;
//    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
}
