package com.xy.xsql.orm.core.sql.element;

import com.xy.xsql.orm.data.sql.element.info.TableName;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class TableNameBuilder {

    public static TableName t(String name){
        return new TableName(name);
    }
}
