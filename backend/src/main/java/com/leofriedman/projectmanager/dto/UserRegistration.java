package com.leofriedman.projectmanager.dto;

import jakarta.validation.constraints.*;

public class UserRegistration {
    // Name of User
    @NotBlank(message = "Username must not be blank")
    @Size(min = 5, max = 30, message = "Username must be between 5 and 30 characters")
    private String username;

    // User password as inputted
    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}", message = "Password must contain at least one uppercase letter, one number, and one special character")
    private String password;

    // User email
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be a valid address")
    private String email;

    // Getters and Setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
