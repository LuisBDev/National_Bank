package com.nationalbank.nationalbankperu.service.impl;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.ServicePayment;
import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.persistence.IBankAccountDAO;
import com.nationalbank.nationalbankperu.persistence.IServicePaymentDAO;
import com.nationalbank.nationalbankperu.persistence.IUserDAO;
import com.nationalbank.nationalbankperu.service.IServicePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Component
public class ServicePaymentServiceImpl implements IServicePaymentService {

    @Autowired
    private IServicePaymentDAO servicePaymentDAO;

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IBankAccountDAO bankAccountDAO;

    @Override
    @Transactional
    public void payService(Long userId, ServicePayment servicePayment) {

        User user = userDAO.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        BankAccount bankAccount = bankAccountDAO.
                findByAccountNumber(servicePayment.getBankAccount().getAccountNumber());

        if (bankAccount.getBalance().compareTo(servicePayment.getAmount()) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        bankAccount.setBalance(bankAccount.getBalance().subtract(servicePayment.getAmount()));
        bankAccountDAO.save(bankAccount);
        servicePayment.setUser(user);
        servicePayment.setPaymentDate(LocalDateTime.now());
        servicePayment.setBankAccount(bankAccount);

        servicePaymentDAO.save(servicePayment);


    }

    @Override
    public ServicePayment findById(Long id) {
        return servicePaymentDAO.findById(id);
    }

    @Override
    public List<ServicePayment> findAll() {
        return servicePaymentDAO.findAll();
    }

    @Override
    public void deleteById(Long id) {
        servicePaymentDAO.deleteById(id);
    }
}
