package com.xy.xsql.tsql.style.constructor.introducer.queries.predicates;

import com.xy.xsql.tsql.style.constructor.introducer.queries.select_;
import com.xy.xsql.tsql.style.constructor.builder.queries.predicates.b_EXISTS;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_SELECT;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface exists {

    static b_EXISTS EXISTS(
            b_SELECT subquery){
        return new b_EXISTS(){
            {
                withSubQuery(subquery.build());
            }
        };
    }
    static b_EXISTS EXISTS(
            b_SELECT.b$query_expression subquery){
        return new b_EXISTS(){
            {
                withSubQuery(select_.$(subquery).build());
            }
        };
    }
    static b_EXISTS EXISTS(
            b_SELECT.b$query_specification subquery){
        return new b_EXISTS(){
            {
                withSubQuery(select_.$(subquery).build());
            }
        };
    }

}
