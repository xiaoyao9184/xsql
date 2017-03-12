package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.CodeTreeBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.datatype.StringConstant;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.predicate.FreeText;
import com.xy.xsql.orm.data.sql.statements.dml.Select;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.xy.xsql.orm.core.sql.ExpressionBuilder.e_string;
import static com.xy.xsql.orm.core.sql.element.ColumnNameBuilder.c;
import static com.xy.xsql.orm.util.FiledBuilder.set;
import static com.xy.xsql.orm.util.ListBuilder.initAdd;
import static com.xy.xsql.orm.util.ListBuilder.initNew;

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
        this.tar.setUseNot(true);
        return this;
    }

    public PredicateBuilder<SearchConditionBuilder<ParentBuilder>> withPredicate(){
        return new PredicateBuilder<SearchConditionBuilder<ParentBuilder>>
                (set(SearchCondition.Predicate::new,
                        tar::setPredicate))
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
    public SearchConditionBuilder<ParentBuilder> withPredicate(SearchCondition.Predicate predicate){
        tar.setPredicate(predicate);
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
    public SearchConditionBuilder<ParentBuilder> withAnd(SearchCondition.Predicate predicate) {
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
    public SearchConditionBuilder<ParentBuilder> withOr(SearchCondition.Predicate predicate) {
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
    public SearchConditionBuilder<ParentBuilder> withAndPredicate(SearchCondition.Predicate predicate) {
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
    public SearchConditionBuilder<ParentBuilder> withOrPredicate(SearchCondition.Predicate predicate) {
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
    public SearchConditionBuilder<ParentBuilder> withAndNotPredicate(SearchCondition.Predicate predicate) {
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
    public SearchConditionBuilder<ParentBuilder> withOrNotPredicate(SearchCondition.Predicate predicate) {
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
            extends CodeTreeBuilder<PredicateBuilder<ParentBuilder>,ParentBuilder,SearchCondition.Predicate> {

        public PredicateBuilder(SearchCondition.Predicate predicate) {
            super(predicate);
        }

        public OperatorPredicateBuilder<ParentBuilder> Operator(){
            return new OperatorPredicateBuilder<ParentBuilder>(tar).in(out());
        }

        public LikePredicateBuilder<ParentBuilder> Like(){
            return new LikePredicateBuilder<ParentBuilder>(tar).in(out());
        }

        public BetweenPredicateBuilder<ParentBuilder> Between(){
            return new BetweenPredicateBuilder<ParentBuilder>(tar).in(out());
        }

        public IsNullPredicateBuilder<ParentBuilder> IsNull(){
            return new IsNullPredicateBuilder<ParentBuilder>(tar).in(out());
        }

        public ContainsPredicateBuilder<ParentBuilder> Contains(){
            return new ContainsPredicateBuilder<ParentBuilder>(tar).in(out());
        }

        public FreeTextPredicateBuilder<ParentBuilder> FreeText(){
            return new FreeTextPredicateBuilder<ParentBuilder>(tar).in(out());
        }

        public InPredicateBuilder<ParentBuilder> In(){
            return new InPredicateBuilder<ParentBuilder>(tar).in(out());
        }

        public ASAPredicateBuilder<ParentBuilder> All_Some_Any(){
            return new ASAPredicateBuilder<ParentBuilder>(tar).in(out());
        }

        public ExistsPredicateBuilder<ParentBuilder> Exists(){
            return new ExistsPredicateBuilder<ParentBuilder>(tar).in(out());
        }

    }

    /**
     * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
     * @param <ParentBuilder>
     */
    public static class OperatorPredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<OperatorPredicateBuilder<ParentBuilder>,ParentBuilder,SearchCondition.Predicate> {

        public OperatorPredicateBuilder() {
            super(new SearchCondition.Predicate());
            tar.setType(SearchCondition.PredicateType.Operator);
        }

        public OperatorPredicateBuilder(SearchCondition.Predicate predicate) {
            super(predicate);
            tar.setType(SearchCondition.PredicateType.Operator);
        }

        private int index = 0;

        public OperatorPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
            if(index == 0){
                this.tar.setExpression(expression);
                index = 1;
            } else {
                this.tar.setOperatorExpression(expression);
                index = 0;
            }
            return this;
        }

        public OperatorPredicateBuilder<ParentBuilder> withOperator(OperatorEnum operatorEnum) {
            this.tar.setOperatorEnum(operatorEnum);
            return this;
        }

        public static SearchCondition.Predicate EQUAL(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(OperatorEnum.EQUAL)
                    .withExpression(right)
                    .build();
        }

        public static SearchCondition.Predicate NOT_EQUAL(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(OperatorEnum.NOT_EQUAL)
                    .withExpression(right)
                    .build();
        }

        public static SearchCondition.Predicate NOT_EQUAL_NOT_ISO(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(OperatorEnum.NOT_EQUAL_NOT_ISO)
                    .withExpression(right)
                    .build();
        }

        public static SearchCondition.Predicate GREATER(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(OperatorEnum.GREATER)
                    .withExpression(right)
                    .build();
        }

        public static SearchCondition.Predicate GREATER_EQUAL(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(OperatorEnum.GREATER_EQUAL)
                    .withExpression(right)
                    .build();
        }

        public static SearchCondition.Predicate NOT_GREATER_NOT_ISO(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(OperatorEnum.NOT_GREATER_NOT_ISO)
                    .withExpression(right)
                    .build();
        }

        public static SearchCondition.Predicate LESS(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(OperatorEnum.LESS)
                    .withExpression(right)
                    .build();
        }

        public static SearchCondition.Predicate LESS_EQUAL(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(OperatorEnum.LESS_EQUAL)
                    .withExpression(right)
                    .build();
        }

        public static SearchCondition.Predicate NOT_LESS_NOT_ISO(Expression left,Expression right){
            return new OperatorPredicateBuilder<Void>()
                    .withExpression(left)
                    .withOperator(OperatorEnum.NOT_LESS_NOT_ISO)
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
            extends CodeTreeBuilder<LikePredicateBuilder<ParentBuilder>,ParentBuilder,SearchCondition.Predicate> {

        public LikePredicateBuilder() {
            super(new SearchCondition.Predicate());
            tar.setType(SearchCondition.PredicateType.Like);
        }

        public LikePredicateBuilder(SearchCondition.Predicate predicate) {
            super(predicate);
            tar.setType(SearchCondition.PredicateType.Like);
        }

        private int index = 0;

        public LikePredicateBuilder<ParentBuilder> withStringExpression(Expression expression) {
            if(index == 0){
                this.tar.setExpression(expression);
                index = 1;
            } else {
                this.tar.setOperatorExpression(expression);
                index = 0;
            }
            return this;
        }

        public LikePredicateBuilder<ParentBuilder> withNot(boolean useNot) {
            this.tar.setNotOperator(useNot);
            return this;
        }

        public LikePredicateBuilder<ParentBuilder> withEscape(StringConstant escape) {
            this.tar.setEscapeCharacter(escape);
            return this;
        }


        public static SearchCondition.Predicate LIKE(Expression left,Expression right){
            return new LikePredicateBuilder<Void>()
                    .withStringExpression(left)
                    .withStringExpression(right)
                    .build();
        }

        public static SearchCondition.Predicate NOT_LIKE(Expression left,Expression right){
            return new LikePredicateBuilder<Void>()
                    .withStringExpression(left)
                    .withNot(true)
                    .withStringExpression(right)
                    .build();
        }


        public static SearchCondition.Predicate LIKE(Expression left,Expression right,StringConstant escape){
            return new LikePredicateBuilder<Void>()
                    .withStringExpression(left)
                    .withStringExpression(right)
                    .withEscape(escape)
                    .build();
        }

        public static SearchCondition.Predicate NOT_LIKE(Expression left,Expression right,StringConstant escape){
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
            extends CodeTreeBuilder<BetweenPredicateBuilder<ParentBuilder>,ParentBuilder,SearchCondition.Predicate> {

        public BetweenPredicateBuilder() {
            super(new SearchCondition.Predicate());
            tar.setType(SearchCondition.PredicateType.Between);
        }

        public BetweenPredicateBuilder(SearchCondition.Predicate predicate) {
            super(predicate);
            tar.setType(SearchCondition.PredicateType.Between);
        }

        private int index = 0;

        public BetweenPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
            if(index == 0){
                this.tar.setExpression(expression);
                index = 1;
            } else if(index == 1) {
                this.tar.setOperatorExpression(expression);
                index = 2;
            } else {
                this.tar.setAndExpression(expression);
                index = 0;
            }
            return this;
        }

        public BetweenPredicateBuilder<ParentBuilder> withNot(boolean useNot) {
            this.tar.setNotOperator(useNot);
            return this;
        }


        public static SearchCondition.Predicate BETWEEN(Expression left,Expression start,Expression end){
            return new BetweenPredicateBuilder<Void>()
                    .withExpression(left)
                    .withExpression(start)
                    .withExpression(end)
                    .build();
        }

        public static SearchCondition.Predicate NOT_BETWEEN(Expression left,Expression start,Expression end){
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
            extends CodeTreeBuilder<IsNullPredicateBuilder<ParentBuilder>,ParentBuilder,SearchCondition.Predicate> {

        public IsNullPredicateBuilder() {
            super(new SearchCondition.Predicate());
            tar.setType(SearchCondition.PredicateType.Null);
        }

        public IsNullPredicateBuilder(SearchCondition.Predicate predicate) {
            super(predicate);
            tar.setType(SearchCondition.PredicateType.Null);
        }

        public IsNullPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
            this.tar.setExpression(expression);
            return this;
        }

        public IsNullPredicateBuilder<ParentBuilder> withNot(boolean useNot) {
            this.tar.setNotOperator(useNot);
            return this;
        }

        public static SearchCondition.Predicate IS_NULL(Expression left){
            return new IsNullPredicateBuilder<Void>()
                    .withExpression(left)
                    .build();
        }

        public static SearchCondition.Predicate IS_NOT_NULL(Expression left){
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
            extends CodeTreeBuilder<ContainsPredicateBuilder<ParentBuilder>,ParentBuilder,SearchCondition.Predicate> {

        public ContainsPredicateBuilder() {
            super(new SearchCondition.Predicate());
            tar.setType(SearchCondition.PredicateType.Contains);
        }

        public ContainsPredicateBuilder(SearchCondition.Predicate predicate) {
            super(predicate);
            tar.setType(SearchCondition.PredicateType.Contains);
        }

        public ContainsPredicateBuilder<ParentBuilder> withColumnName(String columnName) {
            this.tar.getContains().setColumnName(c(columnName));
            return this;
        }

        public ContainsPredicateBuilder<ParentBuilder> withColumn(String... columnName) {
            this.tar.getContains().setColumnList(
                    Arrays.stream(columnName)
                            .map(Column::new)
                            .collect(Collectors.toList()));
            return this;
        }

        public ContainsPredicateBuilder<ParentBuilder> withAllColumn() {
            this.tar.getContains().setUseAllColumn(true);
            return this;
        }

        public ContainsPredicateBuilder<ParentBuilder> withContainsSearchCondition(String containsSearchCondition) {
            this.tar.getContains().setContainsSearchCondition(e_string(containsSearchCondition));
            return this;
        }
    }

    /**
     * FREETEXT ( { column | * } , 'freetext_string' )
     * @param <ParentBuilder>
     */
    public static class FreeTextPredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<FreeTextPredicateBuilder<ParentBuilder>,ParentBuilder,SearchCondition.Predicate> {

        public FreeTextPredicateBuilder() {
            super(new SearchCondition.Predicate());
            tar.setType(SearchCondition.PredicateType.FreeText);
            tar.setFreeText(new FreeText());
        }

        public FreeTextPredicateBuilder(SearchCondition.Predicate predicate) {
            super(predicate);
            tar.setType(SearchCondition.PredicateType.FreeText);
            tar.setFreeText(new FreeText());
        }

        public FreeTextPredicateBuilder<ParentBuilder> withColumnName(String columnName) {
            this.tar.getFreeText().setColumnName(c(columnName));
            return this;
        }

        public FreeTextPredicateBuilder<ParentBuilder> withColumn(String... columnName) {
            this.tar.getFreeText().setColumnList(
                    Arrays.stream(columnName)
                            .map(Column::new)
                            .collect(Collectors.toList()));
            return this;
        }

        public FreeTextPredicateBuilder<ParentBuilder> withAllColumn() {
            this.tar.getFreeText().setUseAllColumn(true);
            return this;
        }

        public FreeTextPredicateBuilder<ParentBuilder> withFreeText(String freetextString) {
            this.tar.getFreeText().setFreetextString(e_string(freetextString));
            return this;
        }
    }

    /**
     * expression [ NOT ] IN ( subquery | expression [ ,...n ] )
     * @param <ParentBuilder>
     */
    public static class InPredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<InPredicateBuilder<ParentBuilder>,ParentBuilder,SearchCondition.Predicate> {

        public InPredicateBuilder() {
            super(new SearchCondition.Predicate());
            tar.setType(SearchCondition.PredicateType.In);
        }

        public InPredicateBuilder(SearchCondition.Predicate predicate) {
            super(predicate);
            tar.setType(SearchCondition.PredicateType.In);
        }

        public InPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
            this.tar.setExpression(expression);
            return this;
        }

        public InPredicateBuilder<ParentBuilder> withNot(boolean useNot) {
            this.tar.setNotOperator(useNot);
            return this;
        }

        public InPredicateBuilder<ParentBuilder> withValueExpression(Expression... expression) {
            initAdd(Arrays.asList(expression),
                    tar::getExpressionList,
                    tar::setExpressionList);
            return this;
        }

        public InPredicateBuilder<ParentBuilder> withSubQuery(Select... subquery) {
            initAdd(Arrays.asList(subquery),
                    tar::getSubqueryList,
                    tar::setSubqueryList);
            return this;
        }


        public static SearchCondition.Predicate IN(Expression left,Expression... in){
            return new InPredicateBuilder<Void>()
                    .withExpression(left)
                    .withValueExpression(in)
                    .build();
        }

        public static SearchCondition.Predicate NOT_IN(Expression left,Expression... in){
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
            extends CodeTreeBuilder<ASAPredicateBuilder<ParentBuilder>,ParentBuilder,SearchCondition.Predicate> {

        public ASAPredicateBuilder() {
            super(new SearchCondition.Predicate());
        }

        public ASAPredicateBuilder(SearchCondition.Predicate predicate) {
            super(predicate);
        }

        public ASAPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
            this.tar.setExpression(expression);
            return this;
        }

        public ASAPredicateBuilder<ParentBuilder> withOperator(OperatorEnum operatorEnum) {
            this.tar.setOperatorEnum(operatorEnum);
            return this;
        }
        
        public ASAPredicateBuilder<ParentBuilder> withALL_SOME_ANY(ALL_SOME_ANY oneOf) {
            this.tar.setType(oneOf.toPredicateType());
            return this;
        }

        public ASAPredicateBuilder<ParentBuilder> withSubQuery(Select subquery) {
            initAdd(subquery,
                    tar::getSubqueryList,
                    tar::setSubqueryList);
            return this;
        }


        /**
         * ALL SOME ANY type Predicate
         */
        public enum ALL_SOME_ANY implements Element {
            ALL(OperatorEnum.ALL, SearchCondition.PredicateType.All),
            SOME(OperatorEnum.SOME, SearchCondition.PredicateType.Some),
            ANY(OperatorEnum.ANY, SearchCondition.PredicateType.Any);

            private OperatorEnum operatorEnum;
            private SearchCondition.PredicateType predicateType;

            ALL_SOME_ANY(OperatorEnum operatorEnum, SearchCondition.PredicateType predicateType){
                this.operatorEnum = operatorEnum;
                this.predicateType = predicateType;
            }

            @Override
            public String toString(){
                return this.operatorEnum.toString();
            }
            
            public SearchCondition.PredicateType toPredicateType(){
                return this.predicateType;
            }
        }


        public static ALL_SOME_ANY ALL(){
            return ALL_SOME_ANY.ALL;
        }

        public static ALL_SOME_ANY SOME(){
            return ALL_SOME_ANY.SOME;
        }

        public static ALL_SOME_ANY ANY(){
            return ALL_SOME_ANY.ANY;
        }

        public static SearchCondition.Predicate EQUAL(Expression left, ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(OperatorEnum.EQUAL)
                    .withSubQuery(subquery)
                    .build();
        }

        public static SearchCondition.Predicate NOT_EQUAL(Expression left, ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(OperatorEnum.NOT_EQUAL)
                    .withSubQuery(subquery)
                    .build();
        }

        public static SearchCondition.Predicate NOT_EQUAL_NOT_ISO(Expression left, ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(OperatorEnum.NOT_EQUAL_NOT_ISO)
                    .withSubQuery(subquery)
                    .build();
        }

        public static SearchCondition.Predicate GREATER(Expression left, ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(OperatorEnum.GREATER)
                    .withSubQuery(subquery)
                    .build();
        }

        public static SearchCondition.Predicate GREATER_EQUAL(Expression left, ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(OperatorEnum.GREATER_EQUAL)
                    .withSubQuery(subquery)
                    .build();
        }

        public static SearchCondition.Predicate NOT_GREATER_NOT_ISO(Expression left, ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(OperatorEnum.NOT_GREATER_NOT_ISO)
                    .withSubQuery(subquery)
                    .build();
        }

        public static SearchCondition.Predicate LESS(Expression left, ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(OperatorEnum.LESS)
                    .withSubQuery(subquery)
                    .build();
        }

        public static SearchCondition.Predicate LESS_EQUAL(Expression left, ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(OperatorEnum.LESS_EQUAL)
                    .withSubQuery(subquery)
                    .build();
        }

        public static SearchCondition.Predicate NOT_LESS_NOT_ISO(Expression left, ALL_SOME_ANY oneOf, Select subquery){
            return new ASAPredicateBuilder<Void>()
                    .withALL_SOME_ANY(oneOf)
                    .withExpression(left)
                    .withOperator(OperatorEnum.NOT_LESS_NOT_ISO)
                    .withSubQuery(subquery)
                    .build();
        }
    }

    /**
     * EXISTS ( subquery )
     * @param <ParentBuilder>
     */
    public static class ExistsPredicateBuilder<ParentBuilder>
            extends CodeTreeBuilder<ExistsPredicateBuilder<ParentBuilder>,ParentBuilder,SearchCondition.Predicate> {

        public ExistsPredicateBuilder() {
            super(new SearchCondition.Predicate());
        }

        public ExistsPredicateBuilder(SearchCondition.Predicate predicate) {
            super(predicate);
            tar.setType(SearchCondition.PredicateType.Exists);
        }

        public ExistsPredicateBuilder<ParentBuilder> withSubQuery(Select subquery) {
            tar.setSubquery(subquery);
            return this;
        }

        public static SearchCondition.Predicate EXISTS(Select subquery){
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
            this.tar.setUseAnd(true);
            return this;
        }

        public AndOrNotItemBuilder<ParentBuilder> withOr() {
            this.tar.setUseAnd(false);
            return this;
        }

        public AndOrNotItemBuilder<ParentBuilder> withNot(boolean useNot) {
            this.tar.setUseNot(useNot);
            return this;
        }

        public AndOrNotItemBuilder<ParentBuilder> withPredicate(SearchCondition.Predicate predicate){
            this.tar.setPredicate(predicate);
            return this;
        }

        public PredicateBuilder<AndOrNotItemBuilder<ParentBuilder>> withPredicate(){
            return new PredicateBuilder<AndOrNotItemBuilder<ParentBuilder>>
                    (set(SearchCondition.Predicate::new,
                            tar::setPredicate))
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
