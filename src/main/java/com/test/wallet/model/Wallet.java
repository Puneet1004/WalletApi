package com.test.wallet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;


@Entity
public class Wallet {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private long id;

    private long balance;

    private Date updatedOn;

    private boolean active;

    private Date createdOn;

    @JsonIgnore
    @OneToMany(mappedBy = "wallet")
    private List<TransactionHistory> transactionHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "wallet1")
    private List<TransactionHistory> transactionHistories1;

    @OneToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User user;

    public Wallet() {
    }



    public Wallet(Long id, Long balance, Date updatedOn) {
        this.id = id;
        this.balance = balance;
        this.updatedOn = updatedOn;
    }

    public Wallet(Long balance, Date updatedOn, boolean active, Date createdOn, User user) {
        this.balance = balance;
        this.updatedOn = updatedOn;
        this.active = active;
        this.createdOn = createdOn;
        this.user = user;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<TransactionHistory> getTransactionHistories1() {
        return transactionHistories1;
    }

    public void setTransactionHistories1(List<TransactionHistory> transactionHistories1) {
        this.transactionHistories1 = transactionHistories1;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }


    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn() {
        this.updatedOn = Calendar.getInstance().getTime();

    }

    public List<TransactionHistory> getTransactionHistories() {
        return transactionHistories;
    }

    public void setTransactionHistories(List<TransactionHistory> transactionHistories) {
        this.transactionHistories = transactionHistories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

