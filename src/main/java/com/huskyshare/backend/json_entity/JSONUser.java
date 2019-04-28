package com.huskyshare.backend.json_entity;

import com.huskyshare.backend.entity.User;

import java.io.Serializable;

public class JSONUser implements Serializable {
    private String name;
    private int id;
    private boolean confirmed;

    public JSONUser(User user){
        this.name = user.getUsername();
        this.confirmed = user.isConfirmed();
        this.id = user.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
