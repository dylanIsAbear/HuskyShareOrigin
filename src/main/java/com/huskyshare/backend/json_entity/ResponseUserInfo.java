package com.huskyshare.backend.json_entity;

import com.huskyshare.backend.entity.User;

import java.util.List;

public class ResponseUserInfo {
    private Integer id;
    private String username;
    private boolean confirmed;
    private Integer profileId;

    public ResponseUserInfo(User user){
        id = user.getId();
        username = user.getUsername();
        confirmed = user.isConfirmed();
        profileId = user.getProfile();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

}
