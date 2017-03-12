package com.xy.xsql.orm.data.sql.element.predicate;


import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.statements.dml.Select;

import java.util.List;

/**
 * expression [ NOT ] IN ( subquery | expression [ ,...n ] )
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class In implements Predicate {
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
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();
        b.append(expression)
                .append(useNotOperator ? GrammarEnum.NOT : null)
                .append(OperatorEnum.IN)
                .append(OtherEnum.GROUP_START)
                .append(OperatorEnum.AND);
        if(subquery != null){
            b.append(subquery);
        }else {
            b.append(expressionList);
        }
        b.append(OtherEnum.GROUP_END);
        return b.build();
    }

}
