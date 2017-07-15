package com.xy.xsql.block.tsql.core.element;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;

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
                                    .and()
                                .czse(d -> d.getSchemaName() != null)
                                    .description("schema_name.")
                                    .sub("schema_name")
                                        .scope(TableName::getSchemaName)
                                        .and()
                                    .sub_keyword(Other.POINT)
                                    .and()
                                .and()
                            .and()
                        .sub("table_name")
                            .scope(TableName::getTableOrViewName)
                            .and()
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
                            .and()
                        .sub("column_name")
                            .scope(ColumnName::getName)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }
}
