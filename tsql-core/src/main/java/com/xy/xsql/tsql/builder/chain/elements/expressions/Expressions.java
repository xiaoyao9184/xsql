package com.xy.xsql.tsql.builder.chain.elements.expressions;

import com.xy.xsql.tsql.model.datatypes.constants.BinaryConstant;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.*;
import com.xy.xsql.tsql.model.elements.expressions.keyword.Default;
import com.xy.xsql.tsql.model.elements.expressions.keyword.KeywordExpression;
import com.xy.xsql.tsql.model.elements.expressions.keyword.Null;
import com.xy.xsql.tsql.model.elements.operators.*;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;

import java.util.Arrays;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.*;

/**
 * Expression Factory
 *
 * https://msdn.microsoft.com/en-us/library/ms190286.aspx
 *
 * Created by xiaoyao9184 on 2017/3/10.
 */
public interface Expressions {

    /**
     * unknown
     * @param expressionString
     * @return
     */
    static UnknownExpression e(String expressionString){
        return new UnknownExpression(expressionString);
    }


    //

    /**
     * null
     * @return
     */
    static KeywordExpression e_null(){
        return new Null();
    }

    /**
     * default
     * @return
     */
    static KeywordExpression e_default(){
        return new Default();
    }

    //constant

    /**
     * string constant
     * @param stringExpression
     * @return StringConstant
     */
    static StringConstant e_string(String stringExpression){
        return c_string(stringExpression);
    }

    /**
     * string constant
     * @param stringExpression
     * @return StringConstant
     */
    static StringConstant e_n_string(String stringExpression){
        return c_n_string(stringExpression);
    }

    /**
     * constant
     * @param bin
     * @return BinaryConstant
     */
    static BinaryConstant e_bin(byte[] bin){
        return new BinaryConstant(bin);
    }

    /**
     * constant
     * @param numberExpression
     * @return NumberConstant
     */
    static NumberConstant e_number(Number numberExpression){
        return c_number(numberExpression);
    }

    //TODO scalar_function

    //column in ColumnNameFactory

    //variable

    /**
     * Is the name of a variable, or parameter
     * @param variableExpression
     * @return
     */
    static LocalVariable e_variable(String variableExpression){
        return new LocalVariable(variableExpression);
    }

    //expression

    /**
     *
     * @param expression
     * @return
     */
    static GroupExpression e(Expression expression){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setExpression(expression);
        return groupExpression;
    }

    //scalar_subquery

    /**
     * TODO maybe not use GroupExpression
     * Is a subquery that returns one value
     * @param select
     * @return
     */
    static Expression e(Select select){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setStatement(select);
        return groupExpression;
    }

    /**
     * TODO maybe not use GroupExpression
     * Is a subquery that returns one value
     * @param query
     * @return
     */
    static Expression e_subquery(Select.QuerySpecification query){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setStatement(query);
        return groupExpression;
    }

    static Expression e_subquery(Select select){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setStatement(select);
        return groupExpression;
    }



    //

    static BinaryExpression e_binary(Expression leftExpression, BinaryOperator operator, Expression... rightExpression){
        BinaryExpression groupExpression = new BinaryExpression();
        groupExpression.setBinaryOperator(operator);
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
    static BinaryExpression e_subtraction(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.SUBTRACTION, rightExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_multiplication(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.MULTIPLICATION, rightExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_division(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.DIVISION, rightExpression);
    }

    /**
     * arithmetic
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_modulo(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Arithmetic.MODULO, rightExpression);
    }

    /**
     * assignment
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_assignment(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Assignment.ASSIGNMENT, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_bitwise_and(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.AND, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_bitwise_and_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.AND_EQUALS, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_bitwise_or(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.OR, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_bitwise_or_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.OR_EQUALS, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_bitwise_xor(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.XOR, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_bitwise_xor_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.XOR_EQUALS, rightExpression);
    }

    /**
     * bitwise
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_bitwise_not(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Bitwise.NOT, rightExpression);
    }

    static BinaryExpression e_equals(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Comparison.EQUAL, rightExpression);
    }
    static BinaryExpression e_grerater(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Comparison.GREATER, rightExpression);
    }
    static BinaryExpression e_less(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Comparison.LESS, rightExpression);
    }
    static BinaryExpression e_greater_equal(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Comparison.GREATER_EQUAL, rightExpression);
    }
    static BinaryExpression e_less_equal(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Comparison.LESS_EQUAL, rightExpression);
    }
    static BinaryExpression e_not_equal(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Comparison.NOT_EQUAL, rightExpression);
    }
    static BinaryExpression e_not_equal_not_iso(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Comparison.NOT_EQUAL_NOT_ISO, rightExpression);
    }
    static BinaryExpression e_not_less_not_iso(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Comparison.NOT_LESS_NOT_ISO, rightExpression);
    }
    static BinaryExpression e_not_greater_not_iso(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Comparison.NOT_GREATER_NOT_ISO, rightExpression);
    }

//    static BinaryExpression e_add_equals(Expression leftExpression, Expression rightExpression){
//        return e_binary(leftExpression, Compound.ADD_ASSIGNMENT, rightExpression);
//    }
//
//    static BinaryExpression e_subtract_equals(Expression leftExpression, Expression rightExpression){
//        return e_binary(leftExpression, Compound.SUBTRACT_ASSIGNMENT, rightExpression);
//    }
//
//    static BinaryExpression e_multiply_equals(Expression leftExpression, Expression rightExpression){
//        return e_binary(leftExpression, Compound.MULTIPLY_ASSIGNMENT, rightExpression);
//    }
//
//    static BinaryExpression e_divide_equals(Expression leftExpression, Expression rightExpression){
//        return e_binary(leftExpression, Compound.DIVIDE_ASSIGNMENT, rightExpression);
//    }
//
//    static BinaryExpression e_modulo_equals(Expression leftExpression, Expression rightExpression){
//        return e_binary(leftExpression, Compound.MODULO_ASSIGNMENT, rightExpression);
//    }
//
//    static BinaryExpression e_bit_and_equals(Expression leftExpression, Expression rightExpression){
//        return e_binary(leftExpression, Compound.BITWISE_AND_ASSIGNMENT, rightExpression);
//    }
//
//    static BinaryExpression e_bit_exclusive_or_equals(Expression leftExpression, Expression rightExpression){
//        return e_binary(leftExpression, Compound.BITWISE_EXCLUSIVE_OR_ASSIGNMENT, rightExpression);
//    }
//
//    static BinaryExpression e_bit_or_equals(Expression leftExpression, Expression rightExpression){
//        return e_binary(leftExpression, Compound.BITWISE_OR_ASSIGNMENT, rightExpression);
//    }


    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_and(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.AND, rightExpression);
    }

    /**
     * logical
     * @param leftExpression
     * @param rightExpression
     * @return
     */
    static BinaryExpression e_or(Expression leftExpression, Expression rightExpression){
        return e_binary(leftExpression, Logical.OR, rightExpression);
    }

    /*
    logical operator
    ALL,AND,ANY,BETWEEN,EXISTS,IN,LIKE,NOT,OR,SOME
    most of these are not binocular operators but special predicate,
    like BETWEEN,EXISTS,IN,LIKE;ALL,ANY,SOME.
    and remain NOT
     */

    /**
     * [ NOT ] boolean_expression is Much like [ NOT ] <predicate>
     * @param expression
     * @return
     */
    static NotExpression e_not(Expression expression){
        NotExpression notExpression = new NotExpression();
        notExpression.setExpression(expression);
        return notExpression;
    }



//    /**
//     * logical
//     * @param leftExpression
//     * @param rightExpression
//     * @return
//     */
//    static BinaryExpression e_all(Expression leftExpression, Expression rightExpression){
//        return e_binary(leftExpression, Logical.ALL, rightExpression);
//    }
//
//    /**
//     * logical
//     * @param leftExpression
//     * @param rightExpression
//     * @return
//     */
//    static BinaryExpression e_some(Expression leftExpression, Expression rightExpression){
//        return e_binary(leftExpression, Logical.SOME, rightExpression);
//    }
//
//    /**
//     * logical
//     * @param leftExpression
//     * @param rightExpression
//     * @return
//     */
//    static BinaryExpression e_any(Expression leftExpression, Expression rightExpression){
//        return e_binary(leftExpression, Logical.ANY, rightExpression);
//    }

    //

    static UnaryExpression e_positive(Expression expression){
        UnaryExpression unaryExpression = new UnaryExpression();
        unaryExpression.setUnary(Unary.POSITIVE);
        unaryExpression.setExpression(expression);
        return unaryExpression;
    }

    static UnaryExpression e_negative(Expression expression){
        UnaryExpression unaryExpression = new UnaryExpression();
        unaryExpression.setUnary(Unary.NEGATIVE);
        unaryExpression.setExpression(expression);
        return unaryExpression;
    }

    static UnaryExpression e_complement(Expression expression){
        UnaryExpression unaryExpression = new UnaryExpression();
        unaryExpression.setUnary(Unary.COMPLEMENT);
        unaryExpression.setExpression(expression);
        return unaryExpression;
    }


    //TODO ranking_windowed_function | aggregate_windowed_function



    /**
     * Case
     * @return
     */
    static CaseBuilder<Void> e_case(){
        return new CaseBuilder<>();
    }

    /**
     * Case
     * @return
     */
    static CaseBuilder<Void> e_case(Expression expression){
        return new CaseBuilder<Void>()
                .withInput(expression);
    }

    /**
     * Coalesce
     * @param expressions
     * @return
     */
    static Expression e_coalesce(Expression... expressions){
        return new CoalesceBuilder<Void>()
                .withExpression(expressions)
                .build();
    }

    /**
     * NullIf
     * @param expressionLeft
     * @param expressionRight
     * @return
     */
    static Expression e_nullif(Expression expressionLeft, Expression expressionRight){
        return new NullIfBuilder<Void>()
                .withExpression(expressionLeft)
                .withExpression(expressionRight)
                .build();
    }
//
//    /**
//     * AtTimeZone
//     * @param expression
//     * @return
//     */
//    static Expression e_at_time_zone(Expression expression,String timeZone){
//        return new AtTimeZoneBuilder<Void>()
//                .withExpression(expression)
//                .withTimezone(timeZone)
//                .build();
//    }

}
