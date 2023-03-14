package com.ding.domain;

/**
 * @author ding
 * @create 28 23:29
 * @description
 */
public class BasePage {
    private Integer page =0;
    private Integer pageSize =20;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
