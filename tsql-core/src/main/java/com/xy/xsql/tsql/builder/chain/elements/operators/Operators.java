package com.xy.xsql.tsql.builder.chain.elements.operators;

import com.xy.xsql.tsql.model.elements.operators.*;

/**
 * Operator Factory
 * Created by xiaoyao9184 on 2016/10/22.
 */
@SuppressWarnings("unused")
public interface Operators {

    /*
     * Arithmetic Operators
     */
    Arithmetic $Addition = Arithmetic.ADDITION;
    Arithmetic $Subtraction = Arithmetic.SUBTRACTION;
    Arithmetic $Multiplication = Arithmetic.MULTIPLICATION;
    Arithmetic $Division = Arithmetic.DIVISION;
    Arithmetic $Modulo = Arithmetic.MODULO;

    /*
     * Assignment Comparison
     */
    Assignment $Assignment = Assignment.ASSIGNMENT;

    /*
     * Bitwise Operators
     */
    Bitwise $BitwiseAnd = Bitwise.AND;
    Bitwise $BitwiseAndEquals = Bitwise.AND_EQUALS;
    Bitwise $BitwiseOr = Bitwise.OR;
    Bitwise $BitwiseOrEquals = Bitwise.OR_EQUALS;
    Bitwise $BitwiseXor = Bitwise.XOR;
    Bitwise $BitwiseXorEquals = Bitwise.XOR_EQUALS;
    Bitwise $BitwiseNot = Bitwise.NOT;

    /*
     * Comparison Operators
     */
    Comparison $Equal = Comparison.EQUAL;
    Comparison $Greater = Comparison.GREATER;
    Comparison $Less = Comparison.LESS;
    Comparison $GreaterEqual = Comparison.GREATER_EQUAL;
    Comparison $LessEqual = Comparison.LESS_EQUAL;
    Comparison $NotEqual = Comparison.NOT_EQUAL;
    Comparison $NotEqualNotIso = Comparison.NOT_EQUAL_NOT_ISO;
    Comparison $NotLessNotIso = Comparison.NOT_LESS_NOT_ISO;
    Comparison $NotGreaterNotIso = Comparison.NOT_GREATER_NOT_ISO;

    /*
     * Compound Operators
     */
    Compound $AssignmentAdd = Compound.ADD_ASSIGNMENT;
    Compound $AssignmentSubtract = Compound.SUBTRACT_ASSIGNMENT;
    Compound $AssignmentMultiply = Compound.MULTIPLY_ASSIGNMENT;
    Compound $AssignmentDivide = Compound.DIVIDE_ASSIGNMENT;
    Compound $AssignmentModulo = Compound.MODULO_ASSIGNMENT;
    Compound $AssignmentBitwiseAnd = Compound.BITWISE_AND_ASSIGNMENT;
    Compound $AssignmentBitwiseExclusiveOr = Compound.BITWISE_EXCLUSIVE_OR_ASSIGNMENT;
    Compound $AssignmentBitwiseOr = Compound.BITWISE_OR_ASSIGNMENT;

    /*
     * Logical Operators
     */
    Logical $All = Logical.ALL;
    Logical $And = Logical.AND;
    Logical $Any = Logical.ANY;
    Logical $Between = Logical.BETWEEN;
    Logical $Exists = Logical.EXISTS;
    Logical $In = Logical.IN;
    Logical $Like = Logical.LIKE;
    Logical $Not = Logical.NOT;
    Logical $Or = Logical.OR;
    Logical $Some = Logical.SOME;

    /*
     * Scope Resolution Comparison
     */
    Scope_Resolution $ScopeResolution = Scope_Resolution.SCOPE_RESOLUTION;

    /*
     * Set Operators
     */
    Set $Except = Set.EXCEPT;
    Set $Intersect = Set.INTERSECT;
    Set $Union = Set.UNION;
    Set $UnionAll = Set.UNION_ALL;

    /*
     * String Operators
     */
    StringConcatenation $StringConcatenation = StringConcatenation.CONCATENATION;
    StringConcatenation $StringConcatenationSet = StringConcatenation.CONCATENATION_ASSIGNMENT;

    /*
     * Unary Operators
     */
    Unary $Positive = Unary.POSITIVE;
    Unary $Negative = Unary.NEGATIVE;
    Unary $Complement = Unary.COMPLEMENT;

}
