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
                        .meta()
                            .description("( <row value expression list> )")
                            .sub_keyword(Other.GROUP_START)
                            .sub("row value expression list")
                                .ref(RowValueExpressionListConverter.class)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .list()
                        .data(TableValueConstructor::getRowValueExpressionListGroup)
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
                            .required()
                            .ref(RowValueExpressionConverter.class)
                            .data(d -> d)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class RowValueExpressionConverter
            implements ModelMetaBlockConverter<Expression> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Expression>()
                        .overall("row value expression")
                        .required()
                        .czse(d -> d instanceof Default,"DEFAULT")
                            .data(d -> d)
                            .and()
                        .czse(d -> d instanceof Null,"NULL")
                            .data(d -> d)
                            .and()
                        .czse(d -> true,"expression")
                            .data(d -> d)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
