package com.xy.xsql.model.page;

/**
 * Page
 * Created by xiaoyao9184 on 2016/1/11.
 */
public class Page<ROWTYPE> {
    private PageQuery<ROWTYPE> query;
    private PageResult<ROWTYPE> result;


    public PageQuery<ROWTYPE> getQuery() {
        return query;
    }

    public void setQuery(PageQuery<ROWTYPE> query) {
        this.query = query;
    }

    public PageResult<ROWTYPE> getResult() {
        return result;
    }

    public void setResult(PageResult<ROWTYPE> result) {
        this.result = result;
    }

    /**
     * set PageQuery
     * @param query PageQuery
     * @return This
     */
    public Page<ROWTYPE> withPageQuery(PageQuery<ROWTYPE> query) {
        this.query = query;
        return this;
    }

    /**
     * set PageResult
     * @param result PageResult
     * @return This
     */
    public Page<ROWTYPE> withPageResult(PageResult<ROWTYPE> result) {
        this.result = result;
        return this;
    }
}
