package com.nationalbank.nationalbankperu.service;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface IBankAccountService {
    List<BankAccount> findAll();

    BankAccount findById(Long id);

    void save(BankAccount bankAccount);

    void deleteById(Long id);
//    List<BankAccount> findByUser(User user);

}
