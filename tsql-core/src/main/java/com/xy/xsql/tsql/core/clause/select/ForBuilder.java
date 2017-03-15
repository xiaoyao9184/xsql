package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.select.For;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class ForBuilder<ParentBuilder>
        extends CodeTreeBuilder<ForBuilder<ParentBuilder>,ParentBuilder,For> {

    public ForBuilder() {
        super(new For());
    }

    public ForBuilder(For forClause) {
        super(forClause);
    }

}
