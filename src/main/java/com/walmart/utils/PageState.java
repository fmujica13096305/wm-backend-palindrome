package com.walmart.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
public class PageState implements Serializable {
    public static final String PAGE_STATE = "pageState";
    private static final long serialVersionUID = 1L;
    private Integer page = 0;
    private Integer perPage = 50;
    private Integer totalPages = 0;
    private Long totalEntries = 0L;
    private String order;
    private String sortBy;

    public PageState() {
        this.order = null;
        this.sortBy = null;
    }

    public PageState(Integer page, Integer perPage) {
        this.page = page;
        this.perPage = perPage;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return this.perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalEntries() {
        return this.totalEntries;
    }

    public void setTotalEntries(Long totalEntries) {
        this.totalEntries = totalEntries;
    }

    public String getSortBy() {
        return this.sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
