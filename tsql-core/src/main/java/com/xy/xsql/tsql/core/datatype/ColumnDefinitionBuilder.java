package com.xy.xsql.tsql.core.datatype;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatype.ColumnDefinition;
import com.xy.xsql.tsql.model.datatype.DataType;
import com.xy.xsql.tsql.model.element.ColumnName;

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
        target.setTable(columnName.getTable());
        target.setName(columnName.getName());
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withDataType(DataType dataType) {
        target.setDataType(dataType);
        return this;
    }

}
