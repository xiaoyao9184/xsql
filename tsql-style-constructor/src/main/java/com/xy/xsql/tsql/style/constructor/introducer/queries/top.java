package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_TOP;
import com.xy.xsql.tsql.style.constructor.builder.queries.k_;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface top {

    static b_TOP TOP(Expression countExpression,
                     k_.k_PERCENT percent,
                     b_TOP.k_WITH_TIES with_ties){
        return new b_TOP(){
            {
                withExpression(countExpression);
                withPercent();
                withTies();
            }
        };
    }
    static b_TOP TOP(Expression countExpression,
                     k_.k_PERCENT percent){
        return new b_TOP(){
            {
                withExpression(countExpression);
                withPercent();
            }
        };
    }
    static b_TOP TOP(Expression countExpression){
        return new b_TOP(){
            {
                withExpression(countExpression);
            }
        };
    }
    static b_TOP TOP(Number count,
                     k_.k_PERCENT percent,
                     b_TOP.k_WITH_TIES with_ties){
        return new b_TOP(){
            {
                withExpression(e_number(count));
                withPercent();
                withTies();
            }
        };
    }
    static b_TOP TOP(Number count,
                     k_.k_PERCENT percent){
        return new b_TOP(){
            {
                withExpression(e_number(count));
                withPercent();
            }
        };
    }
    static b_TOP TOP(Number count){
        return new b_TOP(){
            {
                withExpression(e_number(count));
            }
        };
    }


    static k_.k_PERCENT PERCENT(){
        return null;
    }

    static b_TOP.k_WITH_TIES WITH_TIES(){
        return null;
    }

}
