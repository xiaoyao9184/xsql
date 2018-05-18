package com.xy.xsql.block.tsql.core.element.column;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.index.PartitionConverters;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.column.ColumnIndex;
import com.xy.xsql.tsql.model.elements.operators.Assignment;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class ColumnIndexConverters {

    public static class DiskBased {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,ColumnIndex>()
                        .overall("column_index")
                        .sub_keyword(Keywords.INDEX)
                        .sub("index_name")
                            .scope(d -> d.getIndexName())
                            .and()
                        .sub()
                            .description("clustered/nonclustered")
                            .optional(d -> d.getUseClustered() == null)
                            .czse_keyword(d -> d.getUseClustered(),Keywords.CLUSTERED)
                            .czse_keyword(d -> !d.getUseClustered(),Keywords.NONCLUSTERED)
                            .and()
                        .sub()
                            .sub()
                                .description("with option list")
                                .optional(d -> d.getIndexOptionList() == null)
                                .sub_keyword(Keywords.WITH)
                                .sub_keyword(Other.GROUP_START)
                                .sub("index_option")
                                    .list()
    //                                .ref()
                                    .scope(d -> d.getIndexOptionList())
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .sub()
                                .description("on partition")
                                .sub_keyword(Keywords.ON)
                                .sub()
                                    .description("partition")
                                    .ref(PartitionConverters.PartitionColumnFilegroupDefault.meta)
                                    .scope(d -> d.getPartition())
                                    .syntax_indentation_right()
                                    .and()
                                .and()
                            .sub()
                                .description("filestream on partition")
                                .sub_keyword(Keywords.Key.FILESTREAM_ON)
                                .sub()
                                    .description("partition")
                                    .ref(PartitionConverters.FileStreamGroupNull.meta)
                                    .scope(d -> d.getFileStreamPartition())
                                    .and()
                                .and()
                            .syntax_context_indentation()
                            .syntax_sub_line()
                            .and()
                        .build();
        // @formatter:on
    }

    public static class MemoryOptimized {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,ColumnIndex>()
                        .overall("column_index")
                        .sub_keyword(Keywords.INDEX)
                        .sub("index_name")
                            .scope(d -> d.getIndexName())
                            .and()
                        .sub()
                            .description("other thing")
                            .czse(d -> d.getBucketCount() == null)
                                .sub()
                                    .description("nonclustered")
                                    .optional(d -> !d.getUseClustered())
                                    .keyword(Keywords.NONCLUSTERED)
                                    .and()
                                .and()
                            .czse(d -> d.getBucketCount() != null)
                                .sub()
                                    .description("nonclustered")
                                    .optional(d -> !d.getUseClustered())
                                    .keyword(Keywords.NONCLUSTERED)
                                    .and()
                                .sub_keyword(Keywords.Key.HASH)
                                .sub_keyword(Keywords.WITH)
                                .sub_keyword(Other.GROUP_START)
                                .sub_keyword(Keywords.Key.BUCKET_COUNT)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("bucket_count")
                                    .scope(d -> d.getBucketCount())
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .syntax_required()
                            .syntax_line()
                            .and()
                        .build();
        // @formatter:on
    }

}
