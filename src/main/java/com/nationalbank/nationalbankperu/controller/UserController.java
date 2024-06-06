package com.nationalbank.nationalbankperu.controller;

import com.nationalbank.nationalbankperu.model.BankAccount;
import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<String> save(@RequestBody User user) {
        boolean numIdentificationExists = userService.existsByNumIdentification(user.getNumIdentification());
        if (numIdentificationExists) {
            return ResponseEntity.badRequest().body("Error: NumIdentification ya existe en la bd!");
        } else {
            userService.save(user);
            return ResponseEntity.ok("User saved successfully!");
        }
    }

    //Update user
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody User user) {
        User userToUpdate = userService.findById(id);
        if (userToUpdate == null) {
            return ResponseEntity.badRequest().body("Error: User no encontrado!");
        } else {
            userToUpdate.setNumIdentification(user.getNumIdentification());
            userToUpdate.setPassword(user.getPassword());
            userService.save(userToUpdate);
            return ResponseEntity.ok("User updated successfully!");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        boolean numIdentificationExists = userService.existsByNumIdentification(user.getNumIdentification());
        User userToLogin = userService.findByNumIdentification(user.getNumIdentification());

        if (numIdentificationExists && userToLogin.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(userToLogin);
        } else {
            return ResponseEntity.badRequest().body(null);
        }


    }

    @GetMapping("/bankAccounts/{user_id}")
    public List<BankAccount> findBankAccountsById(@PathVariable Long user_id) {

        User user = userService.findById(user_id);
        return user.getBankAccounts();
    }


}
