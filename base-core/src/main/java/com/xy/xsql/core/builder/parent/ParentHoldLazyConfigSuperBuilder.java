package com.xy.xsql.core.builder.parent;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.core.configurator.BaseConfigurator;
import com.xy.xsql.core.holder.ParentHolder;
import com.xy.xsql.core.lambda.Setter;

/**
 * ParentHoldLazyConfigBuilder
 * Build hold Parent
 * configured at any time
 * ( configured when the 'config' method is called. )
 * Created by xiaoyao9184 on 2016/12/28.
 * @param <This>
 * @param <Parent>
 * @param <Target>
 * @param <SuperTarget>
 */
public abstract class ParentHoldLazyConfigSuperBuilder<This, Parent, Target extends SuperTarget, SuperTarget>
        implements
        ParentHolder<This, Parent>,
        SimpleBuilder<Target>,
        BaseConfigurator<Setter<SuperTarget>> {

    private Parent parent;
    protected Target target;
    protected Setter<SuperTarget> setter;

    public ParentHoldLazyConfigSuperBuilder(){}

    public ParentHoldLazyConfigSuperBuilder(Target target){
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
    public void config(Setter<SuperTarget> setter) {
        this.setter = setter;
    }

    @Override
    public Target build() {
        if(this.setter != null){
            this.setter.set(target);
        }
        return target;
    }


    /**
     * alias
     * @see #in(Object)
     * @see ParentHoldLazyConfigSuperBuilder
     * @see #config(Setter)
     * @param parent Parent
     * @param target Target
     * @param setter SuperTarget Setter
     * @return This
     */
    public This enter(Parent parent, Target target, Setter<SuperTarget> setter) {
        this.target = target;
        this.config(setter);
        return in(parent);
    }

    /**
     * alias
     * @see #out()
     * @see #build()
     * @return Parent
     */
    @Deprecated
    public Parent back() {
        this.build();
        return out();
    }

    /**
     * alias
     * @see #out()
     * @see #build()
     * @return Parent
     */
    public Parent and() {
        this.build();
        return out();
    }

}
