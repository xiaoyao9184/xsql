package com.xy.xsql.tsql.style.constructor.builder.queries;

import com.xy.xsql.tsql.builder.chain.queries.SearchConditionBuilder;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b$search_condition extends SearchConditionBuilder<b$search_condition> {

    public b$search_condition() {
        this.in(this);
    }


    /*
    Item
     */

    public b$search_condition AND(Predicate predicate){
        withAndOrNotItem()
                .withAnd()
                .withPredicate(predicate);
        return this;
    }
    public b$search_condition AND(b$search_condition search_condition){
        withAndOrNotItem()
                .withAnd()
                .withSearchCondition(search_condition.build());
        return this;
    }
    public b$search_condition AND_NOT(Predicate predicate){
        withAndOrNotItem()
                .withAnd()
                .withNot()
                .withPredicate(predicate);
        return this;
    }
    public b$search_condition AND_NOT(b$search_condition search_condition){
        withAndOrNotItem()
                .withAnd()
                .withNot()
                .withSearchCondition(search_condition.build());
        return this;
    }
    public b$search_condition OR(Predicate predicate){
        withAndOrNotItem()
                .withOr()
                .withPredicate(predicate);
        return this;
    }
    public b$search_condition OR(b$search_condition search_condition){
        withAndOrNotItem()
                .withOr()
                .withSearchCondition(search_condition.build());
        return this;
    }
    public b$search_condition OR_NOT(Predicate predicate){
        withAndOrNotItem()
                .withOr()
                .withNot()
                .withPredicate(predicate);
        return this;
    }
    public b$search_condition OR_NOT(b$search_condition search_condition){
        withAndOrNotItem()
                .withOr()
                .withNot()
                .withSearchCondition(search_condition.build());
        return this;
    }


    public static class b_ON extends SearchConditionBuilder<b_ON> {

        public b_ON() {
            this.in(this);
        }

        public b_ON AND(Predicate predicate){
            withAndOrNotItem()
                    .withAnd()
                    .withPredicate(predicate);
            return this;
        }
        public b_ON AND(b$search_condition search_condition){
            withAndOrNotItem()
                    .withAnd()
                    .withSearchCondition(search_condition.build());
            return this;
        }
        public b_ON AND_NOT(Predicate predicate){
            withAndOrNotItem()
                    .withAnd()
                    .withNot()
                    .withPredicate(predicate);
            return this;
        }
        public b_ON AND_NOT(b$search_condition search_condition){
            withAndOrNotItem()
                    .withAnd()
                    .withNot()
                    .withSearchCondition(search_condition.build());
            return this;
        }
        public b_ON OR(Predicate predicate){
            withAndOrNotItem()
                    .withOr()
                    .withPredicate(predicate);
            return this;
        }
        public b_ON OR(b$search_condition search_condition){
            withAndOrNotItem()
                    .withOr()
                    .withSearchCondition(search_condition.build());
            return this;
        }
        public b_ON OR_NOT(Predicate predicate){
            withAndOrNotItem()
                    .withOr()
                    .withNot()
                    .withPredicate(predicate);
            return this;
        }
        public b_ON OR_NOT(b$search_condition search_condition){
            withAndOrNotItem()
                    .withOr()
                    .withNot()
                    .withSearchCondition(search_condition.build());
            return this;
        }

    }

    public static class b_AND extends SearchConditionBuilder<b_AND> {

        public b_AND() {
            this.in(this);
        }

        public b_AND AND(Predicate predicate){
            withAndOrNotItem()
                    .withAnd()
                    .withPredicate(predicate);
            return this;
        }
        public b_AND AND(b$search_condition search_condition){
            withAndOrNotItem()
                    .withAnd()
                    .withSearchCondition(search_condition.build());
            return this;
        }
        public b_AND AND_NOT(Predicate predicate){
            withAndOrNotItem()
                    .withAnd()
                    .withNot()
                    .withPredicate(predicate);
            return this;
        }
        public b_AND AND_NOT(b$search_condition search_condition){
            withAndOrNotItem()
                    .withAnd()
                    .withNot()
                    .withSearchCondition(search_condition.build());
            return this;
        }
        public b_AND OR(Predicate predicate){
            withAndOrNotItem()
                    .withOr()
                    .withPredicate(predicate);
            return this;
        }
        public b_AND OR(b$search_condition search_condition){
            withAndOrNotItem()
                    .withOr()
                    .withSearchCondition(search_condition.build());
            return this;
        }
        public b_AND OR_NOT(Predicate predicate){
            withAndOrNotItem()
                    .withOr()
                    .withNot()
                    .withPredicate(predicate);
            return this;
        }
        public b_AND OR_NOT(b$search_condition search_condition){
            withAndOrNotItem()
                    .withOr()
                    .withNot()
                    .withSearchCondition(search_condition.build());
            return this;
        }


}

}
