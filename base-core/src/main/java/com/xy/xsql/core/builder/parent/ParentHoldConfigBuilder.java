package com.xy.xsql.core.builder.parent;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.core.configurator.BaseConfigurator;
import com.xy.xsql.core.holder.ParentHolder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.core.lambda.Setter;

/**
 * ParentHoldConfigBuilder
 * Build hold Parent
 * configured at any time
 * ( configured when the 'config' method is called. )
 * Created by xiaoyao9184 on 2016/12/28.
 * @param <This>
 * @param <Parent>
 * @param <Target>
 */
public abstract class ParentHoldConfigBuilder<This, Parent, Target>
        implements
        ParentHolder<This, Parent>,
        SimpleBuilder<Target>,
        BaseConfigurator<Setter<Target>> {

    private Parent parent;
    protected Target target;

    public ParentHoldConfigBuilder(){}

    public ParentHoldConfigBuilder(Target target){
        this.init(target);
    }

    /**
     * init
     * @param target Target
     */
    public void init(Target target){
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

    @Override
    public void config(Setter<Target> setter) {
        setter.set(target);
    }


    /**
     * alias
     * @see #init(Target)
     * @see #in(Parent)
     * @param parent Parent
     * @param target Target
     * @return This
     */
    @Deprecated
    public This enter(Parent parent, Target target) {
        this.init(target);
        return in(parent);
    }

    /**
     * alias
     * @see #init(Target)
     * @see #in(Parent)
     * @param parent Parent
     * @param getter Target Getter
     * @return This
     */
    public This enter(Parent parent, Getter<Target> getter) {
        this.init(getter.get());
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
