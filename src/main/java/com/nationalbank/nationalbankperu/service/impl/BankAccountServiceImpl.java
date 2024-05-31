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

//    @Override
//    @Transactional(readOnly = true)
//    public List<BankAccount> findByUser(User user) {
//        return bankAccountDAO.findByUser(user);
//    }

    
}
