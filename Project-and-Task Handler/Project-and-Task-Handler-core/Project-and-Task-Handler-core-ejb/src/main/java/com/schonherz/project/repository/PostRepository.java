package com.schonherz.project.repository;

import com.schonherz.project.entity.Post;
import com.schonherz.project.entity.User;
import com.schonherz.project.interceptor.binding.Logging;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Created by darvasr on 9/7/16.
 */
@Stateless
@Logging
public class PostRepository extends EntityRepositoryFacade<Post> {

    private Serializable obj;

    public interface OnPostCreatedInterface {

        void onPostCreated(Post post);
    }

    public PostRepository() {
        super(Post.class);
    }

    public List<Post> getPostsByUserId(Long userId) {
        List<Post> postList = getEntityManager().createQuery("SELECT p FROM Post p WHERE p.user.id = :param_userId", Post.class)
                .setParameter("param_userId", userId)
                .getResultList();
        if (!postList.isEmpty()) {
            return postList;
        } else {
            return null;
        }
    }

    public List<Post> getPostsByUserAfterDate(User user, Date minDate) {
        List<Post> postList = getEntityManager().createQuery("SELECT p FROM Post p WHERE p.user = :param_user AND p.postDate >= :param_minDate", Post.class)
                .setParameter("param_user", user)
                .setParameter("param_minDate", minDate)
                .getResultList();
        if (!postList.isEmpty()) {
            return postList;
        } else {
            return null;
        }
    }

    public List<Post> getPostsByUserAndForumId(Long userId, Long forumId) {
        List<Post> postList = getEntityManager()
                .createQuery("SELECT p FROM Post p WHERE p.user.id = :param_user_id AND p.forum.id = :param_forum_id", Post.class)
                .setParameter("param_user_id", userId)
                .setParameter("param_forum_id", forumId)
                .getResultList();
        if (!postList.isEmpty()) {
            return postList;
        } else {
            return null;
        }
    }

    public List<Post> getPostListByForumId(Long selectedForumId) {
        List<Post> postList = getEntityManager().createQuery("SELECT p FROM Post p WHERE p.forum.id = :param_forum_id", Post.class)
                .setParameter("param_forum_id", selectedForumId)
                .getResultList();
        if (!postList.isEmpty()) {
            return postList;
        }
        return null;
    }

    public List<Post> getPostsByParentId(Long parentId) {
        List<Post> postList = getEntityManager().createQuery("SELECT p FROM Post p WHERE p.parentPost is not null and p.parentPost.id = :param_parent_id", Post.class).setParameter("param_parent_id", parentId).getResultList();
        if (!postList.isEmpty()) {
            return postList;
        }
        return null;
    }

    public Long getPostCountByForumId(Long selectedForumId) {
        Long postCount = getEntityManager().createQuery("SELECT COUNT(p.id) FROM Post p WHERE p.forum.id = :param_forum_id", Long.class)
                .setParameter("param_forum_id", selectedForumId)
                .getSingleResult();
        return postCount;
    }

    @Override
    public Post create(Post entity) {
        if (obj instanceof OnPostCreatedInterface) {
            ((OnPostCreatedInterface) obj).onPostCreated(entity);
        }
        return super.create(entity);
    }

    public void registrate(Serializable obj) {
        this.obj = obj;
    }

}
