package com.xy.xsql.orm.core.sql;

import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.expression.NumberString;
import com.xy.xsql.orm.data.sql.expression.StringExpression;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class ExpressionBuilder {

    public static Expression e(String stringExpression){
        return new StringExpression(stringExpression);
    }

    public static Expression e(Number numberExpression){
        return new NumberString(numberExpression);
    }

    public static Expression e(ElementList numberExpression, OperatorEnum operator, ElementList numberExpression2){
        //TODO
        return null;
    }
}
