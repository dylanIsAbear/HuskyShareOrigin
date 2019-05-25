package com.huskyshare.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "validation_code")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "registry_id")
    private Integer id;
    @Column(name = "v_email")
    private String email;
    @Column(name = "v_code")
    private String code;

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
