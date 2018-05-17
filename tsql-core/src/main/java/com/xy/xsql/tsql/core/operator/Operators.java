package com.xy.xsql.tsql.core.operator;

import com.xy.xsql.tsql.model.operator.*;

/**
 * Operator Factory
 * Created by xiaoyao9184 on 2017/3/18.
 */
@Deprecated
public class Operators {

    public static class Arithmetic {
        public static Operator ADDITION = com.xy.xsql.tsql.model.operator.Arithmetic.ADDITION;
        public static Operator SUBTRACTION = com.xy.xsql.tsql.model.operator.Arithmetic.SUBTRACTION;
        public static Operator MULTIPLICATION = com.xy.xsql.tsql.model.operator.Arithmetic.MULTIPLICATION;
        public static Operator DIVISION = com.xy.xsql.tsql.model.operator.Arithmetic.DIVISION;
        public static Operator MODULO = com.xy.xsql.tsql.model.operator.Arithmetic.MODULO;
    }

    public static class Assignment {
        public static Operator ASSIGNMENT = com.xy.xsql.tsql.model.operator.Assignment.ASSIGNMENT;
    }

    public static class Bitwise {
        public static Operator AND = com.xy.xsql.tsql.model.operator.Bitwise.AND;
        public static Operator AND_EQUALS = com.xy.xsql.tsql.model.operator.Bitwise.AND_EQUALS;
        public static Operator OR = com.xy.xsql.tsql.model.operator.Bitwise.OR;
        public static Operator OR_EQUALS = com.xy.xsql.tsql.model.operator.Bitwise.OR_EQUALS;
        public static Operator XOR = com.xy.xsql.tsql.model.operator.Bitwise.XOR;
        public static Operator XOR_EQUALS = com.xy.xsql.tsql.model.operator.Bitwise.XOR_EQUALS;
        public static Operator NOT = com.xy.xsql.tsql.model.operator.Bitwise.NOT;
    }

    public static class Comparison {
        public static Operator EQUAL = com.xy.xsql.tsql.model.operator.Comparison.EQUAL;
        public static Operator GREATER = com.xy.xsql.tsql.model.operator.Comparison.GREATER;
        public static Operator LESS = com.xy.xsql.tsql.model.operator.Comparison.LESS;
        public static Operator GREATER_EQUAL = com.xy.xsql.tsql.model.operator.Comparison.GREATER_EQUAL;
        public static Operator LESS_EQUAL = com.xy.xsql.tsql.model.operator.Comparison.LESS_EQUAL;
        public static Operator NOT_EQUAL = com.xy.xsql.tsql.model.operator.Comparison.NOT_EQUAL;
        public static Operator NOT_EQUAL_NOT_ISO = com.xy.xsql.tsql.model.operator.Comparison.NOT_EQUAL_NOT_ISO;
        public static Operator NOT_LESS_NOT_ISO = com.xy.xsql.tsql.model.operator.Comparison.NOT_LESS_NOT_ISO;
        public static Operator NOT_GREATER_NOT_ISO = com.xy.xsql.tsql.model.operator.Comparison.NOT_GREATER_NOT_ISO;
    }

    public static class Compound {
        public static Operator ADD_ASSIGNMENT = com.xy.xsql.tsql.model.operator.Compound.ADD_ASSIGNMENT;
        public static Operator SUBTRACT_ASSIGNMENT = com.xy.xsql.tsql.model.operator.Compound.SUBTRACT_ASSIGNMENT;
        public static Operator MULTIPLY_ASSIGNMENT = com.xy.xsql.tsql.model.operator.Compound.MULTIPLY_ASSIGNMENT;
        public static Operator DIVIDE_ASSIGNMENT = com.xy.xsql.tsql.model.operator.Compound.DIVIDE_ASSIGNMENT;
        public static Operator MODULO_ASSIGNMENT = com.xy.xsql.tsql.model.operator.Compound.MODULO_ASSIGNMENT;
        public static Operator BITWISE_AND_ASSIGNMENT = com.xy.xsql.tsql.model.operator.Compound.BITWISE_AND_ASSIGNMENT;
        public static Operator BITWISE_EXCLUSIVE_OR_ASSIGNMENT = com.xy.xsql.tsql.model.operator.Compound.BITWISE_EXCLUSIVE_OR_ASSIGNMENT;
        public static Operator BITWISE_OR_ASSIGNMENT = com.xy.xsql.tsql.model.operator.Compound.BITWISE_OR_ASSIGNMENT;
    }

    public static class Logical {
        public static Operator ALL = com.xy.xsql.tsql.model.operator.Logical.ALL;
        public static Operator AND = com.xy.xsql.tsql.model.operator.Logical.AND;
        public static Operator ANY = com.xy.xsql.tsql.model.operator.Logical.ANY;
        public static Operator BETWEEN = com.xy.xsql.tsql.model.operator.Logical.BETWEEN;
        public static Operator EXISTS = com.xy.xsql.tsql.model.operator.Logical.EXISTS;
        public static Operator IN = com.xy.xsql.tsql.model.operator.Logical.IN;
        public static Operator LIKE = com.xy.xsql.tsql.model.operator.Logical.LIKE;
        public static Operator NOT = com.xy.xsql.tsql.model.operator.Logical.NOT;
        public static Operator OR = com.xy.xsql.tsql.model.operator.Logical.OR;
        public static Operator SOME = com.xy.xsql.tsql.model.operator.Logical.SOME;
    }

    public static class Set {
        public static Operator EXCEPT = com.xy.xsql.tsql.model.operator.Set.EXCEPT;
        public static Operator INTERSECT = com.xy.xsql.tsql.model.operator.Set.INTERSECT;
        public static Operator UNION = com.xy.xsql.tsql.model.operator.Set.UNION;
        public static Operator UNION_ALL = com.xy.xsql.tsql.model.operator.Set.UNION_ALL;
    }

    public static class StringConcatenation {
        public static Operator CONCATENATION = com.xy.xsql.tsql.model.operator.StringConcatenation.CONCATENATION;
        public static Operator CONCATENATION_SET = com.xy.xsql.tsql.model.operator.StringConcatenation.CONCATENATION_ASSIGNMENT;
    }

    public static class Unary {
        public static Operator POSITIVE = com.xy.xsql.tsql.model.operator.Unary.POSITIVE;
        public static Operator NEGATIVE = com.xy.xsql.tsql.model.operator.Unary.NEGATIVE;
        public static Operator COMPLEMENT = com.xy.xsql.tsql.model.operator.Unary.COMPLEMENT;
    }
}
