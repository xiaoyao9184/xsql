package com.xy.xsql.tsql.core.operator;

import com.xy.xsql.tsql.model.elements.operators.*;

/**
 * Operator Factory
 * Created by xiaoyao9184 on 2017/3/18.
 */
@Deprecated
public class Operators {

    public static class Arithmetic {
        public static Operator ADDITION = com.xy.xsql.tsql.model.elements.operators.Arithmetic.ADDITION;
        public static Operator SUBTRACTION = com.xy.xsql.tsql.model.elements.operators.Arithmetic.SUBTRACTION;
        public static Operator MULTIPLICATION = com.xy.xsql.tsql.model.elements.operators.Arithmetic.MULTIPLICATION;
        public static Operator DIVISION = com.xy.xsql.tsql.model.elements.operators.Arithmetic.DIVISION;
        public static Operator MODULO = com.xy.xsql.tsql.model.elements.operators.Arithmetic.MODULO;
    }

    public static class Assignment {
        public static Operator ASSIGNMENT = com.xy.xsql.tsql.model.elements.operators.Assignment.ASSIGNMENT;
    }

    public static class Bitwise {
        public static Operator AND = com.xy.xsql.tsql.model.elements.operators.Bitwise.AND;
        public static Operator AND_EQUALS = com.xy.xsql.tsql.model.elements.operators.Bitwise.AND_EQUALS;
        public static Operator OR = com.xy.xsql.tsql.model.elements.operators.Bitwise.OR;
        public static Operator OR_EQUALS = com.xy.xsql.tsql.model.elements.operators.Bitwise.OR_EQUALS;
        public static Operator XOR = com.xy.xsql.tsql.model.elements.operators.Bitwise.XOR;
        public static Operator XOR_EQUALS = com.xy.xsql.tsql.model.elements.operators.Bitwise.XOR_EQUALS;
        public static Operator NOT = com.xy.xsql.tsql.model.elements.operators.Bitwise.NOT;
    }

    public static class Comparison {
        public static Operator EQUAL = com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL;
        public static Operator GREATER = com.xy.xsql.tsql.model.elements.operators.Comparison.GREATER;
        public static Operator LESS = com.xy.xsql.tsql.model.elements.operators.Comparison.LESS;
        public static Operator GREATER_EQUAL = com.xy.xsql.tsql.model.elements.operators.Comparison.GREATER_EQUAL;
        public static Operator LESS_EQUAL = com.xy.xsql.tsql.model.elements.operators.Comparison.LESS_EQUAL;
        public static Operator NOT_EQUAL = com.xy.xsql.tsql.model.elements.operators.Comparison.NOT_EQUAL;
        public static Operator NOT_EQUAL_NOT_ISO = com.xy.xsql.tsql.model.elements.operators.Comparison.NOT_EQUAL_NOT_ISO;
        public static Operator NOT_LESS_NOT_ISO = com.xy.xsql.tsql.model.elements.operators.Comparison.NOT_LESS_NOT_ISO;
        public static Operator NOT_GREATER_NOT_ISO = com.xy.xsql.tsql.model.elements.operators.Comparison.NOT_GREATER_NOT_ISO;
    }

    public static class Compound {
        public static Operator ADD_ASSIGNMENT = com.xy.xsql.tsql.model.elements.operators.Compound.ADD_ASSIGNMENT;
        public static Operator SUBTRACT_ASSIGNMENT = com.xy.xsql.tsql.model.elements.operators.Compound.SUBTRACT_ASSIGNMENT;
        public static Operator MULTIPLY_ASSIGNMENT = com.xy.xsql.tsql.model.elements.operators.Compound.MULTIPLY_ASSIGNMENT;
        public static Operator DIVIDE_ASSIGNMENT = com.xy.xsql.tsql.model.elements.operators.Compound.DIVIDE_ASSIGNMENT;
        public static Operator MODULO_ASSIGNMENT = com.xy.xsql.tsql.model.elements.operators.Compound.MODULO_ASSIGNMENT;
        public static Operator BITWISE_AND_ASSIGNMENT = com.xy.xsql.tsql.model.elements.operators.Compound.BITWISE_AND_ASSIGNMENT;
        public static Operator BITWISE_EXCLUSIVE_OR_ASSIGNMENT = com.xy.xsql.tsql.model.elements.operators.Compound.BITWISE_EXCLUSIVE_OR_ASSIGNMENT;
        public static Operator BITWISE_OR_ASSIGNMENT = com.xy.xsql.tsql.model.elements.operators.Compound.BITWISE_OR_ASSIGNMENT;
    }

    public static class Logical {
        public static Operator ALL = com.xy.xsql.tsql.model.elements.operators.Logical.ALL;
        public static Operator AND = com.xy.xsql.tsql.model.elements.operators.Logical.AND;
        public static Operator ANY = com.xy.xsql.tsql.model.elements.operators.Logical.ANY;
        public static Operator BETWEEN = com.xy.xsql.tsql.model.elements.operators.Logical.BETWEEN;
        public static Operator EXISTS = com.xy.xsql.tsql.model.elements.operators.Logical.EXISTS;
        public static Operator IN = com.xy.xsql.tsql.model.elements.operators.Logical.IN;
        public static Operator LIKE = com.xy.xsql.tsql.model.elements.operators.Logical.LIKE;
        public static Operator NOT = com.xy.xsql.tsql.model.elements.operators.Logical.NOT;
        public static Operator OR = com.xy.xsql.tsql.model.elements.operators.Logical.OR;
        public static Operator SOME = com.xy.xsql.tsql.model.elements.operators.Logical.SOME;
    }

    public static class Set {
        public static Operator EXCEPT = com.xy.xsql.tsql.model.elements.operators.Set.EXCEPT;
        public static Operator INTERSECT = com.xy.xsql.tsql.model.elements.operators.Set.INTERSECT;
        public static Operator UNION = com.xy.xsql.tsql.model.elements.operators.Set.UNION;
        public static Operator UNION_ALL = com.xy.xsql.tsql.model.elements.operators.Set.UNION_ALL;
    }

    public static class StringConcatenation {
        public static Operator CONCATENATION = com.xy.xsql.tsql.model.elements.operators.StringConcatenation.CONCATENATION;
        public static Operator CONCATENATION_SET = com.xy.xsql.tsql.model.elements.operators.StringConcatenation.CONCATENATION_ASSIGNMENT;
    }

    public static class Unary {
        public static Operator POSITIVE = com.xy.xsql.tsql.model.elements.operators.Unary.POSITIVE;
        public static Operator NEGATIVE = com.xy.xsql.tsql.model.elements.operators.Unary.NEGATIVE;
        public static Operator COMPLEMENT = com.xy.xsql.tsql.model.elements.operators.Unary.COMPLEMENT;
    }
}
