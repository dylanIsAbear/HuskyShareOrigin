package com.huskyshare.backend.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable{
   // 编号
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private Long id;

   // 用户名
   @Column(updatable = false, name = "user_username", nullable = false, length = 50)
   private String username;

   // 密码
   @Column(name = "password", nullable = false, length = 50)
   private String password;

   //Sex, 0 OTHER, 1 MALE, 2 FEMALE
   @Column(name = "sex", nullable = true)
   private Integer sex;

   // email
   @Column(name = "email", nullable = false, length = 50)
   private String email;

   // First Name
   @Column(name = "first_name", nullable = false, length = 50)
   private String firstName;

   // Last Name
   @Column(name = "last_name", nullable = false, length = 50)
   private String lastName;

   // Mobile
   @Column(name = "mobile", length = 16)
   private String mobile;

   // If user has confirmed email
   @Column(nullable = false)
   private boolean confirmed;

   @Column(name = "user_role")
   private String role = "USER";

   @Column(name = "user_permission")
   private String permission;

   @Column(name = "profile_id")
   private Long profile;

   @Column(name = "created_time")
   @CreatedDate
   private Date createTime;

   @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
   private List<Wish> wishList = new ArrayList<>();

   @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
   private List<Product> productList;

   @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
   private Set<Favorites> favorites;

   @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
   private List<FriendRequest> friendRequests;

   @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
   private List<Order> orderList;

   @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
   @Column(columnDefinition = "tinyint default 0", name = "emotion_list")
   private List<Emotion> emotionList;

   @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
   private List<Post> postList;

   @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
   @Nullable
   private List<MessageList> messageList; //chat list

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public List<MessageList> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageList> messageList) {
        this.messageList = messageList;
    }

    public List<Wish> getWishList() {
        return wishList;
    }

    public void setWishList(List<Wish> wishList) {
        this.wishList = wishList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Set<Favorites> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorites> favorites) {
        this.favorites = favorites;
    }

    public List<FriendRequest> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(List<FriendRequest> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Emotion> getEmotionList() {
        return emotionList;
    }

    public void setEmotionList(List<Emotion> emotionList) {
        this.emotionList = emotionList;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getProfile() {
      return profile;
   }

   public void setProfile(Long profile) {
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
