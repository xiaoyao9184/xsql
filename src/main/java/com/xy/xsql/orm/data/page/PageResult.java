package com.xy.xsql.orm.data.page;

import java.util.List;

/**
 * PageResult
 * Created by xiaoyao9184 on 2016/12/1.
 */
public class PageResult<ROWTYPE> {
    private List<ROWTYPE> rowData;
    private Integer rowNumber;
    private Integer rowCount;
    private Integer pageNumber;
    private Integer pageCount;


    public List<ROWTYPE> getRowData() {
        return rowData;
    }

    public void setRowData(List<ROWTYPE> rowData) {
        this.rowData = rowData;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }


    /**
     * Set Row Data
     * @param rowData Row Data
     * @return This
     */
    public PageResult<ROWTYPE> withRowData(List<ROWTYPE> rowData) {
        this.rowData = rowData;
        return this;
    }

    /**
     * Set Row Count, all Row Count, not Row Data size
     * @param rowCount Row Count
     * @return This
     */
    public PageResult<ROWTYPE> withRowCount(Integer rowCount) {
        this.rowCount = rowCount;
        return this;
    }

    /**
     * Set Page Count
     * @param pageCount Page Count
     * @return This
     */
    public PageResult<ROWTYPE> withPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    /**
     * Set Row Number, Number is start with 1
     * @param rowNumber Row Number
     * @return This
     */
    public PageResult<ROWTYPE> withRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    /**
     * Set Page Number, Number is start with 1
     * @param pageNumber Page Number
     * @return This
     */
    public PageResult<ROWTYPE> withPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }
}
