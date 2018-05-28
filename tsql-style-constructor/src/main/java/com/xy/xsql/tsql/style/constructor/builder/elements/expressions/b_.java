package com.xy.xsql.tsql.style.constructor.builder.elements.expressions;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public interface b_ {


    class AND extends CodeBuilder<Expression> {

        public AND(Expression expression) {
            super(expression);
        }
    }
}
