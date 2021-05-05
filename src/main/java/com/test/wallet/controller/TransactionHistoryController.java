package com.test.wallet.controller;

import com.test.wallet.model.TransactionHistory;
import com.test.wallet.model.Wallet;
import com.test.wallet.queries.CusTransaction;
import com.test.wallet.queries.Login;
import com.test.wallet.queries.SendBalance;
import com.test.wallet.services.TransactionHistoryServices;
import com.test.wallet.services.UserServices;
import com.test.wallet.services.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TransactionHistoryController {
    @Autowired
    private TransactionHistoryServices transactionHistoryServices;

    @Autowired
    private WalletServices walletServices;

    @Autowired
    private UserServices userServices;


    @PutMapping(value = "/sendBalance")
    public String sendBalance(@RequestBody SendBalance send){

        return transactionHistoryServices.sendBalance(send)?"Transaction Successful!!!":"Transaction Failed!!!!";
    }

    @GetMapping(value = "/allhistory")
    public List<TransactionHistory> allHistory(){
        return transactionHistoryServices.getAllHistory();
    }

    @PutMapping(value = "/history")
    public List<CusTransaction> history(@RequestBody Login login){
        return transactionHistoryServices.getHistory(login);
    }

}
