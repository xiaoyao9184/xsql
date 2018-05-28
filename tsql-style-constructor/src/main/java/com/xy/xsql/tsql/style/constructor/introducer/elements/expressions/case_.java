package com.xy.xsql.tsql.style.constructor.introducer.elements.expressions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.elements.expressions.b_CASE;

/**
 * Created by xiaoyao9184 on 2018/5/16.
 */
@SuppressWarnings("unused")
public interface case_ {

    //Simple CASE expression

    static b_CASE CASE(
            Expression input_expression,
            b_CASE.b_WHEN when,
            b_CASE.b_ELSE else_){
        return new b_CASE(){
            {
                withInput(input_expression);
                withWhen(when.build());
                withElse(else_.build());
            }
        };
    }
    static b_CASE CASE(
            Expression input_expression,
            b_CASE.b_WHEN when){
        return new b_CASE(){
            {
                withInput(input_expression);
                withWhen(when.build());
            }
        };
    }

    //Searched CASE expression

    static b_CASE CASE(
            b_CASE.b_WHEN when,
            b_CASE.b_ELSE else_){
        return new b_CASE(){
            {
                withWhen(when.build());
                withElse(else_.build());
            }
        };
    }
    static b_CASE CASE(
            b_CASE.b_WHEN when){
        return new b_CASE(){
            {
                withWhen(when.build());
            }
        };
    }

    //

    static b_CASE.b_WHEN WHEN(
            Expression when_expression,
            b_CASE.b_THEN then){
        return new b_CASE.b_WHEN(){
            {
                withItem()
                        .withWhen(when_expression)
                        .withThen(then.build());
            }
        };
    }

    //

    static b_CASE.b_THEN THEN(Expression result_expression){
        return new b_CASE.b_THEN(result_expression);
    }

    //

    static b_CASE.b_ELSE ELSE(Expression else_result_expression){
        return new b_CASE.b_ELSE(else_result_expression);
    }
}
