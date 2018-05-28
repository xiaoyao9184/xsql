package com.xy.xsql.tsql.style.constructor.builder.elements.expressions;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.elements.expressions.CaseBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Case;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import java.util.ArrayList;
import java.util.List;

import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_CASE extends CaseBuilder<b_CASE> {

    public b_CASE() {
        this.in(this);
    }

    public static class b_WHEN extends CodeBuilder<List<Case.WhenThenExpression>> {

        public b_WHEN() {
            super(new ArrayList<>());
        }

        public CaseBuilder.WhenThenExpressionBuilder<b_WHEN> withItem(){
            return new CaseBuilder.WhenThenExpressionBuilder<b_WHEN>
                    (object(Case.WhenThenExpression::new).set(target::add))
                    .in(this);
        }


        /*
        Item
         */

        public b_WHEN WHEN(Expression when_expression,
                           b_THEN then){
            return withItem()
                    .withWhen(when_expression)
                    .withThen(then.build())
                    .and();
        }

    }

    public static class b_THEN extends CodeBuilder<Expression> {

        public b_THEN(Expression expression) {
            super(expression);
        }

    }

    public static class b_ELSE extends CodeBuilder<Expression> {

        public b_ELSE(Expression expression) {
            super(expression);
        }

    }

}
