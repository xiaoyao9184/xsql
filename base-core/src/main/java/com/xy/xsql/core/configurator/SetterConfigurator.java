package com.xy.xsql.core.configurator;

import com.xy.xsql.core.lambda.Setter;

/**
 * SetterConfigurator
 * Config Target's field using setter in implement Class
 * Created by xiaoyao9184 on 2017/3/19.
 * @param <Target>
 * @param <Value>
 */
public abstract class SetterConfigurator<Target,Value>
        implements BaseConfigurator<Target> {

    public abstract Value getValue();

    public abstract Setter<Value> getSetter(Target target);

    @Override
    public void config(Target target) {
        getSetter(target).set(getValue());
    }

}