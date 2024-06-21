package com.nationalbank.nationalbankperu.controller;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.service.IBankAccountService;
import com.nationalbank.nationalbankperu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bankAccount")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BankAccountController {

    @Autowired
    private IBankAccountService bankAccountService;

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public List<BankAccount> findAll() {
        return bankAccountService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> findById(@PathVariable Long id) {
        BankAccount bankAccount = bankAccountService.findById(id);
        if (bankAccount == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bankAccount);
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createBankAccount(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.badRequest().body("Error: User not found!");
        }

        //si se crea con patrón builder, no se guardara el embeddable audit en la base de datos
        BankAccount bankAccount = new BankAccount();
        bankAccount.setStatus("ACTIVE");
        bankAccount.setAccountNumber(generateAccountNumber());
        bankAccount.setBalance(new BigDecimal(80000));
        bankAccount.setUser(user);

        bankAccountService.save(bankAccount);
        user.getBankAccounts().add(bankAccount);
        userService.save(user);

        return ResponseEntity.ok("BankAccount created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        BankAccount existingBankAccount = bankAccountService.findById(id);
        if (existingBankAccount == null) {
            return ResponseEntity.badRequest().body("Error: BankAccount not found!");
        }

        if (bankAccount.getStatus() != null) {
            existingBankAccount.setStatus(bankAccount.getStatus());
        }
        if (bankAccount.getBalance() != null) {
            existingBankAccount.setBalance(bankAccount.getBalance());
        }
        if (bankAccount.getAccountNumber() != null) {
            existingBankAccount.setAccountNumber(bankAccount.getAccountNumber());
        }

        bankAccountService.save(existingBankAccount);
        return ResponseEntity.ok("BankAccount updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        BankAccount existingBankAccount = bankAccountService.findById(id);
        if (existingBankAccount == null) {
            return ResponseEntity.notFound().build();
        }
        bankAccountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    private String generateAccountNumber() {
        return "PE" + UUID.randomUUID().toString().replace("-", "").substring(0, 9).toUpperCase();
    }
}
