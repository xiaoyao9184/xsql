package com.xy.xsql.tsql.model.predicate;

import com.xy.xsql.tsql.model.expression.BinaryExpression;
import com.xy.xsql.tsql.model.expression.Expression;

/**
 * TODO maybe use Comparison.Comparison named
 * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Comparison
        extends BinaryExpression
        implements Predicate, Expression {
    //expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
    private Expression expression;
    private com.xy.xsql.tsql.model.operator.Comparison operator;
    private Expression operatorExpression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public com.xy.xsql.tsql.model.operator.Comparison getOperator() {
        return operator;
    }

    public void setOperator(com.xy.xsql.tsql.model.operator.Comparison operator) {
        this.operator = operator;
    }

    public Expression getOperatorExpression() {
        return operatorExpression;
    }

    public void setOperatorExpression(Expression operatorExpression) {
        this.operatorExpression = operatorExpression;
    }

}
