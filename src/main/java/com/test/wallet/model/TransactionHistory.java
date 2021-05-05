package com.test.wallet.model;

import javax.persistence.*;

import java.util.*;


@Entity
public class TransactionHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private long id;
    private long transactionAmount;
    private Date transactionDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "walletIdFrom")
    private Wallet wallet;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "walletIdTo")
    private Wallet wallet1;


    public TransactionHistory(){}

    public TransactionHistory(long transactionAmount, Date transactionDate, Wallet wallet, Wallet wallet1) {
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.wallet = wallet;
        this.wallet1 = wallet1;
    }

    public TransactionHistory(long transactionAmount, Date transactionDate, Wallet wallet1) {
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.wallet1 = wallet1;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Wallet getWallet1() {
        return wallet1;
    }

    public void setWallet1(Wallet wallet1) {
        this.wallet1 = wallet1;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }



    public Long getTransactionID() {
        return id;
    }

    public void setTransactionID(Long transactionID) {
        this.id = transactionID;
    }


    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }


    public Wallet getUser() {
        return wallet1;
    }
}
