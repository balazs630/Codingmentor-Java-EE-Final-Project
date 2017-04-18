package com.schonherz.project.controller;

import com.schonherz.project.entity.Client;
import com.schonherz.project.entity.Project;
import com.schonherz.project.entity.User;
import com.schonherz.project.model.ProjectState;
import com.schonherz.project.service.ClientService;
import com.schonherz.project.service.ProjectService;
import com.schonherz.project.service.UserService;
import com.schonherz.project.util.Message;
import com.schonherz.project.util.Popup;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author balazs630 <balazs630@icloud.com>
 */
@SessionScoped
@ManagedBean(name = "addnewproject")
public class AddNewProjectController implements Serializable {

    private Project project;
    private String clientName;
    private String leaderName;

    @Inject
    private ProjectService projectService;

    @Inject
    private ClientService clientService;

    @Inject
    private UserService userService;

    @Inject
    private Logger logger;

    @PostConstruct
    private void init() {
        project = new Project();
    }

    public String createProject() {
        Project newProject = getNewProject();
        Date kickoffDate = new Date();
        Client client = clientService.getClientByName(clientName);
        User user = userService.getUserByUsername(leaderName);
        if (client != null && user != null) {
            newProject.setClient(client);
            newProject.setUser(user);
            newProject.setKickoff(kickoffDate);
            newProject.setState(ProjectState.PENDING);
            projectService.createProject(newProject);
            Popup.pushPopup(Message.PROJECT_ADD_SUCCESS_BODY, Message.PROJECT_ADD_SUCCESS_HEADER);
            return "/administration/project.xhtml?faces-redirect=true";
        } else {
            Popup.pushPopup(Message.PROJECT_ADD_FAILED_BODY, Message.PROJECT_ADD_FAILED_HEADER);
            return "/administration/addnewproject.xhtml?faces-redirect=true";
        }
    }

    private Project getNewProject() {
        Project newProject = new Project();
        try {
            BeanUtils.copyProperties(newProject, project);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return newProject;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
}
