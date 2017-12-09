package com.xy.xsql.entity.model.sql;

import com.xy.xsql.entity.api.meta.ColumnMeta;

import java.util.function.Function;

/**
 * Created by xiaoyao9184 on 2017/10/19
 */
public class ColumnReturn {
    private ColumnMeta columnMeta;
    private String name;


    public ColumnReturn(ColumnMeta columnMeta) {
        this.columnMeta = columnMeta;
    }

    public ColumnReturn(ColumnMeta columnMeta, String name) {
        this.columnMeta = columnMeta;
        this.name = name;
    }

    public ColumnMeta getColumnMeta() {
        return columnMeta;
    }

    public void setColumnMeta(ColumnMeta columnMeta) {
        this.columnMeta = columnMeta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
