package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.queries.TableValueConstructor;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.With;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;

/**
 * TableValueConstructorBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"WeakerAccess","unused"})
public class TableValueConstructorBuilder<ParentBuilder>
        extends ParentHoldBuilder<TableValueConstructorBuilder<ParentBuilder>,ParentBuilder,TableValueConstructor> {

    public TableValueConstructorBuilder() {
        super(new TableValueConstructor());
    }

    public TableValueConstructorBuilder(TableValueConstructor target) {
        super(target);
    }

    /**
     * in
     * @return Count Expression
     */
    public RowValuesBuilder<TableValueConstructorBuilder<ParentBuilder>> withItem(){
        return new RowValuesBuilder<TableValueConstructorBuilder<ParentBuilder>>
                (list(target::getRowValueExpressionListGroup, target::setRowValueExpressionListGroup)
                        .addNew(ArrayList::new))
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
            extends ParentHoldBuilder<RowValuesBuilder<ParentBuilder>,ParentBuilder,List<Expression>> {

        public RowValuesBuilder() {
            super(new ArrayList<>());
        }

        public RowValuesBuilder(List<Expression> target) {
            super(target);
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
