package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.expression.RowValueExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
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


    //TODO rename withItem
    public RowValuesBuilder<TableValueConstructorBuilder<ParentBuilder>> withRowValues(){
        return new RowValuesBuilder<TableValueConstructorBuilder<ParentBuilder>>
                (initNew(ArrayList::new,
                        tar::getRowValueExpressionListGroup,
                        tar::setRowValueExpressionListGroup))
                .in(this);
    }


    public TableValueConstructorBuilder<ParentBuilder> $(RowValueExpression... rowValueExpressions){
        return withRowValues()
                .withRowValueExpression(rowValueExpressions)
                .and();
    }

    public static class RowValuesBuilder<ParentBuilder>
            extends CodeTreeBuilder<RowValuesBuilder<ParentBuilder>,ParentBuilder,List<RowValueExpression>> {

        public RowValuesBuilder(List<RowValueExpression> tar) {
            super(tar);
        }

        public RowValuesBuilder<ParentBuilder> withRowValueExpression(RowValueExpression... rowValueExpressions){
            tar.addAll(Arrays.asList(rowValueExpressions));
            return this;
        }
    }

}
