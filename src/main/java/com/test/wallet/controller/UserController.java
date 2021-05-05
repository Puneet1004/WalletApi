package com.test.wallet.controller;
import com.test.wallet.model.User;
import com.test.wallet.queries.Login;
import com.test.wallet.queries.Registration;
//import com.test.wallet.security.Encode;
import com.test.wallet.services.TransactionHistoryServices;
import com.test.wallet.services.UserServices;
import com.test.wallet.services.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

public class UserController {

    @Autowired
    private TransactionHistoryServices transactionHistoryServices;

    @Autowired
    private WalletServices walletServices;

    @Autowired
    private UserServices userServices;

    @RequestMapping(value = "/users")
    public List<User> getAllUser(){
        return userServices.showAll();
    }

    @PutMapping("/login")
    public String userLogin(@RequestBody Login user){
        boolean check = userServices.login(user);
        if(check) {
            return "login successful";
        }
        else return "Wrong Credentials!!";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody Login user){
        if(userServices.deleteUser(user)){
            return "Account Deactivated!!";
        }
        else return "Account Not Found";
    }

    @PutMapping(value = "/updateUser")
    public String updateUser(@RequestBody Registration user){
        User check = userServices.updateUser(user);
        if(check==null){
            return "User Not Found!!";
        }
        return "Update Successful";
    }
    @PostMapping( value = "/register")
    public String registration(@RequestBody Registration user){
        User check = userServices.addUser(user);
        if(check==null){
            return "User Already Exist";
        }
        walletServices.addWallet(check);
        return "register Successful";
    }
}
