package com.xy.xsql.tsql.core.element.column;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.column.ColumnConstraint;
import com.xy.xsql.tsql.model.element.column.ColumnSetDefinition;
import com.xy.xsql.tsql.model.element.column.ComputedColumnDefinition;
import com.xy.xsql.tsql.model.expression.Expression;

import static com.xy.xsql.core.FiledBuilder.initSet;

/**
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnSetDefinitionBuilder<ParentBuilder>
        extends CodeTreeBuilder<ColumnSetDefinitionBuilder<ParentBuilder>,ParentBuilder,ColumnSetDefinition> {

    public ColumnSetDefinitionBuilder(ColumnSetDefinition tar) {
        super(tar);
    }

    public ColumnSetDefinitionBuilder() {
        super(new ColumnSetDefinition());
    }

    public ColumnSetDefinitionBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        target.setColumnSetName(columnName.getName());
        return this;
    }

}
