package com.xy.xsql.orm.core;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public abstract class CodeListBuilder<This,ChildBuilder,ParentBuilder,ListType>
            extends CodeTreeBuilder<This,ParentBuilder,List<ListType>> {

    public CodeListBuilder(List<ListType> tar) {
        super(tar);
    }

    public abstract ChildBuilder withItem();
}