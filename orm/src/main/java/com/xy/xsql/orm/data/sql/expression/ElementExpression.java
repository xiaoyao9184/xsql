package com.xy.xsql.orm.data.sql.expression;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ElementExpression implements Expression {
    private ElementList elementList;
    private Element element;


    public ElementExpression(Element element) {
        this.element = element;
    }

    public ElementExpression(ElementList elementList) {
        this.elementList = elementList;
    }

    @Override
    public List<Element> toElementList() {
        return this.elementList.toElementList();
    }
}
