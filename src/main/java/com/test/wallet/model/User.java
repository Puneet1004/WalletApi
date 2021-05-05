package com.test.wallet.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class User {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long userId;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "f_name",nullable = true)
    private String fName;

    @Column(name = "l_name",nullable = true)
    private String lName;

    @Column(name = "email",unique = true,nullable = false)
    private String email;

    @Column(name= "phone_number",unique = true,length = 10)
    private String phoneNumber;

    @Column(name = "is_active",nullable = false)
    private boolean active = true;

    private Date createdOn;

    private Date updatedOn;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wallet wallet;

    public User(){

    }



    public User(Long userId, String password, String fName, String lName, String email, String phoneNumber, boolean active,Date date,Date date1) {
        this.userId = userId;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.updatedOn = date;
        this.createdOn = date1;
    }

    public User(String password, String fName, String lName, String email, String phoneNumber, boolean active,Date date,Date date1) {
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.createdOn = date;
        this.updatedOn = date1;
    }



    public Long getUserId() {
        return userId;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }



    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setUserId(Long userId){
        this.userId =userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }


    @Override
    public String toString() {
        return "name : "+ fName +" "+lName+
                "\nEmail : "+ email+
                " \nphoneNumber : "+phoneNumber;
    }
}
