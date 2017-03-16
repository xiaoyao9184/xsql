package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.predicate.ComparisonSubQuery;
import com.xy.xsql.tsql.model.predicate.Predicate;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } { ALL | SOME | ANY} ( subquery )
 * @param <ParentBuilder>
 */
public class ASAPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<ASAPredicateBuilder<ParentBuilder>,ParentBuilder,ComparisonSubQuery> {

    public ASAPredicateBuilder() {
        super(new ComparisonSubQuery());
    }

    public ASAPredicateBuilder(ComparisonSubQuery predicate) {
        super(predicate);
    }

    public ASAPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
        tar.setExpression(expression);
        return this;
    }

    public ASAPredicateBuilder<ParentBuilder> withOperator(com.xy.xsql.tsql.model.operator.Operator operator) {
        tar.setOperator(operator);
        return this;
    }

    public ASAPredicateBuilder<ParentBuilder> withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY oneOf) {
        tar.setAll_some_any(oneOf);
        return this;
    }

    public ASAPredicateBuilder<ParentBuilder> withSubQuery(Select subquery) {
        tar.setSubquery(subquery);
        return this;
    }




    public static ComparisonSubQuery.ALL_SOME_ANY ALL(){
        return ComparisonSubQuery.ALL_SOME_ANY.ALL;
    }

    public static ComparisonSubQuery.ALL_SOME_ANY SOME(){
        return ComparisonSubQuery.ALL_SOME_ANY.SOME;
    }

    public static ComparisonSubQuery.ALL_SOME_ANY ANY(){
        return ComparisonSubQuery.ALL_SOME_ANY.ANY;
    }

    public static Predicate EQUAL(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ASAPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.EQUAL)
                .withSubQuery(subquery)
                .build();
    }

    public static Predicate NOT_EQUAL(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ASAPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.NOT_EQUAL)
                .withSubQuery(subquery)
                .build();
    }

    public static Predicate NOT_EQUAL_NOT_ISO(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ASAPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.NOT_EQUAL_NOT_ISO)
                .withSubQuery(subquery)
                .build();
    }

    public static Predicate GREATER(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ASAPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.GREATER)
                .withSubQuery(subquery)
                .build();
    }

    public static Predicate GREATER_EQUAL(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ASAPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.GREATER_EQUAL)
                .withSubQuery(subquery)
                .build();
    }

    public static Predicate NOT_GREATER_NOT_ISO(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ASAPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.NOT_GREATER_NOT_ISO)
                .withSubQuery(subquery)
                .build();
    }

    public static Predicate LESS(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ASAPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.LESS)
                .withSubQuery(subquery)
                .build();
    }

    public static Predicate LESS_EQUAL(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ASAPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.LESS_EQUAL)
                .withSubQuery(subquery)
                .build();
    }

    public static Predicate NOT_LESS_NOT_ISO(Expression left, ComparisonSubQuery.ALL_SOME_ANY oneOf, Select subquery){
        return new ASAPredicateBuilder<Void>()
                .withALL_SOME_ANY(oneOf)
                .withExpression(left)
                .withOperator(Operators.NOT_LESS_NOT_ISO)
                .withSubQuery(subquery)
                .build();
    }
}
