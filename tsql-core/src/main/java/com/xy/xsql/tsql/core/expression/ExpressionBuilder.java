package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.expression.*;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.operator.Operator;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.statement.dml.Select;
import javafx.beans.binding.StringExpression;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class ExpressionBuilder {

    public static Expression e(String expressionString){
        return new UnknownExpression(expressionString);
    }

    @Deprecated
    public static Expression e(Number numberExpression){
        return new NumberExpression(numberExpression);
    }

    public static Expression e(Expression leftExpression, Operator operator, Expression rightExpression){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setOperator(operator);
        groupExpression.setExpressionLeft(leftExpression);
        groupExpression.setExpressionRight(rightExpression);
        return groupExpression;
    }

    public static Expression e_null(){
        return new KeywordExpression(Keywords.NULL);
    }

    public static Expression e_default(){
        return new KeywordExpression(Keywords.DEFAULT);
    }

    public static StringConstant e_string(String stringExpression){
        return new StringConstant(stringExpression).withQuote();
    }

    public static StringConstant e_n_string(String stringExpression){
        return new StringConstant(stringExpression).withNQuote();
    }

    //TODO NumberConstant
    public static NumberExpression e_number(Number numberExpression){
        return new NumberExpression(numberExpression);
    }


    public static Expression e(Select select){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setStatement(select);
        return groupExpression;
    }

//    public static Expression e(ElementList elementList){
//        return new ElementExpression(elementList);
//    }
}
