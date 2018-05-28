package com.xy.xsql.tsql.style.constructor.introducer.elements.expressions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.elements.expressions.b_COALESCE;

/**
 * Created by xiaoyao9184 on 2018/5/16.
 */
@SuppressWarnings("unused")
public interface coalesce {

    static b_COALESCE COALESCE(
            Expression... expressions){
        return new b_COALESCE(){
            {
                withExpression(expressions);
            }
        };
    }
}
