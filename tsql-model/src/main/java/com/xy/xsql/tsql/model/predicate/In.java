package com.xy.xsql.tsql.model.predicate;

import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.statement.dml.Select;

import java.util.List;

/**
 * expression [ NOT ] IN ( subquery | expression [ ,...n ] )
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class In implements Predicate, Expression {
    //expression [ NOT ] IN ( subquery | expression [ ,...n ] )
    private Expression expression;
    private boolean useNotOperator;
    private Select subquery;
    private List<Expression> expressionList;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public boolean isUseNotOperator() {
        return useNotOperator;
    }

    public void setUseNotOperator(boolean useNotOperator) {
        this.useNotOperator = useNotOperator;
    }

    public Select getSubquery() {
        return subquery;
    }

    public void setSubquery(Select subquery) {
        this.subquery = subquery;
    }

    public List<Expression> getExpressionList() {
        return expressionList;
    }

    public void setExpressionList(List<Expression> expressionList) {
        this.expressionList = expressionList;
    }

}
