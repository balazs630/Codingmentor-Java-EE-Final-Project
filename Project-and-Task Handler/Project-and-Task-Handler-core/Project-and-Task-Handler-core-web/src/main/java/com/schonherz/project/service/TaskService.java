package com.schonherz.project.service;

import com.schonherz.project.entity.Task;
import com.schonherz.project.repository.TaskRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by darvasr on 9/7/16.
 */
@ManagedBean(name = "TaskService")
@ApplicationScoped
@Stateless
public class TaskService {

    @EJB
    private TaskRepository repository;

    //TODO create task with dto
    public Task createTask() {
        return null;
    }

    public Task createTask(Task task) {
        return repository.create(task);
    }

    public Task getTaskById(Long id) {
        Task task = repository.find(id);
        if (task == null) {
            throw new EntityNotFoundException("Task not found with this id!");
        } else {
            return task;
        }
    }

    public List<Task> getUrgentTasksByUserId(Long userId) {
        return repository.getUrgentTasksByUserId(userId);
    }

    public List<Task> getTaskWithBlockedChildren() {
        return repository.getTaskWithBlockedChildren();
    }

    public List<Task> getOpenTasksForProject(Long projectId) {
        return repository.getOpenTasksForProject(projectId);
    }

    public Task updateTask(Task task) {
        return repository.update(task);
    }

    public Task removeTask(Long id) {
        return repository.delete(id);
    }

    public List<Task> getTasks() {
        return repository.findAll();
    }

    public Task getTaskByName(String taskName) {
        return repository.getTaskByName(taskName);
    }
}
