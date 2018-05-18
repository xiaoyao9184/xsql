package com.xy.xsql.block.tsql.core.statement.ddl.create.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.MultipartNamesConverter;
import com.xy.xsql.block.tsql.core.element.column.ColumnDefinitionConverters;
import com.xy.xsql.block.tsql.core.element.table.TableConstraintConverters;
import com.xy.xsql.block.tsql.core.element.table.TableIndexConverters;
import com.xy.xsql.block.tsql.core.element.table.TableOptionConverters;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.element.column.ColumnDefinition;
import com.xy.xsql.tsql.model.element.table.TableConstraint;
import com.xy.xsql.tsql.model.element.table.TableIndex;
import com.xy.xsql.tsql.model.statement.ddl.create.table.MemoryOptimizedCreateTable;
import com.xy.xsql.tsql.model.statement.ddl.create.table.SimpleCreateTable;

/**
 * Created by xiaoyao9184 on 2017/9/4.
 */
public class MemoryOptimizedCreateTableConverter
        implements ModelMetaBlockConverter<MemoryOptimizedCreateTable> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,MemoryOptimizedCreateTable>()
                    .description("--Disk-Based CREATE TABLE Syntax")
                    .sub()
                        .description("create table")
                        .sub_keyword(Keywords.CREATE)
                        .sub_keyword(Keywords.TABLE)
                        .and()
                    .sub()
                        .sub()
                            .description("table name")
                            .ref(MultipartNamesConverter.TableNameConverter.meta)
                            .scope(SimpleCreateTable::getTableName)
                            .and()
                        .sub()
                            .description("column definition list")
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .sub()
                                    .description("column definition list")
                                    .list()
                                    .ref(ItemConverter.meta)
                                    .scope(MemoryOptimizedCreateTable::getItems)
                                    .syntax_required()
                                    .and()
                                .sub()
                                    .description("system time")
                                    .optional(d -> d.getSystemEndTimeColumnName() == null)
                                    .sub_keyword(Keywords.Key.PERIOD)
                                    .sub_keyword(Keywords.FOR)
                                    .sub_keyword(Keywords.Key.SYSTEM_TIME)
                                    .sub_keyword(Other.GROUP_START)
                                    .sub("system_start_time_column_name")
                                        .scope(d -> d.getSystemStartTimeColumnName())
                                        .and()
                                    .sub_keyword(Other.DELIMITER)
                                    .sub("system_end_time_column_name")
                                        .scope(d -> d.getSystemEndTimeColumnName())
                                        .and()
                                    .sub_keyword(Other.GROUP_END)
                                    .and()
                                .syntax_context_indentation()
                                .syntax_sub_line()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub()
                            .optional(d -> d.getTableOptions() == null)
                            .sub_keyword(Keywords.WITH)
                            .sub_keyword(Other.GROUP_START)
                            .sub("table_option")
                                .list()
                                .ref(TableOptionConverters.DiskBased.meta)
                                .scope(d -> d.getTableOptions())
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

    public static class ItemConverter
            implements ModelMetaBlockConverter<MemoryOptimizedCreateTable.Item> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,MemoryOptimizedCreateTable.Item>()
                        .description("")
                        .czse(d -> d instanceof ColumnDefinition,"column_definition")
                            .ref(ColumnDefinitionConverters.MemoryOptimized.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d instanceof TableConstraint, "table_constraint")
                            .ref(TableConstraintConverters.MemoryOptimized.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d instanceof TableIndex, "table_index")
                            .ref(TableIndexConverters.MemoryOptimized.meta)
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
