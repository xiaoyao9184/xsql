package com.xy.xsql.block.tsql.core.statement.ddl.create.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.MultipartNamesConverter;
import com.xy.xsql.block.tsql.core.element.column.ColumnDefinitionConverters;
import com.xy.xsql.block.tsql.core.element.column.ColumnSetDefinitionConverter;
import com.xy.xsql.block.tsql.core.element.column.ComputedColumnDefinitionConverter;
import com.xy.xsql.block.tsql.core.element.index.PartitionConverters;
import com.xy.xsql.block.tsql.core.element.table.TableConstraintConverters;
import com.xy.xsql.block.tsql.core.element.table.TableIndexConverters;
import com.xy.xsql.block.tsql.core.element.table.TableOptionConverters;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnSetDefinition;
import com.xy.xsql.tsql.model.datatypes.table.column.ComputedColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.table.TableConstraint;
import com.xy.xsql.tsql.model.datatypes.table.table.TableIndex;
import com.xy.xsql.tsql.model.statements.create.table.DiskBasedCreateTable;
import com.xy.xsql.tsql.model.statements.create.table.SimpleCreateTable;

/**
 * Created by xiaoyao9184 on 2017/9/4.
 */
public class DiskBasedCreateTableConverter
        implements ModelMetaBlockConverter<DiskBasedCreateTable> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DiskBasedCreateTable>()
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
                            .description("as filetable")
                            .optional(d -> !d.isUseAsFileTable())
                            .sub_keyword(Keywords.AS)
                            .sub("FileTable")
                                .scope(d -> "FileTable")
                                .and()
                            .and()
                        .sub()
                            .description("column definition list")
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .sub()
                                    .description("column definition list")
                                    .list()
                                    .ref(ItemConverter.meta)
                                    .scope(DiskBasedCreateTable::getItems)
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
                            .description("on")
                            .optional(d -> d.getOn() == null)
                            .sub_keyword(Keywords.ON)
                            .sub()
                                .ref(PartitionConverters.PartitionColumnFilegroupDefault.meta)
                                .scope(d -> d.getOn())
                                .syntax_indentation_right()
                                .and()
                            .and()
                        .sub()
                            .description("text image on")
                            .optional(d -> d.getTextImageOn() == null)
                            .sub_keyword(Keywords.Key.TEXTIMAGE_ON)
                            .sub()
                                .ref(PartitionConverters.FilegroupDefault.meta)
                                .scope(d -> d.getTextImageOn())
                                .syntax_indentation_right()
                                .and()
                            .and()
                        .sub()
                            .description("file stream on")
                            .optional(d -> d.getFileStreamOn() == null)
                            .sub_keyword(Keywords.Key.FILESTREAM_ON)
                            .sub()
                                .ref(PartitionConverters.PartitionColumnFilegroupDefault.meta)
                                .scope(d -> d.getFileStreamOn())
                                .syntax_indentation_right()
                                .and()
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
            implements ModelMetaBlockConverter<DiskBasedCreateTable.Item> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,DiskBasedCreateTable.Item>()
                        .description("")
                        .czse(d -> d instanceof ColumnDefinition,"column_definition")
                            .ref(ColumnDefinitionConverters.DiskBased.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d instanceof ComputedColumnDefinition, "computed_column_definition")
                            .ref(ComputedColumnDefinitionConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d instanceof ColumnSetDefinition, "column_set_definition")
                            .ref(ColumnSetDefinitionConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d instanceof TableConstraint, "table_constraint")
                            .ref(TableConstraintConverters.DiskBased.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d instanceof TableIndex, "table_index")
                            .ref(TableIndexConverters.DiskBased.meta)
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
