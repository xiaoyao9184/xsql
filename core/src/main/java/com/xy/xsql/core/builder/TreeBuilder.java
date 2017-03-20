package com.xy.xsql.core.builder;

import com.xy.xsql.core.holder.ParentHolder;

/**
 * TreeBuilder
 * aggregate multiple builders to form a tree,
 * and then through the chain method from the father builder into the sub-builders,
 * then finished jump out
 * Created by xiaoyao9184 on 2016/12/28.
 * @param <This> This builder Type
 * @param <Parent> Parent builder type
 * @param <Source> build source
 * @param <Target> build target
 */
public abstract class TreeBuilder<This, Parent, Source, Target>
        implements
        BaseBuilder<Source, Target>,
        ParentHolder<This, Parent> {

    private Parent parent;

    @SuppressWarnings("unchecked")
    @Override
    public This in(Parent parent) {
        this.parent = parent;
        return (This) this;
    }

    @Override
    public Parent out() {
        return this.parent;
    }

}
