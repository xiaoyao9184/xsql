package com.xy.xsql.tsql.style.constructor.builder.queries.predicates;

import com.xy.xsql.tsql.builder.chain.queries.predicates.ExistsPredicateBuilder;
import com.xy.xsql.tsql.model.queries.predicates.Exists;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_EXISTS extends ExistsPredicateBuilder<b_EXISTS> {

    public b_EXISTS() {
        this.in(this);
    }

}
