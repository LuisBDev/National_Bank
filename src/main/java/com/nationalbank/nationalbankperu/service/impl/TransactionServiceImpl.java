package com.nationalbank.nationalbankperu.service.impl;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.Transaction;
import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.persistence.IBankAccountDAO;
import com.nationalbank.nationalbankperu.persistence.ITransactionDAO;
import com.nationalbank.nationalbankperu.service.ITransactionService;
import com.nationalbank.nationalbankperu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private ITransactionDAO transactionDAO;

    @Autowired
    private IBankAccountDAO bankAccountDAO;


    @Override
    public List<Transaction> findAll() {
        return transactionDAO.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return transactionDAO.findById(id);
    }

    @Override
    public void save(Transaction transaction) {
        transactionDAO.save(transaction);
    }

    @Override
    public void deleteById(Long id) {
        transactionDAO.deleteById(id);
    }

    @Transactional
    @Override
    public String performTransaction(Long id, Transaction transaction)
    {

        BankAccount fromAccount = bankAccountDAO.findByAccountNumber(transaction.getFromAccount());
        BankAccount toAccount = bankAccountDAO.findByAccountNumber(transaction.getToAccount());

        String msg = "";

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("One or both accounts do not exist!");
        }

        if (fromAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient funds!");
        }


        if (fromAccount.getUser().getId().equals(id))
        {
            fromAccount.setBalance(fromAccount.getBalance().subtract(transaction.getAmount()));
            toAccount.setBalance(toAccount.getBalance().add(transaction.getAmount()));
            bankAccountDAO.save(fromAccount);
            bankAccountDAO.save(toAccount);
            transaction.setTransactionDate(LocalDateTime.now());
            transactionDAO.save(transaction);
            msg = "Transaction performed successfully!";
        }
        else
        {
            msg = "You are not authorized to perform this transaction!";
        }

        return msg;
    }
}
