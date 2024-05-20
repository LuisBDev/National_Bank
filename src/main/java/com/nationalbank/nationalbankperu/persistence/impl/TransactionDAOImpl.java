package com.nationalbank.nationalbankperu.persistence.impl;

import com.nationalbank.nationalbankperu.model.Transaction;
import com.nationalbank.nationalbankperu.persistence.ITransactionDAO;
import com.nationalbank.nationalbankperu.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionDAOImpl implements ITransactionDAO {

    @Autowired
    private ITransactionRepository ITransactionRepository;

    @Override
    public List<Transaction> findAll() {
        return ITransactionRepository.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return ITransactionRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Transaction transaction) {
        ITransactionRepository.save(transaction);
    }

    @Override
    public void deleteById(Long id) {
        ITransactionRepository.deleteById(id);
    }


}
