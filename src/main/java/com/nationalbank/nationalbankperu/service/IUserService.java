package com.nationalbank.nationalbankperu.service;

import com.nationalbank.nationalbankperu.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IUserService {

    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);

    boolean existsByUsername(String username);


}
