
package com.schonherz.project.service;

import com.schonherz.project.entity.Comment;
import com.schonherz.project.repository.CommentRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityNotFoundException;

/**
 * Created by darvasr on 9/7/16.
 */
@ManagedBean(name = "CommentService")
@ApplicationScoped
@Stateless
public class CommentService {

    @EJB
    private CommentRepository repository;

    //TODO create forum with DTO
    public Comment createComment() {
        return null;
    }

    public Comment createComment(Comment comment) {
        return repository.create(comment);
    }

    public Comment getCommentById(Long id) {
        Comment comment = repository.find(id);
        if (comment == null) {
            throw new EntityNotFoundException("Comment not found with this id!");
        } else {
            return comment;
        }
    }

    //TODO update user with DTO
    public Comment updateComment(Comment comment) {
        return repository.update(comment);
    }

    public List<Comment> getComments() {
        return repository.findAll();
    }
}
