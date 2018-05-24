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
 */
public abstract class CodeLazyConfigBuilder<Target>
        implements
        SimpleBuilder<Target>, BaseConfigurator<Setter<Target>> {

    protected Target target;
    protected Setter<Target> setter;

    public CodeLazyConfigBuilder(Target target){
        this.target = target;
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


}
