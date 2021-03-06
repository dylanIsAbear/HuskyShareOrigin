package com.huskyshare.backend.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Wish status :   0  FINISHED
 *                 1  ON
 */
@Entity
@Table(name = "wish")
@EntityListeners(AuditingEntityListener.class)
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uid")
    private Long uid;

    @Column(name = "wish_title", length = 50)
    private String wishTitle;

    @Column(name = "wish_content", length = 350)
    private String wishContent;

    @Column(name = "expire_date")
    private Date expiredDate;

    @OneToOne(mappedBy = "wish",  cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Address address;

    @CreatedDate
    @Column(name = "post_time")
    private Date createdTime;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private User user;

    @OneToMany(mappedBy = "wish", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
    private List<WishReply> replys; //OneToMany

    @Column(name = "wish_status")
    private Integer status;

    @Column(name = "deleted")
    private boolean deleted;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
//    @ManyToMany
//    private List<Tag> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWishTitle() {
        return wishTitle;
    }

    public void setWishTitle(String wishTitle) {
        this.wishTitle = wishTitle;
    }

    public String getWishContent() {
        return wishContent;
    }

    public void setWishContent(String wishContent) {
        this.wishContent = wishContent;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WishReply> getReplys() {
        return replys;
    }

    public void setReplys(List<WishReply> replys) {
        this.replys = replys;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
