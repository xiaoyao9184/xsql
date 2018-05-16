package com.xy.xsql.core.configurator;

import com.xy.xsql.core.lambda.Setter;

/**
 * BooleanSetterConfigurator
 * Config Target's Boolean field using setter in implement Class
 * Created by xiaoyao9184 on 2017/3/19.
 * @param <Target>
 */
public abstract class BooleanSetterConfigurator<Target>
        implements BaseConfigurator<Target> {

    public abstract Setter<Boolean> getSetter(Target target);

    @Override
    public void config(Target target) {
        getSetter(target).set(true);
    }

}
