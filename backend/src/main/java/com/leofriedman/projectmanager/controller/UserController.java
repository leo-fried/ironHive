package com.leofriedman.projectmanager.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import com.leofriedman.projectmanager.model.User;
import com.leofriedman.projectmanager.service.UserService;

@RestController
@RequestMapping("api/Users")
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
    public User createUser(@Valid @RequestBody User User)
    {
        return service.createUser(User);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User User)
    {
        return service.updateUser(id, User);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id)
    {
        service.deleteUser(id);
    }
}
