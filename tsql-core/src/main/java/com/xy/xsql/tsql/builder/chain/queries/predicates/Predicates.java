package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Comparison;
import com.xy.xsql.tsql.builder.chain.elements.operators.Operators;
import com.xy.xsql.tsql.model.queries.predicates.ComparisonSubQuery;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;
import com.xy.xsql.tsql.model.queries.Select;

/**
 * Predicates Factory
 * Created by xiaoyao9184 on 2017/3/23.
 */
@SuppressWarnings("unused")
public interface Predicates {

    /**
     * Between Predicate
     * @param left Expression
     * @param start Expression
     * @param end Expression
     * @return Predicate
     */
    static Predicate p_between(Expression left, Expression start, Expression end){
        return new BetweenPredicateBuilder<Void>()
                .withExpression(left)
                .withExpression(start)
                .withExpression(end)
                .build();
    }

    /**
     * Between Predicate
     * @param left Expression
     * @param start Expression
     * @param end Expression
     * @return Predicate
     */
    static Predicate p_not_between(Expression left, Expression start, Expression end){
        return new BetweenPredicateBuilder<Void>()
                .withNot()
                .withExpression(left)
                .withExpression(start)
                .withExpression(end)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left Expression
     * @param right Expression
     * @return Predicate
     */
    static Predicate p_equal(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Comparison.EQUAL)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
      * @param left Expression
     * @param right Expression
     * @return Predicate
     */
    static Predicate p_not_equal(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.$NotEqual)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left Expression
     * @param right Expression
     * @return Predicate
     */
    static Predicate p_not_equal_not_iso(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.$NotEqualNotIso)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left Expression
     * @param right Expression
     * @return Predicate
     */
    static Predicate p_greater(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.$Greater)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left Expression
     * @param right Expression
     * @return Predicate
     */
    static Predicate p_greater_equal(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.$GreaterEqual)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left Expression
     * @param right Expression
     * @return Predicate
     */
    static Predicate p_not_greater_equal_not_iso(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.$NotGreaterNotIso)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left Expression
     * @param right Expression
     * @return Predicate
     */
    static Predicate p_less(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.$Less)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left Expression
     * @param right Expression
     * @return Predicate
     */
    static Predicate p_less_equal(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.$LessEqual)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left Expression
     * @param right Expression
     * @return Predicate
     */
    static Predicate p_not_less_not_iso(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.$NotLessNotIso)
                .withExpression(right)
                .build();
    }

    /**
     * Quick build
     * @return ALL_SOME_ANY
     */
    static ComparisonSubQuery.ALL_SOME_ANY all(){
        return ComparisonSubQuery.ALL_SOME_ANY.ALL;
    }

    /**
     * Quick build
     * @return ALL_SOME_ANY
     */
    static ComparisonSubQuery.ALL_SOME_ANY some(){
        return ComparisonSubQuery.ALL_SOME_ANY.SOME;
    }

    /**
     * Quick build
     * @return ALL_SOME_ANY
     */
    static ComparisonSubQuery.ALL_SOME_ANY any(){
        return ComparisonSubQuery.ALL_SOME_ANY.ANY;
    }

    /**
     * Comparison SubQuery Predicate
     * @param left Expression
     * @param oneOf ALL_SOME_ANY
     * @param subQuery Select
     * @return Predicate
     */
    static Predicate p_equal(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subQuery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withAllSomeAny(oneOf)
                .withExpression(left)
                .withOperator(Operators.$Equal)
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left Expression
     * @param oneOf ALL_SOME_ANY
     * @param subQuery Select
     * @return Predicate
     */
    static Predicate p_not_equal(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subQuery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withAllSomeAny(oneOf)
                .withExpression(left)
                .withOperator(Operators.$NotEqual)
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left Expression
     * @param oneOf ALL_SOME_ANY
     * @param subQuery Select
     * @return Predicate
     */
    static Predicate p_not_equal_not_iso(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subQuery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withAllSomeAny(oneOf)
                .withExpression(left)
                .withOperator(Operators.$NotEqualNotIso)
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left Expression
     * @param oneOf ALL_SOME_ANY
     * @param subQuery Select
     * @return Predicate
     */
    static Predicate p_greater(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subQuery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withAllSomeAny(oneOf)
                .withExpression(left)
                .withOperator(Operators.$Greater)
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left Expression
     * @param oneOf ALL_SOME_ANY
     * @param subQuery Select
     * @return Predicate
     */
    static Predicate p_greater_equal(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subQuery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withAllSomeAny(oneOf)
                .withExpression(left)
                .withOperator(Operators.$GreaterEqual)
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left Expression
     * @param oneOf ALL_SOME_ANY
     * @param subQuery Select
     * @return Predicate
     */
    static Predicate p_not_greater_not_iso(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subQuery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withAllSomeAny(oneOf)
                .withExpression(left)
                .withOperator(Operators.$NotGreaterNotIso)
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left Expression
     * @param oneOf ALL_SOME_ANY
     * @param subQuery Select
     * @return Predicate
     */
    static Predicate p_less(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subQuery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withAllSomeAny(oneOf)
                .withExpression(left)
                .withOperator(Operators.$Less)
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left Expression
     * @param oneOf ALL_SOME_ANY
     * @param subQuery Select
     * @return Predicate
     */
    static Predicate p_less_equal(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subQuery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withAllSomeAny(oneOf)
                .withExpression(left)
                .withOperator(Operators.$LessEqual)
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left Expression
     * @param oneOf ALL_SOME_ANY
     * @param subQuery Select
     * @return Predicate
     */
    static Predicate p_not_less_not_iso(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subQuery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withAllSomeAny(oneOf)
                .withExpression(left)
                .withOperator(Operators.$NotLessNotIso)
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * Exists Predicate
     * @param subQuery Select
     * @return Predicate
     */
    static Predicate p_exists(Select subQuery){
        return new ExistsPredicateBuilder<Void>()
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * FreeText Predicate
     * @param freetextString freetext string
     * @param columnName column name
     * @return Predicate
     */
    static Predicate p_freetext(String freetextString, String... columnName){
        return new FreeTextPredicateBuilder<Void>()
                .withFreeText(freetextString)
                .withColumn(columnName)
                .build();
    }

    /**
     * In Predicate
     * @param left Expression
     * @param in Expression
     * @return Predicate
     */
    static Predicate p_in(Expression left, Expression... in){
        return new InPredicateBuilder<Void>()
                .withExpression(left)
                .withValueExpression(in)
                .build();
    }

    /**
     * In Predicate
     * @param left Expression
     * @param subQuery Select
     * @return Predicate
     */
    static Predicate p_in(Expression left, Select subQuery){
        return new InPredicateBuilder<Void>()
                .withExpression(left)
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * In Predicate
     * @param left Expression
     * @param in Expression
     * @return Predicate
     */
    static Predicate p_not_in(Expression left, Expression... in){
        return new InPredicateBuilder<Void>()
                .withExpression(left)
                .withNot()
                .withValueExpression(in)
                .build();
    }

    /**
     * In Predicate
     * @param left Expression
     * @param subQuery Select
     * @return Predicate
     */
    static Predicate p_not_in(Expression left, Select subQuery){
        return new InPredicateBuilder<Void>()
                .withExpression(left)
                .withNot()
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * IsNull Predicate
     * @param left Expression
     * @return Predicate
     */
    static Predicate p_is_null(Expression left){
        return new IsNullPredicateBuilder<Void>()
                .withExpression(left)
                .build();
    }

    /**
     * IsNull Predicate
     * @param left Expression
     * @return Predicate
     */
    static Predicate p_is_not_null(Expression left){
        return new IsNullPredicateBuilder<Void>()
                .withExpression(left)
                .withNot()
                .build();
    }

    /**
     * Like Predicate
     * @param left Expression
     * @param right Expression
     * @return Predicate
     */
    static Predicate p_like(Expression left, Expression right){
        return new LikePredicateBuilder<Void>()
                .withStringExpression(left)
                .withStringExpression(right)
                .build();
    }

    /**
     * Like Predicate
     * @param left Expression
     * @param right Expression
     * @param escape escape
     * @return Predicate
     */
    static Predicate p_like(Expression left, Expression right, StringConstant escape){
        return new LikePredicateBuilder<Void>()
                .withStringExpression(left)
                .withStringExpression(right)
                .withEscape(escape)
                .build();
    }

    /**
     * Like Predicate
     * @param left Expression
     * @param right Expression
     * @return Predicate
     */
    static Predicate p_not_like(Expression left, Expression right){
        return new LikePredicateBuilder<Void>()
                .withStringExpression(left)
                .withNot()
                .withStringExpression(right)
                .build();
    }

    /**
     * Like Predicate
     * @param left Expression
     * @param right Expression
     * @param escape escape
     * @return Predicate
     */
    static Predicate p_not_like(Expression left, Expression right, StringConstant escape){
        return new LikePredicateBuilder<Void>()
                .withStringExpression(left)
                .withNot()
                .withStringExpression(right)
                .withEscape(escape)
                .build();
    }

}
