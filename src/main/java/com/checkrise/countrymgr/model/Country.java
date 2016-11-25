package com.checkrise.countrymgr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Country {
    @Id
    private String code;

    @Column
    private String name;
    @Column
    private BigDecimal internetUsers;
    @Column
    private BigDecimal adultLiteracyRate;

    // Default constructor for JPA
    public Country() {}

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", internetUsers=" + internetUsers +
                ", adultLiteracyRate=" + adultLiteracyRate +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInternetUsers() {
        return internetUsers;
    }

    public void setInternetUsers(BigDecimal internetUsers) {
        this.internetUsers = internetUsers;
    }

    public BigDecimal getAdultLiteracyRate() {
        return adultLiteracyRate;
    }

    public void setAdultLiteracyRate(BigDecimal adultLiteracyRate) {
        this.adultLiteracyRate = adultLiteracyRate;
    }
}
