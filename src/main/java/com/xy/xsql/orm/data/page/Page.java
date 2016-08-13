package com.xy.xsql.orm.data.page;

import java.util.List;

/**
 * 分页对象
 * Created by xiaoyao9184 on 2016/1/11.
 */
public class Page<T> {
    private int pageSize;
    private int pageNumber;
    private int pageCount;  //pageTotal
    private int rowNumber;  //totalStart
    private int rowCount;   //totalCount
    private List<T> pageList;

    public Page(){}

    /**
     * 创建查询Page
     * @param pageNumber 开始页号
     * @param pageSize 页大小
     */
    public Page(int pageNumber, int pageSize){
        this.setPageSize(pageSize);
        this.setPageNumber(pageNumber);
    }

    /**
     * 创建查询Page
     * @deprecated 不建议使用，但请保留
     * @param rowNumber 开始行号
     * @param pageSize 页大小
     */
    @Deprecated
    public Page(long rowNumber, int pageSize){
        this.setPageSize(pageSize);
        if(rowNumber != 0){
            long pn = rowNumber / pageSize;
            if(pn > Integer.MAX_VALUE){
                pn = Integer.MAX_VALUE;
            }
            this.setPageNumber((int)pn);
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
}
