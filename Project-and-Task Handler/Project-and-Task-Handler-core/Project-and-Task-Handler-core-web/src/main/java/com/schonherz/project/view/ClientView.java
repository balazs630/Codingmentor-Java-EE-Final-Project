package com.schonherz.project.view;

import com.schonherz.project.entity.Client;
import com.schonherz.project.model.ClientModel;
import com.schonherz.project.service.ClientService;
import com.schonherz.project.service.SettingService;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author balazs630 <balazs630@icloud.com>
 */
@ManagedBean(name = "ClientView")
@RequestScoped
public class ClientView implements Serializable {

    public String CLIENT_ID = "client_id";

    @Inject
    private ClientService clientService;

    @Inject
    private ClientModel clients;

    @Inject
    private SettingService settingService;

    @Inject
    private Logger logger;

    private static final int MAX_ROWS = 50;

    public ClientModel getClients() {
        return clients;
    }

    public void setService(ClientService service) {
        this.clientService = service;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Client Edited", ((Client) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Client client = (Client) event.getObject();
        clientService.updateClient(client);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Client) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void setClients(ClientModel clients) {
        this.clients = clients;
    }

    public int getMaxRows() {
        return MAX_ROWS;
    }

    public void onRowsChange() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int newRows = Integer.parseInt(params.get("newRows"));

        if (newRows <= MAX_ROWS) {
            settingService.updatePageSize(newRows);
        }
    }

    public void onRowDelete() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        Long selectedClientId = Long.parseLong(params.get(CLIENT_ID));
        clientService.removeClient(selectedClientId);
    }
}
