package com.test.wallet.services;
import com.test.wallet.model.TransactionHistory;
import com.test.wallet.model.User;
import com.test.wallet.model.Wallet;
import com.test.wallet.queries.CusTransaction;
import com.test.wallet.queries.Login;
import com.test.wallet.queries.SendBalance;
import com.test.wallet.repository.TransactionHistoryRepository;
import com.test.wallet.repository.UserRepository;
import com.test.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class TransactionHistoryServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    private WalletRepository walletRepository;



    public User findUser(String  user){
        User u = userRepository.findByEmail(user);
        User u1 = userRepository.findByPhoneNumber(user);
        if(u==null&&u1==null) return null;
        else if(u==null) return u1;
        else return u;
    }

    public boolean sendBalance(SendBalance sendBalance) {
        // find user by given username ie email or phone number
        User u = findUser(sendBalance.getUserName());
        // if not found return empty(dummy) wallet
        if(u==null) return false;
        //find wallet by userid
        Wallet wallet = walletRepository.findWalletByUserUserId(u.getUserId());
        // deduct balance from sender
        long balance  = wallet.getBalance() - sendBalance.getTransactionAmount();
        wallet.setBalance(balance);
        wallet.setUpdatedOn();
        //save updated wallet
        walletRepository.save(wallet);
        //find receivers
        User user = findUser(sendBalance.getSendToDetail());
        if(user==null) return false;
        //find wallet
        Wallet wallet1 = walletRepository.findWalletByUserUserId(user.getUserId()); ;
        // add sending balance to current wallet
        balance  = wallet1.getBalance() + sendBalance.getTransactionAmount();
        wallet1.setBalance(balance);
        wallet1.setUpdatedOn();
        //save updated wallet
        walletRepository.save(wallet1);
        transactionHistoryRepository.save(new TransactionHistory(sendBalance.getTransactionAmount(),new Date(),wallet,wallet1));
        return true;
    }

    public List<TransactionHistory> getAllHistory() {
        return transactionHistoryRepository.findAll();
    }

    public List<CusTransaction> getHistory(Login login) {
        User u = findUser(login.getUserName());
        List<TransactionHistory> transactionHistories = transactionHistoryRepository.findTransactionHistoryByWalletId(walletRepository.findWalletByUserUserId(u.getUserId()).getId());
        List<TransactionHistory> transactionHistories2 = transactionHistoryRepository.findTransactionHistoryByWallet1Id(walletRepository.findWalletByUserUserId(u.getUserId()).getId());
        List<CusTransaction> list = new ArrayList<>();
        for(TransactionHistory h : transactionHistories){
            CusTransaction cusTransaction = new CusTransaction(h.getTransactionID(),h.getTransactionAmount(),h.getTransactionDate(),h.getWallet().getId(),h.getWallet1().getId(),"DEBIT");
            list.add(cusTransaction);
        }
        for(TransactionHistory h : transactionHistories2){
            long id =0L;
            if(h.getWallet()!=null){
                id = h.getWallet().getId();
            }
            CusTransaction cusTransaction = new CusTransaction(h.getTransactionID(),h.getTransactionAmount(),h.getTransactionDate(),id,h.getWallet1().getId(),"CREDIT");
            list.add(cusTransaction);
        }
        list.sort((l1,l2)->l2.getTransactionDate().compareTo(l1.getTransactionDate()));
//        walletRepository.findWalletByUserUserId(u.getUserId()).getTransactionHistories1();
        return list;
    }
}
