package com.nationalbank.nationalbankperu.service.impl;

import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.persistence.IUserDAO;
import com.nationalbank.nationalbankperu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;


    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public void save(User user) {

        userDAO.save(user);

    }

    @Override
    public void deleteById(Long id) {

        userDAO.deleteById(id);

    }

    @Override
    public boolean existsByNumIdentification(String username) {
        return userDAO.existsByNumIdentification(username);
    }

    @Override
    public User findByNumIdentification(String numIdentification) {
        return userDAO.findByNumIdentification(numIdentification);
    }
}
