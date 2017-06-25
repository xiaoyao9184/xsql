package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
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
        implements BlockConverter<TableValueConstructor> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,TableValueConstructor> builder =
            new ReferenceBlockBuilder<Void,TableValueConstructor>()
                    .overall("Table Value Constructor")
                    .sub_keyword(Keywords.VALUES)
                    .sub()
                        .list()
                        .sub()
                            .sub_keyword(Other.GROUP_START)
                            .sub("row value expression list")
                                .ref(RowValueExpressionListConverter.class)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .data(TableValueConstructor::getRowValueExpressionListGroup)
                        .more()
                    //TODO
                        .data(TableValueConstructor::getRowValueExpressionListGroup)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(TableValueConstructor tableValueConstructor) {
        return builder
                .data(tableValueConstructor)
                .build();
    }


    public static class RowValueExpressionListConverter
            implements BlockConverter<List<Expression>> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,List<Expression>> builder =
                new ReferenceBlockBuilder<Void,List<Expression>>()
                        .overall("row value expression list")
                        .list()
                        .sub("row value expression")
                            .ref(RowValueExpressionConverter.class)
                            .and()
                        .oneMore();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(List<Expression> expressionList) {
            return builder
                    .data(expressionList)
                    .build();
        }
    }


    public static class RowValueExpressionConverter
            implements BlockConverter<Expression> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Expression> builder =
                new ReferenceBlockBuilder<Void,Expression>()
                        .overall("row value expression")
                        .required()
                        .czse(d -> d instanceof Default,"DEFAULT")
                            .and()
                        .czse(d -> d instanceof Null,"NULL")
                            .and()
                        .czse(d -> true,"expression")
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Expression expression) {
            return builder
                    .data(expression)
                    .build();
        }
    }

}
