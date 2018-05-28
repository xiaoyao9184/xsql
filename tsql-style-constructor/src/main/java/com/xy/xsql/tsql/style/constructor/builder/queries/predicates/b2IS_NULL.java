package com.xy.xsql.tsql.style.constructor.builder.queries.predicates;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.queries.predicates.IsNull;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public interface b2IS_NULL
        extends SimpleBuilder<IsNull> {

    b2IS_NULL IS_NULL();
    b2IS_NULL IS_NOT_NULL();

}
