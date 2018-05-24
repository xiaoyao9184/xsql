package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.ContainsPredicateBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.ExistsPredicateBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.FreeTextPredicateBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.PredicateBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.transform.ContainsFreeTextTransformBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.transform.ExpressionTransformBuilder;
import com.xy.xsql.tsql.model.queries.SearchCondition;
import com.xy.xsql.tsql.model.queries.Where;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.predicates.Contains;
import com.xy.xsql.tsql.model.queries.predicates.FreeText;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;
import com.xy.xsql.tsql.model.queries.Select;

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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $NotContains(ColumnName column){
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
    public WhereBuilder<ParentBuilder> $NotContains(String containsSearchCondition){
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $NotFreeText(ColumnName column){
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
    public WhereBuilder<ParentBuilder> $NotFreeText(String freetextString){
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
    public WhereBuilder<ParentBuilder> $NotExists(Select subQuery){
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $AndContains(ColumnName column){
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
    public WhereBuilder<ParentBuilder> $AndContains(String containsSearchCondition){
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $AndFreeText(ColumnName column){
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
    public WhereBuilder<ParentBuilder> $AndFreeText(String freetextString){
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
    public WhereBuilder<ParentBuilder> $AndExists(Select subQuery){
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
    public WhereBuilder<ParentBuilder> $AndNot(Predicate predicate) {
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
    public ExpressionTransformBuilder<WhereBuilder<ParentBuilder>> $AndNot(Expression expression){
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this,this::$AndNot)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $AndNotContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$AndNot)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $AndNotContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$AndNot)
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $AndNotFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$AndNot)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $AndNotFreeText(String freetextString){
        return new FreeTextPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$AndNot)
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
    public WhereBuilder<ParentBuilder> $AndNotExists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$AndNot)
                .withSubQuery(subQuery)
                .back();
    }

    /**
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $AndNot() {
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $OrContains(ColumnName column){
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
    public WhereBuilder<ParentBuilder> $OrContains(String containsSearchCondition){
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $OrFreeText(ColumnName column){
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
    public WhereBuilder<ParentBuilder> $OrFreeText(String freetextString){
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
    public WhereBuilder<ParentBuilder> $OrExists(Select subQuery){
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
    public WhereBuilder<ParentBuilder> $OrNot(Predicate predicate) {
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
    public ExpressionTransformBuilder<WhereBuilder<ParentBuilder>> $OrNot(Expression expression){
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this,this::$OrNot)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $OrNotContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$OrNot)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $OrNotContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$OrNot)
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $OrNotFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$OrNot)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $OrNotFreeText(String freetextString){
        return new FreeTextPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$OrNot)
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
    public WhereBuilder<ParentBuilder> $OrNotExists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
                ()
                .enter(this, this::$OrNot)
                .withSubQuery(subQuery)
                .back();
    }

    /**
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $OrNot() {
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
