package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.expression.Expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * TableValueConstructorBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class TableValueConstructorBuilder<ParentBuilder>
        extends CodeTreeBuilder<TableValueConstructorBuilder<ParentBuilder>,ParentBuilder,TableValueConstructor> {

    public TableValueConstructorBuilder() {
        super(new TableValueConstructor());
    }

    public TableValueConstructorBuilder(TableValueConstructor tableValueConstructor) {
        super(tableValueConstructor);
    }

    public RowValuesBuilder<TableValueConstructorBuilder<ParentBuilder>> withItem(){
        return new RowValuesBuilder<TableValueConstructorBuilder<ParentBuilder>>
                (initNew(ArrayList::new,
                        target::getRowValueExpressionListGroup,
                        target::setRowValueExpressionListGroup))
                .in(this);
    }



    public static TableValueConstructorBuilder<Void> VALUES(){
        return new TableValueConstructorBuilder<>();
    }

    /*
    Quick set
     */

    /**
     * Quick set
     * @param rowValueExpressions
     * @return
     */
    public TableValueConstructorBuilder<ParentBuilder> $(Expression... rowValueExpressions) {
        return withItem()
                .withRowValueExpression(rowValueExpressions)
                .and();
    }


    /**
     * TODO may be RowValueExpression same as this
     * RowValuesBuilder
     * @param <ParentBuilder>
     */
    public static class RowValuesBuilder<ParentBuilder>
            extends CodeTreeBuilder<RowValuesBuilder<ParentBuilder>,ParentBuilder,List<Expression>> {

        public RowValuesBuilder(List<Expression> tar) {
            super(tar);
        }

        public RowValuesBuilder<ParentBuilder> withRowValueExpression(Expression... rowValueExpressions){
            target.addAll(Arrays.asList(rowValueExpressions));
            return this;
        }
    }

}
