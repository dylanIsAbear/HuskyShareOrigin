package com.huskyshare.backend.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
@EntityListeners(AuditingEntityListener.class)
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String filename;

    @Column(name = "host_id")
    private Long host;

    /**
     * type 0  Previous Profile Picture
     *      1  Current Profile Picture
     *      2  Product Picture
     *      3  Wish Picture
     *      4  Post Picture
     */
    @Column(name = "type")
    private Integer type;

    public Long getHost() {
        return host;
    }

    public void setHost(Long host) {
        this.host = host;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
