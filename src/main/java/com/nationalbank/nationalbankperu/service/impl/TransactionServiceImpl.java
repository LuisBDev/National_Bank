package com.nationalbank.nationalbankperu.service.impl;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.Transaction;
import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.persistence.IBankAccountDAO;
import com.nationalbank.nationalbankperu.persistence.ITransactionDAO;
import com.nationalbank.nationalbankperu.repository.IUserRepository;
import com.nationalbank.nationalbankperu.service.ITransactionService;
import com.nationalbank.nationalbankperu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private ITransactionDAO transactionDAO;

    @Autowired
    private IBankAccountDAO bankAccountDAO;

    @Autowired
    private IUserRepository userDAO;


    @Override
    public List<Transaction> findAll() {
        return transactionDAO.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return transactionDAO.findById(id);
    }

    @Override
    public void save(Transaction transaction) {
        transactionDAO.save(transaction);
    }

    @Override
    public void deleteById(Long id) {
        transactionDAO.deleteById(id);
    }

    @Override
    @Transactional
    public void performTransaction(Long id, Transaction transaction) {


        String fromAccountNumber = transaction.getFromAccount().getAccountNumber();
        String toAccountNumber = transaction.getToAccount().getAccountNumber();

        BankAccount fromAccount = bankAccountDAO.findByAccountNumber(fromAccountNumber);
        BankAccount toAccount = bankAccountDAO.findByAccountNumber(toAccountNumber);


        validateAccountAndTransaction(transaction, fromAccount, toAccount);

        boolean isFromAccountOwner = validateOwnerTransaction(id, fromAccount);


        if (isFromAccountOwner) {
            fromAccount.setBalance(fromAccount.getBalance().subtract(transaction.getAmount()));
            toAccount.setBalance(toAccount.getBalance().add(transaction.getAmount()));

            //Persistiendo los cambios en bankAccount
            bankAccountDAO.save(fromAccount);
            bankAccountDAO.save(toAccount);

            //Persistiendo la transacción
            transaction.setFromAccount(fromAccount);
            transaction.setToAccount(toAccount);
            transaction.setTransactionDate(LocalDateTime.now());
            transactionDAO.save(transaction);

        }
    }

    //Verificando que el usuario sea el dueño de la cuenta para realizar la transacción
    public boolean validateOwnerTransaction(Long id, BankAccount fromAccount) {
        User user = userDAO.findById(id).orElse(null);

        List<BankAccount> bankAccounts = user.getBankAccounts();

        boolean isFromAccountOwner = bankAccounts.stream().
                anyMatch(bankAccount -> bankAccount.getAccountNumber().equals(fromAccount.getAccountNumber()));

        return isFromAccountOwner;
    }


    public void validateAccountAndTransaction(Transaction transaction, BankAccount fromAccount, BankAccount toAccount) {
        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("One or both accounts do not exist!");
        }

        //Verificando que ambas cuentas estén activas
        boolean isFromAccountActive = fromAccount.getStatus().equals("ACTIVE");
        boolean isToAccountActive = toAccount.getStatus().equals("ACTIVE");

        if (!isFromAccountActive || !isToAccountActive) {
            throw new IllegalArgumentException("One or both accounts are not active!");
        }

        if (fromAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient funds!");
        }

        if (transaction.getAmount().compareTo(new BigDecimal(0)) <= 0) {
            throw new IllegalArgumentException("El monto a transferir debe ser mayor a 0!");
        }


    }


}
