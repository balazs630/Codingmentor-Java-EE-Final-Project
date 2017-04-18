package com.schonherz.project.repository;

import com.schonherz.project.entity.User;
import com.schonherz.project.interceptor.annotations.DoNotLogParamValues;
import com.schonherz.project.interceptor.annotations.DoNotLogReturnValue;
import com.schonherz.project.interceptor.binding.Logging;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Created by darvasr on 9/7/16.
 */
@Stateless
@Logging
public class UserRepository extends EntityRepositoryFacade<User> {

    public UserRepository() {
        super(User.class);
    }

    public boolean hasUser(String username) {
        Long userCount = getEntityManager()
                .createQuery("SELECT COUNT(u.id) FROM User u WHERE u.username = :param_username", Long.class)
                .setParameter("param_username", username)
                .getSingleResult();
        return userCount > 0;
    }

    @DoNotLogParamValues
    public boolean validate(String username, String password) {
        Long userCount = getEntityManager()
                .createQuery("SELECT COUNT(u.id) FROM User u WHERE u.username = :param_username AND u.password = :param_password", Long.class)
                .setParameter("param_username", username)
                .setParameter("param_password", password)
                .getSingleResult();
        return userCount > 0;
    }

    @DoNotLogReturnValue
    public String getPassword(String username) {
        List<String> password = getEntityManager().createQuery("SELECT u.password FROM User u WHERE u.username = :param_username", String.class)
                .setParameter("param_username", username)
                .getResultList();
        if (!password.isEmpty()) {
            return password.get(0);
        }
        return null;
    }

    public User getUserByUsername(String username) {
        List<User> userList = getEntityManager().createQuery("SELECT u FROM User u WHERE u.username = :param_username", User.class)
                .setParameter("param_username", username)
                .getResultList();
        if (!userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }

}
