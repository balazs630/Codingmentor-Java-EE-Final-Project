package com.schonherz.project.repository;

import com.schonherz.project.entity.Task;
import com.schonherz.project.interceptor.binding.Logging;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Created by darvasr on 9/7/16.
 */
@Stateless
@Logging
public class TaskRepository extends EntityRepositoryFacade<Task> {

    public TaskRepository() {
        super(Task.class);
    }

    public Task getTaskByName(String taskName) {
        List<Task> taskList = getEntityManager().createQuery("SELECT t FROM Task t WHERE t.name = :param_taskname", Task.class)
                .setParameter("param_taskname", taskName)
                .getResultList();
        if (!taskList.isEmpty()) {
            return taskList.get(0);
        }
        return null;
    }

    public List<Task> getUrgentTasksByUserId(Long userId) {
        List<Task> taskList = getEntityManager()
                .createQuery("SELECT t FROM Task t WHERE t.priority = com.schonherz.project.model.Priority.URGENT"
                        + " AND t.user.id = :param_user_id ORDER BY t.deadline DESC", Task.class).setParameter("param_user_id", userId)
                .getResultList();
        if (!taskList.isEmpty()) {
            return taskList;
        } else {
            return null;
        }
    }

    public List<Task> getTaskWithBlockedChildren() {
        List<Task> taskList = getEntityManager()
                .createQuery("SELECT t FROM Task t INNER JOIN t.children p WHERE p.status = com.schonherz.project.model.TaskStatus.BLOCKED", Task.class)
                .getResultList();
        if (!taskList.isEmpty()) {
            return taskList;
        } else {
            return null;
        }
    }

    public List<Task> getOpenTasksForProject(Long projectId) {
        List<Task> taskList = getEntityManager().createQuery("SELECT t FROM Task t WHERE t.project.id=:param_project_id"
                + " AND t.status = com.schonherz.project.model.TaskStatus.OPEN", Task.class)
                .setParameter("param_project_id", projectId).getResultList();
        if (!taskList.isEmpty()) {
            return taskList;
        } else {
            return null;
        }
    }
}
