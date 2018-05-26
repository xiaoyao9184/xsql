package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.tsql.builder.chain.queries.SearchConditionBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.ContainsPredicateBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.ExistsPredicateBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.FreeTextPredicateBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.PredicateBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.transform.ContainsFreeTextTransformBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.transform.ExpressionTransformBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.SearchCondition;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.predicates.Contains;
import com.xy.xsql.tsql.model.queries.predicates.Exists;
import com.xy.xsql.tsql.model.queries.predicates.FreeText;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;
import com.xy.xsql.tsql.model.queries.select.Having;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;
import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * HavingBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class HavingBuilder<ParentBuilder>
        extends ParentHoldBuilder<HavingBuilder<ParentBuilder>,ParentBuilder,Having> {

    public HavingBuilder() {
        super(new Having());
        this.targetReal = new SearchCondition();
        this.target.setSearchCondition(targetReal);
    }

    public HavingBuilder(Having target) {
        super(target);
        this.targetReal = new SearchCondition();
        this.target.setSearchCondition(targetReal);
    }

    private SearchCondition targetReal;


    /**
     * set
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> withNot(){
        targetReal.setUseNot(true);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> withPredicate(Predicate predicate){
        targetReal.setPredicate(predicate);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> withSearchCondition(SearchCondition searchCondition){
        targetReal.setSearchCondition(searchCondition);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> withAndOrNotItem(SearchCondition.AndOrNotItem andOrNotItem){
        list(targetReal::getAndOrList, targetReal::setAndOrList)
                .add(andOrNotItem);
        return this;
    }

    /**
     * in
     * @return PredicateBuilder
     */
    public PredicateBuilder<HavingBuilder<ParentBuilder>> withPredicate(){
        return new PredicateBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), targetReal::setPredicate)
                .in(this);
    }

    /**
     * in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (object(targetReal::getSearchCondition, targetReal::setSearchCondition)
                        .init(SearchCondition::new))
                .in(this);
    }

    /**
     * in
     * @return AndOrNotItemBuilder
     */
    public SearchConditionBuilder.AndOrNotItemBuilder<HavingBuilder<ParentBuilder>> withAndOrNotItem(){
        return new SearchConditionBuilder.AndOrNotItemBuilder<HavingBuilder<ParentBuilder>>
                (list(targetReal::getAndOrList, targetReal::setAndOrList)
                        .addNew(SearchCondition.AndOrNotItem::new))
                .in(this);
    }




    /*
    Quick
    Same as WhereBuilder
     */

    /**
     * Quick set
     * @param predicate predicate
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $(Predicate predicate){
        return withPredicate(predicate);
    }

    /**
     * Quick in
     * Comparison , Like, Between , IsNull , In , ComparisonSubQuery
     * @param expression expression
     * @return ExpressionTransformBuilder
     */
    public ExpressionTransformBuilder<HavingBuilder<ParentBuilder>> $(Expression expression){
        return new ExpressionTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return ContainsFreeTextTransformBuilder
     */
    public ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>> $Contains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $Contains(String containsSearchCondition){
        return new ContainsPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(Contains::new).set(this::$))
                .in(this)
                .withAllColumn()
                .withContainsSearchCondition(containsSearchCondition)
                .and();
    }

    /**
     * Quick in
     * FreeText
     * @param column column
     * @return ContainsFreeTextTransformBuilder
     */
    public ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>> $FreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $FreeText(String freetextString){
        return new FreeTextPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(FreeText::new).set(this::$))
                .in(this)
                .withAllColumn()
                .withFreeText(freetextString)
                .and();
    }

    /**
     * Quick set
     * Exists
     * @param subQuery subquery
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $Exists(Select subQuery){
        return new ExistsPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(Exists::new).set(this::$))
                .in(this)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> $(){
        return withSearchCondition();
    }

    /**
     * Quick set
     * @param predicate predicate
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $Not(Predicate predicate){
        return withNot()
                .withPredicate(predicate);
    }

    /**
     * Quick in
     * Comparison , Like, Between , IsNull , In , ComparisonSubQuery
     * @param expression expression
     * @return ExpressionTransformBuilder
     */
    public ExpressionTransformBuilder<HavingBuilder<ParentBuilder>> $Not(Expression expression){
        return new ExpressionTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$Not)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>> $NotContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$Not)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $NotContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(Contains::new).set(this::$Not))
                .in(this)
                .withAllColumn()
                .withContainsSearchCondition(containsSearchCondition)
                .and();
    }

    /**
     * Quick in
     * FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>> $NotFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$Not)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $NotFreeText(String freetextString){
        return new FreeTextPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(FreeText::new).set(this::$Not))
                .in(this)
                .withAllColumn()
                .withFreeText(freetextString)
                .and();
    }

    /**
     * Quick set
     * Exists
     * @param subQuery subquery
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $NotExists(Select subQuery){
        return new ExistsPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(Exists::new).set(this::$Not))
                .in(this)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> $Not(){
        return withNot()
                .withSearchCondition();
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $And(Predicate predicate) {
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
    public ExpressionTransformBuilder<HavingBuilder<ParentBuilder>> $And(Expression expression){
        return new ExpressionTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$And)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>> $AndContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$And)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $AndContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(Contains::new).set(this::$And))
                .in(this)
                .withAllColumn()
                .withContainsSearchCondition(containsSearchCondition)
                .and();
    }

    /**
     * Quick in
     * FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>> $AndFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$And)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $AndFreeText(String freetextString){
        return new FreeTextPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(FreeText::new).set(this::$And))
                .in(this)
                .withAllColumn()
                .withFreeText(freetextString)
                .and();
    }

    /**
     * Quick set
     * Exists
     * @param subQuery subquery
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $And_Exists(Select subQuery){
        return new ExistsPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(Exists::new).set(this::$And))
                .in(this)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> $And() {
        SearchCondition searchCondition = new SearchCondition();
        withAndOrNotItem()
                .withAnd()
                .withSearchCondition(searchCondition)
                .and();
        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }
    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $AndNot(Predicate predicate) {
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
    public ExpressionTransformBuilder<HavingBuilder<ParentBuilder>> $AndNot(Expression expression){
        return new ExpressionTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$AndNot)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>> $AndNotContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$AndNot)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $AndNotContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(Contains::new).set(this::$AndNot))
                .in(this)
                .withAllColumn()
                .withContainsSearchCondition(containsSearchCondition)
                .and();
    }

    /**
     * Quick in
     * FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>> $AndNotFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$AndNot)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $AndNotFreeText(String freetextString){
        return new FreeTextPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(FreeText::new).set(this::$AndNot))
                .in(this)
                .withAllColumn()
                .withFreeText(freetextString)
                .and();
    }

    /**
     * Quick set
     * Exists
     * @param subQuery subquery
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $AndNotExists(Select subQuery){
        return new ExistsPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(Exists::new).set(this::$AndNot))
                .in(this)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> $AndNot() {
        SearchCondition searchCondition = new SearchCondition();
        withAndOrNotItem()
                .withAnd()
                .withNot()
                .withSearchCondition(searchCondition)
                .and();
        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $Or(Predicate predicate) {
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
    public ExpressionTransformBuilder<HavingBuilder<ParentBuilder>> $Or(Expression expression){
        return new ExpressionTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$Or)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>> $OrContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$Or)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $OrContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(Contains::new).set(this::$Or))
                .in(this)
                .withAllColumn()
                .withContainsSearchCondition(containsSearchCondition)
                .and();
    }

    /**
     * Quick in
     * FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>> $OrFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$Or)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $OrFreeText(String freetextString){
        return new FreeTextPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(FreeText::new).set(this::$Or))
                .in(this)
                .withAllColumn()
                .withFreeText(freetextString)
                .and();
    }

    /**
     * Quick set
     * Exists
     * @param subQuery subquery
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $OrExists(Select subQuery){
        return new ExistsPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(Exists::new).set(this::$Or))
                .in(this)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> $Or() {
        SearchCondition searchCondition = new SearchCondition();
        withAndOrNotItem()
                .withOr()
                .withSearchCondition(searchCondition)
                .and();
        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $OrNot(Predicate predicate) {
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
    public ExpressionTransformBuilder<HavingBuilder<ParentBuilder>> $OrNot(Expression expression){
        return new ExpressionTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$OrNot)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>> $OrNotContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$OrNot)
                .withPredicate(Contains.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * Contains
     * @param containsSearchCondition contains_search_condition
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $OrNotContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(Contains::new).set(this::$OrNot))
                .in(this)
                .withAllColumn()
                .withContainsSearchCondition(containsSearchCondition)
                .and();
    }

    /**
     * Quick in
     * FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>> $OrNotFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<HavingBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$OrNot)
                .withPredicate(FreeText.class)
                .withColumn(column);
    }

    /**
     * Quick set
     * FreeText
     * @param freetextString freetext_string
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $OrNotFreeText(String freetextString){
        return new FreeTextPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(FreeText::new).set(this::$OrNot))
                .in(this)
                .withAllColumn()
                .withFreeText(freetextString)
                .and();
    }

    /**
     * Quick set
     * Exists
     * @param subQuery subquery
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $OrNotExists(Select subQuery){
        return new ExistsPredicateBuilder<HavingBuilder<ParentBuilder>>
                (object(Exists::new).set(this::$OrNot))
                .in(this)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> $OrNot() {
        SearchCondition searchCondition = new SearchCondition();
        withAndOrNotItem()
                .withOr()
                .withNot()
                .withSearchCondition(searchCondition)
                .and();
        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }
}
