package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.EnumConverterUtil;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.index.IndexOption;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.SinglePartitionRebuildOption;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class SinglePartitionRebuildOptionConverter
        implements ModelMetaBlockConverter<SinglePartitionRebuildOption> {

    // @formatter:off
    public static final BlockMeta meta =
            new BlockMetaBuilder<Void,SinglePartitionRebuildOption>()
                    .overall("single_partition_rebuild__option")
                    .czse(d -> d.isUseSortInTempDb())
                        .sub_keyword(Keywords.Key.SORT_IN_TEMPDB)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse_keyword(d -> d.isUseSortInTempDb(),Keywords.ON)
                            .czse_keyword(d -> !d.isUseSortInTempDb(),Keywords.OFF)
                            .syntax_required()
                            .and()
                        .and()
                    .czse(d -> d.getMaxDegreeOfParallelism() != null)
                        .sub_keyword(Keywords.Key.MAXDOP)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("max_degree_of_parallelism")
                            .scope(d -> d.getMaxDegreeOfParallelism())
                            .and()
                        .and()
                    .czse(d -> d.getDataCompression() != null)
                        .sub_keyword(IndexOption.Type.DATA_COMPRESSION)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub(EnumConverterUtil.getSyntaxString(IndexOption.DataCompression.class))
                            .scope(d -> d.getDataCompression().toString())
                            .syntax_required()
                            .and()
                        .and()
                    .czse(d -> true)
                        .sub_keyword(Keywords.Key.ONLINE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse(d -> d.isUseOnline())
                                .sub_keyword(Keywords.ON)
                                .sub()
                                    .optional(d -> d.getLowPriorityLockWait() == null)
                                    .sub_keyword(Other.GROUP_START)
                                    .sub("low_priority_lock_wait")
                                        .ref(LowPriorityLockWaitConverter.meta)
                                        .scope(d -> d.getLowPriorityLockWait())
                                        .and()
                                    .sub_keyword(Other.GROUP_END)
                                    .and()
                                .and()
                            .czse_keyword(d -> !d.isUseOnline(), Keywords.OFF)
                            .syntax_required()
                            .and()
                        .and()
                    .syntax_required()
                    .syntax_context_indentation()
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
