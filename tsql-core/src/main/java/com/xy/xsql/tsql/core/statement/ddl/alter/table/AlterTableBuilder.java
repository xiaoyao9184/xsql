package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.statement.ddl.create.table.SimpleCreateTable;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AlterTableBuilder extends CodeBuilder<SimpleCreateTable> {

    public AlterTableBuilder(SimpleCreateTable simpleCreateTable) {
        super(simpleCreateTable);
    }
}
