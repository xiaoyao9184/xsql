package com.xy.xsql.tsql.builder.chain.queries.predicates.transform;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.*;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Comparison;
import com.xy.xsql.tsql.model.queries.predicates.ComparisonSubQuery;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;
import com.xy.xsql.tsql.model.queries.Select;

/**
 * Abstract Expression Predicate Builder
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ExpressionTransformBuilder<ParentBuilder>
        extends CodeTreeBuilder<ExpressionTransformBuilder<ParentBuilder>,ParentBuilder,Predicate> {

    public ExpressionTransformBuilder(Predicate predicate) {
        super(predicate);
    }

    public ExpressionTransformBuilder() {
        super(null);
    }

    private Expression expression;

    /**
     * set transform
     * @param expression expression
     * @return THIS
     */
    public ExpressionTransformBuilder<ParentBuilder> withExpression(Expression expression) {
        this.expression = expression;
        return this;
    }


    /**
     * in
     * @return ComparisonPredicateBuilder
     */
    public ComparisonPredicateBuilder<ParentBuilder> _Comparison() {
        return new ComparisonPredicateBuilder<ParentBuilder>()
                .enter(out(),p -> {
                    target = p;
                    back();
                });
    }

    /**
     * in
     * @return LikePredicateBuilder
     */
    public LikePredicateBuilder<ParentBuilder> _Like() {
        return new LikePredicateBuilder<ParentBuilder>()
                .enter(out(),p -> {
                    target = p;
                    back();
                });
    }

    /**
     * in
     * @return BetweenPredicateBuilder
     */
    public BetweenPredicateBuilder<ParentBuilder> _Between() {
        return new BetweenPredicateBuilder<ParentBuilder>()
                .enter(out(),p -> {
                    target = p;
                    back();
                });
    }

    /**
     * in
     * @return IsNullPredicateBuilder
     */
    public IsNullPredicateBuilder<ParentBuilder> _IsNull() {
        return new IsNullPredicateBuilder<ParentBuilder>()
                .enter(out(),p -> {
                    target = p;
                    back();
                });
    }

    /**
     * in
     * @return BetweenPredicateBuilder
     */
    public InPredicateBuilder<ParentBuilder> _In() {
        return new InPredicateBuilder<ParentBuilder>()
                .enter(out(),p -> {
                    target = p;
                    back();
                });
    }

    /**
     * in
     * @return ComparisonSubQueryPredicateBuilder
     */
    public ComparisonSubQueryPredicateBuilder<ParentBuilder> _All_Some_Any() {
        return new ComparisonSubQueryPredicateBuilder<ParentBuilder>()
                .enter(out(),p -> {
                    target = p;
                    back();
                });
    }


    /*
    Quick Transform Comparison Predicate
     */

    /**
     * Quick transform
     * Comparison Predicate
     * @param expression expression
     * @return PARENT
     */
    public ParentBuilder $Equal(Expression expression) {
        return _Comparison()
                .withExpression(this.expression)
                .withOperator(Comparison.EQUAL)
                .withExpression(expression)
                .and();
    }

    /**
     * Quick transform
     * Comparison Predicate
     * @param expression expression
     * @return PARENT
     */
    public ParentBuilder $NotEqual(Expression expression) {
        return _Comparison()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_EQUAL_NOT_ISO)
                .withExpression(expression)
                .and();
    }

    /**
     * Quick transform
     * Comparison Predicate
     * @param expression expression
     * @return PARENT
     */
    public ParentBuilder $ISONotEqual(Expression expression) {
        return _Comparison()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_EQUAL)
                .withExpression(expression)
                .and();
    }

    /**
     * Quick transform
     * Comparison Predicate
     * @param expression expression
     * @return PARENT
     */
    public ParentBuilder $Greater(Expression expression) {
        return _Comparison()
                .withExpression(this.expression)
                .withOperator(Comparison.GREATER)
                .withExpression(expression)
                .and();
    }

    /**
     * Quick transform
     * Comparison Predicate
     * @param expression expression
     * @return PARENT
     */
    public ParentBuilder $GreaterEqual(Expression expression) {
        return _Comparison()
                .withExpression(this.expression)
                .withOperator(Comparison.GREATER_EQUAL)
                .withExpression(expression)
                .and();
    }

    /**
     * Quick transform
     * Comparison Predicate
     * @param expression expression
     * @return PARENT
     */
    public ParentBuilder $NotGreater(Expression expression) {
        return _Comparison()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_GREATER_NOT_ISO)
                .withExpression(expression)
                .and();
    }

    /**
     * Quick transform
     * Comparison Predicate
     * @param expression expression
     * @return PARENT
     */
    public ParentBuilder $Less(Expression expression) {
        return _Comparison()
                .withExpression(this.expression)
                .withOperator(Comparison.LESS)
                .withExpression(expression)
                .and();
    }

    /**
     * Quick transform
     * Comparison Predicate
     * @param expression expression
     * @return PARENT
     */
    public ParentBuilder $LessEqual(Expression expression) {
        return _Comparison()
                .withExpression(this.expression)
                .withOperator(Comparison.LESS_EQUAL)
                .withExpression(expression)
                .and();
    }

    /**
     * Quick transform
     * Comparison Predicate
     * @param expression expression
     * @return PARENT
     */
    public ParentBuilder $NotLess(Expression expression) {
        return _Comparison()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_LESS_NOT_ISO)
                .withExpression(expression)
                .and();
    }


    /*
    Quick Transform Between Predicate
     */

    /**
     * Quick transform
     * Like Predicate
     * @return PARENT
     */
    public ParentBuilder $Like(Expression stringExpression) {
        return _Like()
                .withStringExpression(this.expression)
                .withStringExpression(stringExpression)
                .and();
    }

    /**
     * Quick transform
     * Like Predicate
     * @return PARENT
     */
    public ParentBuilder $NotLike(Expression stringExpression) {
        return _Like()
                .withNot()
                .withStringExpression(this.expression)
                .withStringExpression(stringExpression)
                .and();
    }

    /**
     * Quick transform
     * Like Predicate
     * @return PARENT
     */
    public ParentBuilder $Like(Expression stringExpression, StringConstant escape) {
        return _Like()
                .withStringExpression(this.expression)
                .withStringExpression(stringExpression)
                .withEscape(escape)
                .and();
    }

    /**
     * Quick transform
     * Like Predicate
     * @return PARENT
     */
    public ParentBuilder $NotLike(Expression stringExpression, StringConstant escape) {
        return _Like()
                .withNot()
                .withStringExpression(this.expression)
                .withStringExpression(stringExpression)
                .withEscape(escape)
                .and();
    }


    /*
    Quick Transform Between Predicate
     */

    /**
     * Quick transform
     * Between Predicate
     * @return PARENT
     */
    public ParentBuilder $Between(Expression expression1,Expression expression2) {
        return _Between()
                .withExpression(this.expression)
                .withExpression(expression1)
                .withExpression(expression2)
                .and();
    }

    /**
     * Quick transform
     * Between Predicate
     * @return PARENT
     */
    public ParentBuilder $NotBetween(Expression expression1, Expression expression2) {
        return _Between()
                .withNot()
                .withExpression(this.expression)
                .withExpression(expression1)
                .withExpression(expression2)
                .and();
    }


    /*
    Quick Transform IsNull Predicate
     */

    /**
     * Quick transform
     * IsNull Predicate
     * @return PARENT
     */
    public ParentBuilder $IsNull() {
        return _IsNull()
                .withExpression(this.expression)
                .and();
    }

    /**
     * Quick transform
     * IsNull Predicate
     * @return PARENT
     */
    public ParentBuilder $IsNotNull() {
        return _IsNull()
                .withNot()
                .withExpression(this.expression)
                .and();
    }


    /*
    Quick Transform In Predicate
     */

    /**
     * Quick transform
     * In Predicate
     * @param expressions expression
     * @return PARENT
     */
    public ParentBuilder $In(Expression... expressions) {
        return _In()
                .withExpression(this.expression)
                .withValueExpression(expressions)
                .and();
    }

    /**
     * Quick transform
     * In Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $In(Select subQuery) {
        return _In()
                .withExpression(this.expression)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * In Predicate
     * @param expressions expression
     * @return PARENT
     */
    public ParentBuilder $NotIn(Expression... expressions) {
        return _In()
                .withNot()
                .withExpression(this.expression)
                .withValueExpression(expressions)
                .and();
    }

    /**
     * Quick transform
     * In Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotIn(Select subQuery) {
        return _In()
                .withNot()
                .withExpression(this.expression)
                .withSubQuery(subQuery)
                .and();
    }


    /*
    Quick Transform ComparisonSubQuery Predicate
     */

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $EqualALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.EQUAL)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotEqualALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_EQUAL_NOT_ISO)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $ISONotEqualALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_EQUAL)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $GreaterALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.GREATER)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $GreaterEqualALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.GREATER_EQUAL)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotGreaterALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_GREATER_NOT_ISO)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $LessAll(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.LESS)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $LessEqualAll(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.LESS_EQUAL)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotLessAll(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_LESS_NOT_ISO)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $EqualSome(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.EQUAL)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotEqualSome(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_EQUAL_NOT_ISO)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $ISONotEqualSome(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_EQUAL)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $GreaterSome(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.GREATER)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $GreaterEqualSome(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.GREATER_EQUAL)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotGreaterSome(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_GREATER_NOT_ISO)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $LessSome(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.LESS)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $LessEqualSome(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.LESS_EQUAL)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotLessSome(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_LESS_NOT_ISO)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $EqualAny(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.EQUAL)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotEqualAny(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_EQUAL_NOT_ISO)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $ISONotEqualAny(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_EQUAL)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $GreaterAny(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.GREATER)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $GreaterEqualAny(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.GREATER_EQUAL)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotGreaterAny(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_GREATER_NOT_ISO)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $LessAny(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.LESS)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $LessEqualAny(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.LESS_EQUAL)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotLessAny(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Comparison.NOT_LESS_NOT_ISO)
                .withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

}
