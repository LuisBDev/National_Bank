package com.nationalbank.nationalbankperu.persistence;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.User;

import java.util.List;

public interface IBankAccountDAO {
    List<BankAccount> findAll();

    BankAccount findById(Long id);

    void save(BankAccount bankAccount);

    void deleteById(Long id);

//    List<BankAccount> findByUser(User user);

    BankAccount findByAccountNumber(String accountNumber);
}
