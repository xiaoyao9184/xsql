package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.expression.GroupExpression;
import com.xy.xsql.tsql.model.operator.*;

import java.util.Arrays;

/**
 *
 * expression { binary_operator } expression
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class GroupExpressionBuilder {

    public static GroupExpression e_binary(Expression leftExpression, Operator operator, Expression... rightExpression){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setOperator(operator);
        groupExpression.setExpressionLeft(leftExpression);
        groupExpression.setExpressionRight(rightExpression[0]);
        if(rightExpression.length > 1){
            Expression[] newRight = Arrays.copyOfRange(rightExpression,1,rightExpression.length);
            return e_binary(groupExpression,operator,newRight);
        }

        return groupExpression;
    }


    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_addition(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.ADDITION, leftExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_subtraction(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.SUBTRACTION, leftExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_multiplication(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.MULTIPLICATION, leftExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_division(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.DIVISION, leftExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_modulo(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.MODULO, leftExpression);
    }

    /**
     * assignment
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_assignment(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Assignment.ASSIGNMENT, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_bitwise_and(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.AND, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_bitwise_and_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.AND_EQUALS, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_bitwise_or(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.OR, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_bitwise_or_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.OR_EQUALS, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_bitwise_xor(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.XOR, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_bitwise_xor_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.XOR_EQUALS, leftExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_bitwise_not(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.NOT, leftExpression);
    }



    public static GroupExpression e_add_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.ADD_EQUALS, leftExpression);
    }

    public static GroupExpression e_subtract_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.SUBTRACT_EQUALS, leftExpression);
    }

    public static GroupExpression e_multiply_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.MULTIPLY_EQUALS, leftExpression);
    }

    public static GroupExpression e_divide_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.DIVIDE_EQUALS, leftExpression);
    }

    public static GroupExpression e_modulo_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.MODULO_EQUALS, leftExpression);
    }

    public static GroupExpression e_bit_and_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.BITWISE_AND_EQUALS, leftExpression);
    }

    public static GroupExpression e_bit_exclusive_or_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.BITWISE_EXCLUSIVE_OR_EQUALS, leftExpression);
    }

    public static GroupExpression e_bit_or_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.BITWISE_OR_EQUALS, leftExpression);
    }


    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_all(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.ALL, leftExpression);
    }

    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_some(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.SOME, leftExpression);
    }

    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_any(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.ANY, leftExpression);
    }




    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_and(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.AND, leftExpression);
    }

    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static GroupExpression e_or(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.OR, leftExpression);
    }







    /**
     * string concatenation
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression e_concatenation(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, StringConcatenation.CONCATENATION, leftExpression);
    }




    /**
     * unary
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression e_plus(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Unary.PLUS, leftExpression);
    }

    /**
     * unary
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression e_negative(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Unary.NEGATIVE, leftExpression);
    }


}
