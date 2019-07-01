package com.huskyshare.backend.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private User user;

    @OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
    private List<Post> replies;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private Post parentComment;

    @Column(name = "post_content", length = 350)
    private String content;

    @OneToMany(mappedBy = "post",  cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
    private List<Vote> votes;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "to_user")
    private Long to;

    @Column(name = "floor")
    private Integer floor;

    @CreationTimestamp
    @Column(name = "created_time")
    private Timestamp createdTime;

    public void setReplies(List<Post> replies) {
        this.replies = replies;
    }

    public Post getParentComment() {
        return parentComment;
    }

    public void setParentComment(Post parentComment) {
        this.parentComment = parentComment;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public List<Post> getReplies() {
        return replies;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
