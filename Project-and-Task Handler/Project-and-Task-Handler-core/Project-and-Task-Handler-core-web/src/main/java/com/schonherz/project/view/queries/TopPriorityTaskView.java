package com.schonherz.project.view.queries;

import com.schonherz.project.entity.Task;
import com.schonherz.project.entity.User;
import com.schonherz.project.service.TaskService;
import com.schonherz.project.service.UserService;
import com.schonherz.project.util.Message;
import com.schonherz.project.util.Popup;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by darvasr on 2016.09.16..
 */
@ManagedBean(name = "TopPriorityTaskView")
@SessionScoped
public class TopPriorityTaskView {

    private String username;

    private List<Task> tasks;

    @Inject
    private TaskService taskService;

    @Inject
    private UserService userService;

    public String search() {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            tasks = taskService.getUrgentTasksByUserId(user.getId());
        } else {
            Popup.pushPopup(Message.USER_NOT_FOUND_HEADER, Message.USER_NOT_FOUND_BODY);
        }
        return "/protected/user/queries/toppriority.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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
}
