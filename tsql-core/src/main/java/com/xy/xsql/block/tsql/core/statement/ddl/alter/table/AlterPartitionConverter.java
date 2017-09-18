package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.AlterPartition;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.AlterTrigger;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class AlterPartitionConverter
        implements ModelMetaBlockConverter<AlterPartition> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,AlterPartition>()
                    .description("alter table alter partition item")
                    .sub_keyword(Keywords.Key.SWITCH)
                    .sub()
                        .optional(d -> d.getSourcePartitionNumberExpression() == null)
                        .sub_keyword(Keywords.Key.PARTITION)
                        .sub("source_partition_number_expression")
                            .scope(d -> d.getSourcePartitionNumberExpression())
                            .and()
                        .and()
                    .sub()
                        .sub()
                            .sub_keyword(Keywords.TO)
                            .sub("target_table")
                                .scope(d -> d.getSourcePartitionNumberExpression())
                                .and()
                            .and()
                        .sub()
                            .optional(d -> d.getTargetPartitionNumberExpression() == null)
                            .sub_keyword(Keywords.Key.PARTITION)
                            .sub("target_partition_number_expression")
                                .scope(d -> d.getSourcePartitionNumberExpression())
                                .and()
                            .and()
                        .sub()
                            .optional(d -> d.getLowPriorityLockWait() == null)
                            .sub_keyword(Keywords.WITH)
                            .sub_keyword(Other.GROUP_START)
                            .sub("low_lock_priority_wait")
                                .ref(LowPriorityLockWaitConverter.meta)
                                .scope(d -> d.getLowPriorityLockWait())
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .syntax_indentation_right()
                        .syntax_sub_line()
                        .and()
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
