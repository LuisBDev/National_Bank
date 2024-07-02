package com.nationalbank.nationalbankperu.service;

import com.nationalbank.nationalbankperu.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService {
    List<Transaction> findAll();

    Transaction findById(Long id);

    void save(Transaction transaction);

    void deleteById(Long id);

    void performTransaction(Long id, Transaction transaction);
}
