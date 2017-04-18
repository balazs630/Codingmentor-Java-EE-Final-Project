package com.schonherz.project.repository;

import com.schonherz.project.entity.Setting;
import com.schonherz.project.entity.User;
import com.schonherz.project.interceptor.binding.Logging;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Created by darvasr on 9/7/16.
 */
@Stateless
@Logging
public class SettingRepository extends EntityRepositoryFacade<Setting> {

    public SettingRepository() {
        super(Setting.class);
    }

    public Setting findByUser(User user) {
        List<Setting> result = getEntityManager().createQuery("SELECT s FROM Setting s WHERE s.user = :param_user", clazz).setParameter("param_user", user).setMaxResults(1).getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

}
