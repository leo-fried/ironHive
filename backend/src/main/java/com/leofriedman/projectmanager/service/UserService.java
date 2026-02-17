package com.leofriedman.projectmanager.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.leofriedman.projectmanager.exception.UserException;
import com.leofriedman.projectmanager.dto.UserRegistration;
import com.leofriedman.projectmanager.model.User;
import com.leofriedman.projectmanager.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder)
    {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;

    }
    public User registerUser(UserRegistration userRegistration)
    {
        try{
            User user = new User();

            user.setUsername(userRegistration.getUsername());
            user.setEmail(userRegistration.getEmail());
            String hash = passwordEncoder.encode(userRegistration.getPassword());
            user.setPassword(hash);
            return repository.save(user);
        } catch (Exception e) {
            throw new UserException("Failed to register user: " + e.getMessage());
        }
    }
    /**
     * Validates the inputted password against the saved hashed password
     * @param inputtedPassword the password inputted by the user during login
     * @param savedPassword the hashed password stored in the database
     * @return true if the inputted password matches the saved password, false otherwise
     */
    public boolean validatePassword(String inputtedPassword, String savedPassword)
    {
        return passwordEncoder.matches(inputtedPassword, savedPassword);
    }


    public List<User> getAllUsers()
    {
        return repository.findAll();
    }
    public User getUserById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new UserException("User with ID: " + id + " not found"));
    }
    public User getUserByUsername(String username)
    {
        return repository.findByUsername(username);
    }
    public User updateUser(Long id, User updatedUser)
    {
        User user = getUserById(id);
        user.setUsername(updatedUser.getUsername());
        // Update password only if changed to avoid rehashing
        if(!updatedUser.getPassword().isBlank())
        {
            String hash = passwordEncoder.encode(updatedUser.getPassword());
            user.setPassword(hash);
        }
        user.setEmail(updatedUser.getEmail());
        user.setLoggedIn(updatedUser.isLoggedIn());
        return repository.save(user);
    }
    public User patchUsername(Long id, User updatedUser)
    {
        User user = getUserById(id);
        user.setUsername(updatedUser.getUsername());
        return repository.save(user);
    }
    public User patchPassword(Long id, User updatedUser)
    {
        User user = getUserById(id);
        String hash = passwordEncoder.encode(updatedUser.getPassword());
        user.setPassword(hash);
        return repository.save(user);
    }
    public User patchEmail(Long id, User updatedUser)
    {
        User user = getUserById(id);
        user.setEmail(updatedUser.getEmail());
        return repository.save(user);
    }
    public User patchLoggedIn(Long id, User updatedUser)
    {
        User user = getUserById(id);
        user.setLoggedIn(updatedUser.isLoggedIn());
        return repository.save(user);
    }
  
    public void deleteUser(Long id)
    {
        repository.deleteById(id);
    }
}
