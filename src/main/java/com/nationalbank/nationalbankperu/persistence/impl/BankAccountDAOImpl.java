package com.nationalbank.nationalbankperu.persistence.impl;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.persistence.IBankAccountDAO;
import com.nationalbank.nationalbankperu.repository.IBankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BankAccountDAOImpl implements IBankAccountDAO {

    @Autowired
    private IBankAccountRepository IBankAccountRepository;


    @Override
    public List<BankAccount> findAll() {
        return IBankAccountRepository.findAll();
    }

    @Override
    public BankAccount findById(Long id) {
        return IBankAccountRepository.findById(id).orElse(null);
    }

    @Override
    public void save(BankAccount bankAccount) {
        IBankAccountRepository.save(bankAccount);
    }

    @Override
    public void deleteById(Long id) {
        IBankAccountRepository.deleteById(id);
    }

//    @Override
//    public List<BankAccount> findByUser(User user) {
//        return IBankAccountRepository.findByUser(user);
//    }

    @Override
    public BankAccount findByAccountNumber(String accountNumber) {
        return IBankAccountRepository.findByAccountNumber(accountNumber);
    }
}
