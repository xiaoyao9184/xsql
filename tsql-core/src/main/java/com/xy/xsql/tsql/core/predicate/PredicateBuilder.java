package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.predicate.*;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * Abstract Predicate Builder
 *
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class PredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<PredicateBuilder<ParentBuilder>,ParentBuilder,Setter<Predicate>> {

    public PredicateBuilder(Setter<Predicate> setter) {
        super(setter);
    }

    /**
     * Confirm type of Predicate
     * @return ComparisonPredicateBuilder
     */
    public ComparisonPredicateBuilder<ParentBuilder> _Comparison(){
        Comparison predicate = new Comparison();
        target.set(predicate);
        return new ComparisonPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return LikePredicateBuilder
     */
    public LikePredicateBuilder<ParentBuilder> _Like(){
        Like predicate = new Like();
        target.set(predicate);
        return new LikePredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return BetweenPredicateBuilder
     */
    public BetweenPredicateBuilder<ParentBuilder> _Between(){
        Between predicate = new Between();
        target.set(predicate);
        return new BetweenPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return IsNullPredicateBuilder
     */
    public IsNullPredicateBuilder<ParentBuilder> _IsNull(){
        IsNull predicate = new IsNull();
        target.set(predicate);
        return new IsNullPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return ContainsPredicateBuilder
     */
    public ContainsPredicateBuilder<ParentBuilder> _Contains(){
        Contains predicate = new Contains();
        target.set(predicate);
        return new ContainsPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return FreeTextPredicateBuilder
     */
    public FreeTextPredicateBuilder<ParentBuilder> _FreeText(){
        FreeText predicate = new FreeText();
        target.set(predicate);
        return new FreeTextPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return InPredicateBuilder
     */
    public InPredicateBuilder<ParentBuilder> _In(){
        In predicate = new In();
        target.set(predicate);
        return new InPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return ComparisonSubQueryPredicateBuilder
     */
    public ComparisonSubQueryPredicateBuilder<ParentBuilder> _ComparisonSubQuery(){
        ComparisonSubQuery predicate = new ComparisonSubQuery();
        target.set(predicate);
        return new ComparisonSubQueryPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return ExistsPredicateBuilder
     */
    public ExistsPredicateBuilder<ParentBuilder> _Exists(){
        Exists predicate = new Exists();
        target.set(predicate);
        return new ExistsPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }


    /*
    Transform
     */

    private Expression expression;
    private Class<? extends Predicate> predicateClass;
    private ColumnName column;

    /**
     * set transform
     * @param expression expression
     * @return THIS
     */
    public PredicateBuilder<ParentBuilder> withExpression(Expression expression) {
        this.expression = expression;
        return this;
    }

    /**
     * set transform
     * @param predicateClass predicate
     * @return THIS
     */
    public PredicateBuilder<ParentBuilder> withPredicate(Class<? extends Predicate> predicateClass) {
        this.predicateClass = predicateClass;
        return this;
    }

    /**
     * set transform
     * @param column column
     * @return THIS
     */
    public PredicateBuilder<ParentBuilder> withColumn(ColumnName column) {
        this.column = column;
        return this;
    }

    /*
    Quick Transform CONTAINS FREETEXT Predicate
     */

    /**
     * Quick transform
     * Contains or FreeText Predicate
     * @param containsSearchCondition_freetextString expression
     * @return PARENT
     */
    public ParentBuilder $(String containsSearchCondition_freetextString) {
        if(Contains.class.equals(predicateClass)){
            if(column == null){
                return _Contains()
                        .withContainsSearchCondition(containsSearchCondition_freetextString)
                        .withAllColumn()
                        .and();
            }else{
                return _Contains()
                        .withContainsSearchCondition(containsSearchCondition_freetextString)
                        .withColumnName(column)
                        .and();
            }
        }else if(FreeText.class.equals(predicateClass)){
            if(column == null){
                return _FreeText()
                        .withFreeText(containsSearchCondition_freetextString)
                        .withAllColumn()
                        .and();
            }else{
                return _FreeText()
                        .withFreeText(containsSearchCondition_freetextString)
                        .withColumnName(column)
                        .and();
            }
        }
        return and();
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
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
        return _ComparisonSubQuery()
                .withExpression(this.expression)
                .withOperator(Operators.NOT_LESS_NOT_ISO)
                .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ANY)
                .withSubQuery(subQuery)
                .and();
    }

}
