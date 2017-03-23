package com.xy.xsql.core.configurator;

import com.xy.xsql.core.lambda.Setter;

/**
 * BooleanValueConfigurator
 * Config Target' Boolean field in implement Class
 * Created by xiaoyao9184 on 2017/3/19.
 * @param <Target>
 */
public abstract class BooleanValueConfigurator<Target>
        implements BaseConfigurator<Target> {

    public abstract Setter<Boolean> getSetter(Target target);

    @Override
    public void config(Target target) {
        getSetter(target).set(true);
    }

}
