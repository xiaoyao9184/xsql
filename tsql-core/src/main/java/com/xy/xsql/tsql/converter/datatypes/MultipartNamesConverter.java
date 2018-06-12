package com.xy.xsql.tsql.converter.datatypes;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.datatypes.table.TableName;

import java.util.Collections;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class MultipartNamesConverter {

    public static class TableNameConverter
            implements ModelMetaBlockConverter<TableName> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableName>()
                        .description("database_name.schema_name.table_name")
                        .sub()
                            .description("server_name.database_name.schema_name.")
                            .sub()
                                .description("server_name.")
                                .optional(d -> d.getServerName() == null)
                                .sub("server_name")
                                    .scope(TableName::getServerName)
                                    .and()
                                .sub_keyword(Other.POINT)
                                .sub_format_empty_delimiter()
                                .and()
                            .sub()
                                .description("database_name.schema_name.")
                                .optional(d ->
                                        d.getDatabaseName() == null &&
                                        d.getSchemaName() == null
                                )
                                .czse(d -> d.getDatabaseName() != null)
                                    .description("database_name.schema_name.")
                                    .sub("database_name")
                                        .scope(TableName::getDatabaseName)
                                        .and()
                                    .sub_keyword(Other.POINT)
                                    .sub("schema_name")
                                        .optional(d -> d.getSchemaName() == null)
                                        .scope(TableName::getSchemaName)
                                        .and()
                                    .sub_keyword(Other.POINT)
                                    .sub_format_empty_delimiter()
                                    .and()
                                .czse(d -> d.getSchemaName() != null)
                                    .description("schema_name.")
                                    .sub("schema_name")
                                        .scope(TableName::getSchemaName)
                                        .and()
                                    .sub_keyword(Other.POINT)
                                    .sub_format_empty_delimiter()
                                    .and()
                                .and()
                            .sub_format_empty_delimiter()
                            .and()
                        .sub("table_name")
                            .scope(TableName::getTableOrViewName)
                            .and()
                        .sub_format_empty_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class ColumnNameConverter
            implements ModelMetaBlockConverter<ColumnName> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,ColumnName>()
                        .description("database_name.schema_name.table_name.column_name")
                        .sub()
                            .description("table_name.")
                            .optional(d -> d.getTable() == null)
                            .sub()
                                .description("table_name")
                                .ref(TableNameConverter.meta)
                                .scope(ColumnName::getTable)
                                .and()
                            .sub_keyword(Other.POINT)
                            .sub_format_empty_delimiter()
                            .and()
                        .sub("column_name")
                            .scope(ColumnName::getName)
                            .and()
                        .sub_format_empty_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    // @formatter:off
    public static BlockMeta metaSequenceName =
            new BlockMetaBuilder<Void,List<String>>()
                    .description("[ database_name . ] [ schema_name . ]  sequence_name")
                    .sub()
                        .description("database_name.")
                        .optional(d -> {
                            Collections.reverse(d);
                            return d.size() != 3;
                        })
                        .sub("database_name")
                            .scope(d -> d.get(2))
                            .and()
                        .sub_keyword(Other.POINT)
                        .sub_format_empty_delimiter()
                        .and()
                    .sub()
                        .description("schema_name.")
                        .optional(d -> d.size() < 2)
                        .sub("schema_name")
                            .scope(d -> d.get(1))
                            .and()
                        .sub_keyword(Other.POINT)
                        .sub_format_empty_delimiter()
                        .and()
                    .sub("sequence_name")
                        .scope(d -> d.get(0))
                        .and()
                    .sub_format_empty_delimiter()
                    .build();
    // @formatter:on


    // @formatter:off
    public static BlockMeta metaObjectName =
            new BlockMetaBuilder<Void,List<String>>()
                    .description("[ database_name . [ schema_name ] . | schema_name . ] object_name")
                    .sub()
                        .description("database_name.schema_name. / schema_name.")
                        .optional(d -> {
                            Collections.reverse(d);
                            return d.size() != 3 && d.size() != 2;
                        })
                        .czse(d -> d.size() == 3, "[ database_name . [ schema_name ] .")
                            .description("database_name.schema_name.")
                            .sub("database_name")
                                .scope(d -> d.get(2))
                                .and()
                            .sub_keyword(Other.POINT)
                            .sub("schema_name")
                                .scope(d -> d.get(1))
                                .and()
                            .sub_keyword(Other.POINT)
                            .sub_format_empty_delimiter()
                            .and()
                        .czse(d -> d.size() == 2,"schema_name .")
                            .description("schema_name.")
                            .sub("schema_name")
                                .scope(d -> d.get(1))
                                .and()
                            .sub_keyword(Other.POINT)
                            .sub_format_empty_delimiter()
                            .and()
                        .and()
                    .sub("object_name")
                        .scope(d -> d.get(0))
                        .and()
                    .sub_format_empty_delimiter()
                    .build();
    // @formatter:on


}
