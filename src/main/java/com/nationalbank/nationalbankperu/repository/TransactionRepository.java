package com.nationalbank.nationalbankperu.repository;

import com.nationalbank.nationalbankperu.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
