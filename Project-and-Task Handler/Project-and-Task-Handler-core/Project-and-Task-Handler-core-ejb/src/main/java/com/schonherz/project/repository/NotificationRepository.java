package com.schonherz.project.repository;

import com.schonherz.project.entity.Notification;
import com.schonherz.project.interceptor.binding.Logging;
import javax.ejb.Stateless;

/**
 * Created by darvasr on 9/7/16.
 */
@Stateless
@Logging
public class NotificationRepository extends EntityRepositoryFacade<Notification> {

    public NotificationRepository() {
        super(Notification.class);
    }

}
