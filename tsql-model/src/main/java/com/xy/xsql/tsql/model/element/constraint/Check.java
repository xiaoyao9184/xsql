package com.xy.xsql.tsql.model.element.constraint;

import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * Created by xiaoyao9184 on 2017/8/7.
 */
public class Check implements Constraint {
    //[ NOT FOR REPLICATION ]
    private boolean useNotForReplication;
    //( logical_expression )
    private Expression logicalExpression;

    public boolean isUseNotForReplication() {
        return useNotForReplication;
    }

    public void setUseNotForReplication(boolean useNotForReplication) {
        this.useNotForReplication = useNotForReplication;
    }

    public Expression getLogicalExpression() {
        return logicalExpression;
    }

    public void setLogicalExpression(Expression logicalExpression) {
        this.logicalExpression = logicalExpression;
    }
}
