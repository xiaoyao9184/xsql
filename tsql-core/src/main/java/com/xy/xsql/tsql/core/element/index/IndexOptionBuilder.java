package com.xy.xsql.tsql.core.element.index;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.element.index.IndexOption;
import com.xy.xsql.tsql.model.element.index.Partition;

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
