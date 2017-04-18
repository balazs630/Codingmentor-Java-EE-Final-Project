package com.schonherz.project.service;

import com.schonherz.project.entity.Role;
import com.schonherz.project.repository.RoleRepository;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Janos Harsanyi <hajani003@gmail.com>
 */
@ManagedBean(name = "RoleService")
@ApplicationScoped
@Stateless
public class RoleService {

    @EJB
    private RoleRepository repository;

    public Role getRoleByName(String roleName) {
        return repository.getRoleByName(roleName);
    }

    public void update(Role userRole) {
        repository.update(userRole);
    }

}
