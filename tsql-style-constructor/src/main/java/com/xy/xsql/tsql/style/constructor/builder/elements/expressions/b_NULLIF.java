package com.xy.xsql.tsql.style.constructor.builder.elements.expressions;

import com.xy.xsql.tsql.builder.chain.elements.expressions.NullIfBuilder;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_NULLIF extends NullIfBuilder<b_NULLIF> {

    public b_NULLIF() {
        this.in(this);
    }

}
