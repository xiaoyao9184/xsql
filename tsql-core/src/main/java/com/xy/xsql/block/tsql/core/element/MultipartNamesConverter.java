package com.xy.xsql.block.tsql.core.element;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class MultipartNamesConverter {

    public static class TableNameConverter
            implements ReferenceBlockConverter<TableName> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,TableName> builder =
                new ReferenceBlockBuilder<Void,TableName>()
                        .description("[ { database_name .[ schema_name ] . | schema_name . } ]  table_name")
                        .sub()
                            .description("server database schema")
                            .optional(d ->
                                    d.getDatabaseName() == null &&
                                    d.getSchemaName() == null
                            )
                            .czse(d -> d.getDatabaseName() != null)
                                .description("database schema")
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
                                .description("schema")
                                .sub("schema_name")
                                    .data(TableName::getSchemaName)
                                    .and()
                                .sub_keyword(Other.POINT)
                                .and()
                            .and()
                        .sub("table_name")
                            .data(TableName::getTableOrViewName)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(TableName tableName) {
            return builder
                    .data(tableName)
                    .build();
        }

    }

    public static class ColumnNameConverter
            implements ReferenceBlockConverter<ColumnName> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,ColumnName> builder =
                new ReferenceBlockBuilder<Void,ColumnName>()
                        .description("[ [ { database_name .[ schema_name ] . | schema_name . } ]  table_name . ] column_name")
                        .sub()
                            .optional(d -> d.getTable() == null)
                            .sub()
                                .sub_meta(TableNameConverter.meta())
                                .data(ColumnName::getTable)
                                .and()
                            .sub_keyword(Other.POINT)
                            .and()
                        .sub("column_name")
                            .data(ColumnName::getName)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(ColumnName columnName) {
            return builder
                    .data(columnName)
                    .build();
        }

    }
}
