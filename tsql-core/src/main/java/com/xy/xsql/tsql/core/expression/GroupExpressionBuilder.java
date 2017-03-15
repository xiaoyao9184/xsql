package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.expression.GroupExpression;
import com.xy.xsql.tsql.model.operator.*;

/**
 *
 * expression { binary_operator } expression
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class GroupExpressionBuilder {

    public static Expression e_binary(Expression leftExpression, Operator operator, Expression rightExpression){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setOperator(operator);
        groupExpression.setExpressionLeft(leftExpression);
        groupExpression.setExpressionRight(rightExpression);
        return groupExpression;
    }


    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression ADDITION(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.ADDITION, leftExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression SUBTRACTION(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.SUBTRACTION, leftExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression MULTIPLICATION(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.MULTIPLICATION, leftExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression DIVISION(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.DIVISION, leftExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression MODULO(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.MODULO, leftExpression);
    }

    /**
     * assignment
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression ASSIGNMENT(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Assignment.ASSIGNMENT, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression BITWISE_AND(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.AND, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression BITWISE_AND_EQUALS(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.AND_EQUALS, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression BITWISE_OR(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.OR, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression BITWISE_OR_EQUALS(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.OR_EQUALS, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression BITWISE_XOR(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.XOR, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression BITWISE_XOR_EQUALS(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.XOR_EQUALS, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression BITWISE_NOT(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.NOT, leftExpression);
    }



    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression ALL(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.ALL, leftExpression);
    }

    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression SOME(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.SOME, leftExpression);
    }

    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression ANY(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.ANY, leftExpression);
    }




    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression AND(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.AND, leftExpression);
    }

    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression OR(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.OR, leftExpression);
    }







    /**
     * string concatenation
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression CONCATENATION(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, StringConcatenation.CONCATENATION, leftExpression);
    }




    /**
     * unary
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression PLUS(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Unary.PLUS, leftExpression);
    }

    /**
     * unary
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression NEGATIVE(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Unary.NEGATIVE, leftExpression);
    }


}
