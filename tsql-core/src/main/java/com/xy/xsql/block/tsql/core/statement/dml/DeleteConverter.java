package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.clause.hints.TableHintLimitedConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.dml.Delete;
import com.xy.xsql.tsql.model.statement.dml.Insert;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class DeleteConverter
        implements ReferenceBlockConverter<Delete> {

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
                        .description("delete target")
                        .required()
                        .czse(d ->
                                d.getTableAlias() != null ||
                                d.getTableName() != null
                        )
                            .required()
                            .sub()
                                .description("table_alias/object/function")
                                .czse(d -> d.getTableAlias() != null, "table_alias")
                                    .data(Delete::getTableAlias)
                                    .and()
                                .czse(d -> d.getTableName() != null, "<object>")
                                    .data(Delete::getTableName)
                                    .and()
            //                    .czse_meta("rowset_function_limited")
                                .subTakeLine()
                                .and()
                            .sub()
                                .description("with table hint")
                                .optional(d -> d.getTableHintLimitedList() == null)
                                .sub_keyword(Keywords.WITH)
                                .sub_keyword(Other.GROUP_START)
                                .sub("table_hint_limited")
                                    .repeat()
                                    .data(Delete::getTableHintLimitedList)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .subTakeLine()
                            .and()
//                        .czse_meta(d -> d.getTableVariable() != null,"@table_variable")
//                            .data(d -> d.getTableVariable())
//                            .and()
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
                    //TODO support CURRENT OF
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

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public BlockMeta convert(Delete delete) {
        return builder
                .data(delete)
                .build();
    }
}
