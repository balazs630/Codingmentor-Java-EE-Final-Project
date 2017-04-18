package com.schonherz.project.view;

import com.schonherz.project.entity.Forum;
import com.schonherz.project.entity.Project;
import com.schonherz.project.service.ForumService;
import com.schonherz.project.service.ProjectService;
import com.schonherz.project.util.Message;
import com.schonherz.project.util.Popup;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author balazs630 <balazs630@icloud.com>
 */
@ManagedBean(name = "IndexView")
@SessionScoped
public class IndexView implements Serializable {

    private Long selectedProjectId;

    public String PROJECT_ID = "project_id";

    private List<Project> projects;
    private Project project;

    private List<Forum> forums;

    private List<Project> selectedProjects;
    private List<Project> filteredProjects;

    @Inject
    private ProjectService projectService;
    @Inject
    private ForumService forumService;

    @PostConstruct
    public void init() {
        projects = projectService.getAllProjects();
        forums = forumService.getForumListByProjectId(selectedProjectId);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }

    public void setService(ProjectService service) {
        this.projectService = service;
    }

    public List<Project> getSelectedProjects() {
        return selectedProjects;
    }

    public void setSelectedProjects(List<Project> selectedProjects) {
        this.selectedProjects = selectedProjects;
    }

    public List<Project> getFilteredProjects() {
        return filteredProjects;
    }

    public void setFilteredProjects(List<Project> filteredProjects) {
        this.filteredProjects = filteredProjects;
    }

    public List<String> getStates() {
        return projectService.getStates();
    }

    public Long getSelectedProjectId() {
        return selectedProjectId;
    }

    public String showForums() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        selectedProjectId = Long.parseLong(params.get(PROJECT_ID));
        forums = forumService.getForumListByProjectId(selectedProjectId);
        return "/protected/user/forum/forums.xhtml?faces-redirect=true";
    }

    public void showForums(Long id) {
        forums = forumService.getForumListByProjectId(id);
    }

    public String viewProject() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        selectedProjectId = Long.parseLong(params.get(PROJECT_ID));
        project = projectService.getProjectById(selectedProjectId);
        return "/dataviews/view_project.xhtml?faces-redirect=true";
    }

    public String compareProjects() {
        if (selectedProjects.isEmpty()) {
            Popup.pushPopup(Message.NOTHING_TO_COMPARE_BODY, Message.NOTHING_TO_COMPARE_HEADER);
            return "";
        } else {
            return "/dataviews/compare_projects.xhtml?faces-redirect=true";
        }
    }

    public String backFromCompare() {
        selectedProjects = Collections.<Project>emptyList();
        return "/protected/user/index.xhtml?faces-redirect=true";
    }

    public String loadHomepage() {
        return "/protected/user/index.xhtml?faces-redirect=true";
    }
}
