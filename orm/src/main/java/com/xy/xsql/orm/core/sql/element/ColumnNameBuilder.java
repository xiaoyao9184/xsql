package com.xy.xsql.orm.core.sql.element;

import com.xy.xsql.orm.data.sql.element.info.Column;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnNameBuilder {

    public static Column c(String name){
        return new Column(name);
    }
}
