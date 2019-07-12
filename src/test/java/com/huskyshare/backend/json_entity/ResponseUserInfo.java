package com.huskyshare.backend.json_entity;

import com.huskyshare.backend.entity.User;

import java.util.List;

public class ResponseUserInfo {
    private Long id;
    private String username;
    private boolean confirmed;
    private Long profileId;

    public ResponseUserInfo(User user){
        id = user.getId();
        username = user.getUsername();
        confirmed = user.isConfirmed();
        profileId = user.getProfile();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

}
