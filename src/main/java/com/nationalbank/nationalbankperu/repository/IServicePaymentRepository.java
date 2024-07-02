package com.nationalbank.nationalbankperu.repository;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.ServicePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IServicePaymentRepository extends JpaRepository<ServicePayment, Long> {


}
