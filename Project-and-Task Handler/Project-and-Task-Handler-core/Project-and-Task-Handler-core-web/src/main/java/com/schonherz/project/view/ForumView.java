package com.schonherz.project.view;

import com.schonherz.project.entity.Forum;
import com.schonherz.project.entity.Post;
import com.schonherz.project.service.PostService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author balazs630 <balazs630@icloud.com>
 */
@ManagedBean(name = "ForumView")
@SessionScoped
public class ForumView implements Serializable {

    private Long selectedForumId;

    public String FORUM_ID = "forum_id";

    private List<Forum> forums;

    public List<Post> posts;

    @Inject
    private PostService postService;

    public List<Forum> getForums() {
        return forums;
    }

    @PostConstruct
    private void init() {
        posts = postService.getPostsByForumId(selectedForumId);
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

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public Long getSelectedForumId() {
        return selectedForumId;
    }

    public String showPosts() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        selectedForumId = Long.parseLong(params.get(FORUM_ID));
        posts = postService.getPostsByForumId(selectedForumId);
        return "/protected/user/forum/forum_posts.xhtml?faces-redirect=true";
    }

    public void showPosts(Long id) {
        posts = postService.getPostsByForumId(id);
    }

    public Long numberOfPostPerForum(Long forumId) {
        return postService.getNumberOfPostsByForumId(forumId);
    }
}
