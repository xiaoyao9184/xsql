package com.xy.xsql.tsql.converter.statements.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.datatypes.table.index.PartitionConverters;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.statements.alter.table.Set;

import static com.xy.xsql.tsql.converter.EnumConverterUtil.getSyntaxString;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class SetConverter
        implements ModelMetaBlockConverter<Set> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Set>()
                    .description("alter table alter set item")
                    .sub_keyword(Keywords.SET)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse(d -> d.getFileStreamOn() != null)
                            .sub_keyword(Keywords.Key.FILESTREAM_ON)
                            .sub_keyword(Assignment.ASSIGNMENT)
                            .sub()
                                .sub()
                                    .ref(PartitionConverters.PartitionFilegroupDefaultNull.meta)
                                    .scope(d -> d.getFileStreamOn())
                                    .and()
                                .syntax_line()
                                .syntax_indentation_right()
                                .and()
                            .and()
                        .czse(d -> d.getFileStreamOn() == null)
                            .sub_keyword(com.xy.xsql.tsql.model.datatypes.table.table.TableOption.KeyWords.SYSTEM_VERSIONING)
                            .sub_keyword(Assignment.ASSIGNMENT)
                            .sub()
                                .sub()
                                    .czse_keyword(d -> d.isUseSystemVersioningOn(), Keywords.ON)
                                    .czse_keyword(d -> !d.isUseSystemVersioningOn(), Keywords.OFF)
                                    .and()
                                .sub()
                                    .optional(d -> d.getHistoryTableName() == null)
                                    .sub_keyword(Other.GROUP_START)
                                    .sub()
                                        .sub()
                                            .sub_keyword(com.xy.xsql.tsql.model.datatypes.table.table.TableOption.KeyWords.HISTORY_TABLE)
                                            .sub_keyword(Assignment.ASSIGNMENT)
                                            .sub("schema_name")
                                                .scope(d -> d.getSchemaName())
                                                .and()
                                            .sub_keyword(Other.POINT)
                                            .sub("history_table_name")
                                                .scope(d -> d.getHistoryTableName())
                                                .and()
                                            .and()
                                        .sub()
                                            .optional(d -> d.getUseDataConsistencyCheck() != null)
                                            .sub_keyword(Other.DELIMITER)
                                            .sub_keyword(com.xy.xsql.tsql.model.datatypes.table.table.TableOption.KeyWords.DATA_CONSISTENCY_CHECK)
                                            .sub_keyword(Assignment.ASSIGNMENT)
                                            .sub()
                                                .czse_keyword(d -> d.getUseDataConsistencyCheck(), Keywords.ON)
                                                .czse_keyword(d -> !d.getUseDataConsistencyCheck(), Keywords.OFF)
                                                .syntax_required()
                                                .and()
                                            .and()
                                        .sub()
                                            .optional(d -> d.getHistoryRetentionPeriodUnit() != null)
                                            .sub_keyword(Other.DELIMITER)
                                            .sub_keyword(Keywords.Key.HISTORY_RETENTION_PERIOD)
                                            .sub_keyword(Assignment.ASSIGNMENT)
                                            .sub()
                                                .czse_keyword(d -> d.getHistoryRetentionPeriodNumber() == null, Keywords.Key.INFINITE)
                                                .czse(d -> d.getHistoryRetentionPeriodNumber() != null)
                                                    .sub("number")
                                                        .scope(d -> d.getHistoryRetentionPeriodNumber())
                                                        .and()
                                                    .sub(getSyntaxString(Set.HistoryRetentionPeriodUnit.class))
                                                        .scope(d -> d.getHistoryRetentionPeriodUnit().toString())
                                                        .syntax_required()
                                                        .and()
                                                    .and()
                                                .syntax_required()
                                                .syntax_context_indentation()
                                                .and()
                                            .and()
                                        .syntax_indentation_right()
                                        .syntax_sub_line()
                                        .and()
                                    .sub_keyword(Other.GROUP_END)
                                    .syntax_sub_line()
                                    .and()
                                .syntax_required()
                                .syntax_context_indentation()
                                .syntax_sub_line()
                                .and()
                            .and()
                        .syntax_line()
                        .syntax_indentation_right()
                        .syntax_sub_line()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
