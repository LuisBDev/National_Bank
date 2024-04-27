package com.nationalbank.nationalbankperu.controller;

import com.nationalbank.nationalbankperu.model.User;
import com.nationalbank.nationalbankperu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User findById(@PathVariable Long id)
    {
        return userService.findById(id);
    }

//    @PostMapping("/save")
//    public void save(@RequestBody User user)
//    {
//        userService.save(user);
//    }


    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody User user)
    {
        boolean isUsernameExists = userService.existsByUsername(user.getUsername());
        if (isUsernameExists) {
            return ResponseEntity.badRequest().body("Error: Username ya existe en la bd!");
            }
        else {
            userService.save(user);
            return ResponseEntity.ok("User saved successfully!");
        }
    }

    //Update user
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody User user)
    {
        User userToUpdate = userService.findById(id);
        if (userToUpdate == null) {
            return ResponseEntity.badRequest().body("Error: User no encontrado!");
        }
        else {
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(user.getPassword());
            userService.save(userToUpdate);
            return ResponseEntity.ok("User updated successfully!");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id)
    {
        userService.deleteById(id);
    }



}
