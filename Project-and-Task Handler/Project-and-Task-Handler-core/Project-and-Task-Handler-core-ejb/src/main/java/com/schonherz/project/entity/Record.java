package com.schonherz.project.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Created by darvasr on 9/5/16.
 */
@Entity
@Table(name = "RECORD")
public class Record implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;

    private String ip;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOGIN_TIME")
    private Date loginTime;

    public Record() {
    }

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "Record{" + "id=" + id + ", user=" + user + ", ip=" + ip + ", loginTime=" + loginTime + '}';
    }

}
