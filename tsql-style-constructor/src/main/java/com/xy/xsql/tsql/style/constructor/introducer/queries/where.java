package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.queries.predicates.Predicate;
import com.xy.xsql.tsql.style.constructor.builder.queries.b$search_condition;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_WHERE;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface where {

    static b_WHERE WHERE(Predicate predicate){
        return new b_WHERE(){
            {
                withPredicate(predicate);
            }
        };
    }

    static b_WHERE WHERE(b$search_condition search_condition){
        return new b_WHERE(){
            {
                withSearchCondition(search_condition.build());
            }
        };
    }

    static b_WHERE WHERE_NOT(Predicate predicate){
        return new b_WHERE(){
            {
                withNot();
                withPredicate(predicate);
            }
        };
    }

    static b_WHERE WHERE_NOT(b$search_condition search_condition){
        return new b_WHERE(){
            {
                withNot();
                withSearchCondition(search_condition.build());
            }
        };
    }

}
