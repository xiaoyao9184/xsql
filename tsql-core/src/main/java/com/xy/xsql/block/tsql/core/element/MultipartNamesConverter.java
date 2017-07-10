package com.xy.xsql.block.tsql.core.element;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class MultipartNamesConverter {

    public static class TableNameConverter
            implements MetaContextBlockConverter<TableName> {

        // @formatter:off
        private static BlockMetaBuilder<Void,TableName> builder =
                new BlockMetaBuilder<Void,TableName>()
                        .description("database_name.schema_name.table_name")
                        .sub()
                            .description("server_name.database_name.schema_name.")
                            .sub()
                                .description("server_name.")
                                .optional(d -> d.getServerName() == null)
                                .sub("server_name")
                                    .data(TableName::getServerName)
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
                                        .data(TableName::getDatabaseName)
                                        .and()
                                    .sub_keyword(Other.POINT)
                                    .sub("schema_name")
                                        .optional(d -> d.getSchemaName() == null)
                                        .data(TableName::getSchemaName)
                                        .and()
                                    .sub_keyword(Other.POINT)
                                    .and()
                                .czse(d -> d.getSchemaName() != null)
                                    .description("schema_name.")
                                    .sub("schema_name")
                                        .data(TableName::getSchemaName)
                                        .and()
                                    .sub_keyword(Other.POINT)
                                    .and()
                                .and()
                            .and()
                        .sub("table_name")
                            .data(TableName::getTableOrViewName)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(TableName context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }

    public static class ColumnNameConverter
            implements MetaContextBlockConverter<ColumnName> {

        // @formatter:off
        private static BlockMetaBuilder<Void,ColumnName> builder =
                new BlockMetaBuilder<Void,ColumnName>()
                        .description("database_name.schema_name.table_name.column_name")
                        .sub()
                            .description("table_name.")
                            .optional(d -> d.getTable() == null)
                            .sub()
                                .description("table_name")
                                .ref(TableNameConverter.meta())
                                .data(ColumnName::getTable)
                                .and()
                            .sub_keyword(Other.POINT)
                            .and()
                        .sub("column_name")
                            .data(ColumnName::getName)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(ColumnName context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }
}
