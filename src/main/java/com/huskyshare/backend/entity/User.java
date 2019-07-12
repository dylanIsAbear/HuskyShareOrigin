package com.huskyshare.backend.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
public class User implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (isConfirmed() != user.isConfirmed()) return false;
        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        if (getMobile() != null ? !getMobile().equals(user.getMobile()) : user.getMobile() != null) return false;
        if (getRole() != null ? !getRole().equals(user.getRole()) : user.getRole() != null) return false;
        if (getPermission() != null ? !getPermission().equals(user.getPermission()) : user.getPermission() != null)
            return false;
        if (getProfile() != null ? !getProfile().equals(user.getProfile()) : user.getProfile() != null) return false;
        if (getCreateTime() != null ? !getCreateTime().equals(user.getCreateTime()) : user.getCreateTime() != null)
            return false;
        if (getWishList() != null ? !getWishList().equals(user.getWishList()) : user.getWishList() != null)
            return false;
        if (getProductList() != null ? !getProductList().equals(user.getProductList()) : user.getProductList() != null)
            return false;
        if (getFavorites() != null ? !getFavorites().equals(user.getFavorites()) : user.getFavorites() != null)
            return false;
        if (getFriendRequests() != null ? !getFriendRequests().equals(user.getFriendRequests()) : user.getFriendRequests() != null)
            return false;
        if (getOrderList() != null ? !getOrderList().equals(user.getOrderList()) : user.getOrderList() != null)
            return false;
        if (getEmotionList() != null ? !getEmotionList().equals(user.getEmotionList()) : user.getEmotionList() != null)
            return false;
        return getPostList() != null ? getPostList().equals(user.getPostList()) : user.getPostList() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getMobile() != null ? getMobile().hashCode() : 0);
        result = 31 * result + (isConfirmed() ? 1 : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getPermission() != null ? getPermission().hashCode() : 0);
        result = 31 * result + (getProfile() != null ? getProfile().hashCode() : 0);
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        result = 31 * result + (getWishList() != null ? getWishList().hashCode() : 0);
        result = 31 * result + (getProductList() != null ? getProductList().hashCode() : 0);
        result = 31 * result + (getFavorites() != null ? getFavorites().hashCode() : 0);
        result = 31 * result + (getFriendRequests() != null ? getFriendRequests().hashCode() : 0);
        result = 31 * result + (getOrderList() != null ? getOrderList().hashCode() : 0);
        result = 31 * result + (getEmotionList() != null ? getEmotionList().hashCode() : 0);
        result = 31 * result + (getPostList() != null ? getPostList().hashCode() : 0);
        return result;
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
