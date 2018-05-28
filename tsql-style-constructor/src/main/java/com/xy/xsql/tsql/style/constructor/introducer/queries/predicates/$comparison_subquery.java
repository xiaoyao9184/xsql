package com.xy.xsql.tsql.style.constructor.introducer.queries.predicates;

import com.xy.xsql.tsql.style.constructor.builder.queries.b_SELECT;
import com.xy.xsql.tsql.style.constructor.builder.queries.predicates.b2comparison_subquery_predicate_use_logical;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface $comparison_subquery {


    static b2comparison_subquery_predicate_use_logical.ALL ALL(
            b_SELECT select){
        return new b2comparison_subquery_predicate_use_logical.ALL(){
            {
                this.target = select.build();
            }
        };
    }
    static b2comparison_subquery_predicate_use_logical.SOME SOME(
            b_SELECT select){
        return new b2comparison_subquery_predicate_use_logical.SOME(){
            {
                this.target = select.build();
            }
        };
    }
    static b2comparison_subquery_predicate_use_logical.ANY ANY(
            b_SELECT select){
        return new b2comparison_subquery_predicate_use_logical.ANY(){
            {
                this.target = select.build();
            }
        };
    }

}
