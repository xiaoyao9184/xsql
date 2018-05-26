package com.xy.xsql.tsql.builder.chain.datatypes.table.column;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnSetDefinition;

/**
 * ColumnSetDefinitionBuilder
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/3/12.
 */
@SuppressWarnings("unused")
public class ColumnSetDefinitionBuilder<ParentBuilder>
        extends ParentHoldBuilder<ColumnSetDefinitionBuilder<ParentBuilder>,ParentBuilder,ColumnSetDefinition> {

    public ColumnSetDefinitionBuilder() {
        super(new ColumnSetDefinition());
    }

    public ColumnSetDefinitionBuilder(ColumnSetDefinition target) {
        super(target);
    }

    /**
     * set
     * @param columnName ColumnName
     * @return THIS
     */
    public ColumnSetDefinitionBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        target.setColumnSetName(columnName.getName());
        return this;
    }

}
