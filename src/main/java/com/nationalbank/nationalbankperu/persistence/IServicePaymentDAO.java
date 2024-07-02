package com.nationalbank.nationalbankperu.persistence;

import com.nationalbank.nationalbankperu.model.ServicePayment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IServicePaymentDAO {

    void save(ServicePayment servicePayment);

    ServicePayment findById(Long id);

    List<ServicePayment> findAll();

    void deleteById(Long id);


}
