package com.schonherz.project.service;

import com.schonherz.project.entity.Project;
import com.schonherz.project.model.ProjectState;
import com.schonherz.project.repository.ProjectRepository;
import com.schonherz.project.util.EnumUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityNotFoundException;

/**
 * Created by darvasr on 9/7/16.
 */
@ManagedBean(name = "ProjectService")
@ApplicationScoped
@Stateless
public class ProjectService {

    @EJB
    private ProjectRepository repository;

    //TODO create project with dto
    public Project createProject() {
        return null;
    }

    public Project createProject(Project project) {
        return repository.create(project);
    }

    public Project getProjectById(Long id) {
        Project project = repository.find(id);
        if (project == null) {
            throw new EntityNotFoundException("Project not found with this id!");
        } else {
            return project;
        }
    }

    public List<Project> getTasksForProjectByDeadline(Date date) {
        return repository.getTasksForProjectByDeadline(date);
    }
    public Project updateProject(Project project) {
        return repository.update(project);
    }

    public Project removeProject(Long id) {
        return repository.delete(id);
    }

    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    public Project getProjectByName(String projectName) {
        return repository.getProjectByName(projectName);
    }

    public List<String> getStates() {
        return EnumUtil.getNames(ProjectState.class);
    }
}
