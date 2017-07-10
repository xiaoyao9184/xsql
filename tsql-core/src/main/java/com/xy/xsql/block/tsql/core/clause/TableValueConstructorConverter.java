package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
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
        implements ReferenceBlockConverter<TableValueConstructor> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,TableValueConstructor> builder =
            new ReferenceBlockBuilder<Void,TableValueConstructor>()
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
                        .and();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public BlockMeta convert(TableValueConstructor tableValueConstructor) {
        return builder
                .data(tableValueConstructor)
                .build();
    }


    public static class RowValueExpressionListConverter
            implements ReferenceBlockConverter<List<Expression>> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,List<Expression>> builder =
                new ReferenceBlockBuilder<Void,List<Expression>>()
                        .overall("row value expression list")
                        .sub("row value expression")
                            .list()
                            .required()
                            .ref(RowValueExpressionConverter.class)
                            .data(d -> d)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(List<Expression> expressionList) {
            return builder
                    .data(expressionList)
                    .build();
        }
    }


    public static class RowValueExpressionConverter
            implements ReferenceBlockConverter<Expression> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Expression> builder =
                new ReferenceBlockBuilder<Void,Expression>()
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
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Expression expression) {
            return builder
                    .data(expression)
                    .build();
        }
    }

}
