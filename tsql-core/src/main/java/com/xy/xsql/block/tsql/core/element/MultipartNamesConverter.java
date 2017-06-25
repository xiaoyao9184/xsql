package com.xy.xsql.block.tsql.core.element;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class MultipartNamesConverter {

    public static class TableNameConverter
            implements BlockConverter<TableName> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,TableName> builder =
                new ReferenceBlockBuilder<Void,TableName>()
                        .description("[ { database_name .[ schema_name ] . | schema_name . } ]  table_name")
                        .sub()
                            .optional()
                            .czse(d -> d.getDatabaseName() != null)
                                .sub("database_name")
                                    .data(TableName::getDatabaseName)
                                    .and()
                                .sub_keyword(Other.POINT)
                                .sub("schema_name")
                                    .optional()
                                    .data(TableName::getSchemaName)
                                    .and()
                                .sub_keyword(Other.POINT)
                                .and()
                            .czse(d -> d.getSchemaName() != null)
                                .sub("schema_name")
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
        public Block convert(TableName tableName) {
            return builder
                    .data(tableName)
                    .build();
        }

    }

    public static class ColumnNameConverter
            implements BlockConverter<ColumnName> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,ColumnName> builder =
                new ReferenceBlockBuilder<Void,ColumnName>()
                        .description("[ { database_name .[ schema_name ] . | schema_name . } ]  table_name")
                        .sub()
                            .optional()
                            .sub_meta(TableNameConverter.meta())
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
        public Block convert(ColumnName columnName) {
            return builder
                    .data(columnName)
                    .build();
        }

    }
}
