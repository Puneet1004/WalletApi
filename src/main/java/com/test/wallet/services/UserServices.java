package com.test.wallet.services;
import com.test.wallet.model.User;
import com.test.wallet.queries.Login;
import com.test.wallet.queries.Registration;
import com.test.wallet.repository.TransactionHistoryRepository;
import com.test.wallet.repository.UserRepository;
import com.test.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    private WalletRepository walletRepository;

    public List<User> showAll() {
        List<User> l = new ArrayList<>();
        userRepository.findAll().forEach(l::add);
        return l;
    }

    public User findUser(String user){
        User u = userRepository.findByEmail(user);
        User u1 = userRepository.findByPhoneNumber(user);
        if(u==null&&u1==null) return null;
        else if(u==null) return u1;
        else return u;
    }
    public User addUser(Registration user) {
        if(findUser(user.getEmail())!=null || findUser(user.getPhoneNumber())!=null){
            return null;
        }
        User u = new User(user.getPassword(),user.getFirstName(),user.getLastName(),user.getEmail(), user.getPhoneNumber(), true,new Date(),new Date());
        userRepository.save(u);
        return userRepository.findByEmail(user.getEmail());
    }

    public boolean login(Login user) {
        User u = findUser(user.getUserName());
        if(u==null) return false;
        return u.getPassword().equals(user.getPassword());
    }

    public User updateUser(Registration user){
        if(findUser(user.getEmail())==null || findUser(user.getPhoneNumber())==null){
            return null;
        }
        User u = userRepository.findByEmail(user.getEmail());
        return userRepository.save(new User(u.getUserId(),user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), true, new Date(),u.getCreatedOn()));
    }

    public boolean deleteUser(Login user) {
        User u = findUser(user.getUserName());
        if(u==null) return false;
        u.setActive(false);
        userRepository.save(u);
        return true;
        }
}
