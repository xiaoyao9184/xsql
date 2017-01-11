package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.SubBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;

import java.util.ArrayList;

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


    public SearchConditionBuilder<Done> withAnd(){
        this.searchCondition.setUseAnd(true);
        return this;
    }

    public SearchConditionBuilder<Done> withOr(){
        this.searchCondition.setUseOr(true);
        return this;
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

    public SearchConditionBuilder<SearchConditionBuilder<Done>> withOtherSearchCondition(){
        SearchCondition searchCondition = new SearchCondition();
        if(this.searchCondition.getSearchCondition() == null){
            this.searchCondition.setOtherSearchCondition(new ArrayList<SearchCondition>());
        }
        this.searchCondition.getOtherSearchCondition().add(searchCondition);
        SearchConditionBuilder<SearchConditionBuilder<Done>> searchConditionBuilder = new SearchConditionBuilder<>(searchCondition);
        return searchConditionBuilder.in(this);
    }



    public class PredicateBuilder<Done2>
            extends SubBuilder<PredicateBuilder<Done2>,Void,Done2> {

        private SearchCondition.Predicate predicate;

        public PredicateBuilder(SearchCondition.Predicate predicate) {
            this.predicate = predicate;
        }

        public PredicateBuilder<Done2> withExpression(Expression expression) {
            this.predicate.setExpression(expression);
            return this;
        }

        public PredicateBuilder<Done2> withNotOperator() {
            this.predicate.setNotOperator(true);
            return this;
        }

        public PredicateBuilder<Done2> withOperatorEnum(OperatorEnum operatorEnum) {
            this.predicate.setOperatorEnum(operatorEnum);
            return this;
        }

        public PredicateBuilder<Done2> withOperatorExpression(Expression operatorExpression) {
            this.predicate.setOperatorExpression(operatorExpression);
            return this;
        }

        public PredicateBuilder<Done2> withAndExpression(Expression andExpression) {
            this.predicate.setAndExpression(andExpression);
            return this;
        }

        public PredicateBuilder<Done2> withAsa(SearchCondition.Predicate.ASA asa) {
            this.predicate.setAsa(asa);
            return this;
        }


    }

}
