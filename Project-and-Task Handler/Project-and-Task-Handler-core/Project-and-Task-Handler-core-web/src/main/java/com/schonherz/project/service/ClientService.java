package com.schonherz.project.service;

import com.schonherz.project.dto.LoadResultDTO;
import com.schonherz.project.entity.Client;
import com.schonherz.project.repository.ClientRepository;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityNotFoundException;
import org.primefaces.model.SortMeta;

/**
 * Created by darvasr on 9/7/16.
 */
@ManagedBean(name = "ClientService")
@ApplicationScoped
@Stateless
public class ClientService {

    @EJB
    private ClientRepository repository;

    //TODO create with dto
    public Client createClient() {
        return null;
    }

    public Client createClient(Client client) {
        return repository.create(client);
    }

    public Client getClientById(Long id) {
        Client client = repository.find(id);
        if (client == null) {
            throw new EntityNotFoundException("Client not found with this id!");
        } else {
            return client;
        }
    }

    //TODO update with dto
    public Client updateClient(Client client) {
        return repository.update(client);
    }

    public Client removeClient(Long id) {
        return repository.delete(id);
    }

    public List<Client> getClients() {
        return repository.findAll();
    }

    public Client getClientByName(String name) {
        return repository.getClientByName(name);
    }

    public boolean hasClient(String name) {
        return repository.hasClient(name);
    }

    public LoadResultDTO<Client> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        return repository.load(first, pageSize, multiSortMeta, filters);
    }
    
    public Long countClients() {
        return repository.count();
    }
}
