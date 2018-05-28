package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.queries.predicates.Predicate;
import com.xy.xsql.tsql.style.constructor.builder.queries.b$search_condition;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface search_condition {

    static b$search_condition $(Predicate predicate){
        return new b$search_condition(){
            {
                withPredicate(predicate);
            }
        };
    }

    static b$search_condition $(b$search_condition search_condition){
        return new b$search_condition(){
            {
                withSearchCondition(search_condition.build());
            }
        };
    }

}
