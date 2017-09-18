package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.AlterChangeTracking;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.AlterTrigger;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class AlterTriggerConverter
        implements ModelMetaBlockConverter<AlterTrigger> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,AlterTrigger>()
                    .description("alter table alter trigger item")
                    .sub()
                        .czse_keyword(d -> d.isUseEnable(),Keywords.Key.ENABLE)
                        .czse_keyword(d -> !d.isUseEnable(),Keywords.Key.DISABLE)
                        .syntax_required()
                        .and()
                    .sub_keyword(Keywords.TRIGGER)
                    .sub()
                        .description("trigger_name list")
                        .czse_keyword(d -> d.getTriggerNames() == null, Keywords.ALL)
                        .czse(d -> d.getTriggerNames() != null, "trigger_name")
                            .list()
                            .scope(d -> d.getTriggerNames())
                            .and()
                        .syntax_line()
                        .syntax_indentation_right()
                        .syntax_required()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
