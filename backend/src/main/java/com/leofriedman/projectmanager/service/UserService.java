package com.leofriedman.projectmanager.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.leofriedman.projectmanager.dto.UserRegistration;
import com.leofriedman.projectmanager.model.User;
import com.leofriedman.projectmanager.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, EmailService emailService)
    {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }
    public User registerUser(UserRegistration userRegistration)
    {
        try{

            User user = new User();

            user.setUsername(userRegistration.getUsername());
            user.setEmail(userRegistration.getEmail());
            String hash = passwordEncoder.encode(userRegistration.getPassword());
            user.setPassword(hash);

            // Attempt to save user before sending email
            User savedUser = repository.save(user);
            // Send confirmation email
            emailService.sendEmail(userRegistration.getEmail(),
                                   "Verify your Iron Hive Account",
                                "Thank you for registering with Iron Hive!\n\n\n" +
                                "Your Friends. Your Tools. One Place.\n\n" +
                                "We are so excited to have you on the platform and hope you take full advantage of all it has to offer!\n\n\n" +
                                "Please click the link below so that you can sign in and use the application.\n\n" +
                                "*LINK PLACEHOLDER*\n\n\n" +
                                "-Iron Hive Team");
            return savedUser;
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user: " + e);
        }
    }


    public List<User> getAllUsers()
    {
        return repository.findAll();
    }
    public User getUserById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User with ID: " + id + " not found"));
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
