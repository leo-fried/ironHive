package com.leofriedman.projectmanager.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.leofriedman.projectmanager.model.*;
import com.leofriedman.projectmanager.repository.*;

@Service
public class ProjectService {
    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository)
    {
        this.repository = repository;
    }

    public List<Project> getAllProjects()
    {
        return repository.findAll();
    }
    public Project getProjectById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Project with ID: " + id + " not found"));
    }
    public Project createProject(Project Project)
    {
        return repository.save(Project);
    }
    public Project updateProject(Long id, Project updatedProject)
    {
        Project Project = getProjectById(id);
        Project.setTitle(updatedProject.getTitle());
        Project.setDescription(updatedProject.getDescription());
        Project.setDueDate(updatedProject.getDueDate());
        Project.setCompleted(updatedProject.isCompleted());
        return repository.save(Project);
    }
    public void deleteProject(Long id)
    {
        repository.deleteById(id);
    }
}
