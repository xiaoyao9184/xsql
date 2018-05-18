package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Comparison;
import com.xy.xsql.tsql.model.elements.operators.Operators;
import com.xy.xsql.tsql.model.queries.predicates.ComparisonSubQuery;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * Predicates Factory
 * Created by xiaoyao9184 on 2017/3/23.
 */
public class Predicates {

    /**
     * Between Predicate
     * @param left
     * @param start
     * @param end
     * @return
     */
    public static Predicate p_between(Expression left, Expression start, Expression end){
        return new BetweenPredicateBuilder<Void>()
                .withExpression(left)
                .withExpression(start)
                .withExpression(end)
                .build();
    }

    /**
     * Between Predicate
     * @param left
     * @param start
     * @param end
     * @return
     */
    public static Predicate p_not_between(Expression left,Expression start,Expression end){
        return new BetweenPredicateBuilder<Void>()
                .withNot()
                .withExpression(left)
                .withExpression(start)
                .withExpression(end)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left
     * @param right
     * @return
     */
    public static Predicate p_equal(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Comparison.EQUAL)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
      * @param left
     * @param right
     * @return
     */
    public static Predicate p_not_equal(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.NOT_EQUAL)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left
     * @param right
     * @return
     */
    public static Predicate p_not_equal_not_iso(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.NOT_EQUAL_NOT_ISO)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left
     * @param right
     * @return
     */
    public static Predicate p_greater(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.GREATER)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left
     * @param right
     * @return
     */
    public static Predicate p_greater_equal(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.GREATER_EQUAL)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left
     * @param right
     * @return
     */
    public static Predicate p_not_greater_equal_not_iso(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.NOT_GREATER_NOT_ISO)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left
     * @param right
     * @return
     */
    public static Predicate p_less(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.LESS)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left
     * @param right
     * @return
     */
    public static Predicate p_less_equal(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.LESS_EQUAL)
                .withExpression(right)
                .build();
    }

    /**
     * Comparison Predicate
     * @param left
     * @param right
     * @return
     */
    public static Predicate p_not_less_not_iso(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.NOT_LESS_NOT_ISO)
                .withExpression(right)
                .build();
    }


    public static ComparisonSubQuery.ALL_SOME_ANY all(){
        return ComparisonSubQuery.ALL_SOME_ANY.ALL;
    }

    public static ComparisonSubQuery.ALL_SOME_ANY some(){
        return ComparisonSubQuery.ALL_SOME_ANY.SOME;
    }

    public static ComparisonSubQuery.ALL_SOME_ANY any(){
        return ComparisonSubQuery.ALL_SOME_ANY.ANY;
    }


    /**
     * Comparison SubQuery Predicate
     * @param left
     * @param oneOf
     * @param subquery
     * @return
     */
    public static Predicate p_equal(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.EQUAL)
                .withSubQuery(subquery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left
     * @param oneOf
     * @param subquery
     * @return
     */
    public static Predicate p_not_equal(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.NOT_EQUAL)
                .withSubQuery(subquery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left
     * @param oneOf
     * @param subquery
     * @return
     */
    public static Predicate p_not_equal_not_iso(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.NOT_EQUAL_NOT_ISO)
                .withSubQuery(subquery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left
     * @param oneOf
     * @param subquery
     * @return
     */
    public static Predicate p_greater(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.GREATER)
                .withSubQuery(subquery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left
     * @param oneOf
     * @param subquery
     * @return
     */
    public static Predicate p_greater_equal(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.GREATER_EQUAL)
                .withSubQuery(subquery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left
     * @param oneOf
     * @param subquery
     * @return
     */
    public static Predicate p_not_greater_not_iso(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.NOT_GREATER_NOT_ISO)
                .withSubQuery(subquery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left
     * @param oneOf
     * @param subquery
     * @return
     */
    public static Predicate p_less(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.LESS)
                .withSubQuery(subquery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left
     * @param oneOf
     * @param subquery
     * @return
     */
    public static Predicate p_less_equal(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.LESS_EQUAL)
                .withSubQuery(subquery)
                .build();
    }

    /**
     * Comparison SubQuery Predicate
     * @param left
     * @param oneOf
     * @param subquery
     * @return
     */
    public static Predicate p_not_less_not_iso(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ComparisonSubQueryPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.NOT_LESS_NOT_ISO)
                .withSubQuery(subquery)
                .build();
    }

    /**
     * Exists Predicate
     * @param subquery
     * @return
     */
    public static Predicate p_exists(Select subquery){
        return new ExistsPredicateBuilder<Void>()
                .withSubQuery(subquery)
                .build();
    }

    /**
     * FreeText Predicate
     * @param freetextString
     * @param columnName
     * @return
     */
    public static Predicate p_freetext(String freetextString, String... columnName){
        return new FreeTextPredicateBuilder<Void>()
                .withFreeText(freetextString)
                .withColumn(columnName)
                .build();
    }

    /**
     * In Predicate
     * @param left
     * @param in
     * @return
     */
    public static Predicate p_in(Expression left, Expression... in){
        return new InPredicateBuilder<Void>()
                .withExpression(left)
                .withValueExpression(in)
                .build();
    }

    public static Predicate p_in(Expression left, Select subQuery){
        return new InPredicateBuilder<Void>()
                .withExpression(left)
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * In Predicate
     * @param left
     * @param in
     * @return
     */
    public static Predicate p_not_in(Expression left,Expression... in){
        return new InPredicateBuilder<Void>()
                .withExpression(left)
                .withNot()
                .withValueExpression(in)
                .build();
    }

    public static Predicate p_not_in(Expression left, Select subQuery){
        return new InPredicateBuilder<Void>()
                .withExpression(left)
                .withNot()
                .withSubQuery(subQuery)
                .build();
    }

    /**
     * IsNull Predicate
     * @param left
     * @return
     */
    public static Predicate p_is_null(Expression left){
        return new IsNullPredicateBuilder<Void>()
                .withExpression(left)
                .build();
    }

    /**
     * IsNull Predicate
     * @param left
     * @return
     */
    public static Predicate p_is_not_null(Expression left){
        return new IsNullPredicateBuilder<Void>()
                .withExpression(left)
                .withNot()
                .build();
    }

    /**
     * Like Predicate
     * @param left
     * @param right
     * @return
     */
    public static Predicate p_like(Expression left, Expression right){
        return new LikePredicateBuilder<Void>()
                .withStringExpression(left)
                .withStringExpression(right)
                .build();
    }

    /**
     * Like Predicate
     * @param left
     * @param right
     * @param escape
     * @return
     */
    public static Predicate p_like(Expression left, Expression right, StringConstant escape){
        return new LikePredicateBuilder<Void>()
                .withStringExpression(left)
                .withStringExpression(right)
                .withEscape(escape)
                .build();
    }

    /**
     * Like Predicate
     * @param left
     * @param right
     * @return
     */
    public static Predicate p_not_like(Expression left,Expression right){
        return new LikePredicateBuilder<Void>()
                .withStringExpression(left)
                .withNot()
                .withStringExpression(right)
                .build();
    }

    /**
     * Like Predicate
     * @param left
     * @param right
     * @param escape
     * @return
     */
    public static Predicate p_not_like(Expression left,Expression right,StringConstant escape){
        return new LikePredicateBuilder<Void>()
                .withStringExpression(left)
                .withNot()
                .withStringExpression(right)
                .withEscape(escape)
                .build();
    }

}
