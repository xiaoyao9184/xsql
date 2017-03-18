package com.xy.xsql.tsql.core.datatype;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatype.ColumnDefinition;
import com.xy.xsql.tsql.model.datatype.DataType;
import com.xy.xsql.tsql.model.datatype.TableTypeDefinition;
import com.xy.xsql.tsql.model.element.ColumnName;

import static com.xy.xsql.tsql.core.datatype.DataTypeBuilder.*;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnDefinitionBuilder<ParentBuilder>
        extends CodeTreeBuilder<ColumnDefinitionBuilder<ParentBuilder>,ParentBuilder,ColumnDefinition> {

    public ColumnDefinitionBuilder(ColumnDefinition tar) {
        super(tar);
    }

    public ColumnDefinitionBuilder() {
        super(new ColumnDefinition());
    }

    public ColumnDefinitionBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        tar.setTable(columnName.getTable());
        tar.setName(columnName.getName());
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withDataType(DataType dataType) {
        tar.setDataType(dataType);
        return this;
    }

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
