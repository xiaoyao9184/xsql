package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.dml.Delete;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class DeleteConverter
        implements ModelMetaBlockConverter<Delete> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Delete>()
                    .overall("DELETE")
                    .sub("WITH <common_table_expression> [ ,...n ]")
                        .optional(d -> d.getWith() == null)
                        .scope(Delete::getWith)
                        .and()
                    .sub_keyword(Keywords.DELETE)
                    .sub("TOP ( expression ) [ PERCENT ]")
                        .optional(d -> d.getTop() == null)
                        .scope(Delete::getTop)
                        .and()
                    .sub()
                        .optional(d -> !d.isUseForm())
                        .keyword(Keywords.FROM)
                        .and()
                    .sub()
                        .description("delete target")
                        .style_required()
                        .czse(d ->
                                d.getTableAlias() != null ||
                                d.getTableName() != null
                        )
                            .style_required()
                            .sub()
                                .description("table_alias/object/function")
                                .czse(d -> d.getTableAlias() != null, "table_alias")
                                    .scope(Delete::getTableAlias)
                                    .and()
                                .czse(d -> d.getTableName() != null, "<object>")
                                    .scope(Delete::getTableName)
                                    .and()
            //                    .czse_ref("rowset_function_limited")
                                .style_sub_line_delimiter()
                                .and()
                            .sub()
                                .description("with table hint")
                                .optional(d -> d.getTableHintLimitedList() == null)
                                .sub_keyword(Keywords.WITH)
                                .sub_keyword(Other.GROUP_START)
                                .sub("table_hint_limited")
                                    .repeat()
                                    .scope(Delete::getTableHintLimitedList)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .style_sub_line_delimiter()
                            .and()
//                        .czse_ref(d -> d.getTableVariable() != null,"@table_variable")
//                            .data(d -> d.getTableVariable())
//                            .and()
                        .style_convention_line_delimiter()
                        .style_sub_line_delimiter()
                        .format_indentation_right()
                        .and()
                    .sub("<OUTPUT Clause>")
                        .optional(d -> d.getOutput() == null)
                        .scope(Delete::getOutput)
                        .and()
                    .sub("FROM table_source [ ,...n ]")
                        .optional(d -> d.getFrom() == null)
                        .scope(Delete::getFrom)
                        .and()
                    //TODO support CURRENT OF
                    .sub("WHERE { <search_condition>")
                        .optional(d -> d.getWhere() == null)
                        .scope(Delete::getWhere)
                        .and()
                    .sub("OPTION ( <Query Hint> [ ,...n ] )")
                        .optional(d -> d.getOption() == null)
                        .scope(Delete::getOption)
                        .and()
                    .style_sub_line_delimiter()
                    .sub_format_line(true)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
