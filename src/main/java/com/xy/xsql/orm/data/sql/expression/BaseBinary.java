package com.xy.xsql.orm.data.sql.expression;

import com.xy.xsql.orm.build.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 */
public abstract class BaseBinary<T extends Element> implements Expression {

    protected boolean not = false;
    protected Expression leftExpression;
    protected T t;
    protected Expression rightExpression;

    public BaseBinary<T> withNot(boolean not){
        this.not = not;
        return this;
    }

    public BaseBinary<T> withLeftExpression(Expression leftExpression){
        this.leftExpression = leftExpression;
        return this;
    }
    public BaseBinary<T> withNot(T t){
        this.t = t;
        return this;
    }
    public BaseBinary<T> withRightExpression(Expression rightExpression){
        this.rightExpression = rightExpression;
        return this;
    }

    @Override
    public List<Element> toElementList(){
        return new ListElementBuilder()
                .append(not ? OperatorEnum.NOT : null)
                .append(leftExpression)
                .append(t)
                .append(rightExpression)
                .build();
    }

}
