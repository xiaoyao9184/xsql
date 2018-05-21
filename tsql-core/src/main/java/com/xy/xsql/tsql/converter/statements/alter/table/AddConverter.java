package com.xy.xsql.tsql.converter.statements.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.datatypes.table.column.ColumnDefinitionConverters;
import com.xy.xsql.tsql.converter.datatypes.table.column.ColumnSetDefinitionConverter;
import com.xy.xsql.tsql.converter.datatypes.table.column.ComputedColumnDefinitionConverter;
import com.xy.xsql.tsql.converter.datatypes.table.table.TableConstraintConverters;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.datatypes.table.column.DataType;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnSetDefinition;
import com.xy.xsql.tsql.model.datatypes.table.column.ComputedColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.table.TableConstraint;
import com.xy.xsql.tsql.model.statements.alter.table.Add;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AddConverter
        implements ModelMetaBlockConverter<Add> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Add>()
                    .description("alter table add item")
                    .sub_keyword(Keywords.ADD)
                    .sub()
                        .description("add param")
                        .czse(d -> d.getItems() != null)
                            .description("list about")
                            .list()
                            .ref(AddItemConverter.meta)
                            .scope(d -> d.getItems())
                            .syntax_required()
                            .syntax_context_indentation()
                            .and()
                        .czse(d -> d.getItems() == null)
                            .description("system time")
                            .sub()
                                .description("base time")
                                .optional(d -> d.getSystemEndTimeColumnName() == null || d.getSystemStartTimeColumnName() == null)
                                .sub()
                                    .description("start time")
                                    .sub("system_start_time_column_name")
                                        .scope(d -> d.getSystemStartTimeColumnName())
                                        .and()
                                    .sub_keyword(DataType.Synonyms._datetime2)
                                    .sub_keyword(Keywords.Key.GENERATED)
                                    .sub_keyword(Keywords.Key.ALWAYS)
                                    .sub_keyword(Keywords.AS)
                                    .sub_keyword(Keywords.Key.ROW)
                                    .sub_keyword(Keywords.Key.START)
                                    .sub()
                                        .description("hidden not null")
                                        .sub()
                                            .optional(d -> !d.isUseHidden4StartTime())
                                            .keyword(Keywords.Key.HIDDEN)
                                            .and()
                                        .sub()
                                            .optional(d -> !d.isUseNotNull4StartTime())
                                            .sub_keyword(Keywords.NOT)
                                            .sub_keyword(Keywords.NULL)
                                            .and()
                                        .sub()
                                            .optional(d -> d.getConstraintName4StartTime() == null)
                                            .sub_keyword(Keywords.CONSTRAINT)
                                            .sub("constraint_name")
                                                .scope(d -> d.getConstraintName4StartTime())
                                                .and()
                                            .and()
                                        .syntax_line()
                                        .syntax_indentation_right()
                                        .and()
                                    .sub()
                                        .description("default with")
                                        .sub_keyword(Keywords.DEFAULT)
                                        .sub("constant_expression")
                                            .scope(d -> d.getConstraintName4StartTime())
                                            .and()
                                        .sub()
                                            .optional(d -> d.isUseWithValues4StartTime())
                                            .sub_keyword(Keywords.WITH)
                                            .sub_keyword(Keywords.VALUES)
                                            .and()
                                        .sub_keyword(Other.DELIMITER)
                                        .syntax_line()
                                        .syntax_indentation_right()
                                        .and()
                                    .and()
                                .sub()
                                    .description("end time")
                                    .sub("system_end_time_column_name")
                                        .scope(d -> d.getSystemEndTimeColumnName())
                                        .and()
                                    .sub_keyword(DataType.Synonyms._datetime2)
                                    .sub_keyword(Keywords.Key.GENERATED)
                                    .sub_keyword(Keywords.Key.ALWAYS)
                                    .sub_keyword(Keywords.AS)
                                    .sub_keyword(Keywords.Key.ROW)
                                    .sub_keyword(Keywords.Key.END)
                                    .sub()
                                        .description("hidden not null")
                                        .sub()
                                            .optional(d -> !d.isUseHidden4EndTime())
                                            .keyword(Keywords.Key.HIDDEN)
                                            .and()
                                        .sub()
                                            .optional(d -> !d.isUseNotNull4EndTime())
                                            .sub_keyword(Keywords.NOT)
                                            .sub_keyword(Keywords.NULL)
                                            .and()
                                        .sub()
                                            .optional(d -> d.getConstraintName4EndTime() == null)
                                            .sub_keyword(Keywords.CONSTRAINT)
                                            .sub("constraint_name")
                                                .scope(d -> d.getConstraintName4EndTime())
                                                .and()
                                            .and()
                                        .syntax_line()
                                        .syntax_indentation_right()
                                        .and()
                                    .sub()
                                        .description("default with")
                                        .sub_keyword(Keywords.DEFAULT)
                                        .sub("constant_expression")
                                            .scope(d -> d.getConstraintName4EndTime())
                                            .and()
                                        .sub()
                                            .optional(d -> d.isUseWithValues4EndTime())
                                            .sub_keyword(Keywords.WITH)
                                            .sub_keyword(Keywords.VALUES)
                                            .and()
                                        .syntax_line()
                                        .syntax_indentation_right()
                                        .and()
                                    .and()
                                .syntax_context_indentation()
                                .syntax_sub_line()
                                .and()
                            .sub()
                                .description("period time")
                                .sub_keyword(Keywords.Key.PERIOD)
                                .sub_keyword(Keywords.FOR)
                                .sub_keyword(Keywords.Key.SYSTEM_TIME)
                                .sub_keyword(Other.GROUP_START)
                                .sub("system_start_time_column_name")
                                    .scope(d -> d.getSystemStartTimeColumnName4Period())
                                    .and()
                                .sub_keyword(Other.DELIMITER)
                                .sub("system_end_time_column_name ")
                                    .scope(d -> d.getSystemEndTimeColumnName4Period())
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .syntax_sub_line()
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


    public static class AddItemConverter
            implements ModelMetaBlockConverter<Add.AddItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Add.AddItem>()
                        .description("alter table add item")
                        .czse(d -> d instanceof ColumnDefinition,"column_definition")
                            .ref(ColumnDefinitionConverters.DiskBased.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d instanceof ComputedColumnDefinition, "computed_column_definition")
                            .ref(ComputedColumnDefinitionConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d instanceof TableConstraint, "table_constraint")
                            .ref(TableConstraintConverters.DiskBased.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d instanceof ColumnSetDefinition, "column_set_definition")
                            .ref(ColumnSetDefinitionConverter.meta)
                            .scope(d -> d)
                            .and()
                        .syntax_sub_line()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }
}
