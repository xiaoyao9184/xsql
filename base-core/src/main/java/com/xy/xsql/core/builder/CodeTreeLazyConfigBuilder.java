package com.xy.xsql.core.builder;

import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.core.configurator.BaseConfigurator;

/**
 * CodeTreeLazyConfigBuilder
 * use code build Target, set Target if this builder done, compatible CodeTreeBuilder
 * Created by xiaoyao9184 on 2017/3/20.
 * @param <This>
 * @param <Parent>
 * @param <Target>
 * @param <ConfigTarget>
 */
public abstract class CodeTreeLazyConfigBuilder<This, Parent, Target extends ConfigTarget, ConfigTarget>
        extends TreeBuilder<This, Parent, Void, Target>
        implements BaseConfigurator<ConfigTarget> {

    protected Target target;
    protected Setter<ConfigTarget> setter;

    public CodeTreeLazyConfigBuilder(Target target){
        this.target = target;
    }

    public CodeTreeLazyConfigBuilder(Target target, Setter<ConfigTarget> setter){
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
    public void config(ConfigTarget target) {
        this.setter.set(target);
    }
}
