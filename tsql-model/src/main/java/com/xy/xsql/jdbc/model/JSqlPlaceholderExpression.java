package com.xy.xsql.jdbc.model;

import com.xy.xsql.tsql.model.elements.expressions.UnknownExpression;

/**
 * JDBC SQL Placeholder
 * Created by xiaoyao9184 on 2017/9/14.
 */
public class JSqlPlaceholderExpression extends UnknownExpression {
    public JSqlPlaceholderExpression() {
        super("?");
    }
}
