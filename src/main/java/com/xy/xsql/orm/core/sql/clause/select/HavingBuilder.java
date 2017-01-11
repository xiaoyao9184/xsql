package com.xy.xsql.orm.core.sql.clause.select;

import com.xy.xsql.orm.core.SubBuilder;
import com.xy.xsql.orm.data.sql.clause.select.Having;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class HavingBuilder<Done>
        extends SubBuilder<HavingBuilder<Done>,Void,Done> {

    private Having having;

    public HavingBuilder(Having having) {
        this.having = having;
    }
}
