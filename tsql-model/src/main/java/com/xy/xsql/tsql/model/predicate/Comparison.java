package com.xy.xsql.tsql.model.predicate;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * TODO maybe use Comparison.Comparison named
 * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Comparison implements Predicate, Expression {
    //{ expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
    private Expression expression;
    private com.xy.xsql.tsql.model.operator.Operator operator;
    private Expression operatorExpression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public com.xy.xsql.tsql.model.operator.Operator getOperator() {
        return operator;
    }

    public void setOperator(com.xy.xsql.tsql.model.operator.Operator operator) {
        this.operator = operator;
    }

    public Expression getOperatorExpression() {
        return operatorExpression;
    }

    public void setOperatorExpression(Expression operatorExpression) {
        this.operatorExpression = operatorExpression;
    }


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder();
        b.append(expression)
                .append(operator)
                .append(operatorExpression);
        return b.build();
    }
}
