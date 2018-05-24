package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.queries.TableValueConstructor;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * TableValueConstructorBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings("WeakerAccess")
public class TableValueConstructorBuilder<ParentBuilder>
        extends CodeTreeBuilder<TableValueConstructorBuilder<ParentBuilder>,ParentBuilder,TableValueConstructor> {

    public TableValueConstructorBuilder() {
        super(new TableValueConstructor());
    }

    public TableValueConstructorBuilder(TableValueConstructor tableValueConstructor) {
        super(tableValueConstructor);
    }

    /**
     * in
     * @return Count Expression
     */
    public RowValuesBuilder<TableValueConstructorBuilder<ParentBuilder>> withItem(){
        return new RowValuesBuilder<TableValueConstructorBuilder<ParentBuilder>>
                (initNew(ArrayList::new,
                        target::getRowValueExpressionListGroup,
                        target::setRowValueExpressionListGroup))
                .in(this);
    }




    /*
    Quick set
     */

    /**
     * Quick set
     * @param rowValueExpressions Row Value Expression
     * @return THIS
     */
    public TableValueConstructorBuilder<ParentBuilder> $(Expression... rowValueExpressions) {
        return withItem()
                .withRowValueExpression(rowValueExpressions)
                .and();
    }


    /**
     * RowValuesBuilder
     * TODO may be RowValueExpression same as this
     * @see com.xy.xsql.tsql.model.elements.expressions.RowValueExpression
     * @param <ParentBuilder>
     */
    public static class RowValuesBuilder<ParentBuilder>
            extends CodeTreeBuilder<RowValuesBuilder<ParentBuilder>,ParentBuilder,List<Expression>> {

        public RowValuesBuilder(List<Expression> tar) {
            super(tar);
        }

        /**
         * set
         * @param rowValueExpressions Row Value Expression
         * @return THIS
         */
        public RowValuesBuilder<ParentBuilder> withRowValueExpression(Expression... rowValueExpressions){
            target.addAll(Arrays.asList(rowValueExpressions));
            return this;
        }
    }

}
