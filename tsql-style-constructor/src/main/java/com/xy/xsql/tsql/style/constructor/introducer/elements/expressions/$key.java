package com.xy.xsql.tsql.style.constructor.introducer.elements.expressions;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Unary;
import com.xy.xsql.tsql.style.constructor.builder.elements.expressions.b_;
import com.xy.xsql.tsql.style.constructor.builder.elements.expressions.b_unary;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings("unused")
public interface $key {

    static StringConstant N(String stringExpression){
        return c_n_string(stringExpression);
    }


    static b_.AND AND(Expression expression){
        return new b_.AND(expression);
    }


    static b_unary $positive(NumberConstant numberExpression){
        return new b_unary(){
            {
                withUnary(Unary.POSITIVE);
                withExpression(numberExpression);
            }
        };
    }
    static b_unary $negative(NumberConstant numberExpression){
        return new b_unary(){
            {
                withUnary(Unary.NEGATIVE);
                withExpression(numberExpression);
            }
        };
    }
    static b_unary $complement(NumberConstant numberExpression){
        return new b_unary(){
            {
                withUnary(Unary.COMPLEMENT);
                withExpression(numberExpression);
            }
        };
    }

}
