package com.xy.xsql.tsql.style.constructor.builder.queries;

import com.xy.xsql.tsql.builder.chain.queries.TopBuilder;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_TOP
        extends TopBuilder<b_TOP> {

    public b_TOP() {
        this.in(this);
    }

    public static class k_WITH_TIES {}

}
