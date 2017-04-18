package com.schonherz.project.repository;

import com.schonherz.project.entity.Project;
import com.schonherz.project.interceptor.binding.Logging;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Created by darvasr on 9/7/16.
 */
@Stateless
@Logging
public class ProjectRepository extends EntityRepositoryFacade<Project> {

    public ProjectRepository() {
        super(Project.class);
    }

    public Project getProjectByName(String projectName) {
        List<Project> projectList = getEntityManager().createQuery("SELECT p FROM Project p WHERE p.name = :param_projectname", Project.class)
                .setParameter("param_projectname", projectName)
                .getResultList();
        if (!projectList.isEmpty()) {
            return projectList.get(0);
        }
        return null;
    }

    public List<Project> getTasksForProjectByDeadline(Date date) {
        List<Project> projectList = getEntityManager()
                .createQuery("SELECT DISTINCT p FROM Project p INNER JOIN p.tasks t WHERE t.deadline <= :param_task_deadline", Project.class)
                .setParameter("param_task_deadline", date).getResultList();
        if (!projectList.isEmpty()) {
            return projectList;
        }
        return null;
    }
}
