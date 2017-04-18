package com.schonherz.project.controller;

import com.schonherz.project.entity.Post;
import com.schonherz.project.service.ForumService;
import com.schonherz.project.service.PostService;
import com.schonherz.project.service.UserService;
import com.schonherz.project.util.Message;
import com.schonherz.project.util.Popup;
import com.schonherz.project.util.Session;
import com.schonherz.project.view.ForumView;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;



/**
 *
 * @author balazs630 <balazs630@icloud.com>
 */
@SessionScoped
@ManagedBean(name = "AddNewForumPost")
public class AddNewForumPost implements Serializable {

    private Post post;

    @Inject
    private PostService postService;

    @Inject
    private UserService userService;

    @Inject
    private ForumService forumService;

    @Inject
    private Logger logger;
    
    @ManagedProperty(value = "#{ForumView}")
    private ForumView forumView;

    public ForumView getForumView() {
        return forumView;
    }

    public void setForumView(ForumView forumView) {
        this.forumView = forumView;
    }

    @PostConstruct
    private void init() {
        post = new Post();
    }

    public String sendNewPost(Long forumId) {
        Post newPost = getNewPost();
        Date dateSent = new Date();
        newPost.setPostDate(dateSent);
        newPost.setUser(userService.getUserByUsername(Session.getAttribute(String.class, Session.USER_SESSION_USERNAME)));
        newPost.setForum(forumService.getForumById(forumId));

        postService.createPost(newPost);
        forumView.showPosts(forumView.getSelectedForumId());
        Popup.pushPopup(Message.POST_ADD_SUCCESS_BODY, Message.POST_ADD_SUCCESS_HEADER);
        return "/protected/user/forum/forum_posts.xhtml?faces-redirect=true";
    }

    private Post getNewPost() {
        Post newClient = new Post();
        try {
            BeanUtils.copyProperties(newClient, post);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return newClient;
    }

    public void setForumService(ForumService forumService) {
        this.forumService = forumService;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
