package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.datatype.Default;
import com.xy.xsql.tsql.model.datatype.Null;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.Expression;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class TableValueConstructorConverter
        implements ModelMetaBlockConverter<TableValueConstructor> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,TableValueConstructor>()
                    .overall("Table Value Constructor")
                    .sub_keyword(Keywords.VALUES)
                    .sub()
                        .description("( <row value expression list> ) list")
                        .ref()
                            .description("( <row value expression list> )")
                            .sub_keyword(Other.GROUP_START)
                            .sub("row value expression list")
                                .ref(RowValueExpressionListConverter.class)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .format_line()
                            .and()
                        .list()
                        .scope(TableValueConstructor::getRowValueExpressionListGroup)
                        .format_indentation_right()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class RowValueExpressionListConverter
            implements ModelMetaBlockConverter<List<Expression>> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,List<Expression>>()
                        .overall("row value expression list")
                        .sub("row value expression")
                            .list()
                            .syntax_required()
                            .ref(RowValueExpressionConverter.meta)
                            .scope(d -> d)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class RowValueExpressionConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Expression>()
                        .overall("row value expression")
                        .syntax_required()
                        .czse(d -> d instanceof Default,"DEFAULT")
                            .scope(d -> d)
                            .and()
                        .czse(d -> d instanceof Null,"NULL")
                            .scope(d -> d)
                            .and()
                        .czse(d -> true,"expression")
                            .scope(d -> d)
                            .and()
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

    }

}
