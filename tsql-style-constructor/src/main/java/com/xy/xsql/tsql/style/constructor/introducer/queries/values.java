package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_VALUES;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface values {

    static b_VALUES VALUES(Expression... rowValueExpressions){
//        return new VALUES(){
//            {
//                withItem().withRowValueExpression(rowValueExpressions);
//            }
//        };
        return new b_VALUES().$$(rowValueExpressions);
    }




}
