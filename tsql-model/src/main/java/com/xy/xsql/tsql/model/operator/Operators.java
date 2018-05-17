package com.xy.xsql.tsql.model.operator;

/**
 * Created by xiaoyao9184 on 2016/10/22.
 */
public interface Operators {

    /**
     * Arithmetic Operators
     * https://msdn.microsoft.com/en-us/library/ms187716.aspx
     */
    Arithmetic ADDITION = Arithmetic.ADDITION;
    Arithmetic SUBTRACTION = Arithmetic.SUBTRACTION;
    Arithmetic MULTIPLICATION = Arithmetic.MULTIPLICATION;
    Arithmetic DIVISION = Arithmetic.DIVISION;
    Arithmetic MODULO = Arithmetic.MODULO;

    /**
     * Assignment Comparison
     * https://msdn.microsoft.com/en-us/library/ms188343.aspx
     */
    Assignment ASSIGNMENT = Assignment.ASSIGNMENT;

    /**
     * Bitwise Operators
     * https://msdn.microsoft.com/en-us/library/ms176122.aspx
     */
    Bitwise BITWISE_AND = Bitwise.AND;
    Bitwise BITWISE_AND_EQUALS = Bitwise.AND_EQUALS;
    Bitwise BITWISE_OR = Bitwise.OR;
    Bitwise BITWISE_OR_EQUALS = Bitwise.OR_EQUALS;
    Bitwise BITWISE_XOR = Bitwise.XOR;
    Bitwise BITWISE_XOR_EQUALS = Bitwise.XOR_EQUALS;
    Bitwise BITWISE_NOT = Bitwise.NOT;

    /**
     * Comparison Operators
     * https://msdn.microsoft.com/en-us/library/ms188074.aspx
     */
    Comparison EQUAL = Comparison.EQUAL;
    Comparison GREATER = Comparison.GREATER;
    Comparison LESS = Comparison.LESS;
    Comparison GREATER_EQUAL = Comparison.GREATER_EQUAL;
    Comparison LESS_EQUAL = Comparison.LESS_EQUAL;
    Comparison NOT_EQUAL = Comparison.NOT_EQUAL;
    Comparison NOT_EQUAL_NOT_ISO = Comparison.NOT_EQUAL_NOT_ISO;
    Comparison NOT_LESS_NOT_ISO = Comparison.NOT_LESS_NOT_ISO;
    Comparison NOT_GREATER_NOT_ISO = Comparison.NOT_GREATER_NOT_ISO;

    /**
     * Compound Operators
     * https://msdn.microsoft.com/en-us/library/cc645922.aspx
     */
    Compound ADD_ASSIGNMENT = Compound.ADD_ASSIGNMENT;
    Compound SUBTRACT_ASSIGNMENT = Compound.SUBTRACT_ASSIGNMENT;
    Compound MULTIPLY_ASSIGNMENT = Compound.MULTIPLY_ASSIGNMENT;
    Compound DIVIDE_ASSIGNMENT = Compound.DIVIDE_ASSIGNMENT;
    Compound MODULO_ASSIGNMENT = Compound.MODULO_ASSIGNMENT;
    Compound BITWISE_AND_ASSIGNMENT = Compound.BITWISE_AND_ASSIGNMENT;
    Compound BITWISE_EXCLUSIVE_OR_ASSIGNMENT = Compound.BITWISE_EXCLUSIVE_OR_ASSIGNMENT;
    Compound BITWISE_OR_ASSIGNMENT = Compound.BITWISE_OR_ASSIGNMENT;

    /**
     * Logical Operators
     * https://msdn.microsoft.com/en-us/library/ms189773.aspx
     */
    Logical ALL = Logical.ALL;
    Logical AND = Logical.AND;
    Logical ANY = Logical.ANY;
    Logical BETWEEN = Logical.BETWEEN;
    Logical EXISTS = Logical.EXISTS;
    Logical IN = Logical.IN;
    Logical LIKE = Logical.LIKE;
    Logical NOT = Logical.NOT;
    Logical OR = Logical.OR;
    Logical SOME = Logical.SOME;

    /**
     * Scope Resolution Comparison
     * https://msdn.microsoft.com/en-us/library/dd206995.aspx
     */
    Scope_Resolution SCOPE_RESOLUTION = Scope_Resolution.SCOPE_RESOLUTION;

    /**
     * Set Operators
     * https://msdn.microsoft.com/en-us/library/ff848745.aspx
     */
    Set EXCEPT = Set.EXCEPT;
    Set INTERSECT = Set.INTERSECT;
    Set UNION = Set.UNION;
    Set UNION_ALL = Set.UNION_ALL;

    /**
     * String Operators
     * https://msdn.microsoft.com/en-us/library/ms190301.aspx
     */
    StringConcatenation STRING_CONCATENATION = StringConcatenation.CONCATENATION;
    StringConcatenation STRING_CONCATENATION_SET = StringConcatenation.CONCATENATION_ASSIGNMENT;

    /**
     * Unary Operators
     * https://msdn.microsoft.com/en-us/library/ms188400.aspx
     */
    Unary PLUS = Unary.POSITIVE;
    Unary NEGATIVE = Unary.NEGATIVE;
    Unary COMPLEMENT = Unary.COMPLEMENT;

}
