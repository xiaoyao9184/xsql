package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.model.queries.predicates.Predicate;
import com.xy.xsql.tsql.style.constructor.builder.queries.b$search_condition;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_HAVING;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface having {

    static b_HAVING HAVING(Predicate predicate){
        return new b_HAVING(){
            {
                withPredicate(predicate);
            }
        };
    }

    static b_HAVING HAVING(b$search_condition search_condition){
        return new b_HAVING(){
            {
                withSearchCondition(search_condition.build());
            }
        };
    }
}
