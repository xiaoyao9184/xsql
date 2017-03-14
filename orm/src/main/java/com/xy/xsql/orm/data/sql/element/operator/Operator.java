package com.xy.xsql.orm.data.sql.element.operator;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public interface Operator extends Element {
    java.lang.String getKeyword();
    Type getType();
}
