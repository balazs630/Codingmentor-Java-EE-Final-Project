package com.schonherz.project.repository;

import com.schonherz.project.entity.Record;
import com.schonherz.project.interceptor.binding.Logging;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Created by darvasr on 9/7/16.
 */
@Stateless
@Logging
public class RecordRepository extends EntityRepositoryFacade<Record> {

    public RecordRepository() {
        super(Record.class);
    }

    public Record getLastRecordByUsername(String username) {
        List<Record> recordList = getEntityManager().createQuery("SELECT r FROM Record r WHERE r.user.username = :param_username ORDER BY r.loginTime DESC", Record.class)
                .setParameter("param_username", username)
                .getResultList();
        if (!recordList.isEmpty()) {
            return recordList.get(0);
        }
        return null;
    }

    public Record getRecordAtPosition(String username, int position) {

        List<Record> recordList = getEntityManager().createQuery("SELECT r FROM Record r WHERE r.user.username = :param_username ORDER BY r.loginTime DESC", Record.class)
                .setParameter("param_username", username)
                .getResultList();
        if (!recordList.isEmpty()) {
            if (recordList.size() >= position) {
                return recordList.get(position - 1);
            } else {
                //throw new IndexOutOfBoundsException("size: " + recordList.size() + ", index: " + position);
            }
        }
        return null;
    }

}
