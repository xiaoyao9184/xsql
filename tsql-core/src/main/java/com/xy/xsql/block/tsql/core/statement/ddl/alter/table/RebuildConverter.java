package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.index.IndexOptionConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.Rebuild;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class RebuildConverter
        implements ModelMetaBlockConverter<Rebuild> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Rebuild>()
                    .description("alter table alter rebuild item")
                    .sub_keyword(Keywords.Key.REBUILD)
                    .sub()
                        .czse(d -> d.getRebuildOptions() != null || d.isUseAll())
                            .sub()
                                .optional(d -> d.isUseAll())
                                .sub_keyword(Keywords.Key.PARTITION)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub_keyword(Keywords.ALL)
                                .and()
                            .sub()
                                .optional(d -> d.getRebuildOptions() == null)
                                .sub_keyword(Keywords.WITH)
                                .sub_keyword(Other.GROUP_START)
                                .sub("rebuild_option")
                                    .list()
                                    .ref(IndexOptionConverter.meta)
                                    .scope(d -> d.getRebuildOptions())
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .czse(d -> d.getSinglePartitionRebuildOptions() != null)
                            .sub()
                                .optional(d -> d.isUseAll())
                                .sub_keyword(Keywords.Key.PARTITION)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("partition_number")
                                    .scope(d -> d.getPartitionNumber())
                                    .and()
                                .and()
                            .sub()
                                .optional(d -> d.getSinglePartitionRebuildOptions() == null)
                                .sub_keyword(Keywords.WITH)
                                .sub_keyword(Other.GROUP_START)
                                .sub("single_partition_rebuild_option")
                                    .list()
                                    .ref(SinglePartitionRebuildOptionConverter.meta)
                                    .scope(d -> d.getSinglePartitionRebuildOptions())
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
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
