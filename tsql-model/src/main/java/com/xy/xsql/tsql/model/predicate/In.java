package com.xy.xsql.tsql.model.predicate;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.util.ListBlockBuilder;

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


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder();
        b.append(expression)
                .append(useNotOperator ? Keywords.NOT : null)
                .append(Operators.IN)
                .append(Other.GROUP_START)
                .append(Operators.AND);
        if(subquery != null){
            b.append(subquery);
        }else {
            b.append(expressionList);
        }
        b.append(Other.GROUP_END);
        return b.build();
    }

}
