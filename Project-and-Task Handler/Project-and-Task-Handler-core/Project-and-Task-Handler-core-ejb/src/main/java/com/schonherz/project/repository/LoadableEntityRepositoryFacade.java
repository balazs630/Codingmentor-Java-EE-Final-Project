package com.schonherz.project.repository;

import com.schonherz.project.dto.LoadResultDTO;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Janos Harsanyi <hajani003@gmail.com>
 * @param <T>
 */
public abstract class LoadableEntityRepositoryFacade<T> extends EntityRepositoryFacade<T> {

    public LoadableEntityRepositoryFacade(Class<T> clazz) {
        super(clazz);
    }

    private void addFilters(CriteriaQuery<?> cq, Root<?> root, Map<String, Object> filters) {
        if (filters == null) {
            return;
        }
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String property = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                Path<String> propertyPath = root.get(property);
                cq.where(cb.like(propertyPath, "%" + (String) value + "%"));
            } else {
                cq.where(cb.equal(root.get(property), value));
            }
        }
    }

    private void addSorts(CriteriaQuery<?> cq, Root<?> root, List<SortMeta> multiSortMeta) {
        if (multiSortMeta == null) {
            return;
        }
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        for (SortMeta sort : multiSortMeta) {
            Path propertyPath = root.get(sort.getSortField());
            SortOrder sortOrder = sort.getSortOrder();
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(propertyPath));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(propertyPath));
            }
        }
    }

    private Long loadCount(Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(clazz);
        cq.select(cb.count(root));
        addFilters(cq, root, filters);
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    public LoadResultDTO<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);

        addFilters(cq, root, filters);
        addSorts(cq, root, multiSortMeta);

        TypedQuery<T> query = getEntityManager().createQuery(cq);
        query.setFirstResult(first);

        // show all rows if pageSize zero
        if (pageSize > 0) {
            query.setMaxResults(pageSize);
        }

        List<T> result = query.getResultList();
        Long totalCount = loadCount(filters);

        return new LoadResultDTO<>(result, totalCount);
    }

}
