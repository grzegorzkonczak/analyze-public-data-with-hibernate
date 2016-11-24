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
}
