package com.xy.xsql.orm.data.sql;

import java.util.List;

/**
 * Element List
 * elements
 * Created by xiaoyao9184 on 2016/12/20.
 */
public interface ElementList extends Element {
    /**
     * to elements
     * @return elements
     */
    List<Element> toElementList();
}
