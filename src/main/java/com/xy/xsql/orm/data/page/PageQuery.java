package com.xy.xsql.orm.data.page;

/**
 * PageQuery
 * Created by xiaoyao9184 on 2016/12/1.
 */
public class PageQuery<ROWTYPE> {
    protected Integer pageStart;
    protected Integer pageSize;
    protected Class<ROWTYPE> tarClass;
    protected boolean startWithZero = true;


    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Class<ROWTYPE> getTarClass() {
        return tarClass;
    }

    public void setTarClass(Class<ROWTYPE> tarClass) {
        this.tarClass = tarClass;
    }

    public PageQuery<ROWTYPE> withTarClass(Class<ROWTYPE> tarClass) {
        this.tarClass = tarClass;
        return this;
    }

    public boolean isStartWithZero() {
        return startWithZero;
    }

    public void setStartWithZero(boolean startWithZero) {
        this.startWithZero = startWithZero;
    }


    /**
     * Set Page start ZERO flag
     * @param startWithZero start zero
     * @return This
     */
    public PageQuery<ROWTYPE> withStartWithZero(boolean startWithZero) {
        this.startWithZero = startWithZero;
        return this;
    }

    /**
     * Set Page Size
     * @param pageSize Page Size
     * @return This
     */
    public PageQuery<ROWTYPE> withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * Set Page Start
     * @param pageStart Page Start
     * @return This
     */
    public PageQuery<ROWTYPE> withPageNo(Integer pageStart) {
        this.pageStart = pageStart;
        return this;
    }


    /**
     * Get Page Start with 1
     * @return Start with 1
     */
    public int getPageStartWithOne() {
        return this.isStartWithZero() ? this.getPageStart() + 1 : this.getPageStart();
    }

    /**
     * Get Page Start with 0
     * @return Start with 0
     */
    public int getPageStartWithZero() {
        return this.isStartWithZero() ? this.getPageStart() : this.getPageStart() - 1;
    }
}
