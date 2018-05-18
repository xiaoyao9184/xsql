package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.EnumConverterUtil;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.AlterTableOption;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class AlterTableOptionConverter
        implements ModelMetaBlockConverter<AlterTableOption> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,AlterTableOption>()
                    .overall("table_option")
                    .sub_keyword(Keywords.SET)
                    .sub_keyword(Other.GROUP_START)
                    .sub_keyword(Keywords.Key.LOCK_ESCALATION)
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub(EnumConverterUtil.getSyntaxString(AlterTableOption.LockEscalation.class))
                        .scope(d -> d.getLockEscalation().toString())
                        .syntax_required()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .syntax_required()
                    .syntax_context_indentation()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
