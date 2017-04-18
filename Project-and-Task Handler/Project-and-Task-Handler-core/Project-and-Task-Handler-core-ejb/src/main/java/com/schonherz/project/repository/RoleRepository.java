package com.schonherz.project.repository;

import com.schonherz.project.entity.Role;
import com.schonherz.project.interceptor.binding.Logging;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Janos Harsanyi <hajani003@gmail.com>
 */
@Stateless
@Logging
public class RoleRepository extends EntityRepositoryFacade<Role> {

    public RoleRepository() {
        super(Role.class);
    }

    public Role getRoleByName(String roleName) {
        List<Role> userList = getEntityManager().createQuery("SELECT r FROM Role r WHERE r.name = :param_name", Role.class)
                .setParameter("param_name", roleName)
                .getResultList();
        if (!userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }
}
