package com.schonherz.project.view.queries;

import com.schonherz.project.entity.Task;
import com.schonherz.project.service.TaskService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by darvasr on 2016.09.16..
 */
@ManagedBean(name = "BlockedTaskView")
@SessionScoped
public class BlockedTaskView {

    private List<Task> tasks;

    @Inject
    private TaskService taskService;

    @PostConstruct
    private void init() {
        tasks = taskService.getTaskWithBlockedChildren();
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
