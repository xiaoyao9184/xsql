package com.xy.xsql.tsql.model.predicate;

import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * EXISTS ( subquery )
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Exists implements Predicate, Expression {
    //EXISTS ( subquery )
    private Expression expression;
    private Select subquery;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Select getSubquery() {
        return subquery;
    }

    public void setSubquery(Select subquery) {
        this.subquery = subquery;
    }

}
