package com.huskyshare.backend.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable{
   // 编号
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
   private Integer id;

   // 用户名
   @Column(updatable = false, name = "user_username", nullable = false, length = 50)
   private String username;

   // 密码
   @Column(name = "user_password", nullable = false, length = 50)
   private String password;

   // email
   @Column(name = "user_email", nullable = false, length = 50)
   private String email;

   // First Name
   @Column(name = "user_first_name", nullable = false, length = 50)
   private String firstName;

   // Last Name
   @Column(name = "user_last_name", nullable = false, length = 50)
   private String lastName;

   // Mobile
   @Column(name = "user_mobile", length = 16)
   private String mobile;

   // If user has confirmed email
   @Column(nullable = false)
   private boolean confirmed;

   @Column(name = "user_role")
   private String role = "USER";

   @Column(name = "user_permission")
   private String permission;

   @Column(name = "profile_id")
   private Integer profile;

   @Column(name = "created_time")
   private Timestamp createTime;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getProfile() {
      return profile;
   }

   public void setProfile(Integer profile) {
      this.profile = profile;
   }

   public String getPermission() {
      return permission;
   }

   public void setPermission(String permission) {
      this.permission = permission;
   }

   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public User(){

   }

   public boolean isConfirmed() {
      return confirmed;
   }

   public void setConfirmed(boolean confirmed) { this.confirmed = confirmed; }

   public String getMobile() {
      return mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
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

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public User(User user){
      this.confirmed=user.isConfirmed();
      this.email=user.getEmail();
      this.username = user.getUsername();
      this.lastName =user.getLastName();
      this.id = user.getId();
      this.password = user.getPassword();
   }

}
