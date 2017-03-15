package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.SubBuilder;
import com.xy.xsql.tsql.model.clause.select.For;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class ForBuilder<Done>
        extends SubBuilder<ForBuilder<Done>,Void,Done> {

    private For forClause;

    public ForBuilder(For forClause) {
        this.forClause = forClause;
    }
}
