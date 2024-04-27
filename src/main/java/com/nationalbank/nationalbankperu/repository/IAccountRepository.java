package com.nationalbank.nationalbankperu.repository;

import com.nationalbank.nationalbankperu.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Long> {
}
