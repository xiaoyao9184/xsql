package com.xy.xsql.entity.model.sql;

import com.xy.xsql.entity.api.meta.ColumnMeta;

/**
 * Created by xiaoyao9184 on 2017/10/19
 */
public class JoinCondition extends EntityCondition {

    private ColumnMeta leftColumn;
    private ColumnMeta rightColumn;

    public ColumnMeta getLeftColumn() {
        return leftColumn;
    }

    public void setLeftColumn(ColumnMeta leftColumn) {
        this.leftColumn = leftColumn;
    }

    public ColumnMeta getRightColumn() {
        return rightColumn;
    }

    public void setRightColumn(ColumnMeta rightColumn) {
        this.rightColumn = rightColumn;
    }
}
