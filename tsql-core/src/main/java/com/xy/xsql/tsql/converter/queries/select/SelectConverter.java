package com.xy.xsql.tsql.converter.queries.select;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.queries.select.Select;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.elements.operators.Assignment;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SelectConverter
        implements ModelMetaBlockConverter<Select> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Select>()
                    .overall("SELECT Clause")
                    .sub_keyword(Keywords.SELECT)
                    .sub()
                        .description("all/distinct")
                        .optional(d -> !d.isUseAll() && !d.isUseDistinct())
                        .czse_keyword(Select::isUseAll, Keywords.ALL)
                        .czse_keyword(Select::isUseDistinct, Keywords.DISTINCT)
                        .and()
                    .sub("TOP ( expression ) [ PERCENT ] [ WITH TIES ]")
                        .optional(d -> d.getTop() == null)
                        .scope(Select::getTop)
                        .syntax_line()
                        .format_indentation_right()
                        .and()
                    .sub("select_list")
                        .ref(SelectListConverter.class)
                        .scope(Select::getSelectList)
                        .syntax_line()
                        .format_indentation_right()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class SelectListConverter
            implements ModelMetaBlockConverter<List<Select.SelectItem>> {


        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,List<Select.SelectItem>>()
                        .overall("select_list")
                        .sub_list(SelectItemConverter.meta)
                            .scope(d -> d)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class SelectItemConverter
            implements ModelMetaBlockConverter<Select.SelectItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Select.SelectItem>()
                        .description("select item")
                        .czse_keyword(Select.SelectItem::isUseAll, Other.ASTERISK)
                        .czse(d -> d.getTableViewName() != null, "{ table_name | view_name | table_alias }.* ")
                            .description("table all")
                            .scope(d -> d.getTableViewName() + ".*")
                            .and()
                        .czse(d -> !d.isUseEQ())
                            .description("select base item")
                            .sub()
                                .description("column/expression")
                                .czse(d -> d.getColumnName() != null)
                                    .description("column")
                                    .sub("{ table_name | view_name | table_alias }.")
                                        .optional(d -> d.getTableViewName() == null)
                                        .scope(d -> d.getTableViewName() + ".")
                                        .and()
                                    .sub("column_name | $IDENTITY | $ROWGUID")
                                        .syntax_required()
                                        .czse(d -> d.getColumnName() != null, "column_name")
                                            .scope(d -> d.getColumnName().toString())
                                            .and()
                                        //TODO support $IDENTITY $ROWGUID
//                                        .czse_ref("$IDENTITY")
//                                            .and()
//                                        .czse_ref("$ROWGUID")
//                                            .and()
                                        .and()
                                    .and()
                                //TODO support property/field/method
//                                .oneOf()
//                                    .description("property/field/method")
////                                    .filter()
//                                    .sub("udt_column_name")
//                                        .data(null)
//                                        .and()
//                                    .sub()
//                                        .description("property/field/method")
//                                        .optional()
//                                        .sub()
//                                            .description(". | ::")
//                                            .syntax_required()
//                                            .oneOf(".")
//                                                .data(".")
//                                                .and()
//                                            .oneOf("::")
//                                                .data("::")
//                                                .and()
//                                            .and()
//                                        .sub()
//                                            .syntax_required()
//                                            .oneOf()
//                                                .description("property_name | field_name")
//                                                .oneOf("property_name")
//                                                    .data(null)
//                                                    .and()
//                                                .oneOf("field_name")
//                                                    .data(null)
//                                                    .and()
//                                                .and()
//                                            .oneOf()
//                                                .description("method_name ( argument [ ,...n] )")
//                                                .sub("method_name")
//                                                    .data(null)
//                                                    .and()
//                                                .sub_keyword(Other.GROUP_START)
//                                                .sub()
//                                                    .list("argument")
//                                                    .data(null)
//                                                    .oneMore()
//                                                    .and()
//                                                .sub_keyword(Other.GROUP_END)
//                                                .and()
//                                            .and()
//                                        .and()
//                                    .and()
                                .czse(d -> d.getExpression() != null, "expression")
                                    .scope(Select.SelectItem::getExpression)
                                    .and()
                                .syntax_sub_line()
                                .and()
                            .sub()
                                .description("as column_alias")
                                .optional(d -> d.getColumnAlias() == null)
                                .sub()
                                    .optional(d -> !d.isUseAs())
                                    .keyword(Keywords.AS)
                                    .and()
                                .sub("column_alias")
                                    .scope(Select.SelectItem::getColumnAlias)
                                    .and()
                                .and()
                            .syntax_required()
                            .syntax_sub_line()
                            .syntax_context_indentation()
                            .and()
                        .czse(Select.SelectItem::isUseEQ)
                            .description("column_alias = expression")
                            .sub("column_alias")
                                .scope(Select.SelectItem::getColumnAlias)
                                .and()
                            .sub_keyword(Assignment.ASSIGNMENT)
                            .sub("expression")
                                .scope(Select.SelectItem::getExpression)
                                .and()
                            .and()
                        .syntax_required()
                        .syntax_sub_line()
                        .syntax_context_indentation()
                        .format_line()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
