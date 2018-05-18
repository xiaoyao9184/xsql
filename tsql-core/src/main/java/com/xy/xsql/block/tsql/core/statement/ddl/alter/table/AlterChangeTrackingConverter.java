package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.statements.alter.table.AlterChangeTracking;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class AlterChangeTrackingConverter
        implements ModelMetaBlockConverter<AlterChangeTracking> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,AlterChangeTracking>()
                    .description("alter table alter change tracking item")
                    .sub()
                        .czse_keyword(d -> d.isUseEnable(),Keywords.CHECK)
                        .czse_keyword(d -> !d.isUseEnable(),Keywords.NOCHECK)
                        .syntax_required()
                        .and()
                    .sub_keyword(Keywords.Key.CHANGE_TRACKING)
                    .sub()
                        .description("TRACK_COLUMNS_UPDATED")
                        .optional(d -> d.getUseTrackUpdatedOn() == null)
                        .sub_keyword(Keywords.WITH)
                        .sub_keyword(Other.GROUP_START)
                        .sub_keyword(Keywords.Key.TRACK_COLUMNS_UPDATED)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse_keyword(d -> d.getUseTrackUpdatedOn(),Keywords.ON)
                            .czse_keyword(d -> !d.getUseTrackUpdatedOn(),Keywords.OFF)
                            .syntax_required()
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .syntax_line()
                        .syntax_indentation_right()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
