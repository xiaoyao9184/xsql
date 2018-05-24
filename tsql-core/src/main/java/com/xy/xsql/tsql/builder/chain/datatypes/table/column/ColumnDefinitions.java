package com.xy.xsql.tsql.builder.chain.datatypes.table.column;

import com.xy.xsql.tsql.model.datatypes.table.column.ColumnConstraint;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.constraint.NullOrNotNull;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;

import java.util.Collections;

import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes.*;

/**
 * ColumnDefinition Factory
 * Created by xiaoyao9184 on 2017/3/23.
 */
@SuppressWarnings("unused")
public class ColumnDefinitions {

    /**
     * Quick build
     * @param name name
     * @return ColumnDefinition
     */
    public static ColumnDefinition c_int(String name){
        ColumnDefinition columnDefinition = new ColumnDefinition(name);
        columnDefinition.setDataType(_int());
        return columnDefinition;
    }

    /**
     * Quick build
     * @param name name
     * @return ColumnDefinition
     */
    public static ColumnDefinition c_smallint(String name){
        ColumnDefinition columnDefinition = new ColumnDefinition(name);
        columnDefinition.setDataType(_smallint());
        return columnDefinition;
    }

    /**
     * Quick build
     * @param name name
     * @return ColumnDefinition
     */
    public static ColumnDefinition c_varchar(String name,Integer len){
        ColumnDefinition columnDefinition = new ColumnDefinition(name);
        columnDefinition.setDataType(_varchar(len));
        return columnDefinition;
    }

    /**
     * Quick build
     * @param name name
     * @return ColumnDefinition
     */
    public static ColumnDefinition c_datetime(String name){
        ColumnDefinition columnDefinition = new ColumnDefinition(name);
        columnDefinition.setDataType(_datetime());
        return columnDefinition;
    }

    /**
     * Quick build
     * @param columnDefinition ColumnDefinition
     * @return ColumnDefinition
     */
    public static ColumnDefinition c_null(ColumnDefinition columnDefinition){
        ColumnConstraint cc = new ColumnConstraint();
        cc.setConstraint(new NullOrNotNull(true));
        return new ColumnDefinitionBuilder<>(columnDefinition)
                .withColumnConstraint(Collections.singletonList(cc))
                .build();
    }

    /**
     * Quick build
     * @param columnDefinition ColumnDefinition
     * @return ColumnDefinition
     */
    public static ColumnDefinition c_not_null(ColumnDefinition columnDefinition){
        ColumnConstraint cc = new ColumnConstraint();
        cc.setConstraint(new NullOrNotNull(false));
        return new ColumnDefinitionBuilder<>(columnDefinition)
                .withColumnConstraint(Collections.singletonList(cc))
                .build();
    }

    /**
     * Quick build
     * @param columnDefinition ColumnDefinition
     * @return ColumnDefinition
     */
    public static ColumnDefinition c_primary_key(ColumnDefinition columnDefinition){
        ColumnConstraint cc = new ColumnConstraint();
        cc.setConstraint(new PrimaryUnique(true));
        return new ColumnDefinitionBuilder<>(columnDefinition)
                .withColumnConstraint(Collections.singletonList(cc))
                .build();
    }

    /**
     * Quick build
     * @param columnDefinition ColumnDefinition
     * @return ColumnDefinition
     */
    public static ColumnDefinition c_unique(ColumnDefinition columnDefinition){
        ColumnConstraint cc = new ColumnConstraint();
        cc.setConstraint(new PrimaryUnique(false));
        return new ColumnDefinitionBuilder<>(columnDefinition)
                .withColumnConstraint(Collections.singletonList(cc))
                .build();
    }

}
