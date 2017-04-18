package com.schonherz.project.service;

import com.schonherz.project.entity.Record;
import com.schonherz.project.repository.RecordRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityNotFoundException;

/**
 * Created by darvasr on 9/7/16.
 */
@ManagedBean(name = "RecordService")
@ApplicationScoped
@Stateless
public class RecordService {

    @EJB
    private RecordRepository repository;

    //TODO create record with DTO
    public Record createRecord() {
        return null;
    }

    public Record createRecord(Record record) {
        return repository.create(record);
    }

    public Record getRecordById(Long id) {
        Record record = repository.find(id);
        if (record == null) {
            throw new EntityNotFoundException("Record not found with this id!");
        } else {
            return record;
        }
    }

    public Record updateRecord(Record record) {
        return repository.update(record);
    }

    public List<Record> getRecords() {
        return repository.findAll();
    }
    
    public Long countRecords() {
        return repository.count();
    }
    
    public Record getLastRecordByUsername(String username) {
        return repository.getLastRecordByUsername(username);
    }

    public Record getRecordAtPosition(String username, int position) {
        return repository.getRecordAtPosition(username, position);
    }
}
