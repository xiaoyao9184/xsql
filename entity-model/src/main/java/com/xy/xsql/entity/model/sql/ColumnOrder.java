package com.xy.xsql.entity.model.sql;

import com.xy.xsql.entity.api.meta.ColumnMeta;

/**
 * Created by xiaoyao9184 on 2017/10/19
 */
public class ColumnOrder {
    private ColumnMeta columnMeta;
    private Boolean aes;

    public ColumnOrder(ColumnMeta columnMeta) {
        this.columnMeta = columnMeta;
    }

    public ColumnOrder(ColumnMeta columnMeta, Boolean aes) {
        this.columnMeta = columnMeta;
        this.aes = aes;
    }

    public ColumnMeta getColumnMeta() {
        return columnMeta;
    }

    public void setColumnMeta(ColumnMeta columnMeta) {
        this.columnMeta = columnMeta;
    }

    public Boolean getAes() {
        return aes;
    }

    public void setAes(Boolean aes) {
        this.aes = aes;
    }
}
