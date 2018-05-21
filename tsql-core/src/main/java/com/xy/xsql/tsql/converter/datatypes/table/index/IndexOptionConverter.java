package com.xy.xsql.tsql.converter.datatypes.table.index;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.statements.TruncateTableConverter;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;
import com.xy.xsql.tsql.model.elements.operators.Assignment;

import static com.xy.xsql.tsql.converter.EnumConverterUtil.getSyntaxString;


/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class IndexOptionConverter
        implements ModelMetaBlockConverter<IndexOption> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,IndexOption>()
                    .overall("index_option")
                    .czse(d -> d.getType().equals(IndexOption.Type.PAD_INDEX))
                        .sub_keyword(IndexOption.Type.PAD_INDEX)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse_keyword(d -> d.isUseOn(),Keywords.ON)
                            .czse_keyword(d -> !d.isUseOn(),Keywords.OFF)
                            .and()
                        .and()
                    .czse(d -> d.getType().equals(IndexOption.Type.FILLFACTOR))
                        .sub_keyword(IndexOption.Type.FILLFACTOR)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("fillfactor")
                            .scope(d -> d.getFillfactor())
                            .and()
                        .and()
                    .czse(d -> d.getType().equals(IndexOption.Type.IGNORE_DUP_KEY))
                        .sub_keyword(IndexOption.Type.IGNORE_DUP_KEY)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse_keyword(d -> d.isUseOn(),Keywords.ON)
                            .czse_keyword(d -> !d.isUseOn(),Keywords.OFF)
                            .and()
                        .and()
                    .czse(d -> d.getType().equals(IndexOption.Type.STATISTICS_NORECOMPUTE))
                        .sub_keyword(IndexOption.Type.STATISTICS_NORECOMPUTE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse_keyword(d -> d.isUseOn(),Keywords.ON)
                            .czse_keyword(d -> !d.isUseOn(),Keywords.OFF)
                            .and()
                        .and()
                    .czse(d -> d.getType().equals(IndexOption.Type.ALLOW_ROW_LOCKS))
                        .sub_keyword(IndexOption.Type.ALLOW_ROW_LOCKS)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse_keyword(d -> d.isUseOn(),Keywords.ON)
                            .czse_keyword(d -> !d.isUseOn(),Keywords.OFF)
                            .and()
                        .and()
                    .czse(d -> d.getType().equals(IndexOption.Type.ALLOW_PAGE_LOCKS))
                        .sub_keyword(IndexOption.Type.ALLOW_PAGE_LOCKS)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse_keyword(d -> d.isUseOn(),Keywords.ON)
                            .czse_keyword(d -> !d.isUseOn(),Keywords.OFF)
                            .and()
                        .and()
                    .czse(d -> d.getType().equals(IndexOption.Type.COMPRESSION_DELAY))
                        .sub_keyword(IndexOption.Type.COMPRESSION_DELAY)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse(d -> d.getDelay() == null,"0")
                                .scope(d -> 0)
                                .and()
                            .czse(d -> d.getDelay() != null,"delay [Minutes]")
                                .sub()
                                    .scope(d -> d.getDelay())
                                    .and()
                                .sub()
                                    .scope(d -> "Minutes")
                                    .and()
                                .and()
                            .and()
                        .and()
                    .czse(d -> d.getType().equals(IndexOption.Type.DATA_COMPRESSION))
                        .sub_keyword(IndexOption.Type.DATA_COMPRESSION)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub(getSyntaxString(IndexOption.DataCompression.class))
                            .scope(d -> d.getDataCompression())
                            .and()
                        .sub()
                            .description("on partition")
                            .optional(d -> d.getPartitionsList() == null)
                            .sub_keyword(Keywords.ON)
                            .sub_keyword(Keywords.Key.PARTITIONS)
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .description("partition")
                                .list()
                                .ref(TruncateTableConverter.PartitionsConverter.meta)
                                .scope(d -> d.getPartitionsList())
                                .syntax_required_remove()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .syntax_line()
                            .syntax_indentation_right()
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
