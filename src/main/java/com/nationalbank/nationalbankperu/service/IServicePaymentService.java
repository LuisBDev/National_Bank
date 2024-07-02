package com.nationalbank.nationalbankperu.service;

import com.nationalbank.nationalbankperu.model.ServicePayment;
import org.springframework.stereotype.Component;

import java.util.List;


public interface IServicePaymentService {
    void save(ServicePayment servicePayment);

    ServicePayment findById(Long id);

    List<ServicePayment> findAll();

    void deleteById(Long id);
}
