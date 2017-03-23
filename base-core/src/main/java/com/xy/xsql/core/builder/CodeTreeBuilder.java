package com.xy.xsql.core.builder;

/**
 * CodeTreeBuilder
 * use code build Target, compatible TreeBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 * @param <This>
 * @param <Parent>
 * @param <Target>
 */
public abstract class CodeTreeBuilder<This, Parent, Target>
        extends TreeBuilder<This, Parent, Void, Target> {

    protected Target target;

    public CodeTreeBuilder(Target target){
        this.target = target;
    }

    /**
     * @see #build(Object)
     * @param aVoid
     * @return
     */
    @Override
    public Target build(Void aVoid) {
        return target;
    }

    /**
     * @see #build(Void)
     * @return
     */
    public Target build() {
        return target;
    }

    /**
     * @see #out()
     * @return
     */
    public Parent and() {
        return out();
    }
}
