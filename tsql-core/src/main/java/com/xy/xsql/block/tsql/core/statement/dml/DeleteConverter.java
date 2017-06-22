package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.statement.dml.Delete;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class DeleteConverter
        implements BlockConverter<Delete> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Delete> builder =
            new ReferenceBlockBuilder<Void,Delete>()
                    .overall("DELETE")
                    .sub("WITH <common_table_expression> [ ,...n ]")
                        .optional(d -> d.getWith() == null)
                        .data(Delete::getWith)
                        .and()
                    .sub_keyword(Keywords.DELETE)
                    .sub("TOP ( expression ) [ PERCENT ]")
                        .optional(d -> d.getTop() == null)
                        .data(Delete::getTop)
                        .and()
                    .sub("FROM")
                        .optional(d -> !d.isUseForm())
                        .keyword(Keywords.FROM)
                        .and()
                    .sub()
                        .required()
                        .oneOf("table_alias")
                            .optional(d -> d.getTableAlias() == null)
                            .data(Delete::getTableAlias)
                            .and()
                        .oneOf("<object>")
                            .data(Delete::getTableName)
                            .and()
    //                    .oneOf("rowset_function_limited")
    //                    .oneOf("@table_variable")
                        .headFootTakeLine()
                        .subTakeLine()
                        .and()
                    .sub("<OUTPUT Clause>")
                        .optional(d -> d.getOutput() == null)
                        .data(Delete::getOutput)
                        .and()
                    .sub("FROM table_source [ ,...n ]")
                        .optional(d -> d.getFrom() == null)
                        .data(Delete::getFrom)
                        .and()
                    //TODO
                    .sub("WHERE { <search_condition>")
                        .optional(d -> d.getWhere() == null)
                        .data(Delete::getWhere)
                        .and()
                    .sub("OPTION ( <Query Hint> [ ,...n ] )")
                        .optional(d -> d.getOption() == null)
                        .data(Delete::getOption)
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Delete delete) {
        return builder
                .data(delete)
                .build();
    }
}
