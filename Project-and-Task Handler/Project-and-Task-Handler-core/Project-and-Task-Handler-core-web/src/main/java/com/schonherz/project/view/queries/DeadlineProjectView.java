package com.schonherz.project.view.queries;

import com.schonherz.project.entity.Project;
import com.schonherz.project.entity.Task;
import com.schonherz.project.service.ProjectService;
import com.schonherz.project.util.DateUtil;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 * Created by darvasr on 2016.09.16..
 */
@ManagedBean(name = "DeadlineProjectView")
@SessionScoped
public class DeadlineProjectView {

    private Long number;

    private String weekormonth;

    private List<Project> projects;

    private List<Task> tasks;

    private Project selectedProject;

    @Inject
    private ProjectService projectService;

    public String search() {
        Date deadline = null;
        if (weekormonth.equals("week")) {
            deadline = DateUtil.addWeekToDate(number.intValue());
        }
        if (weekormonth.equals("month")) {
            deadline = DateUtil.addWeekToDate(number.intValue() * 4);
        }

        projects = projectService.getTasksForProjectByDeadline(deadline);
        if (projects != null) {
        }
        return "/protected/user/queries/deadlinelookup.xhtml?faces-redirect=true";
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getWeekormonth() {
        return weekormonth;
    }

    public void setWeekormonth(String weekormonth) {
        this.weekormonth = weekormonth;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }
}
