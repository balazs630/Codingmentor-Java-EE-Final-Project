package com.schonherz.project.view.queries;

import com.schonherz.project.entity.Project;
import com.schonherz.project.entity.Task;
import com.schonherz.project.service.ProjectService;
import com.schonherz.project.service.TaskService;
import com.schonherz.project.util.Message;
import com.schonherz.project.util.Popup;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by darvasr on 2016.09.16..
 */
@ManagedBean(name = "OpenTaskView")
@SessionScoped
public class OpenTaskView {

    private String projectName;

    private List<Task> tasks;

    @Inject
    private TaskService taskService;

    @Inject
    private ProjectService projectService;

    @PostConstruct
    private void init(){
        tasks = getTasks();
    }

    public String search(){
        Project project = projectService.getProjectByName(projectName);
        if (project != null) {
            tasks = taskService.getOpenTasksForProject(project.getId());
        } else {
            Popup.pushPopup(Message.PROJECT_NOT_FOUND_HEADER, Message.PROJECT_NOT_FOUND_BODY);
        }
        return "/queries/opentasks.xhtml?faces-redirect=true";
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
}
