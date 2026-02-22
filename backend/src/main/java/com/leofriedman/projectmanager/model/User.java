package com.leofriedman.projectmanager.model;

import java.util.List;

import jakarta.persistence.*;

/**
 * User Class
 */
@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
public class User {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // User ID

    private String hashedId; // Hashed User ID

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lift> lifts; // List of Lifts associated with the User

    // Name of User
    private String username;

    // User password
    private String password;

    private String email;

    // Whether the user has been verified
    private boolean verified = false;

    /**
     * Default Constructor
     */
    public User() {}

    // Getters and Setters

    /**
     * Gets the User ID
     * @return the User ID
     */
    public Long getId() {
        return id;
    }

    public String getHashedId()
    {
        return hashedId;
    }

    public void setHashedId(String hashedId)
    {
        this.hashedId = hashedId;
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
     * @param username the User username to set
     */
    public void setUsername(String username) {
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
     * Checks if the User is verified
     * @return true if the User is verified, false otherwise
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * Sets the User as verified or not
     * @param verified true if the User is verified, false otherwise
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public List<Lift> getLifts() {
        return lifts;
    }
    public void setLifts(List<Lift> lifts) {
        this.lifts = lifts;
    }
}   
