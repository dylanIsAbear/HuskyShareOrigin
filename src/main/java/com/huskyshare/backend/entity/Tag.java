package com.huskyshare.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer uid;

    @Column(name = "tag_content")
    private String content;

    @Column(name = "tag_type")
    private Integer type;

    public Integer getType() {
        return type;
    }



    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
