package com.xy.xsql.core.builder.simple;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.core.configurator.BaseConfigurator;
import com.xy.xsql.core.lambda.Setter;

/**
 * CodeConfigBuilder
 * Use code build,
 * configured at any time
 * ( configured when the 'config' method is called. )
 * Created by xiaoyao9184 on 2016/12/28.
 * @param <Target>
 * @param <SuperTarget>
 */
public abstract class CodeConfigSuperBuilder<Target extends SuperTarget, SuperTarget>
        implements
        SimpleBuilder<Target>, BaseConfigurator<Setter<SuperTarget>> {

    protected Target target;

    public CodeConfigSuperBuilder(Target target){
        this.target = target;
    }

    @Override
    public Target build() {
        return target;
    }

    @Override
    public void config(Setter<SuperTarget> setter) {
        setter.set(target);
    }

}
