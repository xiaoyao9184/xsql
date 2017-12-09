package com.xy.xsql.entity.model.sql;

import com.xy.xsql.entity.api.meta.ColumnMeta;

/**
 * Created by xiaoyao9184 on 2017/10/19
 */
public class ColumnCondition extends EntityCondition {

    private ColumnMeta columnMeta;
    private PredicateType predicateType;
    private Object value;

    public ColumnMeta getColumnMeta() {
        return columnMeta;
    }

    public void setColumnMeta(ColumnMeta columnMeta) {
        this.columnMeta = columnMeta;
    }

    public PredicateType getPredicateType() {
        return predicateType;
    }

    public void setPredicateType(PredicateType predicateType) {
        this.predicateType = predicateType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
