package com.leofriedman.projectmanager.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

import com.leofriedman.projectmanager.dto.LoginRequest;
import com.leofriedman.projectmanager.dto.UserRegistration;
import com.leofriedman.projectmanager.model.User;
import com.leofriedman.projectmanager.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController 
{
    private final UserService service;
    public UserController(UserService service)
    {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id)
    {
        return service.getUserById(id);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody UserRegistration userRegistration)
    {
        return service.registerUser(userRegistration);
    }
    @PostMapping("/login")
    public boolean loginUser(@Valid @RequestBody LoginRequest loginRequest)
    {
        User user = service.getUserByUsername(loginRequest.getUsername());
        if(user != null && service.validatePassword(loginRequest.getPassword(), user.getPassword()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user)
    {
        return service.updateUser(id, user);
    }
    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody User user, @RequestParam String patch)
    {
        switch(patch)
        {
            case "username":
                return service.patchUsername(id, user);
            case "password":
                return service.patchPassword(id, user);
            case "email":
                return service.patchEmail(id, user);
            case "loggedIn":
                return service.patchLoggedIn(id, user);
            default:
                throw new IllegalArgumentException("Invalid patch parameter: " + patch);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id)
    {
        service.deleteUser(id);
    }
}
