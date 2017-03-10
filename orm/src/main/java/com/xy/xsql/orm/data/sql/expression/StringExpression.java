package com.xy.xsql.orm.data.sql.expression;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class StringExpression implements Expression {

    private String string;

    public StringExpression(String string){
        this.string = string;
    }

    @Override
    public List<Element> toElementList() {
        return new ListElementBuilder()
                .append(this)
                .build();
    }

}
