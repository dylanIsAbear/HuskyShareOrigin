package com.huskyshare.backend.json_entity;

import com.huskyshare.backend.entity.User;

import java.io.Serializable;
import java.util.Date;

public class Jsonable<E> implements Serializable {
    private E object;
    private String msg;
    private String status;
    private Date date;
    private String description;

    public E getE() {
        return object;
    }

    public void setE(E e) {
        this.object = e;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
