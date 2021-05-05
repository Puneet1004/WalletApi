package com.test.wallet.repository;

import com.test.wallet.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    List<TransactionHistory> findTransactionHistoryByWalletId(long id);
    List<TransactionHistory> findTransactionHistoryByWallet1Id(long id);
}
