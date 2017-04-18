package com.schonherz.project.view;

import com.schonherz.project.entity.Forum;
import com.schonherz.project.entity.Notification;
import com.schonherz.project.entity.Post;
import com.schonherz.project.entity.Record;
import com.schonherz.project.entity.User;
import com.schonherz.project.model.NotificationType;
import com.schonherz.project.repository.PostRepository;
import com.schonherz.project.service.ForumService;
import com.schonherz.project.service.NotificationService;
import com.schonherz.project.service.PostService;
import com.schonherz.project.service.RecordService;
import com.schonherz.project.service.TaskService;
import com.schonherz.project.service.UserService;
import com.schonherz.project.util.Session;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@ManagedBean(name = "NotificationView")
@SessionScoped
public class NotificationView implements Serializable, PostRepository.OnPostCreatedInterface {

    @Inject
    private NotificationService notificationService;

    @Inject
    private RecordService recordService;

    @Inject
    private ForumService forumService;

    @Inject
    private PostService postService;

    @Inject
    private UserService userService;

    private List<Notification> notificationList;

    private User user;

    public NotificationView() {
        notificationList = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        postService.registrate(this);
        HttpSession session = Session.getSession();
        String username = (String) session.getAttribute(Session.USER_SESSION_USERNAME);
        user = userService.getUserByUsername(username);
        Record lastLogin = recordService.getRecordAtPosition(username, 2);

        List<Forum> forumList = forumService.getForumsWhereUserPosted(user);
        if (forumList != null) {
            for (Forum forum : forumList) {
                List<Post> postsAtCommonForum = postService.getPostsByForumId(forum.getId());
                if (postsAtCommonForum != null) {
                    if (lastLogin != null) {
                        postsAtCommonForum = removeUnnecessaryElements(postsAtCommonForum,
                                lastLogin.getLoginTime());
                    }

                    for (Post post : postsAtCommonForum) {
                        if (!post.getUser().getId().equals(user.getId())) {
                            notificationList.add(createNotification(post));
                        }
                    }
                }
            }
        }
    }

    public Notification createNotification(Post post) {
        Notification notification = new Notification();
        notification.setRelatedForum(post.getForum());
        notification.setCreationDate(post.getPostDate());
        notification.setType(NotificationType.NEW_TASK_COMMENT);
        notification.setNotificationRead(new Date());
        return notification;
    }

    private List<Post> removeUnnecessaryElements(List<Post> list, Date lastLogin) {
        ListIterator it = list.listIterator();
        while (it.hasNext()) {
            Post post = (Post) it.next();
            if (post.getPostDate().before(lastLogin)) {  //for test new Date(1182117600000L)
                it.remove();
            }
        }
        return list;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public Integer getCountOfUnread() {
        return notificationList.size();
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public String back() {
        for (Notification item : notificationList) {
            item.setUser(user);
            notificationService.createNotification(item);
            user.getNotifications().add(item);
            userService.updateUser(user);
        }
        notificationList.clear();
        return "/index.xhtml?faces-redirect=true";
    }

    @Override
    public void onPostCreated(Post post) {
        if (!post.getUser().getId().equals(user.getId())) {
            notificationList.add(createNotification(post));
        }
    }

}
