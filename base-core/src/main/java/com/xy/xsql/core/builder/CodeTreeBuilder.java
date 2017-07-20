package com.xy.xsql.core.builder;

import java.util.function.Consumer;

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
    protected Consumer<Target> backHandleFunction;

    public CodeTreeBuilder(Target target){
        this.target = target;
    }

    /**
     * @see #build(Object)
     * @param aVoid
     * @return
     */
    @Override
    @Deprecated
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

    /**
     * @see #in(Object)
     * @param parent
     * @return This
     */
    public This enter(Parent parent,Consumer<Target> function) {
        this.backHandleFunction = function;
        return in(parent);
    }

    /**
     * @see #out()
     * @return Parent
     */
    public Parent back() {
        if(this.backHandleFunction != null){
            this.backHandleFunction.accept(this.target);
        }
        return out();
    }
}
