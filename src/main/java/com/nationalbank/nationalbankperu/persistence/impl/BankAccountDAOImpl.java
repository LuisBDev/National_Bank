package com.nationalbank.nationalbankperu.persistence.impl;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.persistence.IBankAccountDAO;
import com.nationalbank.nationalbankperu.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BankAccountDAOImpl implements IBankAccountDAO {

    @Autowired
    private BankAccountRepository bankAccountRepository;


    @Override
    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount findById(Long id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    @Override
    public void save(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void deleteById(Long id) {
        bankAccountRepository.deleteById(id);
    }

    @Override
    public List<BankAccount> findByUser(User user) {
        return bankAccountRepository.findByUser(user);
    }

    @Override
    public BankAccount findByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }
}
