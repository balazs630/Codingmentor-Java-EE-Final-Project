package com.schonherz.project.service;

import com.schonherz.project.entity.Forum;
import com.schonherz.project.entity.User;
import com.schonherz.project.repository.ForumRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityNotFoundException;

/**
 * Created by darvasr on 9/7/16.
 */
@ManagedBean(name = "ForumService")
@ApplicationScoped
@Stateless
public class ForumService {

    @EJB
    private ForumRepository repository;

    //TODO create forum with dto
    public Forum createForum() {
        return null;
    }

    public Forum createForum(Forum forum) {
        return repository.create(forum);
    }

    public Forum getForumById(Long id) {
        Forum forum = repository.find(id);
        if (forum == null) {
            throw new EntityNotFoundException("Forum not found with this id!");
        }
        return forum;
    }

    public List<Forum> getForumListByProjectAndUserId(Long projectId, Long userId) {
        return repository.getForumListByProjectAndUserId(projectId, userId);
    }

    public List<Forum> getForumListByProjectId(Long selectedProjectId) {
        return repository.getForumListByProjectId(selectedProjectId);
    }

    public List<Forum> getForumListByUserId(Long userId) {
        return repository.getForumListByUserId(userId);
    }

    //TODO update forum with DTO
    public Forum updateForum(Forum forum) {
        return repository.update(forum);
    }

    public List<Forum> getForumsWhereUserPosted(User user) {
        return repository.getForumsWhereUserPosted(user);
    }

}
