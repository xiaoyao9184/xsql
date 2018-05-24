package com.xy.xsql.core.builder.parent;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.core.holder.ParentHolder;
import com.xy.xsql.core.lambda.Setter;

/**
 * ParentHoldBuilder
 * Build hold Parent
 * Created by xiaoyao9184 on 2016/12/28.
 * @param <This>
 * @param <Parent>
 * @param <Target>
 */
public abstract class ParentHoldBuilder<This, Parent, Target>
        implements
        ParentHolder<This, Parent>,
        SimpleBuilder<Target> {

    private Parent parent;
    protected Target target;

    public ParentHoldBuilder(){}

    public ParentHoldBuilder(Target target){
        this.target = target;
    }

    @Override
    public This in(Parent parent) {
        this.parent = parent;
        //noinspection unchecked
        return (This) this;
    }

    @Override
    public Parent out() {
        return this.parent;
    }

    @Override
    public Target build() {
        return target;
    }


    /**
     * alias
     * @see #in(Object)
     * @see ParentHoldBuilder
     * @param parent Parent
     * @param target Target
     * @return This
     */
    public This enter(Parent parent, Target target) {
        this.target = target;
        return in(parent);
    }

    /**
     * alias
     * @see #out()
     * @return Parent
     */
    @Deprecated
    public Parent back() {
        return out();
    }

    /**
     * alias
     * @see #out()
     * @return Parent
     */
    public Parent and() {
        return out();
    }

}
