package com.huskyshare.backend.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @CreationTimestamp
    private Timestamp createTime;

    @UpdateTimestamp
    private Timestamp modifiedTime;

    @Column(name = "product_price")
    private Integer price;

    @Column(name = "product_quantity")
    private Integer quantity;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_title")
    private String title;

    @Column(name = "product_childTitle")
    private String childTitle;

    @Column(name = "product_finished")
    private boolean finished;

    @Column(name = "product_orderConfirmed")
    private boolean confirmed;

    @Column(name = "product_orderDeclined")
    private boolean declined;

    @Column(name = "product_delayed")
    private boolean delayed;

    @Column(name = "product_availableUntil")
    private Date availableUntil;

    @Column(name = "product_notAvailableUntil")
    private Date notAvailableUntil;

    @Column(name = "product_seller_id")
    private Integer sellerId;

    @Column(name = "product_buyer_id")
    private Integer consumerId;

    @Column(name = "product_picture_url")
    private Integer pictureId;

    @Column(name = "priducy_catagory")
    private String catagory;

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChildTitle() {
        return childTitle;
    }

    public void setChildTitle(String childTitle) {
        this.childTitle = childTitle;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isDeclined() {
        return declined;
    }

    public void setDeclined(boolean declined) {
        this.declined = declined;
    }

    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }

    public Date getAvailableUntil() {
        return availableUntil;
    }

    public void setAvailableUntil(Date availableUntil) {
        this.availableUntil = availableUntil;
    }

    public Date getNotAvailableUntil() {
        return notAvailableUntil;
    }

    public void setNotAvailableUntil(Date notAvailableUntil) {
        this.notAvailableUntil = notAvailableUntil;
    }
}
