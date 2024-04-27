package com.nationalbank.nationalbankperu.persistence;

import com.nationalbank.nationalbankperu.model.Account;

import java.util.List;

public interface IAccountDAO {

    List<Account> findAll();
    Account findById(Long id);
    void save(Account account);
    void deleteById(Long id);

}
