package com.schonherz.project.controller;

import com.schonherz.project.entity.User;
import com.schonherz.project.service.UserService;
import com.schonherz.project.util.Message;
import com.schonherz.project.util.PasswordUtil;
import com.schonherz.project.util.Popup;
import com.schonherz.project.util.Session;
import com.schonherz.project.util.UploadedFiles;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@SessionScoped
@ManagedBean(name = "profileSettings")
public class ProfileSettingsController implements Serializable {

    private int workFactor = PasswordUtil.DEFAULT_WORK_FACTOR;

    @Inject
    private UserService userService;

    @Inject
    private Logger logger;

    private User user;

    private String currentPassword;

    private String newPassword;

    private UploadedFile profilePicture;

    public ProfileSettingsController() {
        user = new User();
    }

    @PostConstruct
    private void init() {
        try {
            workFactor = Integer.parseInt(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getInitParameter(PasswordUtil.WORK_FACTOR_PARAM_NAME));
        } catch (NullPointerException | NumberFormatException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        HttpSession session = Session.getSession();
        Object obj = session.getAttribute(Session.USER_SESSION_USERNAME);
        if (obj instanceof String) {
            String username = (String) obj;
            user = userService.getUserByUsername(username);
        }
    }

    public void profileModification() {
        if (PasswordUtil.checkPassword(currentPassword, userService.getPassword(user.getUsername()))) {
            if (!newPassword.isEmpty()) {
                user.setPassword(PasswordUtil.hashPassword(newPassword, workFactor));
            }
            if (changeProfilePicture()) {
                user = userService.updateUser(user);
                Popup.pushPopup(Popup.PROFLE_SETTING, "Profile info succesfully updated!", "");
            }
        } else {
            Popup.pushPopup(Popup.PROFLE_SETTING, "Current password is wrong.", "");
        }
    }

    private boolean changeProfilePicture() {
        if (UploadedFiles.isNullOrEmpty(profilePicture)) {
            return true;
        }
        boolean validUpload = UploadedFiles.isImage(profilePicture)
                && UploadedFiles.sizeBelow(profilePicture, 500000);
        if (validUpload) {
            try {
                String profilePictureName = UploadedFiles.moveUploadedFile(profilePicture,
                        UploadedFiles.UPLOAD_IMAGES_LOCATION);
                String oldLink = user.getLink();
                user.setLink(profilePictureName);
                UploadedFiles.deleteUpload(oldLink, UploadedFiles.UPLOAD_IMAGES_LOCATION);
                profilePicture = null;
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
                validUpload = false;
            }
        }
        if (!validUpload) {
            Popup.pushError(Message.UPLOAD_FAILED_BODY, Message.UPLOAD_FAILED_HEAD);
        }
        return validUpload;
    }

    public UserService getUserService() {
        return userService;
    }

    public UploadedFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(UploadedFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
