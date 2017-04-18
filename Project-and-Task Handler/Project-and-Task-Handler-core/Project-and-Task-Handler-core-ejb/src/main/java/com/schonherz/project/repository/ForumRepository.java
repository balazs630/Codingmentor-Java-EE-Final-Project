package com.schonherz.project.repository;

import com.schonherz.project.entity.Forum;
import com.schonherz.project.entity.User;
import com.schonherz.project.interceptor.binding.Logging;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Created by darvasr on 9/7/16.
 */
@Stateless
@Logging
public class ForumRepository extends EntityRepositoryFacade<Forum> {

    public ForumRepository() {
        super(Forum.class);
    }

    public List<Forum> getForumListByProjectId(Long selectedProjectId) {
        List<Forum> forumList = getEntityManager().createQuery("SELECT f FROM Forum f WHERE f.project.id = :param_project_id", Forum.class)
                .setParameter("param_project_id", selectedProjectId)
                .getResultList();
        if (!forumList.isEmpty()) {
            return forumList;
        } else {
            return null;
        }
    }

    public List<Forum> getForumListByProjectAndUserId(Long projectId, Long userId) {
        List<Forum> forumList
                = getEntityManager()
                .createQuery("SELECT f FROM Forum f WHERE f.project.id = :param_project_id AND f.user.id=:param_user_id", Forum.class)
                .setParameter("param_project_id", projectId).setParameter("param_user_id", userId).getResultList();
        if (!forumList.isEmpty()) {
            return forumList;
        } else {
            return null;
        }
    }

    public List<Forum> getForumListByUserId(Long userId) {
        List<Forum> forumList
                = getEntityManager()
                .createQuery("SELECT f FROM Forum f WHERE f.user.id=:param_user_id ORDER BY f.project", Forum.class)
                .setParameter("param_user_id", userId).getResultList();
        if (!forumList.isEmpty()) {
            return forumList;
        } else {
            return null;
        }
    }

    public List<Forum> getForumsWhereUserPosted(User user) {
        List<Forum> forumList
                = getEntityManager()
                .createQuery("SELECT DISTINCT f FROM Forum f INNER JOIN f.posts p WHERE p.user = :param_user", Forum.class)
                .setParameter("param_user", user)
                .getResultList();
        if (!forumList.isEmpty()) {
            return forumList;
        } else {
            return null;
        }
    }
}
