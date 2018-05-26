package com.xy.xsql.tsql.builder.chain.datatypes.table.index;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;

/**
 * IndexOptionBuilder
 * Created by xiaoyao9184 on 2017/8/17.
 * @param <ParentBuilder>
 */
@SuppressWarnings("unused")
public class IndexOptionBuilder<ParentBuilder>
        extends ParentHoldBuilder<IndexOptionBuilder<ParentBuilder>,ParentBuilder,IndexOption> {

    public IndexOptionBuilder() {
        super(new IndexOption());
    }

    public IndexOptionBuilder(IndexOption target) {
        super(target);
    }

}
