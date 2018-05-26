package com.xy.xsql.core.builder.parent;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.core.configurator.BaseConfigurator;
import com.xy.xsql.core.holder.ParentHolder;
import com.xy.xsql.core.lambda.Getter;
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
 */
public abstract class ParentHoldLazyConfigBuilder<This, Parent, Target>
        implements
        ParentHolder<This, Parent>,
        SimpleBuilder<Target>,
        BaseBuilder<Target,Target>,
        BaseConfigurator<Setter<Target>> {

    private Parent parent;
    protected Target target;
    protected Setter<Target> setter;

    public ParentHoldLazyConfigBuilder(){}

    public ParentHoldLazyConfigBuilder(Target target){
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
    public void config(Setter<Target> setter) {
        this.setter = setter;
    }

    @Override
    public Target build() {
        if(this.setter != null){
            this.setter.set(target);
        }
        return target;
    }

    @Override
    public Target build(Target target) {
        this.init(target);
        this.build();
        return target;
    }


    /**
     * alias
     * @see #init(Target)
     * @see #config(Setter)
     * @see #in(Parent)
     * @param parent Parent
     * @param target Target
     * @param setter Target Setter
     * @return This
     */
    @Deprecated
    public This enter(Parent parent, Target target, Setter<Target> setter) {
        this.init(target);
        this.config(setter);
        return in(parent);
    }

    /**
     * alias
     * @see #init(Target)
     * @see #config(Setter)
     * @see #in(Parent)
     * @param parent Parent
     * @param getter Target Getter
     * @param setter Target Setter
     * @return This
     */
    public This enter(Parent parent, Getter<Target> getter, Setter<Target> setter) {
        this.init(getter.get());
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
