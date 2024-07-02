package com.nationalbank.nationalbankperu.service;


import com.nationalbank.nationalbankperu.model.ServicePayment;

import java.util.List;


public interface IServicePaymentService {
    void payService(Long userId, ServicePayment servicePayment);

    ServicePayment findById(Long id);

    List<ServicePayment> findAll();

    void deleteById(Long id);

}

