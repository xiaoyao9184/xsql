package com.xy.xsql.tsql.core.element;

import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnNameBuilder {

    public static ColumnName c(String name){
        return new ColumnName(name);
    }

    public static ColumnName t_c(TableName tableName, String name){
        return new ColumnName(tableName,name);
    }

    public static ColumnName c(String tableName, String name){
        return new ColumnName(new TableName(tableName),name);
    }
}
