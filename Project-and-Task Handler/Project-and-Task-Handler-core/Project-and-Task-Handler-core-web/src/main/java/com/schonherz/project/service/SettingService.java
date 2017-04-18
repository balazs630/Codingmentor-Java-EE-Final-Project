package com.schonherz.project.service;

import com.schonherz.project.entity.Setting;
import com.schonherz.project.repository.SettingRepository;
import com.schonherz.project.util.Session;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

/**
 * Created by darvasr on 9/7/16.
 */
@ManagedBean(name = "SettingService")
@ApplicationScoped
@Stateless
public class SettingService {

    @EJB
    private SettingRepository settingRepository;

    @Inject
    private UserService userService;

    //TODO create setting with dto
    public Setting createSetting() {
        return null;
    }

    public Setting createSetting(Setting setting) {
        return settingRepository.create(setting);
    }

    public Setting getProjectById(Long id) {
        Setting setting = settingRepository.find(id);
        if (setting == null) {
            throw new EntityNotFoundException("Setting not found with this id!");
        } else {
            return setting;
        }
    }

    public Setting updateSetting(Setting setting) {
        return settingRepository.update(setting);
    }

    public List<Setting> getSettings() {
        return settingRepository.findAll();
    }

    public Setting getSettingByUsername(String username) {
        return settingRepository.findByUser(userService.getUserByUsername(username));
    }

    public boolean updatePageSize(int newPageSize) {
        Setting setting = getCurrentUserSetting();
        if (newPageSize != setting.getPaging()) {
            setting.setPaging(newPageSize);
            updateSetting(setting);
            return true;
        }
        return false;
    }

    public Setting getCurrentUserSetting() {
        return getSettingByUsername(Session.getAttribute(String.class, Session.USER_SESSION_USERNAME));
    }
}
