package com.xy.xsql.tsql.style.constructor.builder.elements.expressions;

import com.xy.xsql.tsql.builder.chain.elements.expressions.CoalesceBuilder;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_COALESCE extends CoalesceBuilder<b_COALESCE> {

    public b_COALESCE() {
        this.in(this);
    }

}
