package com.xy.xsql.core;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public abstract class TreeBuilder<This,Parent,SrcType,TarType>
        implements
        BaseBuilder<SrcType,TarType>,
        ConfigInOut<This,Parent> {

    private Parent parent;

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
