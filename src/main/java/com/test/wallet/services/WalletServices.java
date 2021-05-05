package com.test.wallet.services;
import com.test.wallet.model.TransactionHistory;
import com.test.wallet.model.User;
import com.test.wallet.model.Wallet;
import com.test.wallet.queries.AddBalance;
import com.test.wallet.queries.Login;
import com.test.wallet.repository.TransactionHistoryRepository;
import com.test.wallet.repository.UserRepository;
import com.test.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class WalletServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> getAllWallet() {
        return walletRepository.findAll();
    }
    public User findUser(String user){
        User u = userRepository.findByEmail(user);
        User u1 = userRepository.findByPhoneNumber(user);
        if(u==null&&u1==null) return null;
        else if(u1==null) return u;
        else return u1;
    }

    public void addWallet(User user){
        walletRepository.save(new Wallet(0L,new Date(),true,new Date(),user));
    }

    public Wallet getWallet(Login user1) {
        User user = findUser(user1.getUserName());
        if (user == null) return new Wallet(0L, 0L, new Date());
        Long id = user.getUserId();
        return walletRepository.findWalletByUserUserId(id);
    }

    public Wallet addBalance(AddBalance balance) {
        User user = findUser(balance.getUserName());
        if(user == null) return new Wallet(0L,0L,new Date());
        Long id  = user.getUserId();
        Wallet wallet = walletRepository.findWalletByUserUserId(id);
        long bal = wallet.getBalance() + balance.getBalance();
        wallet.setBalance(bal);
        wallet.setUpdatedOn();
        walletRepository.save(wallet);
        TransactionHistory transactionHistory = new TransactionHistory( balance.getBalance(), new Date(),wallet);
        transactionHistoryRepository.save(transactionHistory);
        return wallet;
    }

    public boolean deleteWallet(Login user) {
        User u = findUser(user.getUserName());
        if(u==null) return false;
        else {
            Wallet wallet = walletRepository.getOne(u.getUserId());
            wallet.setActive(false);
            walletRepository.save(wallet);
            return true;
        }
    }
}
