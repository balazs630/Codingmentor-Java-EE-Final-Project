package com.schonherz.project.view;

import com.schonherz.project.entity.Project;
import com.schonherz.project.model.ProjectState;
import com.schonherz.project.service.ProjectService;
import com.schonherz.project.util.EnumUtil;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author balazs630 <balazs630@icloud.com>
 */
@ManagedBean(name = "ProjectView")
@RequestScoped
public class ProjectView implements Serializable {

    public String PROJECT_ID = "project_id";

    private List<Project> projects;

    private List<Project> filteredProjects;

    @Inject
    private ProjectService projectService;

    @PostConstruct
    public void init() {
        projects = projectService.getAllProjects();
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void setService(ProjectService service) {
        this.projectService = service;
    }

    public List<Project> getFilteredProjects() {
        return filteredProjects;
    }

    public void setFilteredProjects(List<Project> filteredProjects) {
        this.filteredProjects = filteredProjects;
    }

    public List<String> getProjectState(){
        return EnumUtil.getNames(ProjectState.class);
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Project Edited", ((Project) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Project project = (Project) event.getObject();
        projectService.updateProject(project);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Project) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String onRowDelete() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        Long selectedProjectId = Long.parseLong(params.get(PROJECT_ID));
        projectService.removeProject(selectedProjectId);
        return "/administration/project.xhtml?faces-redirect=true";
    }

    public String compareProjects() {
        return "/dataviews/compare_projects.xhtml?faces-redirect=true";
    }
}
