package com.leofriedman.projectmanager.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

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

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user)
    {
        return service.updateUser(id, user);
    }

    @PatchMapping("/verify/{hashedId}")
    public User verifyUser(@PathVariable String hashedId, @RequestBody User user)
    {  
        // Get complete user info 
        User u = service.getUserByHashedId(hashedId);
        if (u == null) return user;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expTime = u.getVerificationExpiration();
        return now.isBefore(expTime) ? service.patchVerified(hashedId, user) : user; // Only verify the user if the link is valid.
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
                return service.patchVerified(id, user);
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
