package com.leofriedman.projectmanager.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
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
    public String verifyUser(@PathVariable String hashedId, @RequestBody User user)
    {   
        //System.out.println("HELLO WORLD!");
        service.patchVerified(hashedId, user);
        return "redirect:http://127.0.0.1:5500/frontend/src/verify.html"; //redirect user

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
