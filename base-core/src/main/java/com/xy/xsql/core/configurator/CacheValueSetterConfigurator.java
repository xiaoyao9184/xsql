package com.xy.xsql.core.configurator;

import com.xy.xsql.core.lambda.Setter;

/**
 * CacheValueSetterConfigurator
 * Config Target's Value field using setter in implement Class
 * Created by xiaoyao9184 on 2017/3/19.
 * @param <Target>
 * @param <Value>
 */
public abstract class CacheValueSetterConfigurator<Target,Value>
        implements BaseConfigurator<Target> {

    private Value value;

    public CacheValueSetterConfigurator(Value value){
        this.value = value;
    }

    public Value getValue(){
        return value;
    }

    public abstract Setter<Value> getSetter(Target target);

    @Override
    public void config(Target target) {
        getSetter(target).set(value);
    }

}