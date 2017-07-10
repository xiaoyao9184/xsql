package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.predicate.ContainsPredicateBuilder;
import com.xy.xsql.tsql.core.predicate.ExistsPredicateBuilder;
import com.xy.xsql.tsql.core.predicate.FreeTextPredicateBuilder;
import com.xy.xsql.tsql.core.predicate.PredicateBuilder;
import com.xy.xsql.tsql.core.predicate.transform.ContainsFreeTextTransformBuilder;
import com.xy.xsql.tsql.core.predicate.transform.ExpressionTransformBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.predicate.Contains;
import com.xy.xsql.tsql.model.predicate.FreeText;
import com.xy.xsql.tsql.model.predicate.Predicate;
import com.xy.xsql.tsql.model.statement.dml.Select;

import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * WhereBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class WhereBuilder<ParentBuilder>
        extends CodeTreeBuilder<WhereBuilder<ParentBuilder>,ParentBuilder,Where> {

    public WhereBuilder() {
        super(new Where());
        this.targetReal = new SearchCondition();
        this.target.setSearchCondition(targetReal);
    }

    public WhereBuilder(Where where) {
        super(where);
        this.targetReal = new SearchCondition();
        this.target.setSearchCondition(targetReal);
    }

    private SearchCondition targetReal;

    /**
     * set
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> withNot(){
        targetReal.setUseNot(true);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> withPredicate(Predicate predicate){
        targetReal.setPredicate(predicate);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> withSearchCondition(SearchCondition searchCondition){
        targetReal.setSearchCondition(searchCondition);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> withAndOrNotItem(SearchCondition.AndOrNotItem andOrNotItem){
        initAdd(andOrNotItem,
                targetReal::getAndOrList,
                targetReal::setAndOrList);
        return this;
    }

    /**
     * in
     * @return PredicateBuilder
     */
    public PredicateBuilder<WhereBuilder<ParentBuilder>> withPredicate(){
        return new PredicateBuilder<WhereBuilder<ParentBuilder>>
                (targetReal::setPredicate)
                .in(this);
    }

    /**
     * in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (set(SearchCondition::new,
                        targetReal::setSearchCondition))
                .in(this);
    }

    /**
     * in
     * @return AndOrNotItemBuilder
     */
    public SearchConditionBuilder.AndOrNotItemBuilder<WhereBuilder<ParentBuilder>> withAndOrNotItem(){
        return new SearchConditionBuilder.AndOrNotItemBuilder<WhereBuilder<ParentBuilder>>
                (initNew(SearchCondition.AndOrNotItem::new,
                        targetReal::getAndOrList,
                        targetReal::setAndOrList))
                .in(this);
    }


    /*
    Quick
    Same as HavingBuilder
     */

    /**
     * Quick set
     * @param predicate predicate
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $(Predicate predicate){
        return withPredicate(predicate);
    }

    /**
     * Quick in
     * Comparison , Like, Between , IsNull , In , ComparisonSubQuery
     * @param expression expression
     * @return ExpressionTransformBuilder
     */
    public ExpressionTransformBuilder<WhereBuilder<ParentBuilder>> $(Expression expression){
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this,this::$)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return ContainsFreeTextTransformBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $Contains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Contains(String containsSearchCondition){
        return new ContainsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$)
                .withAllColumn()
                .withContainsSearchCondition(containsSearchCondition)
                .back();
    }

    /**
     * Quick in
     * FreeText
     * @param column column
     * @return ContainsFreeTextTransformBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $FreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $FreeText(String freetextString){
        return new FreeTextPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$)
                .withAllColumn()
                .withFreeText(freetextString)
                .back();
    }

    /**
     * Quick set
     * Exists
     * @param subQuery subquery
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Exists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$)
                .withSubQuery(subQuery)
                .back();
    }

    /**
     * Quick in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $(){
        return withSearchCondition();
    }

    /**
     * Quick set
     * @param predicate predicate
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Not(Predicate predicate){
        return withNot()
                .withPredicate(predicate);
    }

    /**
     * Quick in
     * Comparison , Like, Between , IsNull , In , ComparisonSubQuery
     * @param expression expression
     * @return ExpressionTransformBuilder
     */
    public ExpressionTransformBuilder<WhereBuilder<ParentBuilder>> $Not(Expression expression){
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this,this::$Not)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $Not_Contains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Not)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Not_Contains(String containsSearchCondition){
        return new ContainsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Not)
                .withAllColumn()
                .withContainsSearchCondition(containsSearchCondition)
                .back();
    }

    /**
     * Quick in
     * FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $Not_FreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Not)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Not_FreeText(String freetextString){
        return new FreeTextPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Not)
                .withAllColumn()
                .withFreeText(freetextString)
                .back();
    }

    /**
     * Quick set
     * Exists
     * @param subQuery subquery
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Not_Exists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Not)
                .withSubQuery(subQuery)
                .back();
    }

    /**
     * Quick in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $Not(){
        return withNot()
                .withSearchCondition();
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $And(Predicate predicate) {
        return withAndOrNotItem()
                .withAnd()
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick in
     * Comparison , Like, Between , IsNull , In , ComparisonSubQuery
     * @param expression expression
     * @return ExpressionTransformBuilder
     */
    public ExpressionTransformBuilder<WhereBuilder<ParentBuilder>> $And(Expression expression){
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this,this::$And)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $And_Contains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$And)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $And_Contains(String containsSearchCondition){
        return new ContainsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$And)
                .withAllColumn()
                .withContainsSearchCondition(containsSearchCondition)
                .back();
    }

    /**
     * Quick in
     * FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $And_FreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$And)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $And_FreeText(String freetextString){
        return new FreeTextPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$And)
                .withAllColumn()
                .withFreeText(freetextString)
                .back();
    }

    /**
     * Quick set
     * Exists
     * @param subQuery subquery
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $And_Exists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$And)
                .withSubQuery(subQuery)
                .back();
    }

    /**
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $And() {
        SearchCondition searchCondition = new SearchCondition();
        withAndOrNotItem()
                .withAnd()
                .withSearchCondition(searchCondition)
                .and();
        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }
    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $And_Not(Predicate predicate) {
        return withAndOrNotItem()
                .withAnd()
                .withNot(true)
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick in
     * Comparison , Like, Between , IsNull , In , ComparisonSubQuery
     * @param expression expression
     * @return ExpressionTransformBuilder
     */
    public ExpressionTransformBuilder<WhereBuilder<ParentBuilder>> $And_Not(Expression expression){
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this,this::$And_Not)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $And_Not_Contains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$And_Not)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $And_Not_Contains(String containsSearchCondition){
        return new ContainsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$And_Not)
                .withAllColumn()
                .withContainsSearchCondition(containsSearchCondition)
                .back();
    }

    /**
     * Quick in
     * FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $And_Not_FreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$And_Not)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $And_Not_FreeText(String freetextString){
        return new FreeTextPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$And_Not)
                .withAllColumn()
                .withFreeText(freetextString)
                .back();
    }

    /**
     * Quick set
     * Exists
     * @param subQuery subquery
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $And_Not_Exists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$And_Not)
                .withSubQuery(subQuery)
                .back();
    }

    /**
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $And_Not() {
        SearchCondition searchCondition = new SearchCondition();
        withAndOrNotItem()
                .withAnd()
                .withNot()
                .withSearchCondition(searchCondition)
                .and();
        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Or(Predicate predicate) {
        return withAndOrNotItem()
                .withOr()
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick in
     * Comparison , Like, Between , IsNull , In , ComparisonSubQuery
     * @param expression expression
     * @return ExpressionTransformBuilder
     */
    public ExpressionTransformBuilder<WhereBuilder<ParentBuilder>> $Or(Expression expression){
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this,this::$Or)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $Or_Contains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Or)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Or_Contains(String containsSearchCondition){
        return new ContainsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Or)
                .withAllColumn()
                .withContainsSearchCondition(containsSearchCondition)
                .back();
    }

    /**
     * Quick in
     * FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $Or_FreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Or)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Or_FreeText(String freetextString){
        return new FreeTextPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Or)
                .withAllColumn()
                .withFreeText(freetextString)
                .back();
    }

    /**
     * Quick set
     * Exists
     * @param subQuery subquery
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Or_Exists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Or)
                .withSubQuery(subQuery)
                .back();
    }

    /**
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $Or() {
        SearchCondition searchCondition = new SearchCondition();
        withAndOrNotItem()
                .withOr()
                .withSearchCondition(searchCondition)
                .and();
        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Or_Not(Predicate predicate) {
        return withAndOrNotItem()
                .withOr()
                .withNot(true)
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick in
     * Comparison , Like, Between , IsNull , In , ComparisonSubQuery
     * @param expression expression
     * @return ExpressionTransformBuilder
     */
    public ExpressionTransformBuilder<WhereBuilder<ParentBuilder>> $Or_Not(Expression expression){
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this,this::$Or_Not)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $Or_Not_Contains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Or_Not)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Or_Not_Contains(String containsSearchCondition){
        return new ContainsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Or_Not)
                .withAllColumn()
                .withContainsSearchCondition(containsSearchCondition)
                .back();
    }

    /**
     * Quick in
     * FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $Or_Not_FreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Or_Not)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Or_Not_FreeText(String freetextString){
        return new FreeTextPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Or_Not)
                .withAllColumn()
                .withFreeText(freetextString)
                .back();
    }

    /**
     * Quick set
     * Exists
     * @param subQuery subquery
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Or_Not_Exists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$Or_Not)
                .withSubQuery(subQuery)
                .back();
    }

    /**
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $Or_Not() {
        SearchCondition searchCondition = new SearchCondition();
        withAndOrNotItem()
                .withOr()
                .withNot()
                .withSearchCondition(searchCondition)
                .and();
        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }
}
