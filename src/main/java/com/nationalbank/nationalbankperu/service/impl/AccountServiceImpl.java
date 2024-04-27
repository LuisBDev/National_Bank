package com.nationalbank.nationalbankperu.service.impl;

import com.nationalbank.nationalbankperu.model.Account;
import com.nationalbank.nationalbankperu.persistence.IAccountDAO;
import com.nationalbank.nationalbankperu.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDAO accountDAO;


    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountDAO.findById(id);
    }

    @Override
    public void save(Account account) {
        accountDAO.save(account);

    }

    @Override
    public void deleteById(Long id) {
        accountDAO.deleteById(id);
    }
}
