package com.xy.xsql.tsql.style.constructor.builder.queries;

import com.xy.xsql.tsql.builder.chain.queries.WhereBuilder;
import com.xy.xsql.tsql.model.queries.Where;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_WHERE extends WhereBuilder<b_WHERE> {

    public b_WHERE() {
        this.in(this);
    }


    /*
    Item
     */

    /**
     * same as
     * @see b$search_condition
     */


    public b_WHERE AND(Predicate predicate){
        withAndOrNotItem()
                .withAnd()
                .withPredicate(predicate);
        return this;
    }

    public b_WHERE AND(b$search_condition search_condition){
        withAndOrNotItem()
                .withAnd()
                .withSearchCondition(search_condition.build());
        return this;
    }

    public b_WHERE AND_NOT(Predicate predicate){
        withAndOrNotItem()
                .withAnd()
                .withNot()
                .withPredicate(predicate);
        return this;
    }

    public b_WHERE AND_NOT(b$search_condition search_condition){
        withAndOrNotItem()
                .withAnd()
                .withNot()
                .withSearchCondition(search_condition.build());
        return this;
    }

    public b_WHERE OR(Predicate predicate){
        withAndOrNotItem()
                .withOr()
                .withPredicate(predicate);
        return this;
    }

    public b_WHERE OR(b$search_condition search_condition){
        withAndOrNotItem()
                .withOr()
                .withSearchCondition(search_condition.build());
        return this;
    }

    public b_WHERE OR_NOT(Predicate predicate){
        withAndOrNotItem()
                .withOr()
                .withNot()
                .withPredicate(predicate);
        return this;
    }

    public b_WHERE OR_NOT(b$search_condition search_condition){
        withAndOrNotItem()
                .withOr()
                .withNot()
                .withSearchCondition(search_condition.build());
        return this;
    }

}
