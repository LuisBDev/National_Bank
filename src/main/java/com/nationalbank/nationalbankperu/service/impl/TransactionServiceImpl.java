package com.nationalbank.nationalbankperu.service.impl;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.Transaction;
import com.nationalbank.nationalbankperu.persistence.IBankAccountDAO;
import com.nationalbank.nationalbankperu.persistence.ITransactionDAO;
import com.nationalbank.nationalbankperu.service.ITransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void performTransaction(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        BankAccount fromAccount = bankAccountDAO.findByAccountNumber(fromAccountNumber);
        BankAccount toAccount = bankAccountDAO.findByAccountNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("One of the accounts does not exist.");
        }

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance in the source account.");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        bankAccountDAO.save(fromAccount);
        bankAccountDAO.save(toAccount);

        Transaction transaction = Transaction.builder()
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .amount(amount)
                .transactionDate(LocalDateTime.now())
                .build();

        transactionDAO.save(transaction);
    }
}
