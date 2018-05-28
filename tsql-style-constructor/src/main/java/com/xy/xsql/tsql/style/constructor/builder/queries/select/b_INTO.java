package com.xy.xsql.tsql.style.constructor.builder.queries.select;

import com.xy.xsql.tsql.builder.chain.queries.select.IntoBuilder;
import com.xy.xsql.tsql.model.queries.select.Into;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_INTO extends IntoBuilder<b_INTO> {

    public b_INTO() {
        this.in(this);
    }

    /*
    Item
     */

    //TODO may be not use method chain
    public b_INTO ON(String fileGroup){
        withFileGroup(fileGroup);
        return this;
    }

}
