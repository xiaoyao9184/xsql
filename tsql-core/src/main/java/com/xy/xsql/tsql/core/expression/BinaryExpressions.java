package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.tsql.model.expression.BinaryExpression;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.expression.BinaryExpression;
import com.xy.xsql.tsql.model.operator.*;

import java.util.Arrays;

/**
 * Expression Factory
 *
 * expression { binary_operator } expression
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class BinaryExpressions {

    public static BinaryExpression e_binary(Expression leftExpression, Operator operator, Expression... rightExpression){
        BinaryExpression groupExpression = new BinaryExpression();
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
    public static BinaryExpression e_addition(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.ADDITION, rightExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_subtraction(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.SUBTRACTION, rightExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_multiplication(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.MULTIPLICATION, rightExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_division(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.DIVISION, rightExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_modulo(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.MODULO, rightExpression);
    }

    /**
     * assignment
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_assignment(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Assignment.ASSIGNMENT, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_bitwise_and(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.AND, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_bitwise_and_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.AND_EQUALS, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_bitwise_or(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.OR, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_bitwise_or_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.OR_EQUALS, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_bitwise_xor(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.XOR, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_bitwise_xor_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.XOR_EQUALS, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_bitwise_not(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.NOT, rightExpression);
    }



    public static BinaryExpression e_add_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.ADD_EQUALS, rightExpression);
    }

    public static BinaryExpression e_subtract_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.SUBTRACT_EQUALS, rightExpression);
    }

    public static BinaryExpression e_multiply_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.MULTIPLY_EQUALS, rightExpression);
    }

    public static BinaryExpression e_divide_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.DIVIDE_EQUALS, rightExpression);
    }

    public static BinaryExpression e_modulo_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.MODULO_EQUALS, rightExpression);
    }

    public static BinaryExpression e_bit_and_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.BITWISE_AND_EQUALS, rightExpression);
    }

    public static BinaryExpression e_bit_exclusive_or_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.BITWISE_EXCLUSIVE_OR_EQUALS, rightExpression);
    }

    public static BinaryExpression e_bit_or_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Compound.BITWISE_OR_EQUALS, rightExpression);
    }


    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_all(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.ALL, rightExpression);
    }

    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_some(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.SOME, rightExpression);
    }

    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_any(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.ANY, rightExpression);
    }




    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_and(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.AND, rightExpression);
    }

    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static BinaryExpression e_or(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.OR, rightExpression);
    }







    /**
     * string concatenation
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression e_concatenation(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, StringConcatenation.CONCATENATION, rightExpression);
    }




    /**
     * unary
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression e_plus(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Unary.PLUS, rightExpression);
    }

    /**
     * unary
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    public static Expression e_negative(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Unary.NEGATIVE, rightExpression);
    }


}
