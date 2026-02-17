package com.leofriedman.projectmanager.model;

import java.sql.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Project Class
 */
@Entity
public class Project {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of Project
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    // Project Description (Optional)
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    // Project Due Date (Optional)
    private Date dueDate;

    // Whether the project has been completed
    private boolean completed = false;

    /**
     * Default Constructor
     */
    public Project() {}

    /**
     * Parameterized constructor
     * @param title Project title
     * @param description Project description
     */
    public Project(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and Setters

    /**
     * Gets the project ID
     * @return the project ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the project title
     * @return the project title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the project title
     * @param title the project title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the project description
     * @return the project description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the project description
     * @param description the project description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Gets the project due date
     * @return the project due date
     */
    public Date getDueDate() {
        return dueDate;
    }
    /**
     * Sets the project due date
     * @param dueDate the project due date to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    /**
     * Checks if the project is completed
     * @return true if the project is completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the project as completed or not
     * @param completed true if the project is completed, false otherwise
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}   
