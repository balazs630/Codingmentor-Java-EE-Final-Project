package com.schonherz.project.view;

import com.schonherz.project.entity.Task;
import com.schonherz.project.model.Priority;
import com.schonherz.project.model.TaskStatus;
import com.schonherz.project.model.TaskType;
import com.schonherz.project.service.TaskService;
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
@ManagedBean(name = "TaskView")
@RequestScoped
public class TaskView implements Serializable {

    private String username;

    public String TASK_ID = "task_id";

    private List<Task> tasks;

    @Inject
    private TaskService taskService;

    @PostConstruct
    public void init() {
        tasks = taskService.getTasks();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<String> getPriorities() {
        return EnumUtil.getNames(Priority.class);
    }

    public List<String> getStatus() {
        return EnumUtil.getNames(TaskStatus.class);
    }

    public List<String> getType() {
        return EnumUtil.getNames(TaskType.class);
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Task Edited", ((Task) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Task task = (Task) event.getObject();
        taskService.updateTask(task);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Task) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void onRowDelete() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        Long selectedTaskId = Long.parseLong(params.get(TASK_ID));
        taskService.removeTask(selectedTaskId);
    }
}
