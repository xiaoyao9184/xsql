package com.xy.xsql.core.builder;

import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.core.configurator.BaseConfigurator;

/**
 * CodeTreeLazySetBuilder
 * use code build Target, set Target if this builder done, compatible CodeTreeBuilder
 * Created by xiaoyao9184 on 2017/3/20.
 * @param <This>
 * @param <Parent>
 * @param <Target>
 * @param <SetterTarget>
 */
public abstract class CodeTreeLazySetBuilder<This, Parent, Target extends SetterTarget, SetterTarget>
        extends TreeBuilder<This, Parent, Void, Target>
        implements BaseConfigurator<SetterTarget> {

    protected Target target;
    protected Setter<SetterTarget> setter;

    public CodeTreeLazySetBuilder(Target target){
        this.target = target;
    }

    public CodeTreeLazySetBuilder(Target target, Setter<SetterTarget> setter){
        this.target = target;
        this.setter = setter;
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
        if(this.setter != null){
            this.setter.set(target);
        }
        return out();
    }

    @Override
    public void config(SetterTarget target) {
        this.setter.set(target);
    }
}
