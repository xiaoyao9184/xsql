package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.clause.OutputConverter;
import com.xy.xsql.block.tsql.core.clause.TableValueConstructorConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.dml.Insert;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class InsertConverter
        implements ReferenceBlockConverter<Insert> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Insert> builder =
            new ReferenceBlockBuilder<Void,Insert>()
                    .overall("INSERT")
                    .sub("WITH <common_table_expression> [ ,...n ]")
                        .optional(d -> d.getWith() == null)
                        .data(Insert::getWith)
                        .and()
                    .sub_keyword(Keywords.INSERT)
                    .sub("TOP ( expression ) [ PERCENT ]")
                        .optional(d -> d.getTop() == null)
                        .data(Insert::getTop)
                        .and()
                    .sub("INTO")
                        .optional(d -> !d.isUseInto())
                        .keyword(Keywords.INTO)
                        .and()
                    .sub()
                        .description("into target")
                        .required()
                        .czse(d -> d.getTableName() != null, "<object>")
                            .data(Insert::getTableName)
                            .and()
    //                    .czse_meta("rowset_function_limited")
                        .and()
                    .sub()
                        .description("(column_list)")
                        .optional(d -> d.getColumns() == null)
                        .sub_keyword(Other.GROUP_START)
                        .sub("column_list")
                            .ref(ColumnListConverter.meta())
                            .data(Insert::getColumns)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .sub("OUTPUT Clause")
                        .optional(d -> d.getOutput() == null)
                        .ref(OutputConverter.class)
                        .data(Insert::getOutput)
                        .and()
                    .sub()
                        .description("values")
                        .required()
                        //TODO donot use ref
                        .czse(d -> d.getValues() != null, "VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]")
                            .ref(TableValueConstructorConverter.class)
                            .data(Insert::getValues)
                            .and()
    //                    .czse_meta("derived_table")
    //                    .czse_meta("execute_statement")
    //                    .czse_meta("<dml_table_source>")
                        .czse(Insert::isUseDefaultValues)
                            .description("detault values")
                            .sub_keyword(Keywords.DEFAULT)
                            .sub_keyword(Keywords.VALUES)
                            .and()
                        .headFootTakeLine()
                        .subTakeLine()
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(Insert insert) {
        return builder
                .data(insert)
                .build();
    }

    public static class ColumnListConverter
            implements ReferenceBlockConverter<List<ColumnName>> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,List<ColumnName>> builder =
                new ReferenceBlockBuilder<Void,List<ColumnName>>()
                        .description("column_list")
                        .list()
                        .data(d -> d)
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(List<ColumnName> columnNames) {
            return builder
                    .data(columnNames)
                    .build();
        }
    }

}
