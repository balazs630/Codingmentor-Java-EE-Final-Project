package com.schonherz.project.service;

import com.schonherz.project.entity.User;
import com.schonherz.project.repository.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by darvasr on 9/7/16.
 */
@ManagedBean(name = "UserService")
@ApplicationScoped
@Stateless
public class UserService {

    @EJB
    private UserRepository repository;

    //TODO create user with DTO
    public User createUser() {
        return null;
    }

    public User createUser(User user) {
        return repository.create(user);
    }

    public User getUserById(Long id) {
        User user = repository.find(id);
        if (user == null) {
            throw new EntityNotFoundException("User not found with this id!");
        } else {
            return user;
        }
    }

    //TODO update user with DTO
    public User updateUser(User user) {
        return repository.update(user);
    }

    public User removeUser(Long id) {
        return repository.delete(id);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public boolean hasUser(String username) {
        return repository.hasUser(username);
    }

    public boolean validate(User user) {
        return repository.validate(user.getUsername(), user.getPassword());
    }

    public boolean validate(String username, String password) {
        return repository.validate(username, password);
    }

    public User getUserByUsername(String username) {
        return repository.getUserByUsername(username);
    }

    public String getPassword(String username) {
        return repository.getPassword(username);
    }

}
