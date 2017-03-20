package com.xy.xsql.core.builder;

import com.xy.xsql.core.holder.ParentHolder;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
@Deprecated
public abstract class SubBuilder<This, Source, Target>
        implements
        BaseBuilder<Source, Target>,
        ParentHolder<This, Target> {

    private Target done;

    @SuppressWarnings("unchecked")
    @Override
    public This in(Target done) {
        this.done = done;
        return (This) this;
    }

    @Override
    public Target out() {
        return this.done;
    }

    @Override
    public Target build(Source source) {
        return this.done;
    }

}
