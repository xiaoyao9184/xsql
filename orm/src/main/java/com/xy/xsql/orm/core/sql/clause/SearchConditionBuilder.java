package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.CodeTreeBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.element.predicate.Predicate;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.statements.dml.Select;
import com.xy.xsql.tsql.model.predicate.*;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.xy.xsql.orm.core.sql.ExpressionBuilder.e_string;
import static com.xy.xsql.orm.core.sql.element.ColumnNameBuilder.c;
import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SearchConditionBuilder<ParentBuilder>
        extends CodeTreeBuilder<SearchConditionBuilder<ParentBuilder>,ParentBuilder,SearchCondition> {

    public SearchConditionBuilder() {
        super(new SearchCondition());
    }

    public SearchConditionBuilder(SearchCondition searchCondition) {
        super(searchCondition);
    }

    public SearchConditionBuilder<ParentBuilder> withNot(){
        tar.setUseNot(true);
        return this;
    }

    public PredicateBuilder<SearchConditionBuilder<ParentBuilder>> withPredicate(){
        return new PredicateBuilder<SearchConditionBuilder<ParentBuilder>>
                ()
                .in(this);
    }

    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (set(SearchCondition::new,
                        tar::setSearchCondition))
                .in(this);
    }

    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withAndOrNotItem(){
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (initNew(SearchCondition.AndOrNotItem::new,
                        tar::getAndOrList,
                        tar::setAndOrList))
                .in(this);
    }


    /**
     * Quick set predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withPredicate(Predicate predicate){
        tar.setPredicate(predicate);
        return this;
    }

    /**
     * Quick set searchCondition
     * @param searchCondition
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withSearchCondition(SearchCondition searchCondition){
        tar.setSearchCondition(searchCondition);
        return this;
    }


    /**
     * Quick in AndOrNotItemBuilder
     * @deprecated cant simple method name to and() conflict
     * @see #and()
     * @return
     */
    @Deprecated
    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withAnd() {
        SearchCondition.AndOrNotItem andOrNotItem = new SearchCondition.AndOrNotItem();
        initAdd(andOrNotItem,
                tar::getAndOrList,
                tar::setAndOrList);
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (andOrNotItem)
                .withAnd()
                .in(this);
    }

    /**
     * Quick in AndOrNotItemBuilder
     * @deprecated cant simple method name to or() , and() conflict
     * @see #and()
     * @return
     */
    @Deprecated
    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withOr() {
        SearchCondition.AndOrNotItem andOrNotItem = new SearchCondition.AndOrNotItem();
        initAdd(andOrNotItem,
                tar::getAndOrList,
                tar::setAndOrList);
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (andOrNotItem)
                .withOr()
                .in(this);
    }

    /**
     * Quick in AndOrNotItemBuilder
     * @deprecated cant simple method name to andNot() , and() conflict
     * @see #and()
     * @return
     */
    @Deprecated
    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withAndNot() {
        SearchCondition.AndOrNotItem andOrNotItem = new SearchCondition.AndOrNotItem();
        initAdd(andOrNotItem,
                tar::getAndOrList,
                tar::setAndOrList);
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (andOrNotItem)
                .withAnd()
                .withNot(true)
                .in(this);
    }

    /**
     * Quick in AndOrNotItemBuilder
     * @deprecated cant simple method name to orNot() , and() conflict
     * @see #and()
     * @return
     */
    @Deprecated
    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withOrNot() {
        SearchCondition.AndOrNotItem andOrNotItem = new SearchCondition.AndOrNotItem();
        initAdd(andOrNotItem,
                tar::getAndOrList,
                tar::setAndOrList);
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (andOrNotItem)
                .withOr()
                .withNot(true)
                .in(this);
    }





    /**
     * Quick set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withAnd(Predicate predicate) {
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withAnd()
                        .withPredicate(predicate)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return this;
    }

    /**
     * Quick set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withOr(Predicate predicate) {
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withOr()
                        .withPredicate(predicate)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return this;
    }

    /**
     * Quick set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withAndPredicate(Predicate predicate) {
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withAnd()
                        .withPredicate(predicate)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return this;
    }

    /**
     * Quick set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withOrPredicate(Predicate predicate) {
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withOr()
                        .withPredicate(predicate)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return this;
    }

    /**
     * Quick set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withAndNotPredicate(Predicate predicate) {
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withAnd()
                        .withNot(true)
                        .withPredicate(predicate)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return this;
    }

    /**
     * Quick set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withOrNotPredicate(Predicate predicate) {
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withOr()
                        .withNot(true)
                        .withPredicate(predicate)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return this;
    }


    /**
     * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withAndSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withAnd()
                        .withSearchCondition(searchCondition)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withOrSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withOr()
                        .withSearchCondition(searchCondition)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withAndNotSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withAnd()
                        .withNot(true)
                        .withSearchCondition(searchCondition)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withOrNotSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withOr()
                        .withNot(true)
                        .withSearchCondition(searchCondition)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }




    /**
     *
     * @param <ParentBuilder>
     */
    public static class PredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<PredicateBuilder<ParentBuilder>,ParentBuilder,Predicate> {

        public PredicateBuilder() {
            super(null);
        }

        public PredicateBuilder(Predicate predicate) {
            super(predicate);
        }

        public OperatorPredicateBuilder<ParentBuilder> Operator(){
            Operator predicate = new Operator();
            tar = predicate;
            return new OperatorPredicateBuilder<ParentBuilder>(predicate).in(out());
        }

        public LikePredicateBuilder<ParentBuilder> Like(){
            Like predicate = new Like();
            tar = predicate;
            return new LikePredicateBuilder<ParentBuilder>(predicate).in(out());
        }

        public BetweenPredicateBuilder<ParentBuilder> Between(){
            Between predicate = new Between();
            tar = predicate;
            return new BetweenPredicateBuilder<ParentBuilder>(predicate).in(out());
        }

        public IsNullPredicateBuilder<ParentBuilder> IsNull(){
            IsNull predicate = new IsNull();
            tar = predicate;
            return new IsNullPredicateBuilder<ParentBuilder>(predicate).in(out());
        }

        public ContainsPredicateBuilder<ParentBuilder> Contains(){
            Contains predicate = new Contains();
            tar = predicate;
            return new ContainsPredicateBuilder<ParentBuilder>(predicate).in(out());
        }

        public FreeTextPredicateBuilder<ParentBuilder> FreeText(){
            FreeText predicate = new FreeText();
            tar = predicate;
            return new FreeTextPredicateBuilder<ParentBuilder>(predicate).in(out());
        }

        public InPredicateBuilder<ParentBuilder> In(){
            In predicate = new In();
            tar = predicate;
            return new InPredicateBuilder<ParentBuilder>(predicate).in(out());
        }

        public ASAPredicateBuilder<ParentBuilder> All_Some_Any(){
            OperatorSubQuery predicate = new OperatorSubQuery();
            tar = predicate;
            return new ASAPredicateBuilder<ParentBuilder>(predicate).in(out());
        }

        public ExistsPredicateBuilder<ParentBuilder> Exists(){
            Exists predicate = new Exists();
            tar = predicate;
            return new ExistsPredicateBuilder<ParentBuilder>(predicate).in(out());
        }

    }

    /**
     * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
     * @param <ParentBuilder>
     */
    @SuppressWarnings("Duplicates")
    public static class OperatorPredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<OperatorPredicateBuilder<ParentBuilder>,ParentBuilder,Operator> {

        public OperatorPredicateBuilder() {
            super(new Operator());
        }

        public OperatorPredicateBuilder(Operator predicate) {
            super(predicate);
        }

        private int index = 0;

        public OperatorPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
            if(index == 0){
                tar.setExpression(expression);
                index = 1;
            } else {
                tar.setOperatorExpression(expression);
                index = 0;
            }
            return this;
        }

        public OperatorPredicateBuilder<ParentBuilder> withOperator(com.xy.xsql.orm.data.sql.element.operator.Operator operator) {
            tar.setOperator(operator);
            return this;
        }

        public static Predicate EQUAL(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(Operators.EQUAL)
                    .withExpression(right)
                    .build();
        }

        public static Predicate NOT_EQUAL(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(Operators.NOT_EQUAL)
                    .withExpression(right)
                    .build();
        }

        public static Predicate NOT_EQUAL_NOT_ISO(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(Operators.NOT_EQUAL_NOT_ISO)
                    .withExpression(right)
                    .build();
        }

        public static Predicate GREATER(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(Operators.GREATER)
                    .withExpression(right)
                    .build();
        }

        public static Predicate GREATER_EQUAL(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(Operators.GREATER_EQUAL)
                    .withExpression(right)
                    .build();
        }

        public static Predicate NOT_GREATER_NOT_ISO(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(Operators.NOT_GREATER_NOT_ISO)
                    .withExpression(right)
                    .build();
        }

        public static Predicate LESS(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(Operators.LESS)
                    .withExpression(right)
                    .build();
        }

        public static Predicate LESS_EQUAL(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(Operators.LESS_EQUAL)
                    .withExpression(right)
                    .build();
        }

        public static Predicate NOT_LESS_NOT_ISO(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(Operators.NOT_LESS_NOT_ISO)
                    .withExpression(right)
                    .build();
        }

    }

    /**
     * string_expression [ NOT ] LIKE string_expression [ ESCAPE 'escape_character' ]
     * @param <ParentBuilder>
     */
    @SuppressWarnings("Duplicates")
    public static class LikePredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<LikePredicateBuilder<ParentBuilder>,ParentBuilder,Like> {

        public LikePredicateBuilder() {
            super(new Like());
        }

        public LikePredicateBuilder(Like predicate) {
            super(predicate);
        }

        private int index = 0;

        public LikePredicateBuilder<ParentBuilder> withStringExpression(Expression expression) {
            if(index == 0){
                tar.setExpression(expression);
                index = 1;
            } else {
                tar.setLikeExpression(expression);
                index = 0;
            }
            return this;
        }

        public LikePredicateBuilder<ParentBuilder> withNot(boolean useNot) {
            tar.setUseNotOperator(useNot);
            return this;
        }

        public LikePredicateBuilder<ParentBuilder> withEscape(StringConstant escape) {
            tar.setEscapeCharacter(escape);
            return this;
        }


        public static Predicate LIKE(Expression left,Expression right){
            return new LikePredicateBuilder<Void>()
                    .withStringExpression(left)
                    .withStringExpression(right)
                    .build();
        }

        public static Predicate NOT_LIKE(Expression left,Expression right){
            return new LikePredicateBuilder<Void>()
                    .withStringExpression(left)
                    .withNot(true)
                    .withStringExpression(right)
                    .build();
        }


        public static Predicate LIKE(Expression left,Expression right,StringConstant escape){
            return new LikePredicateBuilder<Void>()
                    .withStringExpression(left)
                    .withStringExpression(right)
                    .withEscape(escape)
                    .build();
        }

        public static Predicate NOT_LIKE(Expression left,Expression right,StringConstant escape){
            return new LikePredicateBuilder<Void>()
                    .withStringExpression(left)
                    .withNot(true)
                    .withStringExpression(right)
                    .withEscape(escape)
                    .build();
        }

    }

    /**
     * expression [ NOT ] BETWEEN expression AND expression
     * @param <ParentBuilder>
     */
    public static class BetweenPredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<BetweenPredicateBuilder<ParentBuilder>,ParentBuilder,Between> {

        public BetweenPredicateBuilder() {
            super(new Between());
        }

        public BetweenPredicateBuilder(Between predicate) {
            super(predicate);
        }

        private int index = 0;

        public BetweenPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
            if(index == 0){
                tar.setExpression(expression);
                index = 1;
            } else if(index == 1) {
                tar.setStartExpression(expression);
                index = 2;
            } else {
                tar.setEndExpression(expression);
                index = 0;
            }
            return this;
        }

        public BetweenPredicateBuilder<ParentBuilder> withNot(boolean useNot) {
            tar.setUseNotOperator(useNot);
            return this;
        }


        public static Predicate BETWEEN(Expression left,Expression start,Expression end){
            return new BetweenPredicateBuilder<Void>()
                    .withExpression(left)
                    .withExpression(start)
                    .withExpression(end)
                    .build();
        }

        public static Predicate NOT_BETWEEN(Expression left,Expression start,Expression end){
            return new BetweenPredicateBuilder<Void>()
                    .withNot(true)
                    .withExpression(left)
                    .withExpression(start)
                    .withExpression(end)
                    .build();
        }
    }

    /**
     * expression IS [ NOT ] NULL
     * @param <ParentBuilder>
     */
    public static class IsNullPredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<IsNullPredicateBuilder<ParentBuilder>,ParentBuilder,IsNull> {

        public IsNullPredicateBuilder() {
            super(new IsNull());
        }

        public IsNullPredicateBuilder(IsNull predicate) {
            super(predicate);
        }

        public IsNullPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
            tar.setExpression(expression);
            return this;
        }

        public IsNullPredicateBuilder<ParentBuilder> withNot(boolean useNot) {
            tar.setUseNotOperator(useNot);
            return this;
        }

        public static Predicate IS_NULL(Expression left){
            return new IsNullPredicateBuilder<Void>()
                    .withExpression(left)
                    .build();
        }

        public static Predicate IS_NOT_NULL(Expression left){
            return new IsNullPredicateBuilder<Void>()
                    .withExpression(left)
                    .withNot(true)
                    .build();
        }
    }

    /**
     * CONTAINS ( { column | * } , '< contains_search_condition >' )
     * @param <ParentBuilder>
     */
    public static class ContainsPredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<ContainsPredicateBuilder<ParentBuilder>,ParentBuilder,Contains> {

        public ContainsPredicateBuilder() {
            super(new Contains());
        }

        public ContainsPredicateBuilder(Contains predicate) {
            super(predicate);
        }

        public ContainsPredicateBuilder<ParentBuilder> withColumnName(String columnName) {
            tar.setColumnName(c(columnName));
            return this;
        }

        public ContainsPredicateBuilder<ParentBuilder> withColumn(String... columnName) {
            tar.setColumnList(
                    Arrays.stream(columnName)
                            .map(Column::new)
                            .collect(Collectors.toList()));
            return this;
        }

        public ContainsPredicateBuilder<ParentBuilder> withAllColumn() {
            tar.setUseAllColumn(true);
            return this;
        }

        public ContainsPredicateBuilder<ParentBuilder> withContainsSearchCondition(String containsSearchCondition) {
            tar.setContainsSearchCondition(e_string(containsSearchCondition));
            return this;
        }
    }

    /**
     * FREETEXT ( { column | * } , 'freetext_string' )
     * @param <ParentBuilder>
     */
    public static class FreeTextPredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<FreeTextPredicateBuilder<ParentBuilder>,ParentBuilder,FreeText> {

        public FreeTextPredicateBuilder() {
            super(new FreeText());
        }

        public FreeTextPredicateBuilder(FreeText predicate) {
            super(predicate);
        }

        public FreeTextPredicateBuilder<ParentBuilder> withColumnName(String columnName) {
            tar.setColumnName(c(columnName));
            return this;
        }

        public FreeTextPredicateBuilder<ParentBuilder> withColumn(String... columnName) {
            tar.setColumnList(
                    Arrays.stream(columnName)
                            .map(Column::new)
                            .collect(Collectors.toList()));
            return this;
        }

        public FreeTextPredicateBuilder<ParentBuilder> withAllColumn() {
            tar.setUseAllColumn(true);
            return this;
        }

        public FreeTextPredicateBuilder<ParentBuilder> withFreeText(String freetextString) {
            tar.setFreetextString(e_string(freetextString));
            return this;
        }
    }

    /**
     * expression [ NOT ] IN ( subquery | expression [ ,...n ] )
     * @param <ParentBuilder>
     */
    public static class InPredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<InPredicateBuilder<ParentBuilder>,ParentBuilder,In> {

        public InPredicateBuilder() {
            super(new In());
        }

        public InPredicateBuilder(In predicate) {
            super(predicate);
        }

        public InPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
            tar.setExpression(expression);
            return this;
        }

        public InPredicateBuilder<ParentBuilder> withNot(boolean useNot) {
            tar.setUseNotOperator(useNot);
            return this;
        }

        public InPredicateBuilder<ParentBuilder> withValueExpression(Expression... expression) {
            initAdd(Arrays.asList(expression),
                    tar::getExpressionList,
                    tar::setExpressionList);
            return this;
        }

        public InPredicateBuilder<ParentBuilder> withSubQuery(Select subquery) {
            tar.setSubquery(subquery);
            return this;
        }


        public static Predicate IN(Expression left,Expression... in){
            return new InPredicateBuilder<Void>()
                    .withExpression(left)
                    .withValueExpression(in)
                    .build();
        }

        public static Predicate NOT_IN(Expression left,Expression... in){
            return new InPredicateBuilder<Void>()
                    .withExpression(left)
                    .withNot(true)
                    .withValueExpression(in)
                    .build();
        }

    }

    /**
     * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } { ALL | SOME | ANY} ( subquery )
     * @param <ParentBuilder>
     */
    public static class ASAPredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<ASAPredicateBuilder<ParentBuilder>,ParentBuilder,OperatorSubQuery> {

        public ASAPredicateBuilder() {
            super(new OperatorSubQuery());
        }

        public ASAPredicateBuilder(OperatorSubQuery predicate) {
            super(predicate);
        }

        public ASAPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
            tar.setExpression(expression);
            return this;
        }

        public ASAPredicateBuilder<ParentBuilder> withOperator(com.xy.xsql.orm.data.sql.element.operator.Operator operator) {
            tar.setOperator(operator);
            return this;
        }
        
        public ASAPredicateBuilder<ParentBuilder> withALL_SOME_ANY(OperatorSubQuery.ALL_SOME_ANY oneOf) {
            tar.setAll_some_any(oneOf);
            return this;
        }

        public ASAPredicateBuilder<ParentBuilder> withSubQuery(Select subquery) {
            tar.setSubquery(subquery);
            return this;
        }




        public static OperatorSubQuery.ALL_SOME_ANY ALL(){
            return OperatorSubQuery.ALL_SOME_ANY.ALL;
        }

        public static OperatorSubQuery.ALL_SOME_ANY SOME(){
            return OperatorSubQuery.ALL_SOME_ANY.SOME;
        }

        public static OperatorSubQuery.ALL_SOME_ANY ANY(){
            return OperatorSubQuery.ALL_SOME_ANY.ANY;
        }

        public static Predicate EQUAL(Expression left, OperatorSubQuery.ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(Operators.EQUAL)
                    .withSubQuery(subquery)
                    .build();
        }

        public static Predicate NOT_EQUAL(Expression left, OperatorSubQuery.ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(Operators.NOT_EQUAL)
                    .withSubQuery(subquery)
                    .build();
        }

        public static Predicate NOT_EQUAL_NOT_ISO(Expression left, OperatorSubQuery.ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(Operators.NOT_EQUAL_NOT_ISO)
                    .withSubQuery(subquery)
                    .build();
        }

        public static Predicate GREATER(Expression left, OperatorSubQuery.ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(Operators.GREATER)
                    .withSubQuery(subquery)
                    .build();
        }

        public static Predicate GREATER_EQUAL(Expression left, OperatorSubQuery.ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(Operators.GREATER_EQUAL)
                    .withSubQuery(subquery)
                    .build();
        }

        public static Predicate NOT_GREATER_NOT_ISO(Expression left, OperatorSubQuery.ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(Operators.NOT_GREATER_NOT_ISO)
                    .withSubQuery(subquery)
                    .build();
        }

        public static Predicate LESS(Expression left, OperatorSubQuery.ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(Operators.LESS)
                    .withSubQuery(subquery)
                    .build();
        }

        public static Predicate LESS_EQUAL(Expression left, OperatorSubQuery.ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(Operators.LESS_EQUAL)
                    .withSubQuery(subquery)
                    .build();
        }

        public static Predicate NOT_LESS_NOT_ISO(Expression left, OperatorSubQuery.ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(Operators.NOT_LESS_NOT_ISO)
                    .withSubQuery(subquery)
                    .build();
        }
    }

    /**
     * EXISTS ( subquery )
     * @param <ParentBuilder>
     */
    public static class ExistsPredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<ExistsPredicateBuilder<ParentBuilder>,ParentBuilder,Exists> {

        public ExistsPredicateBuilder() {
            super(new Exists());
        }

        public ExistsPredicateBuilder(Exists predicate) {
            super(predicate);
        }

        public ExistsPredicateBuilder<ParentBuilder> withSubQuery(Select subquery) {
            tar.setSubquery(subquery);
            return this;
        }

        public static Predicate EXISTS(Select subquery){
            return new ExistsPredicateBuilder<Void>()
                    .withSubQuery(subquery)
                    .build();
        }
    }


    /**
     * { AND | OR } [ NOT ] { <predicate> | ( <search_condition> ) }
     * @param <ParentBuilder>
     */
    public static class AndOrNotItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<AndOrNotItemBuilder<ParentBuilder>,ParentBuilder,SearchCondition.AndOrNotItem> {

        public AndOrNotItemBuilder() {
            super(new SearchCondition.AndOrNotItem());
        }

        public AndOrNotItemBuilder(SearchCondition.AndOrNotItem andOr) {
            super(andOr);
        }


        public AndOrNotItemBuilder<ParentBuilder> withAnd() {
            tar.setUseAnd(true);
            return this;
        }

        public AndOrNotItemBuilder<ParentBuilder> withOr() {
            tar.setUseAnd(false);
            return this;
        }

        public AndOrNotItemBuilder<ParentBuilder> withNot(boolean useNot) {
            tar.setUseNot(useNot);
            return this;
        }

        public AndOrNotItemBuilder<ParentBuilder> withPredicate(Predicate predicate){
            tar.setPredicate(predicate);
            return this;
        }

        public PredicateBuilder<AndOrNotItemBuilder<ParentBuilder>> withPredicate(){
            return new PredicateBuilder<AndOrNotItemBuilder<ParentBuilder>>
                    ()
                    .in(this);
        }

        public SearchConditionBuilder<AndOrNotItemBuilder<ParentBuilder>> withSearchCondition(){
            return new SearchConditionBuilder<AndOrNotItemBuilder<ParentBuilder>>
                    (set(SearchCondition::new,
                            tar::setSearchCondition))
                    .in(this);
        }

        public AndOrNotItemBuilder<ParentBuilder> withSearchCondition(SearchCondition searchCondition){
            tar.setSearchCondition(searchCondition);
            return this;
        }
    }
}
