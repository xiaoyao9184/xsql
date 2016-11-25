package com.xy.xsql.orm.data.sql.expression;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class NumberString implements Expression {

    private double number;

    public NumberString(double number){
        this.number = number;
    }

    @Override
    public List<Element> toElementList() {
        return null;
    }
}
