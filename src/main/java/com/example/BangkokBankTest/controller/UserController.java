package com.example.BangkokBankTest.controller;

import com.example.BangkokBankTest.model.User;
import com.example.BangkokBankTest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){

        return  new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable long userId){

        User user = userService.getUserById(userId);

        if(user != null)
            return new ResponseEntity<>(user,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/users")
    public ResponseEntity<?> addProduct(@RequestBody User user){
        return new ResponseEntity<>( userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {

        User user= userService.getUserById(userId);
        if (user != null){
            userService.updateUser(userId, updatedUser);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long userId){
        User user= userService.getUserById(userId);
        if (user != null){
            userService.deleteUser(userId);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
