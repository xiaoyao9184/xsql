package com.xy.xsql.orm.data.sql.element.predicate;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;

import java.util.List;

/**
 * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Operator implements Predicate {
    //{ expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
    private Expression expression;
    private com.xy.xsql.orm.data.sql.element.operator.Operator operator;
    private Expression operatorExpression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public com.xy.xsql.orm.data.sql.element.operator.Operator getOperator() {
        return operator;
    }

    public void setOperator(com.xy.xsql.orm.data.sql.element.operator.Operator operator) {
        this.operator = operator;
    }

    public Expression getOperatorExpression() {
        return operatorExpression;
    }

    public void setOperatorExpression(Expression operatorExpression) {
        this.operatorExpression = operatorExpression;
    }


    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();
        b.append(expression)
                .append(operator)
                .append(operatorExpression);
        return b.build();
    }
}
