package com.xy.xsql.tsql.style.constructor.builder.queries.select;

import com.xy.xsql.tsql.builder.chain.queries.select.HavingBuilder;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;
import com.xy.xsql.tsql.style.constructor.builder.queries.b$search_condition;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_HAVING extends HavingBuilder<b_HAVING> {

    public b_HAVING() {
        this.in(this);
    }


    /*
    Item
     */

    public b_HAVING AND(Predicate predicate){
        withAndOrNotItem()
                .withAnd()
                .withPredicate(predicate);
        return this;
    }
    public b_HAVING AND(b$search_condition search_condition){
        withAndOrNotItem()
                .withAnd()
                .withSearchCondition(search_condition.build());
        return this;
    }
    public b_HAVING AND_NOT(Predicate predicate){
        withAndOrNotItem()
                .withAnd()
                .withNot()
                .withPredicate(predicate);
        return this;
    }
    public b_HAVING AND_NOT(b$search_condition search_condition){
        withAndOrNotItem()
                .withAnd()
                .withNot()
                .withSearchCondition(search_condition.build());
        return this;
    }
    public b_HAVING OR(Predicate predicate){
        withAndOrNotItem()
                .withOr()
                .withPredicate(predicate);
        return this;
    }
    public b_HAVING OR(b$search_condition search_condition){
        withAndOrNotItem()
                .withOr()
                .withSearchCondition(search_condition.build());
        return this;
    }
    public b_HAVING OR_NOT(Predicate predicate){
        withAndOrNotItem()
                .withOr()
                .withNot()
                .withPredicate(predicate);
        return this;
    }
    public b_HAVING OR_NOT(b$search_condition search_condition){
        withAndOrNotItem()
                .withOr()
                .withNot()
                .withSearchCondition(search_condition.build());
        return this;
    }

}
