package com.nationalbank.nationalbankperu.persistence.impl;

import com.nationalbank.nationalbankperu.model.Transaction;
import com.nationalbank.nationalbankperu.persistence.ITransactionDAO;
import com.nationalbank.nationalbankperu.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionDAOImpl implements ITransactionDAO {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
    

}
