package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.predicate.Comparison;
import com.xy.xsql.tsql.model.predicate.Predicate;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 *
 * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
 * @param <ParentBuilder>
 */
@SuppressWarnings("Duplicates")
public class ComparisonPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<ComparisonPredicateBuilder<ParentBuilder>,ParentBuilder,Comparison> {

    public ComparisonPredicateBuilder() {
        super(new Comparison());
    }

    public ComparisonPredicateBuilder(Comparison predicate) {
        super(predicate);
    }

    private int index = 0;

    public ComparisonPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
        if(index == 0){
            tar.setExpression(expression);
            index = 1;
        } else {
            tar.setOperatorExpression(expression);
            index = 0;
        }
        return this;
    }

    public ComparisonPredicateBuilder<ParentBuilder> withOperator(com.xy.xsql.tsql.model.operator.Operator operator) {
        tar.setOperator(operator);
        return this;
    }

    public static Predicate EQUAL(Expression left, Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.EQUAL)
                .withExpression(right)
                .build();
    }

    public static Predicate NOT_EQUAL(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.NOT_EQUAL)
                .withExpression(right)
                .build();
    }

    public static Predicate NOT_EQUAL_NOT_ISO(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.NOT_EQUAL_NOT_ISO)
                .withExpression(right)
                .build();
    }

    public static Predicate GREATER(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.GREATER)
                .withExpression(right)
                .build();
    }

    public static Predicate GREATER_EQUAL(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.GREATER_EQUAL)
                .withExpression(right)
                .build();
    }

    public static Predicate NOT_GREATER_NOT_ISO(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.NOT_GREATER_NOT_ISO)
                .withExpression(right)
                .build();
    }

    public static Predicate LESS(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.LESS)
                .withExpression(right)
                .build();
    }

    public static Predicate LESS_EQUAL(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.LESS_EQUAL)
                .withExpression(right)
                .build();
    }

    public static Predicate NOT_LESS_NOT_ISO(Expression left,Expression right){
        return new ComparisonPredicateBuilder<Void>()
                .withExpression(left)
                .withOperator(Operators.NOT_LESS_NOT_ISO)
                .withExpression(right)
                .build();
    }

}