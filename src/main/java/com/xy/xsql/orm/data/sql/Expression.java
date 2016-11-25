package com.xy.xsql.orm.data.sql;

import java.util.List;

/**
 * Expression
 * Created by xiaoyao9184 on 2016/10/22.
 */
public interface Expression extends Element {
    /**
     * to elements
     * @return elements
     */
    List<Element> toElementList();
}
