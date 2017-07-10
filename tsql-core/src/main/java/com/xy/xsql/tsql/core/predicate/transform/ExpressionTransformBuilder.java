package com.xy.xsql.tsql.core.predicate.transform;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.predicate.*;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.predicate.ComparisonSubQuery;
import com.xy.xsql.tsql.model.predicate.Predicate;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * Abstract Predicate Builder
 *
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
                .withOperator(Operators.EQUAL)
                .withExpression(expression)
                .and();
    }

    /**
     * Quick transform
     * Comparison Predicate
     * @param expression expression
     * @return PARENT
     */
    public ParentBuilder $Not_Equal(Expression expression) {
        return _Comparison()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_EQUAL_NOT_ISO)
                .withExpression(expression)
                .and();
    }

    /**
     * Quick transform
     * Comparison Predicate
     * @param expression expression
     * @return PARENT
     */
    public ParentBuilder $ISO_Not_Equal(Expression expression) {
        return _Comparison()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_EQUAL)
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
                .withOperator(Operators.GREATER)
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
                .withOperator(Operators.GREATER_EQUAL)
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
                .withOperator(Operators.NOT_GREATER_NOT_ISO)
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
                .withOperator(Operators.LESS)
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
                .withOperator(Operators.LESS_EQUAL)
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
                .withOperator(Operators.NOT_LESS_NOT_ISO)
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
    public ParentBuilder $Not_Like(Expression stringExpression) {
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
    public ParentBuilder $Not_Like(Expression stringExpression, StringConstant escape) {
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
    public ParentBuilder $Not_Between(Expression expression1,Expression expression2) {
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
    public ParentBuilder $Is_Null() {
        return _IsNull()
                .withExpression(this.expression)
                .and();
    }

    /**
     * Quick transform
     * IsNull Predicate
     * @return PARENT
     */
    public ParentBuilder $Is_Not_Null() {
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
    public ParentBuilder $Not_In(Expression... expressions) {
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
    public ParentBuilder $Not_In(Select subQuery) {
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
    public ParentBuilder $Equal_ALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.EQUAL)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotEqual_ALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_EQUAL_NOT_ISO)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $ISONotEqual_ALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_EQUAL)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $Greater_ALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.GREATER)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $GreaterEqual_ALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.GREATER_EQUAL)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotGreater_ALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_GREATER_NOT_ISO)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $Less_ALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.LESS)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $LessEqual_ALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.LESS_EQUAL)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotLess_ALL(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_LESS_NOT_ISO)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $Equal_SOME(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.EQUAL)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotEqual_SOME(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_EQUAL_NOT_ISO)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $ISONotEqual_SOME(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_EQUAL)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $Greater_SOME(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.GREATER)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $GreaterEqual_SOME(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.GREATER_EQUAL)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotGreater_SOME(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_GREATER_NOT_ISO)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $Less_SOME(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.LESS)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $LessEqual_SOME(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.LESS_EQUAL)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotLess_SOME(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_LESS_NOT_ISO)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.SOME)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $Equal_ANY(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.EQUAL)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotEqual_ANY(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_EQUAL_NOT_ISO)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $ISONotEqual_ANY(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_EQUAL)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $Greater_ANY(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.GREATER)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $GreaterEqual_ANY(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.GREATER_EQUAL)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotGreater_ANY(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_GREATER_NOT_ISO)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $Less_ANY(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.LESS)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $LessEqual_ANY(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.LESS_EQUAL)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick transform
     * ComparisonSubQuery Predicate
     * @param subQuery subquery
     * @return PARENT
     */
    public ParentBuilder $NotLess_ANY(Select subQuery) {
        return _All_Some_Any()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_LESS_NOT_ISO)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

}
