package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.core.handler.object.SupplierObjectHandler;
import com.xy.xsql.core.lambda.Getter;
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
import com.xy.xsql.tsql.model.queries.Where;
import com.xy.xsql.tsql.model.queries.predicates.Contains;
import com.xy.xsql.tsql.model.queries.predicates.Exists;
import com.xy.xsql.tsql.model.queries.predicates.FreeText;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;
import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * WhereBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class WhereBuilder<ParentBuilder>
        extends ParentHoldBuilder<WhereBuilder<ParentBuilder>,ParentBuilder,Where> {

    public WhereBuilder() {
        super(new Where());
        this.targetReal = new SearchCondition();
        this.target.setSearchCondition(targetReal);
    }

    public WhereBuilder(Where target) {
        super(target);
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
        return new PredicateBuilder<WhereBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), targetReal::setPredicate);
    }

    /**
     * in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (object(targetReal::getSearchCondition, targetReal::setSearchCondition)
                        .init(SearchCondition::new))
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
        list(targetReal::getAndOrList, targetReal::setAndOrList)
                .add(andOrNotItem);
        return this;
    }

    /**
     * in
     * @return AndOrNotItemBuilder
     */
    public SearchConditionBuilder.AndOrNotItemBuilder<WhereBuilder<ParentBuilder>> withAndOrNotItem(){
        return new SearchConditionBuilder.AndOrNotItemBuilder<WhereBuilder<ParentBuilder>>
                (list(targetReal::getAndOrList, targetReal::setAndOrList)
                        .addNew(SearchCondition.AndOrNotItem::new))
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
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return ContainsFreeTextTransformBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $Contains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>()
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
    public WhereBuilder<ParentBuilder> $Contains(String containsSearchCondition){
        return new ContainsPredicateBuilder<WhereBuilder<ParentBuilder>>
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $FreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>()
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
    public WhereBuilder<ParentBuilder> $FreeText(String freetextString){
        return new FreeTextPredicateBuilder<WhereBuilder<ParentBuilder>>
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
    public WhereBuilder<ParentBuilder> $Exists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
                (object(Exists::new).set(this::$))
                .in(this)
                .withSubQuery(subQuery)
                .and();
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
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$Not)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $NotContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>()
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
    public WhereBuilder<ParentBuilder> $NotContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<WhereBuilder<ParentBuilder>>
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $NotFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>()
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
    public WhereBuilder<ParentBuilder> $NotFreeText(String freetextString){
        return new FreeTextPredicateBuilder<WhereBuilder<ParentBuilder>>
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
    public WhereBuilder<ParentBuilder> $NotExists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
                (object(Exists::new).set(this::$Not))
                .in(this)
                .withSubQuery(subQuery)
                .and();
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
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$And)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $AndContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>()
                .enter(this,  Getter.empty(), this::$And)
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
                (SupplierObjectHandler.object(Contains::new).set(this::$And))
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $AndFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>()
                .enter(this,  Getter.empty(), this::$And)
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
                (SupplierObjectHandler.object(FreeText::new).set(this::$And))
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
    public WhereBuilder<ParentBuilder> $AndExists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
                (SupplierObjectHandler.object(Exists::new).set(this::$And))
                .in(this)
                .withSubQuery(subQuery)
                .and();
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
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$AndNot)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $AndNotContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>()
                .enter(this,  Getter.empty(), this::$AndNot)
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
                (SupplierObjectHandler.object(Contains::new).set(this::$AndNot))
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $AndNotFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>()
                .enter(this,  Getter.empty(), this::$AndNot)
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
    public WhereBuilder<ParentBuilder> $AndNotExists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
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
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$Or)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $OrContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>()
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
    public WhereBuilder<ParentBuilder> $OrContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<WhereBuilder<ParentBuilder>>
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $OrFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>()
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
    public WhereBuilder<ParentBuilder> $OrFreeText(String freetextString){
        return new FreeTextPredicateBuilder<WhereBuilder<ParentBuilder>>
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
    public WhereBuilder<ParentBuilder> $OrExists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
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
        return new ExpressionTransformBuilder<WhereBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$OrNot)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $OrNotContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>()
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
    public WhereBuilder<ParentBuilder> $OrNotContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<WhereBuilder<ParentBuilder>>
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
    public ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>> $OrNotFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<WhereBuilder<ParentBuilder>>()
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
    public WhereBuilder<ParentBuilder> $OrNotFreeText(String freetextString){
        return new FreeTextPredicateBuilder<WhereBuilder<ParentBuilder>>
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
    public WhereBuilder<ParentBuilder> $OrNotExists(Select subQuery){
        return new ExistsPredicateBuilder<WhereBuilder<ParentBuilder>>
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
