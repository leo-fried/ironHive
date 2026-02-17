package com.leofriedman.projectmanager.model;

import java.security.*;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * User Class
 */
@Entity
public class User {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // User ID

    // Name of User
    @NotBlank(message = "Username must not be blank")
    @Size(min = 5, max = 30, message = "Username must be between 5 and 30 characters")
    private String username;

    // User password as inputted
    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}", message = "Password must contain at least one uppercase letter, one number, and one special character")
    private char[] passwordInput;

    /**
     * Encrypts the user's password
     * @param password the original password in plain text
     * @return the hashed password (salt:hash)
     * @throws Exception
     */
    private String encryptPassword(char[] password) throws Exception 
    {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        // Convert hash to hex
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        // Return salt + hash, hex encoded
        StringBuilder saltHex = new StringBuilder();
        for (byte b : salt) {
            saltHex.append(String.format("%02x", b));
        }
        return saltHex.toString() + ":" + sb.toString();
    }

    // The hashed password
    private String password;

    // User email
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be a valid address")
    private String email;

    // Whether the user has logged in
    private boolean loggedIn = false;

    /**
     * Default Constructor
     */
    public User() {}

    /**
     * Parameterized constructor
     * @param username user's username
     * @param password user password
     * @param email user's email
     */
    public User(String username, char[] password, String email) throws Exception {
        this.username = username;
        this.password = encryptPassword(password); // Hash Password
        Arrays.fill(passwordInput, '\0'); // Clear plain text password from memory
        this.email = email;
    }

    // Getters and Setters

    /**
     * Gets the User ID
     * @return the User ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the User username
     * @return the User username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the User username
     * @param Username the User username to set
     */
    public void setusername(String username) {
        this.username = username;
    }

    /**
     * Gets the User password
     * @return the User password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the User password
     * @param password the User password to set (hashed)
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Gets the User email
     * @return the User email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the User email
     * @param email the User email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Checks if the User is loggedIn
     * @return true if the User is loggedIn, false otherwise
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets the User as loggedIn or not
     * @param loggedIn true if the User is loggedIn, false otherwise
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}   
