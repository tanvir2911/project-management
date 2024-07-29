package com.example.projectmanagement.controller;

import com.example.projectmanagement.entity.Project;
import com.example.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String listProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "project_list";
    }

    @GetMapping("/create")
    public String showCreateProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "create_project";
    }

    @PostMapping
    public String createProject(@ModelAttribute Project project) {
        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/edit/{id}")
    public String showEditProjectForm(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        return "edit_project";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute Project project) {
        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }
}
