package com.schonherz.project.service;

import com.schonherz.project.entity.Notification;
import com.schonherz.project.repository.NotificationRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityNotFoundException;

/**
 * Created by darvasr on 9/7/16.
 */
@ManagedBean(name = "NotificationService")
@ApplicationScoped
@Stateless
public class NotificationService {

    @EJB
    private NotificationRepository repository;

    //TODO create notification with dto
    public Notification createNotification() {
        return null;
    }

    public Notification createNotification(Notification notification) {
        return repository.create(notification);
    }

    public Notification getNotificationById(Long id) {
        Notification notification = repository.find(id);
        if (notification == null) {
            throw new EntityNotFoundException("Post not found with this id!");
        } else {
            return notification;
        }
    }

    //TODO update user with DTO
    public Notification updateNotification(Notification notification) {
        return repository.update(notification);
    }

    public List<Notification> getNotifications() {
        return repository.findAll();
    }

}
