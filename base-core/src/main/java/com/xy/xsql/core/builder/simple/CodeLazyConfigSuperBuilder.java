package com.xy.xsql.core.builder.simple;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.core.configurator.BaseConfigurator;
import com.xy.xsql.core.lambda.Setter;

/**
 * CodeLazyConfigBuilder
 * Use code build,
 * configured when the build is complete
 * ( not configured when the 'config' method is called, when 'build' method called. )
 * Created by xiaoyao9184 on 2016/12/28.
 * @param <Target>
 * @param <SuperTarget>
 */
public abstract class CodeLazyConfigSuperBuilder<Target extends SuperTarget, SuperTarget>
        implements
        SimpleBuilder<Target>, BaseConfigurator<Setter<SuperTarget>> {

    protected Target target;
    protected Setter<SuperTarget> setter;

    public CodeLazyConfigSuperBuilder(Target target){
        this.target = target;
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


}
