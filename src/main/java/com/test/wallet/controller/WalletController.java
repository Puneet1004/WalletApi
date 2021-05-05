package com.test.wallet.controller;

import com.test.wallet.model.Wallet;
import com.test.wallet.queries.AddBalance;
import com.test.wallet.queries.Login;
import com.test.wallet.services.TransactionHistoryServices;
import com.test.wallet.services.UserServices;
import com.test.wallet.services.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class WalletController {

    @Autowired
    private TransactionHistoryServices transactionHistoryServices;

    @Autowired
    private WalletServices walletServices;

    @Autowired
    private UserServices userServices;

    @RequestMapping(value = "/wallets")
    public List<Wallet> walletList(){
        return walletServices.getAllWallet();
    }

    @DeleteMapping(value = "/wallet/delete")
    public  String deleteWallet(@RequestBody Login user){
        return walletServices.deleteWallet(user)?"Deleted Successfully!!!":"Wallet Not Found!!!";
    }

    @PutMapping("/walletBalance")
    public Wallet checkBalance(@RequestBody Login user){
        boolean check = userServices.login(user);
        return walletServices.getWallet(user);
    }

    @PutMapping("/addBalance")
    public Wallet addBalance(@RequestBody AddBalance balance){
       return walletServices.addBalance(balance);
    }
}
