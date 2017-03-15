package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.expression.GroupExpression;
import com.xy.xsql.tsql.model.operator.Arithmetic;
import com.xy.xsql.tsql.model.operator.Operator;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class GroupExpressionBuilder {
    public static Expression e_arithmetic(Expression leftExpression, Operator operator, Expression rightExpression){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setOperator(operator);
        groupExpression.setExpressionLeft(leftExpression);
        groupExpression.setExpressionRight(rightExpression);
        return groupExpression;
    }



    public static Expression ADDITION(Expression leftExpression, Expression rightExpression){
        return e_arithmetic(leftExpression, Arithmetic.ADDITION, leftExpression);
    }

    public static Expression SUBTRACTION(Expression leftExpression, Expression rightExpression){
        return e_arithmetic(leftExpression, Arithmetic.SUBTRACTION, leftExpression);
    }

    public static Expression MULTIPLICATION(Expression leftExpression, Expression rightExpression){
        return e_arithmetic(leftExpression, Arithmetic.MULTIPLICATION, leftExpression);
    }

    public static Expression DIVISION(Expression leftExpression, Expression rightExpression){
        return e_arithmetic(leftExpression, Arithmetic.DIVISION, leftExpression);
    }

    public static Expression MODULO(Expression leftExpression, Expression rightExpression){
        return e_arithmetic(leftExpression, Arithmetic.MODULO, leftExpression);
    }




}
