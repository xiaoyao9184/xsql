package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
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
import com.xy.xsql.tsql.model.queries.predicates.Contains;
import com.xy.xsql.tsql.model.queries.predicates.Exists;
import com.xy.xsql.tsql.model.queries.predicates.FreeText;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;
import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * SearchConditionBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class SearchConditionBuilder<ParentBuilder>
        extends ParentHoldBuilder<SearchConditionBuilder<ParentBuilder>,ParentBuilder,SearchCondition> {

    public SearchConditionBuilder() {
        super(new SearchCondition());
    }

    public SearchConditionBuilder(SearchCondition target) {
        super(target);
    }

    /**
     * set
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> withNot(){
        target.setUseNot(true);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> withPredicate(Predicate predicate){
        target.setPredicate(predicate);
        return this;
    }

    /**
     * in
     * @return PredicateBuilder
     */
    public PredicateBuilder<SearchConditionBuilder<ParentBuilder>> withPredicate(){
        return new PredicateBuilder<SearchConditionBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), target::setPredicate);
    }

    /**
     * set
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> withSearchCondition(SearchCondition searchCondition){
        target.setSearchCondition(searchCondition);
        return this;
    }

    /**
     * in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (object(target::getSearchCondition, target::setSearchCondition)
                        .init(SearchCondition::new))
                .in(this);
    }

    /**
     * set
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> withAndOrNotItem(SearchCondition.AndOrNotItem andOrNotItem){
        list(target::getAndOrList, target::setAndOrList)
                .add(andOrNotItem);
        return this;
    }

    /**
     * in
     * @return AndOrNotItemBuilder
     */
    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withAndOrNotItem(){
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (list(target::getAndOrList, target::setAndOrList)
                        .addNew(SearchCondition.AndOrNotItem::new))
                .in(this);
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param predicate predicate
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> $(Predicate predicate){
        return withPredicate(predicate);
    }

    /**
     * Quick in
     * Comparison , Like, Between , IsNull , In , ComparisonSubQuery
     * @param expression expression
     * @return ExpressionTransformBuilder
     */
    public ExpressionTransformBuilder<SearchConditionBuilder<ParentBuilder>> $(Expression expression){
        return new ExpressionTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return ContainsFreeTextTransformBuilder
     */
    public ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>> $Contains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
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
    public SearchConditionBuilder<ParentBuilder> $Contains(String containsSearchCondition){
        return new ContainsPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>> $FreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
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
    public SearchConditionBuilder<ParentBuilder> $FreeText(String freetextString){
        return new FreeTextPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public SearchConditionBuilder<ParentBuilder> $Exists(Select subQuery){
        return new ExistsPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
                (object(Exists::new).set(this::$))
                .in(this)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $(){
        return withSearchCondition();
    }

    /**
     * Quick set
     * @param predicate predicate
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> $Not(Predicate predicate){
        return withNot()
                .withPredicate(predicate);
    }

    /**
     * Quick in
     * Comparison , Like, Between , IsNull , In , ComparisonSubQuery
     * @param expression expression
     * @return ExpressionTransformBuilder
     */
    public ExpressionTransformBuilder<SearchConditionBuilder<ParentBuilder>> $Not(Expression expression){
        return new ExpressionTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$Not)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>> $NotContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
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
    public SearchConditionBuilder<ParentBuilder> $NotContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>> $NotFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
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
    public SearchConditionBuilder<ParentBuilder> $NotFreeText(String freetextString){
        return new FreeTextPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public SearchConditionBuilder<ParentBuilder> $NotExists(Select subQuery){
        return new ExistsPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
                (object(Exists::new).set(this::$Not))
                .in(this)
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $Not(){
        return withNot()
                .withSearchCondition();
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> $And(Predicate predicate) {
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
    public ExpressionTransformBuilder<SearchConditionBuilder<ParentBuilder>> $And(Expression expression){
        return new ExpressionTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$And)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>> $AndContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
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
    public SearchConditionBuilder<ParentBuilder> $AndContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>> $AndFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
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
    public SearchConditionBuilder<ParentBuilder> $AndFreeText(String freetextString){
        return new FreeTextPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public SearchConditionBuilder<ParentBuilder> $AndExists(Select subQuery){
        return new ExistsPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $And() {
        SearchCondition searchCondition = new SearchCondition();
        withAndOrNotItem()
                .withAnd()
                .withSearchCondition(searchCondition)
                .and();
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }
    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> $AndNot(Predicate predicate) {
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
    public ExpressionTransformBuilder<SearchConditionBuilder<ParentBuilder>> $AndNot(Expression expression){
        return new ExpressionTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$AndNot)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>> $AndNotContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
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
    public SearchConditionBuilder<ParentBuilder> $AndNotContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>> $AndNotFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
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
    public SearchConditionBuilder<ParentBuilder> $AndNotFreeText(String freetextString){
        return new FreeTextPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public SearchConditionBuilder<ParentBuilder> $AndNotExists(Select subQuery){
        return new ExistsPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $AndNot() {
        SearchCondition searchCondition = new SearchCondition();
        withAndOrNotItem()
                .withAnd()
                .withNot()
                .withSearchCondition(searchCondition)
                .and();
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> $Or(Predicate predicate) {
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
    public ExpressionTransformBuilder<SearchConditionBuilder<ParentBuilder>> $Or(Expression expression){
        return new ExpressionTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$Or)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>> $OrContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
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
    public SearchConditionBuilder<ParentBuilder> $OrContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>> $OrFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
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
    public SearchConditionBuilder<ParentBuilder> $OrFreeText(String freetextString){
        return new FreeTextPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public SearchConditionBuilder<ParentBuilder> $OrExists(Select subQuery){
        return new ExistsPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $Or() {
        SearchCondition searchCondition = new SearchCondition();
        withAndOrNotItem()
                .withOr()
                .withSearchCondition(searchCondition)
                .and();
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> $OrNot(Predicate predicate) {
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
    public ExpressionTransformBuilder<SearchConditionBuilder<ParentBuilder>> $OrNot(Expression expression){
        return new ExpressionTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), this::$OrNot)
                .withExpression(expression);
    }

    /**
     * Quick in
     * Contains or FreeText
     * @param column column
     * @return PredicateBuilder
     */
    public ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>> $OrNotContains(ColumnName column){
        return new ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
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
    public SearchConditionBuilder<ParentBuilder> $OrNotContains(String containsSearchCondition){
        return new ContainsPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>> $OrNotFreeText(ColumnName column){
        return new ContainsFreeTextTransformBuilder<SearchConditionBuilder<ParentBuilder>>()
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
    public SearchConditionBuilder<ParentBuilder> $OrNotFreeText(String freetextString){
        return new FreeTextPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public SearchConditionBuilder<ParentBuilder> $OrNotExists(Select subQuery){
        return new ExistsPredicateBuilder<SearchConditionBuilder<ParentBuilder>>
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
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $OrNot() {
        SearchCondition searchCondition = new SearchCondition();
        withAndOrNotItem()
                .withOr()
                .withNot()
                .withSearchCondition(searchCondition)
                .and();
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }


    /**
     * AndOrNotItemBuilder
     * All Quick method move to SearchConditionBuilder
     * @param <ParentBuilder>
     */
    public static class AndOrNotItemBuilder<ParentBuilder>
            extends ParentHoldBuilder<AndOrNotItemBuilder<ParentBuilder>,ParentBuilder,SearchCondition.AndOrNotItem> {

        public AndOrNotItemBuilder() {
            super(new SearchCondition.AndOrNotItem());
        }

        public AndOrNotItemBuilder(SearchCondition.AndOrNotItem target) {
            super(target);
        }


        /**
         * set
         * @param useAnd and
         * @return THIS
         */
        public AndOrNotItemBuilder<ParentBuilder> withAnd(boolean useAnd) {
            target.setUseAnd(useAnd);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public AndOrNotItemBuilder<ParentBuilder> withAnd() {
            return withAnd(true);
        }

        /**
         * set
         * @param useOr or
         * @return THIS
         */
        public AndOrNotItemBuilder<ParentBuilder> withOr(boolean useOr) {
            target.setUseAnd(!useOr);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public AndOrNotItemBuilder<ParentBuilder> withOr() {
            return withOr(true);
        }

        /**
         * set
         * @param useNot not
         * @return THIS
         */
        public AndOrNotItemBuilder<ParentBuilder> withNot(boolean useNot) {
            target.setUseNot(useNot);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public AndOrNotItemBuilder<ParentBuilder> withNot() {
            return withNot(true);
        }

        /**
         * set
         * @param predicate Predicate
         * @return THIS
         */
        public AndOrNotItemBuilder<ParentBuilder> withPredicate(Predicate predicate){
            target.setPredicate(predicate);
            return this;
        }

        /**
         * in
         * @return PredicateBuilder
         */
        public PredicateBuilder<AndOrNotItemBuilder<ParentBuilder>> withPredicate(){
            return new PredicateBuilder<AndOrNotItemBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), target::setPredicate);
        }

        /**
         * set
         * @param searchCondition SearchCondition
         * @return THIS
         */
        public AndOrNotItemBuilder<ParentBuilder> withSearchCondition(SearchCondition searchCondition){
            target.setSearchCondition(searchCondition);
            return this;
        }

        /**
         * in
         * @return SearchConditionBuilder
         */
        public SearchConditionBuilder<AndOrNotItemBuilder<ParentBuilder>> withSearchCondition(){
            return new SearchConditionBuilder<AndOrNotItemBuilder<ParentBuilder>>
                    (object(target::getSearchCondition, target::setSearchCondition)
                            .init(SearchCondition::new))
                    .in(this);
        }

    }

}
