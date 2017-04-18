package com.schonherz.project.controller;

import com.schonherz.project.entity.Role;
import com.schonherz.project.entity.Setting;
import com.schonherz.project.entity.User;
import com.schonherz.project.util.EnumUtil;
import com.schonherz.project.model.Gender;
import com.schonherz.project.service.RoleService;
import com.schonherz.project.service.SettingService;
import com.schonherz.project.service.UserService;
import com.schonherz.project.util.Message;
import com.schonherz.project.util.PasswordUtil;
import com.schonherz.project.util.Popup;
import com.schonherz.project.util.UploadedFiles;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@SessionScoped
@ManagedBean(name = "registration")
public class RegistrationController implements Serializable {

    private int workFactor = PasswordUtil.DEFAULT_WORK_FACTOR;

    private User user;

    @Inject
    private UserService clientService;

    @Inject
    private RoleService roleService;

    @Inject
    private Logger logger;

    @Inject
    private SettingService settingService;

    private UploadedFile profilePicture;

    public UserService getClientService() {
        return clientService;
    }

    public void setClientService(UserService clientService) {
        this.clientService = clientService;
    }

    public UploadedFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(UploadedFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    @PostConstruct
    private void init() {
        user = new User();

        try {
            workFactor = Integer.parseInt(FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getInitParameter(PasswordUtil.WORK_FACTOR_PARAM_NAME));
        } catch (NullPointerException | NumberFormatException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public String createUser() {
        User newUser = getNewUser();
        if (!uploadProfilePicture(newUser)) {
            return "";
        }
        if (!clientService.hasUser(newUser.getUsername())) {
            String password = newUser.getPassword();
            String hashed = PasswordUtil.hashPassword(password, workFactor);
            newUser.setPassword(hashed);

            Role userRole = roleService.getRoleByName("user");
            newUser.setRoles(Arrays.asList(userRole));
            clientService.createUser(newUser);
            createDefaultSettingForUser(newUser);

            userRole.getUsers().add(newUser);
            roleService.update(userRole);

            Popup.pushPopup(Message.REG_SUCCESS_BODY, Message.REG_SUCCESS_HEADER);
            return "/protected/user/index.xhtml?faces-redirect=true";
        } else {
            Popup.pushPopup(Message.REG_FAILED_BODY, Message.REG_FAILED_HEADER);
            return "";
        }
    }

    public static int DEFAULT_PAGE_SIZE = 10;

    private void createDefaultSettingForUser(User newUser) {
        Setting newUserSettings = new Setting();
        newUserSettings.setPaging(DEFAULT_PAGE_SIZE);
        newUserSettings.setUser(newUser);
        settingService.createSetting(newUserSettings);
    }

    private boolean uploadProfilePicture(User newUser) {
        if (UploadedFiles.isNullOrEmpty(profilePicture)) {
            return true;
        }
        boolean validUpload = UploadedFiles.isImage(profilePicture)
                && UploadedFiles.sizeBelow(profilePicture, 500000);
        if (validUpload) {
            try {
                String profilePictureName = UploadedFiles.moveUploadedFile(profilePicture, UploadedFiles.UPLOAD_IMAGES_LOCATION);
                newUser.setLink(profilePictureName);
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

    private User getNewUser() {
        User newUser = new User();
        try {
            BeanUtils.copyProperties(newUser, user);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return newUser;
    }

    public List<String> genderAttrs(String query) {
        return EnumUtil.getNames(Gender.class);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
