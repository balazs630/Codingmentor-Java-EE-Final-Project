package com.schonherz.project.repository;

import com.schonherz.project.entity.Client;
import com.schonherz.project.interceptor.binding.Logging;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Created by darvasr on 9/7/16.
 */
@Stateless
@Logging
public class ClientRepository extends LoadableEntityRepositoryFacade<Client> {

    public ClientRepository() {
        super(Client.class);
    }

    public boolean hasClient(String name) {
        Long clientCount = getEntityManager()
                .createQuery("SELECT COUNT(c.id) FROM Client c WHERE c.name=:param_name", Long.class)
                .setParameter("param_name", name)
                .getSingleResult();
        return clientCount > 0;
    }

    public Client getClientByName(String name) {
        List<Client> clientList = getEntityManager().createQuery("SELECT c FROM Client c WHERE c.name = :param_name", Client.class)
                .setParameter("param_name", name)
                .getResultList();
        if (!clientList.isEmpty()) {
            return clientList.get(0);
        }
        return null;
    }
}
