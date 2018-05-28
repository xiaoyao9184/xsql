package com.xy.xsql.tsql.style.constructor.introducer.elements.expressions;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.elements.expressions.b_NULLIF;

/**
 * Created by xiaoyao9184 on 2018/5/16.
 */
@SuppressWarnings("unused")
public interface nullif {

    static b_NULLIF NULLIF(
            Expression expression,
            Expression expression2){
        return new b_NULLIF(){
            {
                withExpression(expression);
                withExpression(expression2);
            }
        };
    }

    static b_NULLIF NULLIF(
            Expression expression,
            SimpleBuilder<? extends Expression> expression2){
        return new b_NULLIF(){
            {
                withExpression(expression);
                withExpression(expression2.build());
            }
        };
    }
    static b_NULLIF NULLIF(
            SimpleBuilder<? extends Expression> expression,
            Expression expression2){
        return new b_NULLIF(){
            {
                withExpression(expression.build());
                withExpression(expression2);
            }
        };
    }
    static b_NULLIF NULLIF(
            SimpleBuilder<? extends Expression> expression,
            SimpleBuilder<? extends Expression> expression2){
        return new b_NULLIF(){
            {
                withExpression(expression.build());
                withExpression(expression2.build());
            }
        };
    }
}
