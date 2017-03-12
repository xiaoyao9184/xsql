package com.xy.xsql.orm.data.sql.element.predicate;


import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;

import java.util.List;

/**
 * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Operator implements Predicate {
    //{ expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
    private Expression expression;
    private OperatorEnum operatorEnum;
    private Expression operatorExpression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public OperatorEnum getOperatorEnum() {
        return operatorEnum;
    }

    public void setOperatorEnum(OperatorEnum operatorEnum) {
        this.operatorEnum = operatorEnum;
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
                .append(operatorEnum)
                .append(operatorExpression);
        return b.build();
    }
}
