package com.xy.xsql.tsql.core.element;

import com.xy.xsql.tsql.model.element.TableName;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class TableNameBuilder {

    public static TableName t(String name){
        return new TableName(name);
    }
}
