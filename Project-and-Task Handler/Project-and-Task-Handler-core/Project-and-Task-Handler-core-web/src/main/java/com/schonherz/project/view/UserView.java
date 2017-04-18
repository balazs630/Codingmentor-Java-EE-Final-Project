package com.schonherz.project.view;

import com.schonherz.project.entity.User;
import com.schonherz.project.service.UserService;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author balazs630 <balazs630@icloud.com>
 */
@ManagedBean(name = "UserView")
@RequestScoped
public class UserView implements Serializable {

    public String USER_ID = "user_id";

    private List<User> users;

    @Inject
    private UserService userService;

    @PostConstruct
    public void init() {
        users = userService.getUsers();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("User Edited", ((User) event.getObject()).getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        User user = (User) event.getObject();
        userService.updateUser(user);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((User) event.getObject()).getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowDelete() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        Long selectedUserId = Long.parseLong(params.get(USER_ID));
        userService.removeUser(selectedUserId);
    }
}
