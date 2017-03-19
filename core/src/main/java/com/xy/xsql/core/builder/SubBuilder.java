package com.xy.xsql.core.builder;

import com.xy.xsql.core.configurator.ConfigInOut;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public abstract class SubBuilder<This,SrcType,Done>
        implements
        BaseBuilder<SrcType,Done>,
        ConfigInOut<This,Done> {

    private Done done;

    @Override
    public This in(Done done) {
        this.done = done;
        return (This) this;
    }

    @Override
    public Done out() {
        return this.done;
    }

    @Override
    public Done build(SrcType srcType) {
        return this.done;
    }

}
