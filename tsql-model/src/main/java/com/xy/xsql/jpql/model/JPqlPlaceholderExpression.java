package com.xy.xsql.jpql.model;

import com.xy.xsql.tsql.model.elements.expressions.UnknownExpression;

/**
 * Created by xiaoyao9184 on 2017/9/14.
 */
public class JPqlPlaceholderExpression extends UnknownExpression {
    public JPqlPlaceholderExpression(int index) {
        super("?" + index);
    }
}
