package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.EnumConverterUtil;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.LowPriorityLockWait;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class LowPriorityLockWaitConverter
        implements ModelMetaBlockConverter<LowPriorityLockWait> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,LowPriorityLockWait>()
                    .overall("low_priority_lock_wait")
                    .sub_keyword(Keywords.Key.WAIT_AT_LOW_PRIORITY)
                    .sub_keyword(Other.GROUP_START)
                    .sub_keyword(Keywords.Key.MAX_DURATION)
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub("<time>")
                        .scope(d -> d.getTime())
                        .and()
                    .sub()
                        .optional(d -> d.isUseMinutes())
                        .keyword(Keywords.Key.MINUTES)
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub()
                        .sub_keyword(Keywords.Key.ABORT_AFTER_WAIT)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub(EnumConverterUtil.getSyntaxString(LowPriorityLockWait.AbortAfterWait.class))
                            .syntax_required()
                            .scope(d -> d.getAbortAfterWait().toString())
                            .and()
                        .syntax_line()
                        .syntax_indentation_right()
                        .and()
                    .syntax_required()
                    .syntax_context_indentation()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
