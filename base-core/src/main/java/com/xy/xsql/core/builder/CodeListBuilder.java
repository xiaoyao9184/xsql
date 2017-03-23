package com.xy.xsql.core.builder;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 * @param <This>
 * @param <Child>
 * @param <Parent>
 * @param <TargetList>
 */
@Deprecated
public abstract class CodeListBuilder<This, Child, Parent, TargetList>
        extends CodeTreeBuilder<This, Parent, List<TargetList>> {

    public CodeListBuilder(List<TargetList> tar) {
        super(tar);
    }

    public abstract Child withItem();
}