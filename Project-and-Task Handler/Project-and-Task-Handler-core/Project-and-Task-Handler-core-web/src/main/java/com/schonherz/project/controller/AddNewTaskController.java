package com.schonherz.project.controller;

import com.schonherz.project.entity.Project;
import com.schonherz.project.entity.Task;
import com.schonherz.project.entity.User;
import com.schonherz.project.util.EnumUtil;
import com.schonherz.project.model.Priority;
import com.schonherz.project.model.TaskStatus;
import com.schonherz.project.model.TaskType;
import com.schonherz.project.service.ProjectService;
import com.schonherz.project.service.TaskService;
import com.schonherz.project.service.UserService;
import com.schonherz.project.util.Message;
import com.schonherz.project.util.Popup;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;

/**
 * @author balazs630 <balazs630@icloud.com>
 */
@SessionScoped
@ManagedBean(name = "addnewtask")
public class AddNewTaskController implements Serializable {

    private Task task;

    private String parentTaskName;

    private String projectName;

    private String leaderUsrName;

    @Inject
    private TaskService taskService;

    @Inject
    private ProjectService projectService;

    @Inject
    private UserService userService;

    @Inject
    private Logger logger;

    @PostConstruct
    private void init() {
        task = new Task();
        task.setStatus(TaskStatus.CREATED);
    }

    public String createTask() {
        Task newTask = getNewTask();
        Project project = projectService.getProjectByName(getProjectName());
        User user = userService.getUserByUsername(getLeaderUsrName());
        Task parentTask = taskService.getTaskByName(getParentTaskName());
        if (project != null && user != null) {
            newTask.setProject(project);
            newTask.setUser(user);
            newTask.setParentTask(parentTask);
            taskService.createTask(newTask);
            Popup.pushPopup(Message.TASK_ADD_SUCCESS_BODY, Message.TASK_ADD_SUCCESS_HEADER);
            return "/administration/task.xhtml?faces-redirect=true";
        } else {
            Popup.pushPopup(Message.TASK_ADD_FAILED_BODY, Message.TASK_ADD_FAILED_HEADER);
            return "/administration/addnewtask.xhtml?faces-redirect=true";
        }
    }

    private Task getNewTask() {
        Task newTask = new Task();
        try {
            BeanUtils.copyProperties(newTask, task);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return newTask;
    }

    public List<String> priorityAttrs(String query) {
        return EnumUtil.getNames(Priority.class);
    }

    public List<String> typeAttrs(String query) {
        return EnumUtil.getNames(TaskType.class);
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }

    public String getLeaderUsrName() {
        return leaderUsrName;
    }

    public void setLeaderUsrName(String leaderUsrName) {
        this.leaderUsrName = leaderUsrName;
    }
    
}
