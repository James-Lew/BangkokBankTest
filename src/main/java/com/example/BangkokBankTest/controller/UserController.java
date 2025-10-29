package com.example.BangkokBankTest.controller;

import com.example.BangkokBankTest.model.User;
import com.example.BangkokBankTest.service.UserService;
import jakarta.validation.Valid;
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


    @GetMapping(path="/users" , produces={"application/json"})
    public ResponseEntity<List<User>> getAllUsers(){

        return  new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }

    @GetMapping(path="/users/{userId}", produces={"application/json"})
    public ResponseEntity<User> getUserById(@PathVariable long userId){

        User user = userService.getUserById(userId);

        if(user != null)
            return new ResponseEntity<>(user,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping(path="/users", consumes={"application/json"})
    public ResponseEntity<?> addUser(@Valid @RequestBody User user){
        return new ResponseEntity<>( userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping(path="/users/{userId}", consumes={"application/json"})
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @Valid @RequestBody User updatedUser) {

        User user= userService.getUserById(userId);
        if (user != null){
            userService.updateUser(userId, updatedUser);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId){
        User user= userService.getUserById(userId);
        if (user != null){
            userService.deleteUser(userId);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
