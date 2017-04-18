package com.schonherz.project.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Created by darvasr on 9/7/16.
 */
abstract class EntityRepositoryFacade<T> {

    protected final Class<T> clazz;

    @PersistenceContext(name = "ProjectHandlerPU")
    private EntityManager entityManager;

    public EntityRepositoryFacade(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    public T create(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    public T update(T entity) {
        return getEntityManager().merge(entity);
    }

    public T find(Object id) {
        return getEntityManager().find(clazz, id);
    }

    public List<T> findAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(clazz);
        cq.select(cq.from(clazz));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public Long count() {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(clazz)));
        return entityManager.createQuery(cq).getSingleResult();
    }

    public T delete(Object id) {
        T entity = find(id);
        if (entity != null) {
            getEntityManager().remove(entity);
            return entity;
        } else {
            return null;
        }
    }

}
