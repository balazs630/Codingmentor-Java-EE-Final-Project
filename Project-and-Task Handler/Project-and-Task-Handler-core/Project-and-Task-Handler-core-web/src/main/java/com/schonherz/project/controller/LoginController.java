package com.schonherz.project.controller;

import com.schonherz.project.entity.Record;
import com.schonherz.project.entity.User;
import com.schonherz.project.service.RecordService;
import com.schonherz.project.service.UserService;
import com.schonherz.project.util.Message;
import com.schonherz.project.util.Popup;
import com.schonherz.project.util.Session;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@SessionScoped
@ManagedBean(name = "login")
public class LoginController implements Serializable {

    private String username;
    private String password;
    private Long loginCount;

    @Inject
    private Logger logger;

    @Inject
    private UserService userService;

    @Inject
    private RecordService recordService;

    @PostConstruct
    public void init() {
        loginCount = recordService.countRecords();
    }

    public String validateUsernamePassword() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = (HttpServletRequest) context.getRequest();
        HttpServletResponse resp = (HttpServletResponse) context.getResponse();
        HttpSession session = Session.getSession(req, resp);
        try {
            Principal userPrincipal = req.getUserPrincipal();
            if (userPrincipal != null) {
                req.logout();
            }
            req.login(username, password);
            userPrincipal = req.getUserPrincipal();

            User user = userService.getUserByUsername(req.getRemoteUser());
            session.setMaxInactiveInterval(3000);
            session.setAttribute(Session.USER_SESSION_USERNAME, userPrincipal.getName());
            Record record = new Record();
            record.setUser(user);
            record.setLoginTime(new Date());
            record.setIp(Session.getRemoteAddress(req));
            recordService.createRecord(record);
            return "/protected/user/index.xhtml?faces-redirect=true";
        } catch (ServletException ex) {
            logger.log(Level.WARNING, null, ex);
            try {
                req.logout();
            } catch (ServletException ex1) {
                logger.log(Level.WARNING, null, ex1);
            }
            session.invalidate();
            Popup.pushPopup(Message.LOGIN_FAILED_BODY, Message.LOGIN_FAILED_HEADER);
            return "";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logout() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = (HttpServletRequest) context.getRequest();
        HttpServletResponse resp = (HttpServletResponse) context.getResponse();
        HttpSession session = Session.getSession(req, resp);
        try {
            req.logout();
        } catch (ServletException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        session.invalidate();
        return "/protected/user/index.xhtml?faces-redirect=true";
    }

    public Long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }

}
