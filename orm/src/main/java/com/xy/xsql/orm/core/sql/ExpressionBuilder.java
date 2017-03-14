package com.xy.xsql.orm.core.sql;

import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.orm.data.sql.element.operator.Operator;
import com.xy.xsql.orm.data.sql.expression.ElementExpression;
import com.xy.xsql.orm.data.sql.expression.GrammarExpression;
import com.xy.xsql.orm.data.sql.expression.NumberString;
import com.xy.xsql.orm.data.sql.expression.StringExpression;
import com.xy.xsql.orm.data.sql.statements.dml.Select;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class ExpressionBuilder {

    public static Expression e(String expressionString){
        return new StringExpression(expressionString);
    }

    @Deprecated
    public static Expression e(Number numberExpression){
        return new NumberString(numberExpression);
    }

    public static Expression e(ElementList numberExpression, Operator operator, ElementList numberExpression2){
        //TODO
        return null;
    }

    public static Expression e_null(){
        return new GrammarExpression(GrammarEnum.NULL);
    }

    public static Expression e_default(){
        return new GrammarExpression(GrammarEnum.DEFAULT);
    }

    public static StringConstant e_string(String stringExpression){
        return new StringConstant(stringExpression).withQuote();
    }

    public static StringConstant e_n_string(String stringExpression){
        return new StringConstant(stringExpression).withNQuote();
    }

    public static NumberString e_number(Number numberExpression){
        return new NumberString(numberExpression);
    }


    public static Expression e(Select select){
        return new ElementExpression(select);
    }

    public static Expression e(ElementList elementList){
        return new ElementExpression(elementList);
    }
}
