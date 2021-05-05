package com.test.wallet.repository;

import com.test.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Long> {

    Wallet findWalletByUserUserId(Long userId);
}
