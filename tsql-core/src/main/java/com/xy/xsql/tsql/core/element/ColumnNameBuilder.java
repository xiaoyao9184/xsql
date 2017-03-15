package com.xy.xsql.tsql.core.element;

import com.xy.xsql.tsql.model.element.ColumnName;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnNameBuilder {

    public static ColumnName c(String name){
        return new ColumnName(name);
    }
}
