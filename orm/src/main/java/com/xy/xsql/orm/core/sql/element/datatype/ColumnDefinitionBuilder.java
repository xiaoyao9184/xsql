package com.xy.xsql.orm.core.sql.element.datatype;

import com.xy.xsql.tsql.model.datatype.ColumnDefinition;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnDefinitionBuilder {

    public static ColumnDefinition smallint(String name){
        return new ColumnDefinition(name);
    }

    public static ColumnDefinition varchar(String name,Integer len){
        return new ColumnDefinition(name,"varchar",len);
    }

    public static ColumnDefinition datetime(String name){
        return new ColumnDefinition(name,"datetime");
    }

}
