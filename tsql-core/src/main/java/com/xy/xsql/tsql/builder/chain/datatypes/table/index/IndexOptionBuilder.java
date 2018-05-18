package com.xy.xsql.tsql.builder.chain.datatypes.table.index;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;

/**
 * Created by xiaoyao9184 on 2017/8/17.
 * @param <ParentBuilder>
 */
public class IndexOptionBuilder<ParentBuilder>
        extends CodeTreeBuilder<IndexOptionBuilder<ParentBuilder>,ParentBuilder,IndexOption> {

    public IndexOptionBuilder(IndexOption tar) {
        super(tar);
    }


}
