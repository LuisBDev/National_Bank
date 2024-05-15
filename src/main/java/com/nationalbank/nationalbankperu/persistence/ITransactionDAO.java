package com.nationalbank.nationalbankperu.persistence;

import com.nationalbank.nationalbankperu.model.Transaction;

import java.util.List;

public interface ITransactionDAO {
    List<Transaction> findAll();

    Transaction findById(Long id);

    void save(Transaction transaction);

    void deleteById(Long id);
}
