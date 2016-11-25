package com.xy.xsql.orm.data.sql.expression.operator.relational;

import com.xy.xsql.orm.build.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OtherEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * (expression, expression...)
 *
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class Group implements Expression {

    private List<Expression> expressionList;

    public Group withExpression(Expression expression){
        if(this.expressionList == null){
            this.expressionList = new ArrayList<>();
        }
        this.expressionList.add(expression);
        return this;
    }

    public List<Expression> getExpressionList(){
        return expressionList;
    }


    @Override
    public List<Element> toElementList() {
        List<Element> list = new ArrayList<>();
        for (Expression ex: expressionList) {
            list.add(ex);
        }

        return new ListElementBuilder()
                .append(OtherEnum.GROUP_START)
                .append(list,OtherEnum.DELIMITER)
                .append(OtherEnum.GROUP_END)
                .build();
    }
}
