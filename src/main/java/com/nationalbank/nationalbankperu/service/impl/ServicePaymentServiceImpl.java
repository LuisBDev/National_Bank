package com.nationalbank.nationalbankperu.service.impl;

import com.nationalbank.nationalbankperu.model.ServicePayment;
import com.nationalbank.nationalbankperu.service.IServicePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicePaymentServiceImpl implements IServicePaymentService {

    @Autowired
    private IServicePaymentService servicePaymentService;

    @Override
    public void save(ServicePayment servicePayment) {
        servicePaymentService.save(servicePayment);
    }

    @Override
    public ServicePayment findById(Long id) {
        return servicePaymentService.findById(id);
    }

    @Override
    public List<ServicePayment> findAll() {
        return servicePaymentService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        servicePaymentService.deleteById(id);
    }
}
