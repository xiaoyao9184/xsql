package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.SubBuilder;
import com.xy.xsql.orm.core.sql.statements.InsertBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.statements.dml.Insert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SearchConditionBuilder<Done>
        extends SubBuilder<SearchConditionBuilder<Done>,Void,Done> {


    private SearchCondition searchCondition;

    public SearchConditionBuilder(SearchCondition searchCondition) {
        this.searchCondition = searchCondition;
    }

    public SearchConditionBuilder() {
        this.searchCondition = new SearchCondition();
    }

    public SearchConditionBuilder<Done> withNot(){
        this.searchCondition.setUseNot(true);
        return this;
    }

    public PredicateBuilder<SearchConditionBuilder<Done>> withPredicate(){
        SearchCondition.Predicate predicate = new SearchCondition.Predicate();
        this.searchCondition.setPredicate(predicate);
        PredicateBuilder<SearchConditionBuilder<Done>> predicateBuilder = new PredicateBuilder<>(predicate);
        return predicateBuilder.in(this);
    }

    public SearchConditionBuilder<SearchConditionBuilder<Done>> withSearchCondition(){
        SearchCondition searchCondition = new SearchCondition();
        this.searchCondition.setSearchCondition(searchCondition);
        SearchConditionBuilder<SearchConditionBuilder<Done>> searchConditionBuilder = new SearchConditionBuilder<>(searchCondition);
        return searchConditionBuilder.in(this);
    }

    public SearchConditionAndOrListBuilder<SearchConditionBuilder<Done>> withAndOrList() {
        List<SearchCondition.AndOrItem> andOrList = new LinkedList<>();
        this.searchCondition.setAndOrList(andOrList);
        SearchConditionAndOrListBuilder<SearchConditionBuilder<Done>> searchConditionBuilder = new SearchConditionAndOrListBuilder<>(andOrList);
        return searchConditionBuilder.in(this);
    }


    public static class PredicateBuilder<Done2>
            extends SubBuilder<PredicateBuilder<Done2>,Void,Done2> {

        protected SearchCondition.Predicate predicate;

        public PredicateBuilder(SearchCondition.Predicate predicate) {
            this.predicate = predicate;
        }

        public BasePredicateBuilder<Done2> Base(){
            return new BasePredicateBuilder<Done2>(predicate).in(out());
        }

        public LikePredicateBuilder<Done2> Like(){
            return new LikePredicateBuilder<Done2>(predicate).in(out());
        }

        public BetweenPredicateBuilder<Done2> Between(){
            return new BetweenPredicateBuilder<Done2>(predicate).in(out());
        }

        public IsNullPredicateBuilder<Done2> IsNull(){
            return new IsNullPredicateBuilder<Done2>(predicate).in(out());
        }

        //
        public InPredicateBuilder<Done2> In(){
            return new InPredicateBuilder<Done2>(predicate).in(out());
        }

        public ASAPredicateBuilder<Done2> ASA(){
            return new ASAPredicateBuilder<Done2>(predicate).in(out());
        }

        public ExistsPredicateBuilder<Done2> Exists(){
            return new ExistsPredicateBuilder<Done2>(predicate).in(out());
        }

    }

    /**
     * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
     * @param <Done2>
     */
    public static class BasePredicateBuilder<Done2>
            extends SubBuilder<BasePredicateBuilder<Done2>,Void,Done2>  {

        protected SearchCondition.Predicate predicate;

        public BasePredicateBuilder(SearchCondition.Predicate predicate) {
            this.predicate = predicate;
        }

        private int index = 0;

        public BasePredicateBuilder<Done2> withExpression(Expression expression) {
            if(index == 0){
                this.predicate.setExpression(expression);
                index = 1;
            } else {
                this.predicate.setOperatorExpression(expression);
                index = 0;
            }
            return this;
        }

        public BasePredicateBuilder<Done2> withOperator(OperatorEnum operatorEnum) {
            this.predicate.setOperatorEnum(operatorEnum);
            return this;
        }
    }

    /**
     * string_expression [ NOT ] LIKE string_expression [ ESCAPE 'escape_character' ]
     * @param <Done2>
     */
    public static class LikePredicateBuilder<Done2>
            extends SubBuilder<LikePredicateBuilder<Done2>,Void,Done2>  {

        protected SearchCondition.Predicate predicate;

        public LikePredicateBuilder(SearchCondition.Predicate predicate) {
            this.predicate = predicate;
        }

        private int index = 0;

        public LikePredicateBuilder<Done2> withStringExpression(Expression expression) {
            if(index == 0){
                this.predicate.setExpression(expression);
                index = 1;
            } else {
                this.predicate.setOperatorExpression(expression);
                index = 0;
            }
            return this;
        }

        public LikePredicateBuilder<Done2> withNot(boolean useNot) {
            this.predicate.setNotOperator(useNot);
            return this;
        }
    }

    /**
     * expression [ NOT ] BETWEEN expression AND expression
     * @param <Done2>
     */
    public static class BetweenPredicateBuilder<Done2>
            extends SubBuilder<BetweenPredicateBuilder<Done2>,Void,Done2>  {

        protected SearchCondition.Predicate predicate;

        public BetweenPredicateBuilder(SearchCondition.Predicate predicate) {
            this.predicate = predicate;
        }

        private int index = 0;

        public BetweenPredicateBuilder<Done2> withExpression(Expression expression) {
            if(index == 0){
                this.predicate.setExpression(expression);
                index = 1;
            } else if(index == 1) {
                this.predicate.setOperatorExpression(expression);
                index = 2;
            } else {
                this.predicate.setAndExpression(expression);
                index = 0;
            }
            return this;
        }

        public BetweenPredicateBuilder<Done2> withNot(boolean useNot) {
            this.predicate.setNotOperator(useNot);
            return this;
        }
    }

    /**
     * expression IS [ NOT ] NULL
     * @param <Done2>
     */
    public static class IsNullPredicateBuilder<Done2>
            extends SubBuilder<IsNullPredicateBuilder<Done2>,Void,Done2>  {

        protected SearchCondition.Predicate predicate;

        public IsNullPredicateBuilder(SearchCondition.Predicate predicate) {
            this.predicate = predicate;
        }

        public IsNullPredicateBuilder<Done2> withExpression(Expression expression) {
            this.predicate.setExpression(expression);
            return this;
        }

        public IsNullPredicateBuilder<Done2> withNot(boolean useNot) {
            this.predicate.setNotOperator(useNot);
            return this;
        }
    }

    //TODO CONTAINS ( { column | * } , '< contains_search_condition >' )

    //TODO FREETEXT ( { column | * } , 'freetext_string' )

    /**
     * expression [ NOT ] IN ( subquery | expression [ ,...n ] )
     * @param <Done2>
     */
    public static class InPredicateBuilder<Done2>
            extends SubBuilder<InPredicateBuilder<Done2>,Void,Done2>  {

        protected SearchCondition.Predicate predicate;

        public InPredicateBuilder(SearchCondition.Predicate predicate) {
            this.predicate = predicate;
        }

        public InPredicateBuilder<Done2> withExpression(Expression expression) {
            this.predicate.setExpression(expression);
            return this;
        }

        public InPredicateBuilder<Done2> withNot(boolean useNot) {
            this.predicate.setNotOperator(useNot);
            return this;
        }

        public InPredicateBuilder<Done2> withValueExpression(Expression expression) {
            //TODO
            return this;
        }

//        public InPredicateBuilder<Done2> withSubQuery(SubQuery subquery) {
//
//            return this;
//        }

    }

    /**
     * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } { ALL | SOME | ANY} ( subquery )
     * @param <Done2>
     */
    public static class ASAPredicateBuilder<Done2>
            extends SubBuilder<ASAPredicateBuilder<Done2>,Void,Done2>  {

        protected SearchCondition.Predicate predicate;

        public ASAPredicateBuilder(SearchCondition.Predicate predicate) {
            this.predicate = predicate;
        }

        public ASAPredicateBuilder<Done2> withExpression(Expression expression) {
            this.predicate.setExpression(expression);
            return this;
        }

        public ASAPredicateBuilder<Done2> withOperator(OperatorEnum operatorEnum) {
            this.predicate.setOperatorEnum(operatorEnum);
            return this;
        }

        public ASAPredicateBuilder<Done2> withAsa(SearchCondition.Predicate.ASA asa) {
            this.predicate.setAsa(asa);
            return this;
        }

        //TODO subquery
//        public InPredicateBuilder<Done2> withSubQuery(SubQuery subquery) {
//
//            return this;
//        }
    }

    /**
     * EXISTS ( subquery )
     * @param <Done2>
     */
    public static class ExistsPredicateBuilder<Done2>
            extends SubBuilder<ExistsPredicateBuilder<Done2>,Void,Done2>  {

        protected SearchCondition.Predicate predicate;

        public ExistsPredicateBuilder(SearchCondition.Predicate predicate) {
            this.predicate = predicate;
        }

        //TODO subquery
//        public InPredicateBuilder<Done2> withSubQuery(SubQuery subquery) {
//
//            return this;
//        }
    }

    /**
     * [ { AND | OR } [ NOT ] { <predicate> | ( <search_condition> ) } ]
     * [ ,...n ]
     * @param <Done>
     */
    public static class SearchConditionAndOrListBuilder<Done>
            extends SubBuilder<SearchConditionAndOrListBuilder<Done>,Void,Done> {

        private List<SearchCondition.AndOrItem> andOrList;

        public SearchConditionAndOrListBuilder(List<SearchCondition.AndOrItem> andOrList) {
            this.andOrList = andOrList;
        }

        public SearchConditionAndOrBuilder<SearchConditionAndOrListBuilder<Done>> withItem(){
            SearchCondition.AndOrItem andOr = new SearchCondition.AndOrItem();
            if(this.andOrList == null){
                this.andOrList = new LinkedList<>();
            }
            this.andOrList.add(andOr);
            return new SearchConditionAndOrBuilder<SearchConditionAndOrListBuilder<Done>>(andOr)
                    .in(this);
        }
    }

    /**
     * { AND | OR } [ NOT ] { <predicate> | ( <search_condition> ) }
     * @param <Done2>
     */
    public static class SearchConditionAndOrBuilder<Done2>
            extends SubBuilder<SearchConditionAndOrBuilder<Done2>,Void,Done2>  {

        protected SearchCondition.AndOrItem andOr;

        public SearchConditionAndOrBuilder(SearchCondition.AndOrItem andOr) {
            this.andOr = andOr;
        }


        public SearchConditionAndOrBuilder<Done2> withAnd() {
            this.andOr.setUseAnd(true);
            return this;
        }

        public SearchConditionAndOrBuilder<Done2> withOr() {
            this.andOr.setUseAnd(false);
            return this;
        }

        public SearchConditionAndOrBuilder<Done2> withNot(boolean useNot) {
            this.andOr.setUseNot(useNot);
            return this;
        }

        public PredicateBuilder<SearchConditionAndOrBuilder<Done2>> withPredicate(){
            SearchCondition.Predicate predicate = new SearchCondition.Predicate();
            this.andOr.setPredicate(predicate);
            PredicateBuilder<SearchConditionAndOrBuilder<Done2>> predicateBuilder = new PredicateBuilder<>(predicate);
            return predicateBuilder.in(this);
        }

        public SearchConditionBuilder<SearchConditionAndOrBuilder<Done2>> withSearchCondition(){
            SearchCondition searchCondition = new SearchCondition();
            this.andOr.setSearchCondition(searchCondition);
            SearchConditionBuilder<SearchConditionAndOrBuilder<Done2>> searchConditionBuilder = new SearchConditionBuilder<>(searchCondition);
            return searchConditionBuilder.in(this);
        }
    }
}
