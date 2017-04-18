package com.schonherz.project.dto;

import java.util.List;

/**
 *
 * @author Janos Harsanyi <hajani003@gmail.com>
 * @param <T>
 */
public class LoadResultDTO<T> {

    private List<T> rows;
    private Long totalCount;

    public LoadResultDTO() {
    }

    public LoadResultDTO(List<T> result, Long totalCount) {
        this.rows = result;
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "LoadResultDTO{" + "rows=" + rows + ", totalCount=" + totalCount + '}';
    }

}
