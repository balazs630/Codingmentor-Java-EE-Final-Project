package com.schonherz.project.controller;

import com.schonherz.project.entity.Client;
import com.schonherz.project.service.ClientService;
import com.schonherz.project.util.Message;
import com.schonherz.project.util.Popup;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author balazs630 <balazs630@icloud.com>
 */
@SessionScoped
@ManagedBean(name = "addnewclient")
public class AddNewClientController implements Serializable {

    private Client client;

    @Inject
    private ClientService clientService;

    @Inject
    private Logger logger;

    @PostConstruct
    private void init() {
        client = new Client();
    }

    public String addClient() {
        Client newClient = getNewClient();
        clientService.createClient(newClient);
        Popup.pushPopup(Message.CLIENT_ADD_SUCCESS_BODY, Message.CLIENT_ADD_SUCCESS_HEADER);
        return "/administration/client.xhtml?faces-redirect=true";
    }

    private Client getNewClient() {
        Client newClient = new Client();
        try {
            BeanUtils.copyProperties(newClient, client);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return newClient;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
