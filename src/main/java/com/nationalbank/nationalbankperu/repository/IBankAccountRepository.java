package com.nationalbank.nationalbankperu.repository;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBankAccountRepository extends JpaRepository<BankAccount, Long> {

//    List<BankAccount> findByUser(User user);

    BankAccount findByAccountNumber(String accountNumber);

}
