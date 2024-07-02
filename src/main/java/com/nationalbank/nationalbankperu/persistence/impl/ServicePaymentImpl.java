package com.nationalbank.nationalbankperu.persistence.impl;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.ServicePayment;
import com.nationalbank.nationalbankperu.persistence.IServicePaymentDAO;
import com.nationalbank.nationalbankperu.repository.IServicePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicePaymentImpl implements IServicePaymentDAO {

    @Autowired
    private IServicePaymentRepository servicePaymentRepository;

    @Override
    public void save(ServicePayment servicePayment) {
        servicePaymentRepository.save(servicePayment);
    }

    @Override
    public ServicePayment findById(Long id) {
        return servicePaymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<ServicePayment> findAll() {
        return servicePaymentRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        servicePaymentRepository.deleteById(id);
    }


}
