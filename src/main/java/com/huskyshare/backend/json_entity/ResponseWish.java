package com.huskyshare.backend.json_entity;

import com.huskyshare.backend.entity.Wish;

import java.util.Date;
import java.util.List;

public class ResponseWish {
    private Long id;
    private String content;
    private String title;
    private ResponseAddress address;
    private List<ResponseWishReply> replyList;
    private Date createDate;

    public ResponseWish(Wish wish){
        id = wish.getId();
        content = wish.getWishContent();
        title = wish.getWishTitle();
        createDate = wish.getCreatedTime();
    }

    public List<ResponseWishReply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ResponseWishReply> replyList) {
        this.replyList = replyList;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ResponseAddress getAddress() {
        return address;
    }

    public void setAddress(ResponseAddress address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
