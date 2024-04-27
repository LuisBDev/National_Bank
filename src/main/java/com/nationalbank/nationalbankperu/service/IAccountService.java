package com.nationalbank.nationalbankperu.service;

import com.nationalbank.nationalbankperu.model.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();
    Account findById(Long id);
    void save(Account account);
    void deleteById(Long id);

}
