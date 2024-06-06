package com.nationalbank.nationalbankperu.persistence;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.User;

import java.util.List;


public interface IUserDAO {

    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);

    boolean existsByNumIdentification(String numIdentification);

    User findByNumIdentification(String numIdentification);


}
