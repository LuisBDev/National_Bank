package com.nationalbank.nationalbankperu.controller;

import com.nationalbank.nationalbankperu.model.Transaction;
import com.nationalbank.nationalbankperu.service.IBankAccountService;
import com.nationalbank.nationalbankperu.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;


    @GetMapping("/all")
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }


    @PostMapping("/transfer/{id}")
    public ResponseEntity<?> performTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {

        try {
            transactionService.performTransaction(id, transaction);
            return ResponseEntity.ok("Transaction con el monto de " + transaction.getAmount() + " realizada con éxito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
