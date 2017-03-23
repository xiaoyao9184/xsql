package com.xy.xsql.tsql.core.datatype;

import com.xy.xsql.tsql.model.datatype.ColumnDefinition;

import static com.xy.xsql.tsql.core.datatype.DataTypes.*;

/**
 * ColumnDefinitionFactory
 * Created by xiaoyao9184 on 2017/3/23.
 */
public class ColumnDefinitionFactory {


    public static ColumnDefinition c_int(String name){
        return new ColumnDefinition(name)
                .withDataType(_int());
    }

    public static ColumnDefinition c_smallint(String name){
        return new ColumnDefinition(name)
                .withDataType(_smallint());
    }

    public static ColumnDefinition c_varchar(String name,Integer len){
        return new ColumnDefinition(name)
                .withDataType(_varchar(len));
    }

    public static ColumnDefinition c_datetime(String name){
        return new ColumnDefinition(name)
                .withDataType(_datetime());
    }

}
