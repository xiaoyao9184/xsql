package com.xy.xsql.orm.data.sql;

/**
 * Created by xiaoyao9184 on 2016/7/14.
 */
@Deprecated
public interface Value extends Expression {
    boolean isUnKnowValue();

    String toValueString();
}
