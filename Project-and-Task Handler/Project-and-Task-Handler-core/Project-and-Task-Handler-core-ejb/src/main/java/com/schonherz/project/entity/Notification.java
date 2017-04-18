package com.schonherz.project.entity;

import com.schonherz.project.model.NotificationType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/**
 * Created by darvasr on 9/5/16.
 */
@Entity
@Table(name = "NOTIFICATION")
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NOTIFICATION_READ")
    private Date notificationRead;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @ManyToOne
    private Forum relatedForum;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getNotificationRead() {
        return notificationRead;
    }

    public void setNotificationRead(Date notificationRead) {
        this.notificationRead = notificationRead;
    }

    public Forum getRelatedForum() {
        return relatedForum;
    }

    public void setRelatedForum(Forum relatedForum) {
        this.relatedForum = relatedForum;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.user);
        hash = 83 * hash + Objects.hashCode(this.notificationRead);
        hash = 83 * hash + Objects.hashCode(this.creationDate);
        hash = 83 * hash + Objects.hashCode(this.relatedForum);
        hash = 83 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Notification other = (Notification) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.notificationRead, other.notificationRead)) {
            return false;
        }
        if (!Objects.equals(this.creationDate, other.creationDate)) {
            return false;
        }
        if (!Objects.equals(this.relatedForum, other.relatedForum)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", user=" + user + ", notificationRead=" + notificationRead + ", creationDate=" + creationDate + ", relatedTask=" + relatedForum + ", type=" + type + '}';
    }

}
