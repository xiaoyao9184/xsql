package com.xy.xsql.tsql.model.operator;

/**
 * Created by xiaoyao9184 on 2016/10/22.
 */
public enum Operators implements Operator {

    /**
     * Arithmetic Operators
     * https://msdn.microsoft.com/en-us/library/ms187716.aspx
     */
    ADDITION(Arithmetic.ADDITION, Type.Arithmetic),
    SUBTRACTION(Arithmetic.SUBTRACTION, Type.Arithmetic),
    MULTIPLICATION(Arithmetic.MULTIPLICATION, Type.Arithmetic),
    DIVISION(Arithmetic.DIVISION, Type.Arithmetic),
    MODULO(Arithmetic.MODULO, Type.Arithmetic),

    /**
     * Assignment Operator
     * https://msdn.microsoft.com/en-us/library/ms188343.aspx
     */
    ASSIGNMENT(Assignment.ASSIGNMENT, Type.Assignment),

    /**
     * Bitwise Operators
     * https://msdn.microsoft.com/en-us/library/ms176122.aspx
     */
    BITWISE_AND(Bitwise.AND, Type.Bitwise),
    BITWISE_AND_EQUALS(Bitwise.AND_EQUALS, Type.Bitwise),
    BITWISE_OR(Bitwise.OR, Type.Bitwise),
    BITWISE_OR_EQUALS(Bitwise.OR_EQUALS, Type.Bitwise),
    BITWISE_XOR(Bitwise.XOR, Type.Bitwise),
    BITWISE_XOR_EQUALS(Bitwise.XOR_EQUALS, Type.Bitwise),
    BITWISE_NOT(Bitwise.NOT, Type.Bitwise),

    /**
     * Comparison Operators
     * https://msdn.microsoft.com/en-us/library/ms188074.aspx
     */
    EQUAL(Comparison.EQUAL, Type.Comparison),
    GREATER(Comparison.GREATER, Type.Comparison),
    LESS(Comparison.LESS, Type.Comparison),
    GREATER_EQUAL(Comparison.GREATER_EQUAL, Type.Comparison),
    LESS_EQUAL(Comparison.LESS_EQUAL, Type.Comparison),
    NOT_EQUAL(Comparison.NOT_EQUAL, Type.Comparison),
    NOT_EQUAL_NOT_ISO(Comparison.NOT_EQUAL_NOT_ISO, Type.Comparison),
    NOT_LESS_NOT_ISO(Comparison.NOT_LESS_NOT_ISO, Type.Comparison),
    NOT_GREATER_NOT_ISO(Comparison.NOT_GREATER_NOT_ISO, Type.Comparison),

    /**
     * Compound Operators
     * https://msdn.microsoft.com/en-us/library/cc645922.aspx
     */
    ADD_EQUALS(Compound.ADD_EQUALS, Type.Compound),
    SUBTRACT_EQUALS(Compound.SUBTRACT_EQUALS, Type.Compound),
    MULTIPLY_EQUALS(Compound.MULTIPLY_EQUALS, Type.Compound),
    DIVIDE_EQUALS(Compound.DIVIDE_EQUALS, Type.Compound),
    MODULO_EQUALS (Compound.MODULO_EQUALS, Type.Compound),
    BIT_AND_EQUALS(Compound.BITWISE_AND_EQUALS, Type.Compound),
    BIT_EXCLUSIVE_OR_EQUALS(Compound.BITWISE_EXCLUSIVE_OR_EQUALS, Type.Compound),
    BIT_OR_EQUALS(Compound.BITWISE_OR_EQUALS, Type.Compound),

    /**
     * Logical Operators
     * https://msdn.microsoft.com/en-us/library/ms189773.aspx
     */
    ALL(Logical.ALL, Type.Logical),
    AND(Logical.AND, Type.Logical),
    ANY(Logical.ANY, Type.Logical),
    BETWEEN(Logical.BETWEEN, Type.Logical),
    EXISTS(Logical.EXISTS, Type.Logical),
    IN(Logical.IN, Type.Logical),
    LIKE(Logical.LIKE, Type.Logical),
    NOT(Logical.NOT, Type.Logical),
    OR(Logical.OR, Type.Logical),
    SOME(Logical.SOME, Type.Logical),

    /**
     * Scope Resolution Operator
     * https://msdn.microsoft.com/en-us/library/dd206995.aspx
     */
    SCOPE_RESOLUTION(Scope_Resolution.SCOPE_RESOLUTION, Type.Scope_Resolution),

    /**
     * Set Operators
     * https://msdn.microsoft.com/en-us/library/ff848745.aspx
     */
    EXCEPT(Set.EXCEPT,Type.Set),
    INTERSECT(Set.INTERSECT,Type.Set),
    UNION(Set.UNION,Type.Set),
    UNION_ALL(Set.UNION_ALL,Type.Set),

    /**
     * String Operators
     * https://msdn.microsoft.com/en-us/library/ms190301.aspx
     */
    STRING_CONCATENATION(String.CONCATENATION, Type.String),
    STRING_CONCATENATION_SET(String.CONCATENATION_SET, Type.String),

    /**
     * Unary Operators
     * https://msdn.microsoft.com/en-us/library/ms188400.aspx
     */
    PLUS(Unary.PLUS,Type.Unary),
    NEGATIVE(Unary.NEGATIVE,Type.Unary);

    private Operator operator;
    private Type type;

    Operators(Operator operator, Type type){
        this.operator = operator;
        this.type = type;
    }

    public java.lang.String getKeyword(){
        return this.operator.getKeyword();
    }

    public Type getType(){
        return this.type;
    }

}
