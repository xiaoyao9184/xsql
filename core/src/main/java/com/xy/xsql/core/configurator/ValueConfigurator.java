package com.xy.xsql.core.configurator;

import com.xy.xsql.core.lambda.Setter;

/**
 * ValueConfigurator
 * Config Target' Value field in implement Class
 * Created by xiaoyao9184 on 2017/3/19.
 */
public abstract class ValueConfigurator<Target,Value>
        implements BaseConfigurator<Target> {

    private Value value;

    public ValueConfigurator(Value value){
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