package com.nationalbank.nationalbankperu.repository;


import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    boolean existsByNumIdentification(String numIdentification);

    User findByNumIdentification(String numIdentification);

}
