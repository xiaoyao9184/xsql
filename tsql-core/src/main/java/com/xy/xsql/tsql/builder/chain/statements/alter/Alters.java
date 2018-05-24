package com.xy.xsql.tsql.builder.chain.statements.alter;

import com.xy.xsql.tsql.builder.chain.statements.alter.table.AlterTableBuilder;

/**
 * Alter Factory
 * Created by xiaoyao9184 on 2018/5/24.
 */
public interface Alters {

    /**
     * Quick in
     * @return AlterTableBuilder
     */
    static AlterTableBuilder $AlterTable(){
        return new AlterTableBuilder();
    }

}
