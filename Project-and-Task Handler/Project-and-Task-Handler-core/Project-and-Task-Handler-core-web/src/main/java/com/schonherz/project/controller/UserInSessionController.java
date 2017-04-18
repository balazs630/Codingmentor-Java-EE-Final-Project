package com.schonherz.project.controller;

import com.schonherz.project.entity.Record;
import com.schonherz.project.entity.User;
import com.schonherz.project.service.RecordService;
import com.schonherz.project.service.UserService;
import com.schonherz.project.util.Session;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@SessionScoped
@ManagedBean(name = "userInSession")
public class UserInSessionController {

    @Inject 
    private UserService userService;

    @Inject 
    private RecordService recordService;
    
    private Record record;
    
    private User user;
    
    @PostConstruct
    private void init() {
        String username = Session.getAttribute(String.class, Session.USER_SESSION_USERNAME);
        user = userService.getUserByUsername(username);
        record = recordService.getLastRecordByUsername(username);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
