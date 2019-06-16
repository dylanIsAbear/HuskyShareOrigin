package com.huskyshare.backend.json_entity;

import com.huskyshare.backend.entity.Profile;

public class ResponseProfile {
    private Integer profile_id;

    private String picture;

    private String description;

    private String address;

    public ResponseProfile(Profile profile){
        this.profile_id = profile.getProfile_id();
        this.address = profile.getAddress();
        this.picture = profile.getPicture();
        this.description = profile.getDescription();
    }

    public Integer getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
