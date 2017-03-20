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

    protected Target tar;

    public CodeTreeBuilder(Target tar){
        this.tar = tar;
    }

    /**
     * @see #build(Object)
     * @param aVoid
     * @return
     */
    @Override
    public Target build(Void aVoid) {
        return tar;
    }

    /**
     * @see #build(Void)
     * @return
     */
    public Target build() {
        return tar;
    }

    /**
     * @see #out()
     * @return
     */
    public Parent and() {
        return out();
    }
}
