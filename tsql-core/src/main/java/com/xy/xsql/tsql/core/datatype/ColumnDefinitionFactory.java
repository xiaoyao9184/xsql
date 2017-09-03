package com.xy.xsql.tsql.core.datatype;

import com.xy.xsql.tsql.core.element.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.model.element.column.ColumnConstraint;
import com.xy.xsql.tsql.model.element.column.ColumnDefinition;
import com.xy.xsql.tsql.model.element.constraint.NullOrNotNull;
import com.xy.xsql.tsql.model.element.constraint.PrimaryUnique;

import java.util.Arrays;
import java.util.Collections;

import static com.xy.xsql.tsql.core.datatype.DataTypes.*;

/**
 * ColumnDefinitionFactory
 * Created by xiaoyao9184 on 2017/3/23.
 */
public class ColumnDefinitionFactory {


    public static ColumnDefinition c_int(String name){
        ColumnDefinition columnDefinition = new ColumnDefinition(name);
        columnDefinition.setDataType(_int());
        return columnDefinition;
    }

    public static ColumnDefinition c_smallint(String name){
        ColumnDefinition columnDefinition = new ColumnDefinition(name);
        columnDefinition.setDataType(_smallint());
        return columnDefinition;
    }

    public static ColumnDefinition c_varchar(String name,Integer len){
        ColumnDefinition columnDefinition = new ColumnDefinition(name);
        columnDefinition.setDataType(_varchar(len));
        return columnDefinition;
    }

    public static ColumnDefinition c_datetime(String name){
        ColumnDefinition columnDefinition = new ColumnDefinition(name);
        columnDefinition.setDataType(_datetime());
        return columnDefinition;
    }


    public static ColumnDefinition c_null(ColumnDefinition columnDefinition){
        ColumnConstraint cc = new ColumnConstraint();
        cc.setConstraint(new NullOrNotNull(true));
        return new ColumnDefinitionBuilder<>(columnDefinition)
                .withColumnConstraint(Collections.singletonList(cc))
                .build();
    }

    public static ColumnDefinition c_not_null(ColumnDefinition columnDefinition){
        ColumnConstraint cc = new ColumnConstraint();
        cc.setConstraint(new NullOrNotNull(false));
        return new ColumnDefinitionBuilder<>(columnDefinition)
                .withColumnConstraint(Collections.singletonList(cc))
                .build();
    }

    public static ColumnDefinition c_primary_key(ColumnDefinition columnDefinition){
        ColumnConstraint cc = new ColumnConstraint();
        cc.setConstraint(new PrimaryUnique(true));
        return new ColumnDefinitionBuilder<>(columnDefinition)
                .withColumnConstraint(Collections.singletonList(cc))
                .build();
    }

    public static ColumnDefinition c_unique(ColumnDefinition columnDefinition){
        ColumnConstraint cc = new ColumnConstraint();
        cc.setConstraint(new PrimaryUnique(false));
        return new ColumnDefinitionBuilder<>(columnDefinition)
                .withColumnConstraint(Collections.singletonList(cc))
                .build();
    }



}
