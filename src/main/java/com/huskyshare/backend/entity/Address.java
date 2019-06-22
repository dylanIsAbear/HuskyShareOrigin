package com.huskyshare.backend.entity;

import com.huskyshare.backend.entity.types.TypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private Wish wish;

    @Column(name = "address_owner")
    @Enumerated(EnumType.ORDINAL)
    private TypeEnum addrType;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "notes", nullable = true, length = 60)
    private String notes;

    public Wish getWish() {
        return wish;
    }

    public void setWish(Wish wish) {
        this.wish = wish;
    }

    public TypeEnum getAddrType() {
        return addrType;
    }

    public void setAddrType(TypeEnum addrType) {
        this.addrType = addrType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
