package com.leofriedman.projectmanager.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.leofriedman.projectmanager.model.*;
import com.leofriedman.projectmanager.repository.*;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository)
    {
        this.repository = repository;
    }

    public List<User> getAllUsers()
    {
        return repository.findAll();
    }
    public User getUserById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User with ID: " + id + " not found"));
    }
    public User createUser(User User)
    {
        return repository.save(User);
    }
    public User updateUser(Long id, User updatedUser)
    {
        User User = getUserById(id);
        User.setusername(updatedUser.getUsername());
        User.setPassword(updatedUser.getPassword());
        User.setEmail(updatedUser.getEmail());
        User.setLoggedIn(updatedUser.isLoggedIn());
        return repository.save(User);
    }
    public void deleteUser(Long id)
    {
        repository.deleteById(id);
    }
}
