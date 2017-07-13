package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.Select;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Assignment;

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
                        .description("ALL | DISTINCT")
                        .optional(d -> !d.isUseAll() && !d.isUseDistinct())
                        .czse(Select::isUseAll)
                            .keyword(Keywords.ALL)
                            .and()
                        .czse(Select::isUseDistinct)
                            .keyword(Keywords.DISTINCT)
                            .and()
                        .and()
                    .sub("TOP ( expression ) [ PERCENT ] [ WITH TIES ]")
                        .optional(d -> d.getTop() == null)
                        .data(Select::getTop)
                        .startNewline()
                        .and()
                    .sub("select_list")
                        .ref(SelectListConverter.class)
                        .data(Select::getSelectList)
                        .startNewline()
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
                        .list()
                        .ref(SelectItemConverter.meta)
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
                        .required()
                        .czse(Select.SelectItem::isUseAll, "*")
                            .description("all")
                            .keyword(Other.ASTERISK)
                            .and()
                        .czse(d -> d.getTableViewName() != null, "{ table_name | view_name | table_alias }.* ")
                            .description("table all")
                            .data(d -> d.getTableViewName() + ".*")
                            .and()
                        .czse(d -> !d.isUseEQ())
                            .description("base")
                            .required()
                            .sub()
                                .description("column/expression")
                                .czse(d -> d.getColumnName() != null)
                                    .description("column")
                                    .sub("{ table_name | view_name | table_alias }.")
                                        .optional(d -> d.getTableViewName() == null)
                                        .data(d -> d.getTableViewName() + ".")
                                        .and()
                                    .sub("column_name | $IDENTITY | $ROWGUID")
                                        .required()
                                        .czse(d -> d.getColumnName() != null, "column_name")
                                            .data(d -> d.getColumnName().toString())
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
//                                            .required()
//                                            .oneOf(".")
//                                                .data(".")
//                                                .and()
//                                            .oneOf("::")
//                                                .data("::")
//                                                .and()
//                                            .and()
//                                        .sub()
//                                            .required()
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
                                    .data(Select.SelectItem::getExpression)
                                    .and()
                                .subTakeLine()
                                .and()
                            .sub()
                                .description("[ AS ] column_alias")
                                .optional(d -> d.getColumnAlias() == null)
                                .sub()
                                    .optional(Select.SelectItem::isUseAs)
                                    .keyword(Keywords.AS)
                                    .and()
                                .sub("column_alias")
                                    .data(Select.SelectItem::getColumnAlias)
                                    .and()
                                .and()
                            .subTakeLine()
                            .headFootTakeLine()
                            .and()
                        .czse(Select.SelectItem::isUseEQ)
                            .description("column_alias = expression")
                            .sub("column_alias")
                                .data(Select.SelectItem::getColumnAlias)
                                .and()
                            .sub_keyword(Assignment.ASSIGNMENT)
                            .sub("expression")
                                .data(Select.SelectItem::getExpression)
                                .and()
                            .and()
                        .subTakeLine()
                        .headFootTakeLine()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
