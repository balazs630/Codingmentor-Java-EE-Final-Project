package com.schonherz.project.repository;

import com.schonherz.project.entity.Comment;
import com.schonherz.project.interceptor.binding.Logging;
import javax.ejb.Stateless;

/**
 * Created by darvasr on 9/7/16.
 */
@Stateless
@Logging
public class CommentRepository extends EntityRepositoryFacade<Comment> {

    public CommentRepository() {
        super(Comment.class);
    }

}
