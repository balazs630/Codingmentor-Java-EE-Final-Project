package com.schonherz.project.view.queries;

import com.schonherz.project.entity.Forum;
import com.schonherz.project.entity.Post;
import com.schonherz.project.entity.User;
import com.schonherz.project.service.ForumService;
import com.schonherz.project.service.PostService;
import com.schonherz.project.service.UserService;
import com.schonherz.project.util.Message;
import com.schonherz.project.util.Popup;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by darvasr on 2016.09.16..
 */
@ManagedBean(name = "ForumPostView")
@SessionScoped
public class ForumPostView {

    private List<Forum> forums;

    private List<Post> posts;

    @Inject
    private ForumService forumService;

    @Inject
    private UserService userService;

    @Inject
    private PostService postService;

    private String username;

    public String search() {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            posts = postService.getPostsByUserId(user.getId());
        } else {
            Popup.pushPopup(Message.USER_NOT_FOUND_HEADER, Message.USER_NOT_FOUND_BODY);
        }
        return "/protected/user/queries/forumposts.xhtml?faces-redirect=true";
    }

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public ForumService getForumService() {
        return forumService;
    }

    public void setForumService(ForumService forumService) {
        this.forumService = forumService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
