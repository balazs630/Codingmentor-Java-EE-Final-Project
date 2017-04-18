package com.schonherz.project.service;

import com.schonherz.project.entity.Post;
import com.schonherz.project.entity.User;
import com.schonherz.project.repository.PostRepository;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityNotFoundException;

/**
 * Created by darvasr on 9/7/16.
 */
@ManagedBean(name = "PostService")
@ApplicationScoped
@Stateless
public class PostService {

    @EJB
    private PostRepository repository;

    //TODO create post with dto
    public Post createPost() {
        return null;
    }

    public Post createPost(Post post) {
        return repository.create(post);
    }

    public Post getPostById(Long id) {
        Post post = repository.find(id);
        if (post == null) {
            throw new EntityNotFoundException("Post not found with this id!");
        } else {
            return post;
        }
    }

    public List<Post> getPostsByUserAndForumId(Long userId, Long forumId) {
        return repository.getPostsByUserAndForumId(userId, forumId);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return repository.getPostsByUserId(userId);
    }

    //TODO update user with DTO
    public Post updatePost(Post post) {
        return repository.update(post);
    }

    public List<Post> getPosts() {
        return repository.findAll();
    }

    public List<Post> getPostsByForumId(Long selectedForumId) {
        return repository.getPostListByForumId(selectedForumId);
    }

    public Long getNumberOfPostsByForumId(Long selectedForumId) {
        return repository.getPostCountByForumId(selectedForumId);
    }

    public List<Post> getPostsByParentId(Long id) {
        return repository.getPostsByParentId(id);
    }
    
    public void registrate(Serializable object) {
        repository.registrate(object);
    }

    public List<Post> getPostsByUserAfterDate(User user, Date minDate) {
        return repository.getPostsByUserAfterDate(user, minDate);
    }
}
