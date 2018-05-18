package com.xy.xsql.tsql.model.element.column;

import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.statements.alter.table.Add;
import com.xy.xsql.tsql.model.statements.create.table.DiskBasedCreateTable;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql
 * Created by xiaoyao9184 on 2017/8/7.
 */
public class ComputedColumnDefinition
        extends ColumnName
        implements DiskBasedCreateTable.Item, Add.AddItem {

    //AS computed_column_expression
    private Expression computedColumnExpression;

    //[ PERSISTED [ NOT NULL ] ]
    private Boolean persistedNotNull;

    //
    private ColumnConstraint constraint;

    public Expression getComputedColumnExpression() {
        return computedColumnExpression;
    }

    public void setComputedColumnExpression(Expression computedColumnExpression) {
        this.computedColumnExpression = computedColumnExpression;
    }

    public Boolean getPersistedNotNull() {
        return persistedNotNull;
    }

    public void setPersistedNotNull(Boolean persistedNotNull) {
        this.persistedNotNull = persistedNotNull;
    }

    public ColumnConstraint getConstraint() {
        return constraint;
    }

    public void setConstraint(ColumnConstraint constraint) {
        this.constraint = constraint;
    }
}
