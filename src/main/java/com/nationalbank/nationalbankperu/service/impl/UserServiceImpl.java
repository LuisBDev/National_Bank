package com.nationalbank.nationalbankperu.service.impl;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.persistence.IUserDAO;
import com.nationalbank.nationalbankperu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;


    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(User user) {

        userDAO.save(user);

    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        userDAO.deleteById(id);

    }

    @Override
    public boolean existsByNumIdentification(String username) {
        return userDAO.existsByNumIdentification(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByNumIdentification(String numIdentification) {
        return userDAO.findByNumIdentification(numIdentification);
    }


}
