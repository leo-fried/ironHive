package com.leofriedman.projectmanager.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import com.leofriedman.projectmanager.model.Project;
import com.leofriedman.projectmanager.service.ProjectService;

@RestController
@RequestMapping("api/Projects")
public class ProjectController 
{
    private final ProjectService service;
    public ProjectController(ProjectService service)
    {
        this.service = service;
    }

    @GetMapping
    public List<Project> getAllProjects(){
        return service.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id)
    {
        return service.getProjectById(id);
    }

    @PostMapping
    public Project createProject(@Valid @RequestBody Project Project)
    {
        return service.createProject(Project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project Project)
    {
        return service.updateProject(id, Project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id)
    {
        service.deleteProject(id);
    }
}
