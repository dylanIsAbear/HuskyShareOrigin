package com.huskyshare.backend.entity;

import com.huskyshare.backend.entity.types.TypeEnum;

import javax.persistence.*;

/**
 * Tag type: 1  Wish
 *           2  Product
 *           3  Post
 *           4  Emotion
 * SQL: select t from T t where t.type is ?1 and t.content is ?2
 *      select t from T t where t.type is ?1 and t.uid is ?2 and t.content=?3
 */
@Entity
@Table(name = "user_tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer uid;    //ID that used this tag

    @Column(name = "tag_content")
    private String content;

    @Column(name = "tag_type")
    private TypeEnum type;

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
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
