package com.schonherz.project.model;

import com.schonherz.project.controller.RegistrationController;
import com.schonherz.project.dto.LoadResultDTO;
import com.schonherz.project.entity.Client;
import com.schonherz.project.service.ClientService;
import com.schonherz.project.service.SettingService;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author Janos Harsanyi <hajani003@gmail.com>
 */
@SessionScoped
public class ClientModel extends LazyDataModel<Client> {

    @Inject
    private ClientService clientService;

    @Inject
    private SettingService settingService;

    public SettingService getSettingService() {
        return settingService;
    }

    public void setSettingService(SettingService settingService) {
        this.settingService = settingService;
    }

    private List<Client> data;

    private long rowCount;

    @PostConstruct
    private void init() {
        rowCount = clientService.countClients();
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public int getRowCount() {
        return (int) rowCount;
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public List<Client> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        settingService.updatePageSize(pageSize);
        LoadResultDTO<Client> loadResult = clientService.load(first, pageSize, multiSortMeta, filters);
        data = loadResult.getRows();
        rowCount = loadResult.getTotalCount();
        return data;
    }

    public int getUserPageSize() {
        Integer userPageSize = settingService.getCurrentUserSetting().getPaging();
        if (userPageSize == null) {
            return RegistrationController.DEFAULT_PAGE_SIZE;
        }
        return userPageSize;
    }

    @Override
    public Client getRowData(String rowKeyString) {
        Long id = Long.parseLong(rowKeyString);
        for (Client client : data) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(Client client) {
        return client.getId();
    }
}
