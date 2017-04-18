package com.schonherz.project.controller;

import com.schonherz.project.entity.Forum;
import com.schonherz.project.service.ForumService;
import com.schonherz.project.service.ProjectService;
import com.schonherz.project.service.UserService;
import com.schonherz.project.util.Message;
import com.schonherz.project.util.Popup;
import com.schonherz.project.util.Session;
import com.schonherz.project.view.IndexView;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
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
@ManagedBean(name = "AddNewForumTopic")
public class AddNewForumTopic implements Serializable {

    private Forum forum;

    @Inject
    private ForumService forumService;

    @Inject
    private UserService userService;

    @Inject
    private ProjectService projectService;

    @Inject
    private Logger logger;

    @PostConstruct
    private void init() {
        forum = new Forum();
    }

    @ManagedProperty(value = "#{IndexView}")
    private IndexView indexView;

    public IndexView getIndexView() {
        return indexView;
    }

    public void setIndexView(IndexView indexView) {
        this.indexView = indexView;
    }

    public String createNewTopic(Long projectId) {
        Forum newForum = getNewForum();
        newForum.setUser(userService.getUserByUsername(Session.getAttribute(String.class, Session.USER_SESSION_USERNAME)));
        newForum.setProject(projectService.getProjectById(projectId));

        forumService.createForum(newForum);
        indexView.showForums(indexView.getSelectedProjectId());
        Popup.pushPopup(Message.FORUM_ADD_SUCCESS_BODY, Message.FORUM_ADD_SUCCESS_HEADER);
        return "/protected/user/forum/forum.xhtml?faces-redirect=true";
    }

    private Forum getNewForum() {
        Forum newForum = new Forum();
        try {
            BeanUtils.copyProperties(newForum, forum);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return newForum;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

}
