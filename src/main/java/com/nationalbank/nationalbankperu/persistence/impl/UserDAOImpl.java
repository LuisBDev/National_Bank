package com.nationalbank.nationalbankperu.persistence.impl;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.persistence.IUserDAO;
import com.nationalbank.nationalbankperu.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl implements IUserDAO {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {

        userRepository.deleteById(id);

    }

    @Override
    public boolean existsByNumIdentification(String numIdentification) {
        return userRepository.existsByNumIdentification(numIdentification);
    }

    @Override
    public User findByNumIdentification(String numIdentification) {
        return userRepository.findByNumIdentification(numIdentification);

    }


}
