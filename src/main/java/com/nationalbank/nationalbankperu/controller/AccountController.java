package com.nationalbank.nationalbankperu.controller;

import com.nationalbank.nationalbankperu.model.Account;
import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.service.IAccountService;
import com.nationalbank.nationalbankperu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IUserService userService;

    @PostMapping("/open/{userId}")
    public ResponseEntity<String> openAccount(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.badRequest().body("El usuario con ID " + userId + " no existe");
        }

        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);

        accountService.save(account);

        return ResponseEntity.ok("Se ha abierto una nueva cuenta para el usuario " + user.getUsername());
    }


    @GetMapping("/all")
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Account account) {
        accountService.save(account);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        accountService.deleteById(id);
    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 14; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
