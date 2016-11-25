package com.xy.xsql.orm.model;

import com.xy.xsql.orm.annotation.EColumn;

/**
 * 基本行集合
 * Created by xiaoyao9184 on 2016/6/21.
 */
public class BaseRowNumberList {

    @EColumn(name = "rowNumber")
    private int rowNumber;

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
}
