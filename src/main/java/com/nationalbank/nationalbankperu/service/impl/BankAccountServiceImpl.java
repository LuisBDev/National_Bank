package com.nationalbank.nationalbankperu.service.impl;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.persistence.IBankAccountDAO;
import com.nationalbank.nationalbankperu.service.IBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankAccountServiceImpl implements IBankAccountService {

    @Autowired
    private IBankAccountDAO bankAccountDAO;

    @Override
    @Transactional(readOnly = true)
    public List<BankAccount> findAll() {
        return bankAccountDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BankAccount findById(Long id) {
        return bankAccountDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(BankAccount bankAccount) {
        bankAccountDAO.save(bankAccount);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bankAccountDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankAccount> findByUser(User user) {
        return bankAccountDAO.findByUser(user);
    }

//    @Override
//    @Transactional
//    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
//        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
//            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
//        }
//
//        BankAccount fromAccount = bankAccountDAO.findById(fromAccountId);
//        BankAccount toAccount = bankAccountDAO.findById(toAccountId);
//
//        if (fromAccount == null) {
//            throw new IllegalArgumentException("Source account does not exist.");
//        }
//
//        if (toAccount == null) {
//            throw new IllegalArgumentException("Destination account does not exist.");
//        }
//
//        if (fromAccount.getBalance().compareTo(amount) < 0) {
//            throw new IllegalArgumentException("Insufficient balance in the source account.");
//        }
//
//        // Debitar el monto de la cuenta de origen
//        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
//
//        // Acreditar el monto en la cuenta de destino
//        toAccount.setBalance(toAccount.getBalance().add(amount));
//
//        // Guardar los cambios en ambas cuentas
//        bankAccountDAO.save(fromAccount);
//        bankAccountDAO.save(toAccount);
//    }
}
