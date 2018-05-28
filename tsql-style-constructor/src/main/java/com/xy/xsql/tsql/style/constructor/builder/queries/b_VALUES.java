package com.xy.xsql.tsql.style.constructor.builder.queries;

import com.xy.xsql.tsql.builder.chain.queries.TableValueConstructorBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_VALUES extends TableValueConstructorBuilder<b_VALUES> {

    public b_VALUES() {
        this.in(this);
    }


    /*
    Item
     */

    public b_VALUES $$(Expression... rowValueExpressions){
        withItem().withRowValueExpression(rowValueExpressions);
        return this;
    }

}
