package com.anuksha.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.anuksha.wallet.entity.Transaction;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    List<Transaction> findBySenderIdOrReceiverId(Long senderId, Long receiverId);
}
