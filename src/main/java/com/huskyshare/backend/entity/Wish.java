package com.huskyshare.backend.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "wish")
@EntityListeners(AuditingEntityListener.class)
public class Wish {
    private String wishTitle;

    private String wishContent;

    private Date createdTime;

    private List<Reply> replys;
}
